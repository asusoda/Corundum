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
import org.corundummc.entities.Player;
import org.corundummc.exceptions.CIE;
import org.corundummc.exceptions.CorundumException;
import org.corundummc.listeners.CorundumListener;
import org.corundummc.listeners.ListenerCaller;
import org.corundummc.listeners.plugins.PluginLoadListener;
import org.corundummc.listeners.results.EventResult;
import org.corundummc.utils.myList.myList;

public abstract class CorundumPlugin implements CorundumListener {
    private final JarFile jar;
    private final URLClassLoader loader;

    private boolean enabled = false;
    private myList<CorundumListener> listeners = new myList<CorundumListener>(this);
    private HashMap<String, Method> commands = new HashMap<>();

    public CorundumPlugin() throws CIE {
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
        return (CorundumServer) PluginThread.currentThread().getServer();
    }

    // internal Corundum plugin handling
    /** This method takes a given {@link JarFile} and attempts to load it as a {@link CorundumPlugin}. If successful, it will create a new {@link CorundumPlugin} based on the
     * given {@link JarFile}, add it to the {@link CorundumServer#plugins global plugins list}, and call the {@link CorundumPlugin}'s {@link #onLoad() onLoad() method}. Loading a
     * plugin will load the plugin and its data into the RAM and make its classes accessible, but will not start the plugin or use any of its {@link CorundumListener}s.
     * 
     * @param file_path
     *            is the path to the {@link JarFile} from which the plugin will be loaded.
     * @return all the {@link CorundumPlugin}s loaded from the {@link JarFile} at the given <b><tt>file_path</b></tt>.
     * @throws CIE
     *             if any input/output issues arise while loading or attempting to cancel the loading of this plugin.
     * @see {@link #onLoad()}, {@link #enable()}, {@link #disable()}, and {@link #unload()}. */
    @SuppressWarnings({ "unchecked", "resource" })
    public static final CorundumPlugin[] load(String file_path) throws CIE {
        JarFile jar;
        try {
            jar = new JarFile(file_path);
        } catch (IOException exception) {
            throw new CIE("There was no jar file at the specified path of this plugin jar!", exception, "file path=\"" + file_path + "\"");
        }

        // load the plugin from its jar file using ClassLoaders
        URLClassLoader loader;
        try {
            // create a Classloader to load the plugin
            loader = new URLClassLoader(new URL[] { new URL("jar:file:" + jar.getName() + "!/") });
        } catch (MalformedURLException exception) {
            throw new CIE("The plugin file's path didn't translate properly into a U.R.L.!", exception, "file path=\"jar:file:" + jar.getName() + "!/\"");
        }

        // skip past the directories and resources in the jar to get to the classes
        Enumeration<JarEntry> entries = jar.entries();
        ArrayList<Class<CorundumPlugin>> main_classes = new ArrayList<>();

        if (entries.hasMoreElements())
            for (JarEntry entry = entries.nextElement(); entries.hasMoreElements(); entry = entries.nextElement())
                if (!entry.isDirectory() && entry.getName().endsWith(".class"))
                    try {
                        // first, load the class
                        Class<?> loaded_class = loader.loadClass(entry.getName().substring(0, entry.getName().length() - 6).replaceAll("/", "."));

                        // then, figure out if the class extends CorundumPlugin
                        Class<?> superclass = loaded_class.getSuperclass();
                        while (superclass != null && !superclass.equals(CorundumPlugin.class))
                            superclass = superclass.getSuperclass();

                        // if it found a superclass of type CorundumPlugin, load it
                        if (superclass != null && superclass.equals(CorundumPlugin.class))
                            main_classes.add((Class<CorundumPlugin>) loaded_class);
                    } catch (ClassNotFoundException exception) {
                        try {
                            loader.close();
                        } catch (IOException exception2) {
                            // this has to be a continue instead of an exception because an exception will stop this function without closing loader, causing Eclipse errors
                            continue;
                        }
                        throw new CIE("I couldn't find the class in the JarEntry!", exception, "entry name=\"" + entry.getName() + "\"");
                    }

        // load the classes and put them into a temporary list for later handling
        ArrayList<CorundumPlugin> loaded_plugins = new ArrayList<>();
        for (Class<CorundumPlugin> main_class : main_classes) {
            // load the main class as a new CorundumPlugin
            CorundumPlugin plugin;
            try {
                plugin = main_class.asSubclass(CorundumPlugin.class).newInstance();
            } catch (InstantiationException exception) {
                throw new CIE("I couldn't instantiate this new plugin!", exception, "jar file=\"" + jar.getName() + "\"");
            } catch (IllegalAccessException exception) {
                throw new CIE("I couldn't access this new plugin's default constructor to instantiate it!", exception, "jar file=\"" + jar.getName() + "\"");
            }

            loaded_plugins.add(plugin);
        }

        handleDependencyChecking(loaded_plugins);

        // handle the actual loading.
        for (CorundumPlugin currPlugin : loaded_plugins) {
            // add the newly loaded plugin to the plugins list
            CorundumServer.getInstance().plugins.add(currPlugin);

            // generate an event describing the loading
            final CorundumPlugin pluginF = currPlugin;
            EventResult result = CorundumServer.getInstance().generateEvent(new ListenerCaller<PluginLoadListener, EventResult>() {
                @Override
                public EventResult generateEvent(PluginLoadListener listener, EventResult result) {
                    if (result == null)
                        result = new EventResult();

                    return listener.onPluginLoad(pluginF, result);
                }
            });

            // if the a cancellation was requested, close the URLClassLoader
            if (result.isCancelled()) {
                currPlugin.debug("plugin \"" + currPlugin.getName() + "\" v" + currPlugin.getVersion() + "\" load cancelled");
                CorundumServer.getInstance().plugins.remove(currPlugin);
                try {
                    loader.close();
                } catch (IOException exception) {
                    throw new CIE(currPlugin.getName() + " had an issue while trying to cancel the loading process!", exception);
                }
                return null;
            }

            // call the plugin's onLoad() method
            boolean success;
            try {
                success = currPlugin.onLoad();
            } catch (CorundumException exception) {
                exception.err();
                return null;
            } catch (Exception exception) {
                new CorundumException(currPlugin.getName() + " had a problem while being loaded!", exception).err();
                return null;
            }

            if (!success) {
                currPlugin.debug("plugin \"" + currPlugin.getName() + "\" v" + currPlugin.getVersion() + "\" cancelled its own loading");
                CorundumServer.getInstance().plugins.remove(currPlugin);
                try {
                    loader.close();
                } catch (IOException exception) {
                    throw new CIE(currPlugin.getName() + " had an issue while trying to cancel the loading process!", exception);
                }
                return null;
            }
        }

        try {
            jar.close();
        } catch (IOException exception) {
            throw new CIE("I couldn't close \"" + jar.getName() + "\" at the end of the loading process!", exception);
        }

        return loaded_plugins.toArray(new CorundumPlugin[loaded_plugins.size()]);
    }

    public static void handleDependencyChecking(List<CorundumPlugin> pluginsToCheck) {
        Map<String, List<CorundumPlugin>> dependencies = new HashMap<>();
        Map<String, CorundumPlugin> pluginNames = new HashMap<>();

        for (CorundumPlugin plugin : pluginsToCheck) {
            String[] pluginDependencies = plugin.getDependencies();
            pluginNames.put(plugin.getName(), plugin);

            if (pluginDependencies.length >= 1) {
                for (String pluginDependency : pluginDependencies) {
                    if (!dependencies.keySet().contains(pluginDependency) && !pluginDependency.equals("")) {
                        List<CorundumPlugin> plugins = dependencies.get(pluginDependency);
                        plugins.add(plugin);
                        dependencies.put(pluginDependency, plugins);
                    }
                }
            }
        }

        for (String dependency : dependencies.keySet()) {
            if (!pluginNames.keySet().contains(dependency)) {
                for (CorundumPlugin plugin : dependencies.get(dependency)) {
                    CorundumServer.getInstance()
                            .message("Plugin " + plugin.getName() + " will NOT load, as it is missing dependency " + dependency + ", and possibly others!");
                    pluginsToCheck.remove(plugin);
                }
            }
        }
    }

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
            new CorundumException(getName() + " had a problem while being unloaded!", exception).err();
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
    public void debug(String message) {
        CorundumServer.getInstance().debug(message);
    }

    public void tellConsole(String message) {
        CorundumServer.getInstance().message(getPrefix() + message);
    }

    public void tellPlayer(Player player, String message) {
        player.message(getPrefix() + message);
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

    // listener management
    public boolean startListener(CorundumListener listener) {
        if (listeners.contains(listener))
            return false;
        else {
            listeners.add(listener);
            return true;
        }
    }

    public myList<CorundumListener> getListeners() {
        return listeners;
    }

    public boolean stopListener(CorundumListener listener) {
        int removed_listener_index = listeners.remove(listener);
        return removed_listener_index != -1;
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
    public final String toString() {
        return getName() + " v" + getVersion();
    }

    // currently non abstract method used in dependencies.
    public String[] getDependencies() {
        return new String[] { "" };
    }
}
