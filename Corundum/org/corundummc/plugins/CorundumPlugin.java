package org.corundummc.plugins;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.corundummc.CorundumServer;
import org.corundummc.entities.living.Player;
import org.corundummc.exceptions.CIE;
import org.corundummc.exceptions.CorundumException;
import org.corundummc.hub.CorundumThread;
import org.corundummc.listeners.CorundumListener;
import org.corundummc.listeners.Event;
import org.corundummc.listeners.ListenerCaller;
import org.corundummc.listeners.plugins.PluginLoadListener;
import org.corundummc.utils.ListUtilities;
import org.corundummc.utils.interfaces.Matchable;
import org.corundummc.utils.myList.myList;

public abstract class CorundumPlugin implements Matchable<CorundumPlugin> {
    private final JarFile jar;
    private final URLClassLoader loader;
    private final CorundumServer server;

    private boolean enabled = false;
    private HashMap<String, Method> commands = new HashMap<>();

    public CorundumPlugin() {
        this.server = (CorundumServer) CorundumThread.currentThread().getServer();

        // get the URLClassLoader used to load this class
        loader = (URLClassLoader) getClass().getClassLoader();

        // first, try to find the jar's URL from the URLClassLoader used to load it
        URL jar_URL;
        try {
            jar_URL = loader.getURLs()[0];
        } catch (ClassCastException exception) {
            throw new CorundumException("Someone loaded this plugin with something besides a URLClassLoader!", exception, "ClassLoader type="
                    + getClass().getClassLoader().getClass().getSimpleName());
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new CorundumException("How does this URLClassLoader have no URLs?!", exception);
        }

        // then, try to isolate the jar's path from the URL into a new JarFile
        try {
            jar = new JarFile(jar_URL.toString().substring(9 /* remove the "jar:file:" */, jar_URL.toString().length() - 2/* remove the "!/" */));
        } catch (IOException exception) {
            throw new CIE("There was a problem getting the jar file path while loading a new CorundumPlugin!", exception, "path=\""
                    + jar_URL.toString().substring(9, jar_URL.toString().length() - 2) + "\"");
        }
    }

    public CorundumServer getServer() {
        return server;
    }

    // internal Corundum plugin handling
    /** This method enables this {@link CorundumPlugin} and calls the plugin's {@link #onEnable() onEnable() method}. Enabling a plugin causes all its {@link CorundumListener}s
     * to become active and its commands to become usable.
     * 
     * @see {@link #onEnable()}, {@link CorundumPlugin#load(String)}, {@link #disable()}, and {@link #unload()}. */
    public final void enable() {
        enabled = true;
        try {
            onEnable();
        } catch (CorundumException exception) {
            exception.err();
        } catch (Exception exception) {
            new CorundumException(getName() + " had a problem while being enabled!", exception).err();
        }
    }

    /** This method disables this {@link CorundumPlugin} and calls the plugin's {@link #onDisable() onDisable() method}. Disabling a plugin causes all its
     * {@link CorundumListener}s to become inactive and its commands to become useless, but does not remove the plugin and its data from the RAM and does not allow the
     * operating system to "unlock" the plugin's jar file. To clear plugins from RAM and release Corundum's hold on the jar file, use the {@link #unload() unload() method}.
     * 
     * @see {@link #onDisable()}, {@link #load(String)}, {@link #enable()}, and {@link #unload()}. */
    public final void disable() {
        enabled = false;

        try {
            onDisable();
        } catch (CorundumException exception) {
            exception.err();
        } catch (Exception exception) {
            new CorundumException(getName() + " had a problem while being disabled!", exception).err();
        }
    }

    /** This method unloads this {@link CorundumPlugin} and calls the plugin's {@link #onUnload() onUnload() method}. Unloading a plugin first {@link #disable() disables} the
     * plugin if it was not already, then clears all of the plugin's memory from RAM and closes the {@link ClassLoader} that loaded the jar into Corundum, closing off all
     * access to the classes and functions inside the jar from Corundum and its plugins and allowing the operating system to modify, move, or delete the plugin's jar file
     * freely.
     * 
     * @throws CIE
     *             if closing the {@link URLClassLoader class loader} causes issues.
     * 
     * @see {@link #onUnload()}, {@link #load(String)}, {@link #enable()}, and {@link #disable()}. */
    public final void unload() throws CIE {
        if (enabled)
            disable();

        try {
            onUnload();
        } catch (CorundumException exception) {
            exception.err();
        } catch (Exception exception) {
            throw new CorundumException(getName() + " had a problem while being unloaded!", exception);
        }

        // remove this plugin from the plugins list
        CorundumServer.getInstance().plugins.remove(this);

        // shut down the ClassLoader for this plugin
        try {
            ((URLClassLoader) getClass().getClassLoader()).close();
        } catch (ClassCastException exception) {
            throw new CIE("Something besides a URLClassLoader was used to load this plugin \"" + getName() + "\" that I'm trying to unload!", exception, "ClassLoader type="
                    + getClass().getClassLoader().getClass().getSimpleName());
        } catch (IOException exception) {
            throw new CIE("I couldn't close the ClassLoader while unloading this plugin \"" + getName() + "\"!", exception);
        }

        // run garbage collection to gain back the RAM that was used by the plugin
        System.gc();
    }

    // messenger utilities
    /** This method sends the given message to the console, players, and logs that are in "verbose debugging mode". These messages can contain very detailed information
     * relevant to debugging, unlike the regular {@link #debug(String) debug() method} which should be given only basic debugging information. Players and the console can enter
     * or exit verbose debugging mode with a command. In addition, if "verbose debug logging" is active, the message will be logged in Corundum's log files.
     * 
     * @param message
     *            is the debug message to send to the verbosely debugging parties.
     * @see {@link #debug(String)} */
    public void bloviate(String message) {
        CorundumServer.getInstance().bloviate(message);
    }

    /** This method broadcasts the given message to the server, displaying it in the console, in all players' chats, and in the logs.
     * 
     * @param message
     *            is the message to be broadcasted to the server.
     * @see {@link CorundumServer#broadcast(String)} */
    public void broadcast(String message) {
        tellConsole(message);
        CorundumServer.getInstance().broadcast(message);
    }

    /** This method sends the given message to all the players (or console) that are currently in "debugging mode". These messages should contain basic important information
     * relevant to debugging, unlike the verbose {@link #bloviate(String) bloviate() method} which can output large amounts of very detailed information. Players and the
     * console can enter or exit debugging mode with a command. In addition, if "debug logging" is active, the message will be logged in Corundum's log files.
     * 
     * @param message
     *            is the debug message to send to the debugging parties.
     * 
     * @see {@link #bloviate(String)} */
    @SuppressWarnings("static-method")
    public void debug(String message) {
        server.debug(message);
    }

    public void tellConsole(String message) {
        server.message(getPrefix() + message);
    }

    // plugin handling event handling for plugin makers
    public boolean onLoad() {
        // do nothing by default
        return false;
    }

    public void onEnable() {
        // do nothing by default
    }

    public void onDisable() {
        // do nothing by default
    }

    public void onUnload() {
        // do nothing by default
    }

    // other utilities
    public boolean isEnabled() {
        return enabled;
    }

    // abstract getters
    public abstract String[] getAuthors();

    public abstract String getName();

    public abstract String getPrefix();

    public abstract String getVersion();

    @Override
    public final Object[] getSortPriorities() {
        return new Object[] { getName(), getVersion() };
    }

    @Override
    public String toString() {
        return getName() + " v" + getVersion();
    }

    // currently non abstract method used in dependencies.
    public String[] getDependencies() {
        return new String[0];
    }
}
