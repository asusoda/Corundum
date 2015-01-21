package org.corundummc;

import static org.corundummc.utils.StringUtilities.capitalize;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Logger;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChatComponentText;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.corundummc.entities.Entity.EntityType;
import org.corundummc.entities.living.Player;
import org.corundummc.entities.living.Player.GameMode;
import org.corundummc.exceptions.CIE;
import org.corundummc.exceptions.CorundumException;
import org.corundummc.exceptions.CorundumSecurityException;
import org.corundummc.hub.Server;
import org.corundummc.hub.CorundumHub;
import org.corundummc.hub.CorundumThread;
import org.corundummc.listeners.CommandListener;
import org.corundummc.listeners.CommandListener.CommandEvent;
import org.corundummc.listeners.CorundumListener;
import org.corundummc.listeners.Event;
import org.corundummc.listeners.ListenerCaller;
import org.corundummc.plugins.CorundumPlugin;
import org.corundummc.listeners.plugins.PluginLoadListener.PluginLoadEvent;
import org.corundummc.plugins.PluginThread;
import org.corundummc.utils.ListUtilities;
import org.corundummc.utils.SettingsManager;
import org.corundummc.utils.interfaces.Commander;
import org.corundummc.utils.interfaces.SECURED;
import org.corundummc.utils.myList.myList;
import org.corundummc.utils.types.IDedType;
import org.corundummc.utils.versioning.Version;

/** This class represents the entirety of a Corundum server.
 * 
 * @author REALDrummer */
public class CorundumServer extends DedicatedServer implements Server, Commander {
    /** This {@link OperatingSystem} represents the operating system is currently running on. */
    public final OperatingSystem OS = OperatingSystem.getFromName(System.getProperty("os.name"));

    private final Version VERSION = new Version("pre-α"), MCVERSION = new Version("1.7.10");
    private final File PLUGINS_FOLDER = new File("plugins");

    private String name, prefix;

    /** This list contains all the currently loaded {@link CorundumPlugin}s on the server. Note that loading and unloading plugins will add or remove them from this list,
     * respectively, but enabling or disabling them will <i>not</i> affect this list. */
    public myList<CorundumPlugin> plugins = new myList<>();
    /** This {@link HashMap} stores all the <i>enabled</i> {@link CorundumListener}s based on their {@link Class} in order to allow {@link Event}s to be most efficiently locate
     * the appropriate {@link CorundumListener}s to call when one of them occurs. */
    private HashMap<Class<Event<?>>, ArrayList<CorundumListener>> enabled_listeners = new HashMap<>();
    /** This {@link HashMap} stores all the <i>loaded</i> {@link CorundumListener}s based on the {@link CorundumPlugin} that they were loaded from. */
    private HashMap<CorundumPlugin, ArrayList<CorundumListener>> loaded_listeners = new HashMap<>();

    private CorundumGui corundumGui;
    private boolean corundum_GUI_enabled = false;
    /** This <b>boolean</b> determines whether or not the server uses the default Minecraft GUI. Usually false but can be changed via --mc-gui. */
    private boolean usingMCGui = false;

    /** The {@link ArgInfo} concerning the args passed in {@link #start}. */
    private ArgInfo argInfo;

    /** This <b>boolean</b> determines whether or not the server is running in "debug" mode, which will cause the server to log debugging messages to the console. Debug mode is
     * off (<b>false</b>) by default. Debug mode can be enabled by passing the argument <tt>--debug</tt> (a.k.a. <tt>-d</tt>) to the console as a command line argument when
     * starting the server. */
    private boolean debugMode;
    /** This <b>boolean</b> determines whether or not the server is running in "verbose" mode, which will cause the server to log a large amount of debugging messages to the
     * console. Verbose mode is off (<b>false</b>) by default. Verbose mode can be enabled by passing the argument <tt>--verbose</tt> (a.k.a. <tt>-v</tt>) to the console as a
     * command line argument when starting the server. Note that if verbose mode is enabled, so is {@link #debugMode debug mode}. */
    private boolean verboseMode;

    /** This {@link myList} keeps track of the players who are currently in debugging mode; <b>null</b> repesents {@link Corundum#CONSOLE the console} while non-<b>null</b>
     * {@link java.util.UUID}s represent players. Note that {@link #verbose_debuggers verbose debuggers} also appear in this list in addition to appearing in
     * {@link #verbose_debuggers the verbose debuggers list}. */
    private myList<UUID> debuggers = new myList<>();

    /** This {@link myList} keeps track of the players who are currently in verbose debugging mode; <b>null</b> represents {@link Corundum#CONSOLE the console} while
     * non-<b>null</b> {@link UUID}s represent players. Note that all players (+ {@link Corundum#CONSOLE console}) who are in verbose debugging mode are also in regular
     * debugging mode and are also in the {@link #debuggers debuggers list}. */
    private myList<UUID> verbose_debuggers = new myList<>();

    // config variable names start
    public static final String CONFIG_FILE_NAME = "settings.json";
    /** This is the variable name for changing the name of the server */
    public static final String SERVER_NAME_VAR = "name";

    private SettingsManager settings = new SettingsManager(new File(CONFIG_FILE_NAME), SERVER_NAME_VAR, "Corundum");

    /** This constructor creates a new {@link CorundumServer}, which extends Minecraft's {@link DedicatedServer} class, allowing it to change some of Minecraft's behaviors.
     * Through {@link DedicatedServer}'s constructor, it will also set {@link MinecraftServer#mcServer} to this new server. <br>
     * <b><i><u>WARNING</b></i></u>: There should only ever be one of these! You can use its instance from {@link CorundumServer#SERVER}.
     * 
     * @param name
     *            is the name of this {@link CorundumServer}. */
    public CorundumServer(String name) {
        // this DedicatedServer constructor sets up the server and sets MinecraftServer.mcServer to this
        super(new File("."));

        this.name = name;
        prefix = "[" + name + "]";
    }

    /** This enum represents a type of operating system. It can be {@link #WINDOWS Windows}, {@link #MAC Mac OS}, {@link #LINUX Linux}, {@link #UNIX Unix}, or {@link #OTHER
     * "other"}. The public static final <tt>OperatingSystem</tt> {@link CorundumServer#OS OS} represents the operating system that this server is currently running on.
     * 
     * @author REALDrummer */
    public static enum OperatingSystem {
        WINDOWS, MAC, LINUX, UNIX, OTHER;

        public static OperatingSystem getFromName(String name) {
            if (name.startsWith("Windows"))
                return WINDOWS;
            else if (name.startsWith("Mac OS"))
                return MAC;
            else if (name.startsWith("Linux"))
                return LINUX;
            else if (name.contains("Unix"))
                return UNIX;
            else
                return OTHER;
        }

        @Override
        public String toString() {
            if (this == MAC)
                return "Mac OS";
            else if (this == WINDOWS)
                return "Windows";
            else if (this == LINUX)
                return "Linux";
            else if (this == UNIX)
                return "Unix";
            else if (this == OTHER)
                return "other";
            else
                return capitalize(super.toString());
        }
    }

    public static class Difficulty extends IDedType<Difficulty> {
        public static final Difficulty PEACEFUL = new Difficulty("Peaceful"), EASY = new Difficulty("Easy"), NORMAL = new Difficulty("Normal"), HARD = new Difficulty("Hard");

        private final String name;

        protected Difficulty(String name) {
            super(values() == null ? 0 : values().length);

            this.name = name;
        }

        // overridden utilities
        @Override
        public String getName() {
            return name;
        }

        // pseudo-enum utilities
        public static Difficulty getByID(int id) {
            return getByID(Difficulty.class, id);
        }

        public static Difficulty[] values() {
            return values(Difficulty.class);
        }
    }

    // start up and shut down
    /** This method starts this {@link CorundumServer}.
     * 
     * @param arguments
     *            are the command-line arguments used to configure the properties of this server on startup. */
    @Override
    public final void start(String[] arguments) throws CorundumSecurityException {
        secure("call CorundumServer.start()");

        System.out.println("Starting Corundum server...");

        // To make arg reading easier.
        this.argInfo = new ArgInfo(arguments);
        // --no-debug takes priority.
        this.debugMode = this.argInfo.hasArg("--no-debug", "-D") ? false : this.argInfo.hasArg("--debug", "-d");
        this.verboseMode = this.argInfo.hasArg("--no-verbose", "-V") ? false : this.argInfo.hasArg("--verbose", "-v");

        if (this.argInfo.hasArg("--gui-enabled", "-g")) {
            this.enableGUI();
        }

        try {
            main(arguments);
        } catch (Exception exception) {
            CIE.err("There was a problem starting this Corundum server!", exception);
        }

        // Vanilla property setting is after the server is started-started as, otherwise, the properties gotten from
        // server.properties takes priority.
        /* TODO: I had to comment these parts below because they break the build: Minecraft puts the Minecraft server in a separate thread, which causes startServer() to not
         * be called until later, which causes the lines below to throw a NullPointerException because the MinecraftServer PropertyManager is not initialized until
         * startServer() is called. In addition, I can't just call startServer() before this because when the server thread starts immediately after this method and
         * startServer() is called there, it can't bind to port because it's basically trying ot run two servers in the same place at the same time and it crashes. We also
         * can't put it before the main() call, for obvious reasons. */
        // if (argInfo.hasArg("--online-mode", "-o")) {
        // super.setProperty("online-mode", true);
        // } else if (this.argInfo.hasArg("--offline-mode", "-O")) {
        // super.setProperty("online-mode", false);
        // }
        //
        // if (this.argInfo.hasArg("--world")) {
        // super.setProperty("level-name", this.argInfo.getArgValue("--world"));
        // } else {
        // super.setProperty("level-name", "world");
        // }
    }

    /** This method shuts down Corundum completely.
     * 
     * @throws CorundumSecurityException
     *             if a {@link CorundumPlugin} attempts to call this method. */
    @Override
    public final void quit() throws CorundumSecurityException {
        secure("call CorundumServer.quit()");

        getInstance().stopServer();

        // close Corundum
        System.exit(0);
    }

    // security
    public static CorundumServer getInstance() {
        if (Thread.currentThread() instanceof CorundumThread)
            return (CorundumServer) CorundumThread.currentThread().getServer();
        else if (CorundumHub.isMainThread())
            throw new CIE("Someone tried to retrieve the current server from the Corundum main thread!"
                    + " The main Corundum thread has no server instance because it controls more than one server!",
                    "attempt to get the current server instance from the main Corundum thread");
        else
            throw new CorundumSecurityException("unknown thread", "retrieve the current server instance");
    }

    /** This method can check at any given spot whether or not a call is "secure". The point is to ensure that certain methods are only called form inside Corundum, not by
     * Corundum plugins. If the spot is not secure, it will throw a {@link CorundumSecurityException}.
     * 
     * @param offense
     *            is a very brief description of the illegal action the plugin attempted to perform, e.g. <tt>"call {@link CorundumServer#quit()}"</tt>.
     * 
     * @throws CorundumSecurityException
     *             if the method was called by something other than Corundum internal code.
     * @see {@link #secure(String)} */
    public static void secure(String offense) throws CorundumSecurityException {
        secure(null, offense);
    }

    /** This method can check at any given spot whether or not a call is "secure". The point is to ensure that certain methods are only called form inside Corundum, not by
     * Corundum plugins. If the spot is not secure, it will throw a {@link CorundumSecurityException}.
     * 
     * @param offense
     *            is a very brief description of the illegal action the plugin attempted to perform, e.g. <tt>"call {@link CorundumServer#quit()}"</tt>.
     * 
     * @param message
     *            is the message that will go with the {@link CorundumSecurityException} if one is thrown. It should be used in cases where this method is not just used to
     *            secure a whole method, but to secure it in a certain case, so as not to mislead users trying to debug their plugin.
     * 
     * @throws CorundumSecurityException
     *             if the method was called by something other than Corundum internal code.
     * @see {@link #secure(String)} */
    public static void secure(String message, String offense) throws CorundumSecurityException {
        // if this thread is a PluginThread, refuse it access to this secure area
        if (Thread.currentThread() instanceof PluginThread)
            throw new CorundumSecurityException(message, ((PluginThread) Thread.currentThread()).getPlugin().toString(), offense);
        // if this thread is an unidentified thread, refuse it access to this secure area
        else if (!(Thread.currentThread() instanceof CorundumThread) && !CorundumHub.isMainThread())
            throw new CorundumSecurityException(message, "unknown thread", offense);
        // otherwise, let it pass
    }

    // private utilities
    private static void handleDependencyChecking(List<CorundumPlugin> pluginsToCheck) {
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

    // overrides
    @Override
    public String toString() {
        return "the Corundum server \"" + getName() + "\" (v" + getVersion() + ")";
    }

    // Minecraft utils
    public boolean canGenerateStructures() {
        // This method is necessary because super.canStructuresSpawn() will be obfuscated!
        return super.canStructuresSpawn();
    }

    public void enableGUI() {
        setGuiEnabled();
    }

    @Override
    public void setGuiEnabled() {
        if (this.argInfo.hasArg("--mc-gui", "-mc-g") || !corundum_GUI_enabled) {
            if (!corundum_GUI_enabled) {
                System.out
                        .println("The Corundum GUI is not enabled! This is a dev thing and hardcoded (ie. Unalterable via args). Corundum is likely in alpha as this is the only time it's likely to be disabled.");
                System.out.println("Using vanilla server GUI as Corundum's GUI is not enabled [HARDCODED VALUE].");
            }

            super.setGuiEnabled();
            this.usingMCGui = true;
            this.corundum_GUI_enabled = false;
        } else {
            this.corundumGui = new CorundumGui(this);
            this.corundum_GUI_enabled = true;
        }
    }

    @Override
    public boolean getGuiEnabled() {
        return this.corundum_GUI_enabled ? true : super.getGuiEnabled();
    }

    public boolean getUsingGui() {
        // Necessary as getGuiEnabled will be obfuscated.
        return this.getGuiEnabled();
    }

    public GameMode getDefaultGameMode() {
        return GameMode.getByID(getGameType().getID());
    }

    public Difficulty getDifficulty() {
        return Difficulty.getByID(/* get server default difficulty */func_147135_j()./* get difficulty enum I.D. */func_151525_a());
    }

    public int getSpawnRadius() {
        return getSpawnProtectionSize();
    }

    public String getHostName() {
        return getHostname();
    }

    public boolean hasNether() {
        return super.getAllowNether();
    }

    public boolean getIsHardcore() {
        // This method is necessary because super.isHardcore() will be obfuscated!
        return super.isHardcore();
    }

    public boolean isRunningGUI() {
        return getGuiEnabled();
    }

    public boolean isSnooping() {
        return super.isSnooperEnabled();
    }

    public boolean isUsingCommandBlocks() {
        return isCommandBlockEnabled();
    }

    // Corundum utils
    public void bloviate(String message) {
        message(message);

        for (UUID playerUUID : this.verbose_debuggers) {
            Player.getByUUID(playerUUID).message(message);
        }
    }

    /** This method broadcasts a given message to every player on the server and to the console.
     * 
     * @param message
     *            is the message to be broadcasted. */
    @SuppressWarnings("unchecked")
    public void broadcast(String message) {
        message(message);

        for (Player player : Player.getOnlinePlayers())
            player.message(message);
    }

    @Override
    public void command(final String command) {
        CommandEvent event = new CommandEvent(this, command).runOn(this);

        if (!event.isCancelled())
            addPendingCommand(command, this);
    }

    public void debug(String message) {
        logDebug(message);

        for (UUID playerUUID : this.debuggers) {
            Player.getByUUID(playerUUID).message(message);
        }

        for (UUID playerUUID : this.verbose_debuggers) {
            Player.getByUUID(playerUUID).message(message);
        }
    }

    @Override
    public Version getMCVersion() {
        return MCVERSION;
    }

    @Override
    public String getName() {
        return settings.getString(SERVER_NAME_VAR);
    }

    @Override
    public Version getVersion() {
        return VERSION;
    }

    /** Helper method as the actual method is SRG named in MCP 1.7.10
     * 
     * @return this server's {@link PlayerProfileCache}. */
    PlayerProfileCache getPlayerProfileCache() {
        return super.func_152358_ax();
    }

    public File getPluginsFolder() {
        return PLUGINS_FOLDER;
    }

    @Override
    public void message(String message) {
        super.addChatMessage(new ChatComponentText(message));
    }

    /** This method takes a given {@link JarFile} and attempts to load any {@link CorundumPlugin}s inside it. If successful, it will create one or more {@link CorundumPlugin}s
     * based on the given {@link JarFile}, add the resulting plugin(s) to the {@link CorundumServer#loaded_plugins global plugins list}, and call the {@link CorundumPlugin}'s
     * {@link #onLoad() onLoad() method}. Loading a plugin will load the plugin and its data into the RAM and make its classes accessible, but will not start the plugin or use
     * any of its {@link CorundumListener}s.
     * 
     * @param file_path
     *            is the path to the {@link JarFile} from which the plugin will be loaded.
     * @return all the {@link CorundumPlugin}s loaded from the {@link JarFile} at the given <b><tt>file_path</b></tt>.
     * @throws CIE
     *             if any input/output issues arise while loading or attempting to cancel the loading of this plugin.
     * @see {@link #onLoad()}, {@link #enable()}, {@link #disable()}, and {@link #unload()}. */
    @SuppressWarnings({ "unchecked", "resource" })
    public final CorundumPlugin[] loadPluginsFromJar(String file_path) throws CIE {
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
            PluginLoadEvent result = new PluginLoadEvent(currPlugin).runOn(this);

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

    public static String timeStamp() {
        return timeStamp("-", ":");
    }

    public static String timeStamp(String date_separator, String time_separator) {
        return String.valueOf(Calendar.getInstance().get(Calendar.MONTH)) + date_separator + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + date_separator
                + (Calendar.getInstance().get(Calendar.YEAR) - 2000) + " " + (Calendar.getInstance().get(Calendar.HOUR) == 0 ? 12 : Calendar.getInstance().get(Calendar.HOUR))
                + time_separator + Calendar.getInstance().get(Calendar.MINUTE) + time_separator + Calendar.getInstance().get(Calendar.SECOND)
                + (Calendar.getInstance().get(Calendar.AM_PM) == Calendar.AM ? "a" : "p") + ".m.";
    }

    public boolean isDebugging() {
        return this.debugMode;
    }

    public boolean isVerboselyDebugging() {
        return this.verboseMode;
    }

    public ArgInfo getArgInfo() {
        return this.argInfo;
    }

    // listener management
    @SuppressWarnings("unchecked")
    public boolean startListener(CorundumListener listener) {
        if (enabled_listeners.get(listener.getClass()) != null && ListUtilities.contains(enabled_listeners.get(listener.getClass()), listener))
            return false;
        else {
            enabled_listeners.get(listener.getClass()).add(listener);
            return true;
        }
    }

    public CorundumListener[] getListeners() {
        return (CorundumListener[]) enabled_listeners.values().toArray();
    }

    @SuppressWarnings("unchecked")
    public <E extends Event<E>> CorundumListener[] getListeners(Class<? extends Event<E>> event) {
        return (CorundumListener[]) enabled_listeners.get(event).toArray();
    }

    public boolean stopListener(CorundumListener listener) {
        if (enabled_listeners.get(listener.getClass()) != null && enabled_listeners.get(listener.getClass()).contains(listener)) {
            enabled_listeners.get(listener.getClass()).remove(listener);
            return true;
        } else
            return false;
    }

    // messenger configuration getters and setters
    public myList<UUID> getDebuggers() {
        return this.debuggers;
    }

    public myList<UUID> getVerboseDebuggers() {
        return this.verbose_debuggers;
    }
}
