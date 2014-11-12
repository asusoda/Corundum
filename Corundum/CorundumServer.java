package Corundum;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.util.ChatComponentText;
import Corundum.entities.Player;
import Corundum.entities.Player.GameMode;
import Corundum.exceptions.CIE;
import Corundum.exceptions.CorundumException;
import Corundum.listeners.CommandListener;
import Corundum.listeners.CorundumListener;
import Corundum.listeners.ListenerCaller;
import Corundum.listeners.results.EventResult;
import Corundum.utils.interfaces.Commander;
import Corundum.utils.interfaces.IDedType;
import Corundum.utils.myList.myList;

/** This class represents the entirety of a Corundum server.
 * 
 * @author REALDrummer */
public class CorundumServer extends DedicatedServer implements Commander {
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

    /** This list contains all the currently loaded {@link CorundumPlugin}s on the server. Note that loading and unloading plugins will add or remove them from this list,
     * respectively, but enabling or disabling them will <i>not</i> affect this list. */
    public myList<CorundumPlugin> plugins = new myList<CorundumPlugin>();

    /** This constructor creates a new {@link CorundumServer}, which extends Minecraft's {@link DedicatedServer} class, allowing it to change some of Minecraft's behaviors.
     * Through {@link DedicatedServer}'s constructor, it will also set {@link MinecraftServer#mcServer} to this new server. <br>
     * <b><i><u>WARNING</b></i></u>: There should only ever be one of these! You can use its instance from {@link Corundum#SERVER}.
     *
     * @param file_path
     *            is the path of the file from which this server should be loaded. */
    public CorundumServer(String file_path) {
        // this DedicatedServer constructor sets up the server and sets MinecraftServer.mcServer to this
        super(new File(file_path));
    }

    public enum Difficulty implements IDedType<Difficulty> {
        PEACEFUL, EASY, NORMAL, HARD;

        @Override
        public short getID() {
            return (short) ordinal();
        }

        /** This method returns the {@link Difficulty} associated with the given I.D.
         * 
         * @param id
         *            is the I.D. of the {@link Difficulty} to search for.
         * @return the {@link Difficulty} with the give I.D. of <b>null</b> if no {@link Difficulty} has the given I.D. */
        public static Difficulty getByIDHelper(int id) {
            return values()[id];
        }
    }

    /** This method starts this {@link CorundumServer}.
     * 
     * @param arguments
     *            are the command-line arguments used to configure the properties of this server on startup. */
    public void start(String[] arguments) {
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

    // obfuscated DedicatedServer method renaming
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
        return Difficulty.getByIDHelper(func_147135_j().func_151525_a());
    }

    public int getSpawnRadius() {
        return getSpawnProtectionSize();
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

    // property overrides
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

    @Override
    public String getName() {
        return getHostname();
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
     * {@link Corundum}.{@link Corundum#generateEvent(ListenerCaller) generateEvent}(<b>new</b> {@link ListenerCaller}() {
     *                <b>public boolean</b> {@link ListenerCaller#generateEvent(CorundumListener, Corundum.listeners.results.EventResult) generateEvent}({@link ListenerCaller} caller) {
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

    public boolean getIsDebugMode() {
        return this.debugMode;
    }

    public boolean getIsVerboseMode() {
        return this.verboseMode;
    }

    public ArgInfo getArgInfo() {
        return this.argInfo;
    }
}
