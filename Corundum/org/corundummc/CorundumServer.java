package org.corundummc;

import static org.corundummc.utils.StringUtilities.capitalize;

import java.io.File;
import java.util.*;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.EnumDifficulty;

import org.corundummc.entities.living.Player;
import org.corundummc.entities.living.Player.GameMode;
import org.corundummc.exceptions.CIE;
import org.corundummc.exceptions.CorundumException;
import org.corundummc.exceptions.CorundumSecurityException;
import org.corundummc.hub.CorundumHub;
import org.corundummc.hub.CorundumServerBackend;
import org.corundummc.hub.CorundumThread;
import org.corundummc.listeners.CommandListener;
import org.corundummc.listeners.CorundumListener;
import org.corundummc.listeners.ListenerCaller;
import org.corundummc.listeners.results.EventResult;
import org.corundummc.plugins.CorundumPlugin;
import org.corundummc.plugins.PluginThread;
import org.corundummc.utils.ListUtilities;
import org.corundummc.utils.interfaces.Commander;
import org.corundummc.utils.interfaces.MCEquivalent;
import org.corundummc.utils.myList.myList;
import org.corundummc.utils.types.IDedType;
import org.corundummc.utils.versioning.Version;

/** This class represents the entirety of a Corundum server.
 * 
 * @author REALDrummer */
public class CorundumServer implements Commander, MCEquivalent<CorundumServerBackend> {
    /** This {@link OperatingSystem} represents the operating system is currently running on. */
    public final OperatingSystem OS = OperatingSystem.getFromName(System.getProperty("os.name"));

    private final CorundumServerBackend BACKEND;

    /** This list contains all the currently loaded {@link CorundumPlugin}s on the server. Note that loading and unloading plugins will add or remove them from this list,
     * respectively, but enabling or disabling them will <i>not</i> affect this list. */
    public myList<CorundumPlugin> plugins = new myList<CorundumPlugin>() {
        @Override
        public int compare(CorundumPlugin plugin1, CorundumPlugin plugin2) {
            return ListUtilities.compare(getSortPriorities(plugin1), getSortPriorities(plugin2));
        }

        private Object[] getSortPriorities(CorundumPlugin plugin) {
            return new Object[] { plugin.isEnabled(), plugin.getName(), plugin.getVersion() };
        }
    };

    /** This {@link myList} keeps track of the players who are currently in debugging mode; <b>null</b> repesents {@link Corundum#CONSOLE the console} while non-<b>null</b>
     * {@link java.util.UUID}s represent players. Note that {@link #verbose_debuggers verbose debuggers} also appear in this list in addition to appearing in
     * {@link #verbose_debuggers the verbose debuggers list}. */
    private myList<UUID> debuggers = new myList<>();

    /** This {@link myList} keeps track of the players who are currently in verbose debugging mode; <b>null</b> represents {@link Corundum#CONSOLE the console} while
     * non-<b>null</b> {@link UUID}s represent players. Note that all players (+ {@link Corundum#CONSOLE console}) who are in verbose debugging mode are also in regular
     * debugging mode and are also in the {@link #debuggers debuggers list}. */
    private myList<UUID> verbose_debuggers = new myList<>();

    /** This constructor creates a new {@link CorundumServer}, which extends Minecraft's {@link DedicatedServer} class, allowing it to change some of Minecraft's behaviors.
     * Through {@link DedicatedServer}'s constructor, it will also set {@link MinecraftServer#mcServer} to this new server. <br>
     * <b><i><u>WARNING</b></i></u>: There should only ever be one of these! You can use its instance from {@link CorundumServer#SERVER}.
     * 
     * @param name
     *            is the name of this {@link CorundumServer}. */
    public CorundumServer(String name) {
        BACKEND = new CorundumServerBackend(name);
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

        protected Difficulty() {
            super(values() == null ? 0 : values().length);
        }

        // overridden utilities
        @Override
        public String getName() {
            return new ChatComponentTranslation(EnumDifficulty.values()[getID()].getDifficultyResourceKey(), new Object[0]).getUnformattedText();
        }

        // pseudo-enum utilities
        public static Difficulty getByID(int id) {
            return getByID(Difficulty.class, id);
        }

        public static Difficulty[] values() {
            return values(Difficulty.class);
        }
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

        BACKEND.addPendingCommand(command, BACKEND);
    }

    public void debug(String message) {
        BACKEND.logDebug(message);

        for (UUID playerUUID : this.debuggers) {
            Player.getByUUID(playerUUID).message(message);
        }

        for (UUID playerUUID : this.verbose_debuggers) {
            Player.getByUUID(playerUUID).message(message);
        }
    }

    public Version getMCVersion() {
        return BACKEND.getMCVersion();
    }

    @Override
    public String getName() {
        return BACKEND.getName();
    }

    public String getPrefix() {
        return BACKEND.getPrefix();
    }

    public Version getVersion() {
        return BACKEND.getVersion();
    }

    public File[] getPluginsFolders() {
        // in the future, this could be configurable for more than one plugins folder
        return BACKEND.getPluginFolders();
    }

    @Override
    public void message(String message) {
        BACKEND.addChatMessage(new ChatComponentText(message));
    }

    // Minecraft utils
    public boolean canGenerateStructures() {
        return BACKEND.canStructuresSpawn();
    }

    public boolean canUseCommandBlocks() {
        return BACKEND.isCommandBlockEnabled();
    }

    public boolean isUsingGUI() {
        return BACKEND.getGuiEnabled() || BACKEND.isUsingCorundumGUI();
    }

    public GameMode getDefaultGameMode() {
        return GameMode.getByID(BACKEND.getGameType().getID());
    }

    public Difficulty getDifficulty() {
        return Difficulty.getByID(BACKEND.getDifficulty().ordinal());
    }

    public String getHostname() {
        return BACKEND.getHostname();
    }

    public int getSpawnProtectionRadius() {
        return BACKEND.getSpawnProtectionSize();
    }

    public boolean hasNether() {
        return BACKEND.getAllowNether();
    }

    public boolean isHardcore() {
        return BACKEND.isHardcore();
    }

    public boolean isUsingMCGUI() {
        return BACKEND.getGuiEnabled();
    }

    public boolean isSnooping() {
        return BACKEND.isSnooperEnabled();
    }

    @Override
    public CorundumServerBackend MC() {
        return BACKEND;
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

    public boolean isDebugging() {
        return BACKEND.isDebugging();
    }

    public boolean isVerboselyDebugging() {
        return BACKEND.isVerboselyDebugging();
    }
}
