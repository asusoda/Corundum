package org.corundummc;

import static org.corundummc.utils.StringUtilities.capitalize;

import java.io.File;
import java.io.IOException;
import java.util.*;
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
import org.corundummc.entities.Player;
import org.corundummc.entities.Player.GameMode;
import org.corundummc.exceptions.CIE;
import org.corundummc.exceptions.CorundumException;
import org.corundummc.exceptions.CorundumSecurityException;
import org.corundummc.hub.Server;
import org.corundummc.hub.CorundumHub;
import org.corundummc.hub.CorundumThread;
import org.corundummc.listeners.CommandListener;
import org.corundummc.listeners.CorundumListener;
import org.corundummc.listeners.ListenerCaller;
import org.corundummc.listeners.results.EventResult;
import org.corundummc.plugins.CorundumPlugin;
import org.corundummc.plugins.MultiplePluginLoader;
import org.corundummc.plugins.PluginThread;
import org.corundummc.types.IDedType;
import org.corundummc.utils.SettingsManager;
import org.corundummc.utils.interfaces.Commander;
import org.corundummc.utils.myList.myList;
import org.corundummc.utils.versioning.Version;

/** This class represents the entirety of a Corundum server.
 * 
 * @author REALDrummer */
public class CorundumServer extends DedicatedServer implements Server, Commander {
    private static final Version VERSION = new Version("pre-α"), MCVERSION = new Version("1.7.10");

    /** This {@link OperatingSystem} represents the operating system is currently running on. */
    public static final OperatingSystem OS = OperatingSystem.getFromName(System.getProperty("os.name"));
    private final File PLUGINS_FOLDER = new File("plugins");

    /** This list contains all the currently loaded {@link CorundumPlugin}s on the server. Note that loading and unloading plugins will add or remove them from this list,
     * respectively, but enabling or disabling them will <i>not</i> affect this list. */
    public myList<CorundumPlugin> plugins = new myList<>();

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

    public static final String CONFIG_FILE_NAME = "settings.json";

    // config variable names start

    /** This is the variable name for changing the name of the server */
    public static final String SERVER_NAME_VAR = "name";

    //config variable names end

    private SettingsManager settings = new SettingsManager(new File(CONFIG_FILE_NAME), SERVER_NAME_VAR, "Corundum");

    /** This constructor creates a new {@link CorundumServer}, which extends Minecraft's {@link DedicatedServer} class, allowing it to change some of Minecraft's behaviors.
     * Through {@link DedicatedServer}'s constructor, it will also set {@link MinecraftServer#mcServer} to this new server. <br>
     * <b><i><u>WARNING</b></i></u>: There should only ever be one of these! You can use its instance from {@link CorundumServer#SERVER}. */
    public CorundumServer() {
        // this DedicatedServer constructor sets up the server and sets MinecraftServer.mcServer to this
        super(new File("."));
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
        public static final Difficulty PEACEFUL = new Difficulty(), EASY = new Difficulty(), NORMAL = new Difficulty(), HARD = new Difficulty();

        /** This method returns the {@link Difficulty} associated with the given I.D.
         * 
         * @param id
         *            is the I.D. of the {@link Difficulty} to search for.
         * @return the {@link Difficulty} with the give I.D. of <b>null</b> if no {@link Difficulty} has the given I.D. */
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

        File pluginsDir = new File("plugins");

        if (!pluginsDir.exists() || !pluginsDir.isDirectory()) {
            pluginsDir.mkdir();
        }

        try {
            new MultiplePluginLoader(this, pluginsDir).loadPlugins();
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

    public ServerConfigurationManager getServerConfigurationManager() {
        return super.getConfigurationManager();
    }

    public boolean isPlayerOp(GameProfile playerProfile) {
        return this.getServerConfigurationManager().func_152596_g(playerProfile);
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

        for (EntityPlayerMP player : (List<EntityPlayerMP>) super.getEntityWorld().playerEntities)
            new Player(player).message(message);
    }

    @Override
    public void command(final String command) {
        final Commander _this = this;
        EventResult result = generateEvent(new ListenerCaller<CommandListener, EventResult>() {
            @Override
            public EventResult generateEvent(CommandListener listener, EventResult result) {
                return listener.onCommand(_this, command, result);
            }
        });

        // if the event was cancelled, we're done here
        if (result.isCancelled())
            return;

        // if the event wasn't cancelled, broadcast the message and execute the command
        if (result.getServerMessage() != null)
            broadcast(result.getServerMessage());

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
    public PlayerProfileCache getPlayerProfileCache() {
        return super.func_152358_ax();
    }

    public File getPluginsFolder() {
        return PLUGINS_FOLDER;
    }

    @Override
    public void message(String message) {
        super.addChatMessage(new ChatComponentText(message));
    }

    // listener handling
    /** This method is used to generate an event that is run through all {@link CorundumListener}s on the server. <b><i>NOTE:</b></i> this method is intended for use by
     * Corundum, not plugin developers; we highly recommend that plugin developers generate events by actually causing the event to happen.
     * 
     * @param caller
     *            is a {@link ListenerCaller} that calls the appropriate listener method with arguments that properly represent the event. This method is set up to work
     *            perfectly with anonymous classes, kind of like this:<br>
     * 
     *            <pre>
     * {@link CorundumServer}.{@link CorundumServer#generateEvent(ListenerCaller) generateEvent}(<b>new</b> {@link ListenerCaller}() {
     *                <b>public boolean</b> {@link ListenerCaller#generateEvent(CorundumListener, org.corundummc.listeners.results.EventResult) generateEvent}({@link ListenerCaller} caller) {
     *                    <i>// do stuff in here</i>
     *                }
     *            })
     * </pre>
     * @return the {@link EventResult} resulting from passes through the last listener method. */
    @SuppressWarnings("unchecked")
    public EventResult generateEvent(@SuppressWarnings("rawtypes") ListenerCaller caller) {
        EventResult result = null;
        for (CorundumPlugin plugin : plugins)
            if (plugin.isEnabled())
                for (CorundumListener listener : plugin.getListeners())
                    try {
                        result = caller.generateEvent(listener, result);
                    } catch (CorundumException exception) {
                        exception.err();
                    }
        return result;
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

    // messenger configuration getters and setters
    public myList<UUID> getDebuggers() {
        return this.debuggers;
    }

    public myList<UUID> getVerboseDebuggers() {
        return this.verbose_debuggers;
    }
}
