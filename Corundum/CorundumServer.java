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
    /** Whether or not the server is running in debug mode - basically if the string --debug is passed to this server's
     * {@link #start(String[])} method.
     */
    private boolean debugMode;

    /** Whether or not the server is running in verbose mode. Only true if --verbose is passed to {@link #start(String[])} and --no-verbose isn't.
     */
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
        public static Difficulty getByID(int id) {
            return values()[id];
        }
    }

    /** This method starts this {@link CorundumServer}.
     * 
     * @param arguments
     *            are the command-line arguments used to configure the properties of this server on startup. */
    public void start(String[] arguments) {
        //To make arg reading easier.
        List<String> args = Arrays.asList(arguments);
        // --no-debug takes priority.
        this.debugMode = args.contains("--no-debug") ? false : args.contains("--debug") || args.contains("-d");
        this.verboseMode = args.contains("--no-verbose") ? false : args.contains("--verbose");

        try {
            super.startServer();
        } catch (IOException exception) {
            CIE.err("There was a problem starting this Corundum server!", exception, "This may have something to do with the Minecraft YGGDrasil authentication setup.");
        } catch (Exception exception) {
            CIE.err("There was a problem starting this Corundum server!", exception);
        }

        //Vanilla property setting is after the server is started-started as, otherwise, the properties gotten from
        //server.properties takes priority.
        if (args.contains("--online-mode") || args.contains("-o")) {
            super.setProperty("online-mode", true);
        } else if (args.contains("--offline-mode") || args.contains("-O")) {
            super.setProperty("online-mode", false);
        }

        if (args.contains("--world")) {
            super.setProperty("level-name", this.getArgValue("--world", args));
        } else {
            super.setProperty("level-name", "world");
        }
    }

    public String getArgValue(String argName, List<String> args) {
        for (int i = 0; i == args.size(); i++) {
            if (args.get(i).equals(argName)) {
                String potentialValue = args.get(i + 1);

                if (!potentialValue.startsWith("--")) {
                    return potentialValue;
                } else {
                    CorundumException.err("Arg " + argName + " doesn't have a value!", "The arg " + argName + " had it's value requested, however, the value directly after it in the args list was another value!");
                }
            }
        }

        throw new CorundumException("Arg " + argName + " does not exist in the args list!", "The argument " + argName + "'s value was requested to be found, but the arg itself does not exist!");
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
        // This method is necessary because super.isHardcore() will be obfuscated!
        return super.canStructuresSpawn();
    }

    public GameMode getDefaultGameMode() {
        return GameMode.getByID(getGameType().getID());
    }

    public Difficulty getDifficulty() {
        return Difficulty.getByID(func_147135_j().func_151525_a());
    }

    public boolean hasNether() {
        return super.getAllowNether();
    }

    public boolean isHardcore() {
        // This method is necessary because super.isHardcore() will be obfuscated!
        return super.isHardcore();
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
     *                <b>public boolean</b> {@link ListenerCaller#generateEvent(CorundumListener) generateEvent}({@link ListenerCaller} caller) {
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
}
