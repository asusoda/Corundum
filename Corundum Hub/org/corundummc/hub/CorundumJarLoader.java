package org.corundummc.hub;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CorundumJarLoader extends URLClassLoader {
    private static CorundumJarLoader head_loader = null;

    private final boolean is_Minecraft_server_jar;

    public CorundumJarLoader(File file, ClassLoader parent) throws MalformedURLException {
        this(file, parent, false);
    }

    CorundumJarLoader(File file, ClassLoader parent, boolean is_Minecraft_server_jar) throws MalformedURLException {
        super(new URL[] { file.toURI().toURL() }, parent);

        this.is_Minecraft_server_jar = is_Minecraft_server_jar;

        if (head_loader == null)
            head_loader = this;
    }

    public static CorundumJarLoader getHeadLoader() {
        return head_loader;
    }

    @SuppressWarnings("resource")
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        CorundumHub.debug("finding " + name + " for " + getURLs()[0] + "...");

        // first, try finding the class if it's loaded already
        Class<?> clazz = findLoadedClass(name);
        if (clazz != null)
            return clazz;

        // TODO TEMP RPLC
        // // find the file for the class in the appropriate jar
        // String resource_path = name.replaceAll("\\.", File.separator) + ".class";
        // InputStream class_stream = getResourceAsStream(resource_path);
        // if (class_stream == null && getParent() != null)
        // class_stream = getParent().getResourceAsStream(resource_path);
        // if (class_stream == null)
        // return null;
        //
        // try {
        // // load the class file into a byte[]
        // byte[] bytes = new byte[class_stream.available()];
        // DataInputStream stream = new DataInputStream(class_stream);
        // stream.readFully(bytes);
        // stream.close();
        // class_stream.close();
        //
        // // finally, define the class
        // return defineClass(name, bytes, 0, bytes.length);
        // } catch (FileNotFoundException exception) {
        // throw new CIE("I couldn't find the file for the class " + name + "!", exception, "path searched = \"" + class_stream.toString() + "\"");
        // } catch (IOException exception) {
        // throw new CIE("Something went wrong while loading this " + name + " class from file!", exception);
        // }
        else
            return loadClass(name);
    }

    @Override
    protected Class<?> loadClass(String class_name, boolean resolve) throws ClassNotFoundException {
        CorundumHub.debug("loading " + class_name + " for " + getURLs()[0] + "...");

        // first, try loading it from here
        try {
            if (class_name.startsWith("sun") || class_name.startsWith("java."))
                return super.loadClass(class_name, resolve);
            else {
                Class<?> clazz = findClass(class_name);
                if (clazz == null)
                    throw new ClassNotFoundException(class_name);
                else if (resolve)
                    resolveClass(clazz);
                return clazz;
            }
        } catch (ClassNotFoundException exception) {
            // if that fails, try to load the class via the parents (calling multiple parents via recursion)
            if (getParent() != null) {
                Class<?> clazz = getParent().loadClass(class_name);
                resolveClass(clazz);
                return clazz;
            } else
                throw exception;
        }
    }

    public void loadJar() throws IOException, NoClassDefFoundError, ClassNotFoundException, URISyntaxException {
        loadJar(null);
    }

    @SuppressWarnings("resource")
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

    public interface ClassLoadAction {
        public void onClassLoad(Class<?> clazz);
    }
}
