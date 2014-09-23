package Corundum;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import Corundum.exceptions.CIE;
import Corundum.exceptions.CorundumException;
import Corundum.utils.messaging.Messenger;
import Corundum.utils.myList.myList;

public abstract class CorundumPlugin implements CorundumListener, Messenger {
    private final JarFile JAR;

    private boolean enabled = false;
    private myList<CorundumListener> listeners = new myList<CorundumListener>(this);
    private HashMap<String, Method> commands = new HashMap<String, Method>();

    public CorundumPlugin() throws CIE {
        // first, try to find the jar's URL from the URLClassLoader used to load it
        URL jar_URL;
        try {
            jar_URL = ((URLClassLoader) getClass().getClassLoader()).getURLs()[0];
        } catch (ClassCastException exception) {
            throw new CorundumException("Someone loaded this plugin with something besides a URLClassLoader!", exception, "ClassLoader type="
                    + getClass().getClassLoader().getClass().getSimpleName());
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new CorundumException("How does this URLClassLoader have no URLs?!", exception);
        }

        // then, try to isolate the jar's path from the URL into a new JarFile
        try {
            JAR = new JarFile(jar_URL.toString().substring(9 /* remove the "jar:file:" */, jar_URL.toString().length() - 2/* remove the "!/" */));
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
     * @param jar
     *            is the {@link JarFile} from which the plugin will be loaded.
     * @throws CIE
     *             if any input/output issues arise while loading or attempting to cancel the loading of this plugin.
     * @see {@link #onLoad()}, {@link #enable()}, {@link #disable()}, and {@link #unload()}. */
    @SuppressWarnings("unchecked")
    public static final void load(JarFile jar) throws CIE {
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

        // once all the classes are loaded, close the ClassLoader and run the plugin's main class(es) load() method(s)
        try {
            loader.close();
        } catch (IOException exception) {
            throw new CIE("I couldn't close the URLClassLoader used to load this plugin!", exception, "jar file=\"" + jar.getName() + "\"");
        }
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
            Corundum.plugins.add(plugin);

            // generate an event describing the loading
            CorundumListener cancelling_listener = CorundumListener.generateEvent(listener -> listener.onPluginLoad(plugin));

            // if the a cancellation was requested, close the URLClassLoader
            if (cancelling_listener != null) {
                plugin.debug("plugin \"" + plugin.getName() + "\" v" + plugin.getVersion() + "\" load cancelled by plugin \"" + cancelling_listener.getPlugin().getName()
                        + "\"'s listener class \"" + cancelling_listener.getClass().getSimpleName() + "\"");
                Corundum.plugins.remove(plugin);
                try {
                    loader.close();
                } catch (IOException exception) {
                    throw new CIE(plugin.getName() + " had an issue while trying to cancel the loading process!", exception);
                }
                return;
            }

            // call the plugin's onLoad() method
            boolean cancelled;
            try {
                cancelled = plugin.onLoad();
            } catch (CorundumException exception) {
                exception.err();
                return;
            } catch (Exception exception) {
                new CorundumException(plugin.getName() + " had a problem while being loaded!", exception).err();
                return;
            }

            if (cancelled) {
                plugin.debug("plugin \"" + plugin.getName() + "\" v" + plugin.getVersion() + "\" cancelled its own loading");
                Corundum.plugins.remove(plugin);
                try {
                    loader.close();
                } catch (IOException exception) {
                    throw new CIE(plugin.getName() + " had an issue while trying to cancel the loading process!", exception);
                }
                return;
            }
        }
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
        Corundum.plugins.remove(this);

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
