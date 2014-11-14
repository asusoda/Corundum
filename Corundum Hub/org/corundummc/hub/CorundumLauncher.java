package org.corundummc.hub;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.corundummc.exceptions.CorundumSecurityException;

public class CorundumLauncher {
    public static final String VERSION = "pre-α", MC_VERSION = "1.7.10";

    private static final Thread MAIN_THREAD = Thread.currentThread();

    /** This is the main method of Corundum. When Corundum is started, this is the method that is called to start the program.
     * 
     * @param arguments
     *            is the list of <tt>String</tt> arguments given in the command to start this program. These arguments are given through a command line like the Windows
     *            command prompt or Unix terminal.<br>
     * <br>
     *            Accepted arguments:<br>
     *            <i>debugging</i>:<br>
     *            <li><tt>--debug, -d</tt> to activate debugging mode, which outputs debugging information to the console and the logs</li> <li><tt>--no-debug, -D</tt> to
     *            deactivate debugging mode</li> <li><tt>--verbose, -v</tt> to activate verbose debugging mode, which outputs lots of extra detailed debugging information to
     *            the console and the logs</li> <li><tt>--no-verbose, -V</tt> to deactivate verbose debugging mode</li> <i>server properties <b>(not yet supported)</b></i>: <br>
     *            <li>
     *            <tt>--online-mode, -o</tt> to activate online mode, which makes the server connect to Mojang's authentication servers and verify users when they try to
     *            connect to the server</li> <li><tt>--offline-mode, -O</tt> to deactivate online mode</li> <li><tt>--world=[WORLD]</tt> to specify the name of your main world
     *            (usually the Overworld); leave it blank to specify the default world name, "world"</li> */
    public static void main(final String[] arguments) {
        downloadMCServer(new File("."));

        // load the Minecraft server jar
        System.out.println("Loading the Minecraft server jar...");
        @SuppressWarnings("resource")
        CorundumJarLoader Minecraft_loader = loadJar(new File("minecraft_server.jar"), CorundumLauncher.class.getClassLoader(), true);
        if (Minecraft_loader == null)
            return;

        // start Corundum
        CorundumServerThread server_thread = new CorundumServerThread("Corundum.jar", Minecraft_loader);
        server_thread.start();
    }

    public static Thread mainThread() {
        if (!Thread.currentThread().equals(MAIN_THREAD) && !(Thread.currentThread() instanceof CorundumServerThread))
            throw new CorundumSecurityException("an unknown Thread", "access the Corundum main thread");

        return MAIN_THREAD;
    }

    public static void downloadMCServer(File outDir) {
        try {
            File outJar = new File(outDir, "minecraft_server.jar");

            // Clear the file if it already exists. Allows for easily updating the server jar over MC Versions.
            if (outJar.exists()) {
                outJar.delete();
            }

            if (!outJar.exists() && outDir.isDirectory()) {
                outJar.createNewFile();
                URL mcServerDownload =
                        new URL("https://s3.amazonaws.com/Minecraft.Download/versions/${mcVer}/minecraft_server.${mcVer}.jar".replace("${mcVer}", MC_VERSION) /* Makes updating
                                                                                                                                                               * the version of
                                                                                                                                                               * the Minecraft
                                                                                                                                                               * server
                                                                                                                                                               * downloaded
                                                                                                                                                               * easier */);
                ReadableByteChannel byteChannel = Channels.newChannel(mcServerDownload.openStream());
                FileOutputStream outputStream = new FileOutputStream(outJar);
                outputStream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /** This method loads all the classes in a given jar file.
     * 
     * @param file
     *            is the jar file to be loaded. A {@link File} is given as an argument instead of a simple <tt>String</tt> file path to ensure that the file path is relative
     *            to its caller class rather than the location of the {@link CorundumLauncher} jar.
     * @param parent
     *            is the {@link ClassLoader} that will be used as a parent to the {@link ClassLoader} that will be used to load the jar and be returned at the end of this
     *            method. It is important that you choose your parent {@link ClassLoader}s wisely; a child {@link ClassLoader} can use all the classes loaded by its parent
     *            {@link ClassLoader}, even if both {@link ClassLoader}s are loading from different files or {@link URL}s.
     * @return the {@link URLClassLoader} used to load the classes inside the specified jar file or <b>null</b> if there was an error. */
    public static CorundumJarLoader loadJar(File file, ClassLoader parent) {
        return loadJar(file, parent, false);
    }

    private static CorundumJarLoader loadJar(File file, ClassLoader parent, boolean is_Minecraft_server_jar) {
        CorundumJarLoader loader;
        try {
            loader = new CorundumJarLoader(file, parent, is_Minecraft_server_jar);
        } catch (MalformedURLException exception) {
            System.out.println("The URL for this jar file was malformed!");
            System.out.println("file path=" + file.getAbsolutePath());
            exception.printStackTrace();
            return null;
        }

        try {
            loader.loadJar();
        } catch (NoClassDefFoundError | ClassNotFoundException exception) {
            System.out.println("There seem to be some missing classes!");
            exception.printStackTrace();
            return null;
        } catch (IOException exception) {
            System.out.println("The jar file to be loaded seems to be missing!");
            exception.printStackTrace();
            return null;
        } catch (URISyntaxException exception) {
            System.out.println("Something went wrong in converting the URL back to a file path!");
            exception.printStackTrace();
            return null;
        }

        return loader;
    }
}
