package org.corundummc.hub;

import java.io.ByteArrayInputStream;
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

    /** <b><i>DEV NOTES</b></i>:<br>
     * This parent exists to allow the {@link ClassLoader}s to search parents and children for a requested class. Though {@link ClassLoader}s already have parents, Log4J does
     * weird things with {@link ClassLoader}s that break the code, so we have to use this separate parent variable to allow {@link CorundumJarLoader}s to search parents for
     * requested classes while making sure that Log4J can't get a {@link CorundumJarLoader}'s parent and try to load things from the main {@link ClassLoader}, which does not
     * have the resources it searches for and which we have no control over. */
    private CorundumJarLoader parent = null;
    private CorundumJarLoader child = null;

    public CorundumJarLoader(File file, CorundumJarLoader parent) throws MalformedURLException {
        this(file, parent, false);
    }

    CorundumJarLoader(File file, CorundumJarLoader parent, boolean is_Minecraft_server_jar) throws MalformedURLException {
        super(new URL[] { file.toURI().toURL() }, null);

        this.is_Minecraft_server_jar = is_Minecraft_server_jar;
        this.parent = parent;

        if (parent != null)
            parent.child = this;

        if (head_loader == null)
            head_loader = this;
    }

    public static CorundumJarLoader getHeadLoader() {
        return head_loader;
    }

    @Override
    public URL getResource(String path) {
        // eliminate the "/" at the beginning if there is one to avoid conflicts with the "!/" that we will prepend
        if (path.startsWith("/"))
            path.substring(1);

        // first, try loading it from here
        URL result =
                super.getResource(/* prepend the U.R.L. of the jar file associated with this loader to force the classloader to search inside this jar */"jar:"
                        + getURLs()[0].toString() + "!/" + path);

        if (result != null || parent == null)
            return result;
        // if that fails, try to load the resource via the parents (calling multiple parents via recursion)
        else
            return parent.getResource(path);
    }

    @Override
    public InputStream getResourceAsStream(String path) {
        // eliminate the "/" at the beginning if there is one to avoid conflicts with the "!/" that we will prepend
        if (path.startsWith("/"))
            path.substring(1);

        // first, try loading it from here
        InputStream result =
                super.getResourceAsStream(/* prepend the U.R.L. of the jar file associated with this loader to force the classloader to search inside this jar */"jar:"
                        + getURLs()[0].toString() + "!/" + path);

        if (result != null || parent == null)
            return result;
        // if that fails, try to load the resource via the parents (calling multiple parents via recursion)
        else
            return parent.getResourceAsStream(path);
    }

    @SuppressWarnings("resource")
    @Override
    protected Class<?> findClass(String name) {
        // find the file for the class in the appropriate jar
        InputStream class_stream = getResourceAsStream(name.replaceAll("\\.", File.pathSeparator) + ".class");
        if (class_stream == null)
            throw new CIE("I couldn't find this " + name + " class in " + getURLs()[0].toString() + "!", "missing class file", "resource path = \""
                    + name.replaceAll("\\.", File.pathSeparator) + ".class\"");

        try {
            // load the class file into a byte[]
            byte[] bytes = new byte[class_stream.available()];
            DataInputStream stream = new DataInputStream(class_stream);
            stream.readFully(bytes);
            stream.close();
            class_stream.close();

            // finally, define the class
            return defineClass(name, bytes, 0, bytes.length);
        } catch (FileNotFoundException exception) {
            throw new CIE("I couldn't find the file for the class " + name + "!", exception, "path searched = \"" + class_stream.toString() + "\"");
        } catch (IOException exception) {
            throw new CIE("Something went wrong while loading this " + name + " class from file!", exception);
        }
    }

    @Override
    protected Class<?> loadClass(String class_name, boolean resolve) throws ClassNotFoundException {
        // first, try loading it from here
        try {
            if (class_name.startsWith("sun") || class_name.startsWith("java"))
                return super.loadClass(class_name, resolve);
            else {
                Class<?> clazz = findLoadedClass(class_name);
                if (clazz == null) {
                    clazz = findClass(class_name);
                    if (resolve)
                        resolveClass(clazz);
                }
                return clazz;
            }
        } catch (ClassNotFoundException exception) {
            // if that fails, try to load the class via the parents (calling multiple parents via recursion)
            if (parent != null)
                return parent.loadClass(class_name, resolve);
            else
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

    @SuppressWarnings("unused")
    private void oldResourceGetting() {
        /* public Class<?> loadClass(String class_name) throws ClassNotFoundException { // TODO TEMP System.out.println("loading " + class_name + " for " +
         * getURLs()[0].toString());
         * 
         * return loadClassHelper(class_name, false); }
         * 
         * private Class<?> loadClassHelper(String class_name, boolean recurse_up) throws ClassNotFoundException { // TODO TEMP System.out.println("searching for " +
         * class_name + " in " + getURLs()[0]); if (child != null && !recurse_up) { // TODO TEMP // System.out.println("recursing down with child..."); return
         * child.loadClassHelper(class_name, false); } else { try { // TODO TEMP // System.out.println("attempting load with CorundumJarLoader during upward recursion...");
         * return super.loadClass(class_name); } catch (ClassNotFoundException exception) { // TODO TEMP //
         * System.out.println("failed to load with this loader; attempting to load with parents..."); if (parent != null) if (parent instanceof CorundumJarLoader) { // TODO
         * TEMP // System.out.println("recursing up to CorundumJarLoader..."); return ((CorundumJarLoader) parent).loadClassHelper(class_name, true); } else { // TODO TEMP //
         * System.out.println("attempting load with non-CorundumJarLoader parent..."); return parent.loadClass(class_name); } else throw exception; } } }
         * 
         * 
         * @Override public URL getResource(String path) { // TODO TEMP System.out.println("searching for " + path + " for " + getURLs()[0].toString());
         * 
         * // eliminate the "/" at the beginning if there is one to avoid conflicts with the "!/" that we will prepend if (path.startsWith("/")) path.substring(1);
         * 
         * // TODO EXT TEMP RPLC // return getResourceHelper(path, false); URL resource = getResourceHelper(path, false); if (resource == null)
         * System.out.println("FAILED to find " + path + " for " + getURLs()[0].toString()); else System.out.println("found " + path + " for " + getURLs()[0].toString() +
         * " at " + resource.toString()); return resource; // TODO END TEMP RPLC }
         * 
         * private URL getResourceHelper(String path, boolean recurse_up) { // TODO TEMP System.out.println("searching for " + path + " in " + getURLs()[0]); if (child != null
         * && !recurse_up) { // TODO TEMP System.out.println("recursing down with child..."); return child.getResourceHelper(path, false); } else { // TODO TEMP
         * System.out.println("attempting retrieval with CorundumJarLoader during upward recursion..."); URL result = super.getResource(path); if (result != null || parent ==
         * null) return result; else { // TODO TEMP System.out.println("failed to find resource with this loader; attempting to find with parents..."); if (parent instanceof
         * CorundumJarLoader) { // TODO TEMP System.out.println("recursing up to CorundumJarLoader..."); return ((CorundumJarLoader) parent).getResourceHelper(path, true); }
         * else { // TODO TEMP System.out.println("attempting resource retrieval with non-CorundumJarLoader parent..."); return parent.getResource(path); } } } }
         * 
         * @Override public InputStream getResourceAsStream(String path) { // TODO TEMP System.out.println("searching for " + path + " for " + getURLs()[0].toString());
         * 
         * // eliminate the "/" at the beginning if there is one to avoid conflicts with the "!/" that we will prepend if (path.startsWith("/")) path.substring(1);
         * 
         * // TODO EXT TEMP RPLC // return getResourceAsStreamHelper(path, false); InputStream resource_stream = getResourceAsStreamHelper(path, false); if (resource_stream ==
         * null) System.out.println("FAILED to find " + path + " for " + getURLs()[0].toString()); else System.out.println("found " + path + " for " + getURLs()[0].toString()
         * + " at " + resource_stream.toString()); return resource_stream; // TODO END TEMP RPLC }
         * 
         * private InputStream getResourceAsStreamHelper(String path, boolean recurse_up) { // TODO TEMP System.out.println("searching for " + path + " in " + getURLs()[0]);
         * if (child != null && !recurse_up) { // TODO TEMP // System.out.println("recursing down with child..."); return child.getResourceAsStreamHelper(path, false); } else
         * { // TODO TEMP // System.out.println("attempting retrieval with CorundumJarLoader during upward recursion..."); InputStream result =
         * super.getResourceAsStream(path); if (result != null || parent == null) return result; else { // TODO TEMP //
         * System.out.println("failed to find resource with this loader; attempting to find with parents..."); if (parent instanceof CorundumJarLoader) { // TODO TEMP //
         * System.out.println("recursing up to CorundumJarLoader..."); return ((CorundumJarLoader) parent).getResourceAsStreamHelper(path, true); } else { // TODO TEMP //
         * System.out.println("attempting resource retrieval with non-CorundumJarLoader parent..."); return parent.getResourceAsStream(path); } } } } */
    }

    public interface ClassLoadAction {
        public void onClassLoad(Class<?> clazz);
    }
}
