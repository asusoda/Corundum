package Corundum.launcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Loads the jars necessary for Corundum to work correctly. Previously, the loading code was inside CorundumLauncher,
 * but this class should more modularise it and make updating the loading code much easier to do. This is at
 * time of writing not used by default. Note that it uses a lot of REALDrummer's original loading code.
 *
 * @author Niadel
 */
public class CorundumLoader extends URLClassLoader {
    public ClassLoader parent;

    public CorundumLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
        this.parent = parent;
    }

    public void loadJars() {
        System.out.println("Loading the Minecraft server jar...");
        @SuppressWarnings("resource")
        URLClassLoader minecraftLoader = this.loadJar("minecraft_server.jar", true);
        if (minecraftLoader == null)
            return;
        System.out.println("Success!");

        // load the Corundum jar
        System.out.println("Loading the Corundum server jar...");
        @SuppressWarnings("resource")
        URLClassLoader corundumLoader = this.loadJar("Corundum.jar");
        if (corundumLoader == null) {
            try {
                minecraftLoader.close();
            } catch (IOException exception) {
                System.out.println("I was unable to close the Minecraft jar ClassLoader after Corundum's loading failed!");
                exception.printStackTrace();
            }
            return;
        }
        System.out.println("Success!");

        try {
            this.close();
        } catch (IOException exception) {
            System.err.println("Unexpected error closing the Corundum Loader!");
            exception.printStackTrace();
        }
    }

    public URLClassLoader loadJar(String filePath) {
        return this.loadJar(filePath, false);
    }

    public URLClassLoader loadJar(String filePath, boolean isMinecraftServerJar) {
        JarFile jar;
        try {
            jar = new JarFile(filePath);
        } catch (FileNotFoundException exception) {
            System.out.println("I couldn't find " + filePath + "!");
            exception.printStackTrace();
            return null;
        } catch (IOException exception) {
            System.out.println("There was a problem loading " + filePath + "!");
            exception.printStackTrace();
            return null;
        }

        URLClassLoader loader;
        try {
            loader = this.addUrl(new URL("jar:file:" + filePath + "!/"));
        } catch (MalformedURLException exception) {
            try {
                jar.close();
            } catch (IOException exception2) {
                System.out.println("I was unable to close " + filePath + " when this " + exception.getClass().getSimpleName() + " popped up!");
                exception2.printStackTrace();
            }
            System.out.println("The URL for this jar file was malformed!");
            System.out.println("file path=" + filePath);
            exception.printStackTrace();
            return null;
        }

        Enumeration<JarEntry> e = jar.entries();
        for (JarEntry entry = e.nextElement(); e.hasMoreElements(); entry = e.nextElement())
            if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                String class_name = entry.getName().substring(0, entry.getName().length() - 6).replaceAll("/", "");
                try {
                    loader.loadClass(class_name);
                } catch (NoClassDefFoundError | ClassNotFoundException exception) {
                    System.out.println("failed to load " + class_name);

                    /* if the Minecraft server jar threw the error, just ignore it; there are classes in the Minecraft server jar that reference other non-existent classes, so
                     * we'll just have to deal with it and ignore it since we can't change the Minecraft server jar */
                    if (isMinecraftServerJar)
                        continue;

                    try {
                        jar.close();
                    } catch (IOException exception2) {
                        System.out.println("I was unable to close " + filePath + "'s jar when " + class_name + " failed to load!");
                        exception2.printStackTrace();
                    }
                    exception.printStackTrace();
                    return null;
                }
            }

        // close the jar (which will not affect access to the loaded classes)
        try {
            jar.close();
        } catch (IOException exception2) {
            System.out.println("I couldn't close the jar while failing to close the loader after loading " + filePath + "!");
            exception2.printStackTrace();
        }

        System.out.println("loaded " + filePath);
        return this;
    }

    public URLClassLoader addUrl(URL url) {
        this.addURL(url);
        return this;
    }
}
