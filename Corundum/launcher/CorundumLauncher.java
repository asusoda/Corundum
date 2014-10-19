package Corundum.launcher;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.util.Scanner;

public class CorundumLauncher {
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
        // TODO: download the minecraft_server.jar from minecraft.net
        downloadMCServer(new File("."));
        // load the Minecraft server jar
        System.out.println("Loading the Minecraft server jar...");
        @SuppressWarnings("resource")
        URLClassLoader Minecraft_loader = loadJar(new File("minecraft_server.jar"), CorundumLauncher.class.getClassLoader(), true);
        if (Minecraft_loader == null)
            return;

        // load the Corundum jar
        System.out.println("Loading the Corundum server jar...");
        @SuppressWarnings("resource")
        final URLClassLoader Corundum_loader = loadJar(new File("Corundum.jar"), Minecraft_loader);
        if (Corundum_loader == null) {
            try {
                Minecraft_loader.close();
            } catch (IOException exception) {
                System.out.println("I was unable to close the Minecraft jar ClassLoader after Corundum's loading failed!");
                exception.printStackTrace();
            }
            return;
        }

        // start Corundum
        try {
            Thread.currentThread().setContextClassLoader(Minecraft_loader);
            System.out.println("Running Corundum server...");
            Class<?> main_class = Class.forName("Corundum.Corundum", true, Corundum_loader);
            main_class.getMethod("start", String[].class).invoke(null, (Object) arguments);
        } catch (ClassNotFoundException | IllegalArgumentException | SecurityException | IllegalAccessException | InvocationTargetException | NoSuchMethodException exception) {
            System.out.println("I couldn't load the Corundum main class!");
            exception.printStackTrace();
        } catch (Exception | Error exception) {
            System.out.println("Something went wrong in initializing the Corundum server!");
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
    public static URLClassLoader loadJar(File file, ClassLoader parent) {
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

    public static void downloadMCServer(File outDir) {
        try {
            File outJar = new File(outDir, "minecraft_server.jar");

            if (!outJar.exists() && outDir.isDirectory()) {
                URL
                mcServerDownload = new URL("https://s3.amazonaws.com/Minecraft.Download/versions/1.7.10/minecraft_server.1.7.10.jar");
                HttpURLConnection downloadUrlConnection = (HttpURLConnection) mcServerDownload.openConnection();
                //A Scanner is an easier way to read a stream than the while ((var = stream.read()) != -1) method,
                //in my opinion. It's also neater.
                Scanner streamScanner = new Scanner(downloadUrlConnection.getInputStream());
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outJar));

                while (streamScanner.hasNextByte()) {
                    outputStream.write(streamScanner.nextByte());
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
