package Corundum;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import Corundum.entities.Player;
import Corundum.exceptions.CIE;
import Corundum.exceptions.CorundumException;
import Corundum.listeners.CorundumListener;
import Corundum.listeners.ListenerCaller;
import Corundum.listeners.plugins.PluginLoadListener;
import Corundum.utils.messaging.MessengerUtilities;
import Corundum.utils.myList.myList;

public abstract class CorundumPlugin implements CorundumListener {
    private final JarFile jar;
    private final URLClassLoader loader;

    private boolean enabled = false;
    private myList<CorundumListener> listeners = new myList<CorundumListener>(this);
    private HashMap<String, Method> commands = new HashMap<String, Method>();

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

    // internal Corundum plugin handling
    /** This method takes a given {@link JarFile} and attempts to load it as a {@link CorundumPlugin}. If successful, it will create a new {@link CorundumPlugin} based on the
     * given {@link JarFile}, add it to the {@link Corundum#plugins global plugins list}, and call the {@link CorundumPlugin}'s {@link #onLoad() onLoad() method}. Loading a
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
        ArrayList<Class<CorundumPlugin>> main_classes = new ArrayList<Class<CorundumPlugin>>();

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

        // once all the classes are loaded, run the plugin's main class(es) load() method(s)
        ArrayList<CorundumPlugin> loaded_plugins = new ArrayList<CorundumPlugin>();
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

            // add the newly loaded plugin to the plugins list
            Corundum.SERVER.plugins.add(plugin);

            // generate an event describing the loading
            final CorundumPlugin pluginF = plugin;
            CorundumListener cancelling_listener = Corundum.SERVER.generateEvent(new ListenerCaller<PluginLoadListener>() {
                @Override
                public boolean generateEvent(PluginLoadListener listener) {
                    return listener.onPluginLoad(pluginF);
                }
            });

            // if the a cancellation was requested, close the URLClassLoader
            if (cancelling_listener != null) {
                plugin.debug("plugin \"" + plugin.getName() + "\" v" + plugin.getVersion() + "\" load cancelled by plugin \"" + cancelling_listener.getPlugin().getName()
                        + "\"'s listener class \"" + cancelling_listener.getClass().getSimpleName() + "\"");
                Corundum.SERVER.plugins.remove(plugin);
                try {
                    loader.close();
                } catch (IOException exception) {
                    throw new CIE(plugin.getName() + " had an issue while trying to cancel the loading process!", exception);
                }
                return null;
            }

            // call the plugin's onLoad() method
            boolean cancelled;
            try {
                cancelled = plugin.onLoad();
            } catch (CorundumException exception) {
                exception.err();
                return null;
            } catch (Exception exception) {
                new CorundumException(plugin.getName() + " had a problem while being loaded!", exception).err();
                return null;
            }

            if (cancelled) {
                plugin.debug("plugin \"" + plugin.getName() + "\" v" + plugin.getVersion() + "\" cancelled its own loading");
                Corundum.SERVER.plugins.remove(plugin);
                try {
                    loader.close();
                } catch (IOException exception) {
                    throw new CIE(plugin.getName() + " had an issue while trying to cancel the loading process!", exception);
                }
                return null;
            }

            loaded_plugins.add(plugin);
        }

        try {
            jar.close();
        } catch (IOException exception) {
            throw new CIE("I couldn't close \"" + jar.getName() + "\" at the end of the loading process!", exception);
        }

        return loaded_plugins.toArray(new CorundumPlugin[loaded_plugins.size()]);
    }

    /** This method enables this {@link CorundumPlugin} and calls the plugin's {@link #onEnable() onEnable() method}. Enabling a plugin causes all its {@link CorundumListener}s
     * to become active and its commands to become usable.
     * 
     * @see {@link #onEnable()}, {@link #load(JarFile)}, {@link #disable()}, and {@link #unload()}. */
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
     * @see {@link #onDisable()}, {@link #load(JarFile)}, {@link #enable()}, and {@link #unload()}. */
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
     * @see {@link #onUnload()}, {@link #load(JarFile)}, {@link #enable()}, and {@link #disable()}. */
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
        Corundum.SERVER.plugins.remove(this);

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
        if (getPlugin() == null)
            Corundum.secure("No CorundumPlugin's Messengers should return null for a plugin!");

        MessengerUtilities.bloviate(getPlugin(), message);
    }

    /** This method broadcasts the given message to the server, displaying it in the console, in all players' chats, and in the logs.
     * 
     * @param message
     *            is the message to be broadcasted to the server.
     * @see {@link Messenger#broadcast(String)} */
    public void broadcast(String message) {
        if (getPlugin() == null)
            Corundum.secure("No CorundumPlugin's Messengers should return null for a plugin!");

        tellConsole(message);

        // TODO: when supportable, send the message to all players on the server
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
        MessengerUtilities.debug(getPlugin(), message);
    }

    public void tellConsole(String message) {
        MessengerUtilities.tellConsole(getPlugin(), message);
    }

    public void tellPlayer(Player player, String message) {
        MessengerUtilities.tellPlayer(getPlugin(), player, message);
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
}
