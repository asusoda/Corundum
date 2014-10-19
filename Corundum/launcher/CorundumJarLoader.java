package Corundum.launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CorundumJarLoader extends URLClassLoader {
    private final boolean is_Minecraft_server_jar;

    public CorundumJarLoader(File file, ClassLoader parent) throws MalformedURLException {
        this(file, parent, false);
    }

    CorundumJarLoader(File file, ClassLoader parent, boolean is_Minecraft_server_jar) throws MalformedURLException {
        super(new URL[] { file.toURI().toURL() }, parent);

        this.is_Minecraft_server_jar = is_Minecraft_server_jar;
    }

    @Override
    public Class<?> loadClass(String class_name) throws ClassNotFoundException {
        try {
            return CorundumLauncher.class.getClassLoader().loadClass(class_name);
        } catch (ClassNotFoundException exception) {
            return super.loadClass(class_name);
        }
    }

    public void loadJar() throws IOException, NoClassDefFoundError, ClassNotFoundException, URISyntaxException {
        loadJar(null);
    }

    public void loadJar(ClassLoadAction action) throws IOException, NoClassDefFoundError, ClassNotFoundException, URISyntaxException {
        String file_path = new File(getURLs()[0].toURI()).getAbsolutePath();

        ZipInputStream zip = new ZipInputStream(new FileInputStream(file_path));
        for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
            if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                int first_letter_index = entry.getName().lastIndexOf("!/") + 2;
                if (first_letter_index == 1)
                    first_letter_index = 0;
                String class_name = entry.getName().substring(first_letter_index, entry.getName().length() - 6).replaceAll("/", ".");
                try {
                    Class<?> clazz = loadClass(class_name);
                    if (action != null)
                        action.onClassLoad(clazz);
                } catch (NoClassDefFoundError | ClassNotFoundException exception) {
                    /* if the Minecraft server jar threw the error, just ignore it; there are classes in the Minecraft server jar that reference other non-existent classes, so
                     * we'll just have to deal with it and ignore it since we can't change the Minecraft server jar */
                    if (!is_Minecraft_server_jar) {
                        try {
                            close();
                        } catch (IOException exception2) {
                            System.out.println("I was unable to close " + class_name + "'s loader when this " + exception.getClass().getSimpleName() + " popped up!");
                            exception2.printStackTrace();
                        }
                        try {
                            zip.close();
                        } catch (IOException exception2) {
                            System.out.println("I was unable to close " + file_path + "'s jar when " + class_name + " failed to load!");
                            exception2.printStackTrace();
                        }

                        throw exception;
                    }
                }
            }
        }

        // close the ClassLoader (which will not affect access to the loaded classes)
        close();

        // close the JarFile (which will also not affect access to the loaded classes)
        zip.close();

        System.out.println("loaded " + file_path);
    }

    @Override
    public URL getResource(String path) {
        // eliminate the "/" at the beginning if there is one to avoid conflicts with the "!/" at the end of the URL
        if (path.startsWith("/"))
            path.substring(1);

        String URL_string = "jar:" + getURLs()[0] + "!/" + path;
        try {
            return new URL(URL_string);
        } catch (MalformedURLException exception) {
            System.out.println("There was something wrong with the URL representing this resource!");
            System.out.println("URL=\"" + URL_string + "\"");
            exception.printStackTrace();
            return null;
        }
    }

    public interface ClassLoadAction {
        public void onClassLoad(Class<?> clazz);
    }
}
