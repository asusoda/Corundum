package Corundum;

import java.io.File;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import Corundum.exceptions.CorundumException;
import Corundum.listeners.CommandListener;
import Corundum.listeners.CorundumListener;
import Corundum.listeners.ListenerCaller;
import Corundum.utils.interfaces.Commander;
import Corundum.utils.messaging.MessageReceiver;
import Corundum.utils.myList.myList;
import Corundum.world.Location;

public class CorundumServer extends DedicatedServer implements MessageReceiver, Commander {
    /** This list contians all the currently loaded {@link CorundumPlugin}s on the server. Note that loading and unloading plugins will add or remove them from this list,
     * respectively, but enabling or disabling them will <i>not</i> affect this list. */
    public static myList<CorundumPlugin> plugins = new myList<CorundumPlugin>();

    /** This constructor creates a new {@link CorundumServer}, which extends Minecraft's {@link DedicatedServer} class, allowing it to change some of Minecraft's behaviors.
     * Through {@link DedicatedServer}'s constructor, it will also set {@link MinecraftServer#mcServer} to this new server. <br>
     * <b><i><u>WARNING</b></i></u>: There should only ever be one of these! You can use its instance from {@link Corundum#SERVER}.
     * 
     * @param loader
     *            is the class loader used to load the server from its jar file.
     * @param file_path
     *            is the path of the file from which this server should be loaded. */
    public CorundumServer(String file_path) {
        // this DedicatedServer constructor sets up the server and sets MinecraftServer.mcServer to this
        super(new File(file_path));
    }

    public Location getLocation() {
        return null;
    }

    public String getName() {
        return getHostname();
    }

    public void message(String message) {
        logInfo(message);
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
     * @return <b>true</b> if a cancellation of the event was requested; <b>false</b> otherwise. */
    public static CorundumListener generateEvent(ListenerCaller caller) {
        for (CorundumPlugin plugin : plugins)
            if (plugin.isEnabled())
                for (CorundumListener listener : plugin.getListeners())
                    try {
                        /* if the listener method called by the event returned true, return this listener to indicate that this CorundumListener cancelled the event */
                        if (caller.generateEvent(listener))
                            return listener;
                    } catch (CorundumException exception) {
                        exception.err();
                    }

        /* if the event gets through all the listener methods and doesn't get cancelled, return null to indicate that no listeners cancelled the event */
        return null;
    }

    // server command handling
    @Override
    public void command(final String command) {
        final Commander _this = this;
        generateEvent(new ListenerCaller<CommandListener>() {
            @Override
            public boolean generateEvent(CommandListener listener) {
                return listener.onCommand(_this, command);
            }
        });
    }
}
