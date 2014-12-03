package org.corundummc.hub;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.corundummc.exceptions.CIE;

public class CorundumThread extends Thread {
    private final File jar_file;
    private final CorundumJarLoader Minecraft_loader, Corundum_loader;
    private final String[] arguments;

    private Server server = null;

    public CorundumThread(String file_path, CorundumJarLoader Minecraft_loader, String... arguments) {
        this.Minecraft_loader = Minecraft_loader;
        this.arguments = arguments;

        jar_file = new File(file_path);

        // load the Corundum jar
        System.out.println("Loading the Corundum server jar...");
        Corundum_loader = CorundumHub.loadJar(jar_file, Minecraft_loader);
        if (Corundum_loader == null) {
            try {
                Minecraft_loader.close();
            } catch (IOException exception) {
                System.out.println("I was unable to close the Minecraft jar ClassLoader after Corundum's loading failed!");
                exception.printStackTrace();
            }
            return;
        }

        setContextClassLoader(Minecraft_loader);
    }

    protected CorundumThread(String name) {
        super(name);

        // if this thread was instantiated from another CorundumServerThread, retrieve all the important info from that thread
        if (Thread.currentThread() instanceof CorundumThread) {
            jar_file = currentThread().jar_file;
            Minecraft_loader = currentThread().Minecraft_loader;
            Corundum_loader = currentThread().Corundum_loader;
            arguments = currentThread().arguments;
        } // otherwise, err out
        else
            throw new CIE(
                    "Someone attempted to make a CorundumServerThread outside of a CorundumServerThread without using the large custom constructor!"
                            + "That means that this new CorundumServerThread would have no way of retrieving vital operational information like the CorundumServer it's associated with!",
                    "illegal call to a CorundumServerThread constrcutor");
    }

    protected CorundumThread(Runnable target) {
        super(target);

        // if this thread was instantiated from another CorundumServerThread, retrieve all the important info from that thread
        if (Thread.currentThread() instanceof CorundumThread) {
            jar_file = currentThread().jar_file;
            Minecraft_loader = currentThread().Minecraft_loader;
            Corundum_loader = currentThread().Corundum_loader;
            arguments = currentThread().arguments;
        } // otherwise, err out
        else
            throw new CIE(
                    "Someone attempted to make a CorundumServerThread outside of a CorundumServerThread without using the large custom constructor!"
                            + "That means that this new CorundumServerThread would have no way of retrieving vital operational information like the CorundumServer it's associated with!",
                    "illegal call to a CorundumServerThread constrcutor");
    }

    protected CorundumThread(Runnable target, String name) {
        super(target, name);

        // if this thread was instantiated from another CorundumServerThread, retrieve all the important info from that thread
        if (Thread.currentThread() instanceof CorundumThread) {
            jar_file = currentThread().jar_file;
            Minecraft_loader = currentThread().Minecraft_loader;
            Corundum_loader = currentThread().Corundum_loader;
            arguments = currentThread().arguments;
        } // otherwise, err out
        else
            throw new CIE(
                    "Someone attempted to make a CorundumServerThread outside of a CorundumServerThread without using the large custom constructor!"
                            + "That means that this new CorundumServerThread would have no way of retrieving vital operational information like the CorundumServer it's associated with!",
                    "illegal call to a CorundumServerThread constrcutor");
    }

    protected CorundumThread(ThreadGroup group, Runnable target) {
        super(group, target);

        // if this thread was instantiated from another CorundumServerThread, retrieve all the important info from that thread
        if (Thread.currentThread() instanceof CorundumThread) {
            jar_file = currentThread().jar_file;
            Minecraft_loader = currentThread().Minecraft_loader;
            Corundum_loader = currentThread().Corundum_loader;
            arguments = currentThread().arguments;
        } // otherwise, err out
        else
            throw new CIE(
                    "Someone attempted to make a CorundumServerThread outside of a CorundumServerThread without using the large custom constructor!"
                            + "That means that this new CorundumServerThread would have no way of retrieving vital operational information like the CorundumServer it's associated with!",
                    "illegal call to a CorundumServerThread constrcutor");
    }

    protected CorundumThread(ThreadGroup group, String name) {
        super(group, name);

        // if this thread was instantiated from another CorundumServerThread, retrieve all the important info from that thread
        if (Thread.currentThread() instanceof CorundumThread) {
            jar_file = currentThread().jar_file;
            Minecraft_loader = currentThread().Minecraft_loader;
            Corundum_loader = currentThread().Corundum_loader;
            arguments = currentThread().arguments;
        } // otherwise, err out
        else
            throw new CIE(
                    "Someone attempted to make a CorundumServerThread outside of a CorundumServerThread without using the large custom constructor!"
                            + "That means that this new CorundumServerThread would have no way of retrieving vital operational information like the CorundumServer it's associated with!",
                    "illegal call to a CorundumServerThread constrcutor");
    }

    protected CorundumThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);

        // if this thread was instantiated from another CorundumServerThread, retrieve all the important info from that thread
        if (Thread.currentThread() instanceof CorundumThread) {
            jar_file = currentThread().jar_file;
            Minecraft_loader = currentThread().Minecraft_loader;
            Corundum_loader = currentThread().Corundum_loader;
            arguments = currentThread().arguments;
        } // otherwise, err out
        else
            throw new CIE(
                    "Someone attempted to make a CorundumServerThread outside of a CorundumServerThread without using the large custom constructor!"
                            + "That means that this new CorundumServerThread would have no way of retrieving vital operational information like the CorundumServer it's associated with!",
                    "illegal call to a CorundumServerThread constrcutor");
    }

    protected CorundumThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);

        // if this thread was instantiated from another CorundumServerThread, retrieve all the important info from that thread
        if (Thread.currentThread() instanceof CorundumThread) {
            jar_file = currentThread().jar_file;
            Minecraft_loader = currentThread().Minecraft_loader;
            Corundum_loader = currentThread().Corundum_loader;
            arguments = currentThread().arguments;
        } // otherwise, err out
        else
            throw new CIE(
                    "Someone attempted to make a CorundumServerThread outside of a CorundumServerThread without using the large custom constructor!"
                            + "That means that this new CorundumServerThread would have no way of retrieving vital operational information like the CorundumServer it's associated with!",
                    "illegal call to a CorundumServerThread constrcutor");
    }

    public static CorundumThread currentThread() {
        return (CorundumThread) Thread.currentThread();
    }

    public String getActor() {
        return server.toString();
    }

    public Server getServer() {
        return server;
    }

    @Override
    public void run() {
        System.out.println("Running Corundum server...");

        try {
            // get the CorundumServer class
            Class<?> main_class = Class.forName("org.corundummc.CorundumServer", true, Corundum_loader);
            // make a new CorundumServer
            server = (Server) main_class.getConstructor().newInstance();
            // start the new CorundumServer
            main_class.getMethod("start", String[].class).invoke(server, (Object) arguments);
        } catch (ClassNotFoundException | IllegalArgumentException | SecurityException | IllegalAccessException | InvocationTargetException | NoSuchMethodException exception) {
            System.out.println("I couldn't load the Corundum main class!");
            exception.printStackTrace();
        } catch (Exception | Error exception) {
            System.out.println("Something went wrong in initializing the Corundum server!");
            exception.printStackTrace();
        }
    }
}
