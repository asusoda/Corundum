package Corundum;

import java.io.File;
import java.util.List;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import Corundum.exceptions.CorundumException;
import Corundum.listeners.CommandListener;
import Corundum.listeners.CorundumListener;
import Corundum.listeners.ListenerCaller;
import Corundum.listeners.results.EventResult;
import Corundum.utils.interfaces.Commander;
import Corundum.utils.messaging.MessageReceiver;
import Corundum.utils.myList.myList;
import Corundum.world.Location;

public class CorundumServer extends DedicatedServer implements MessageReceiver, Commander {
    /** This list contians all the currently loaded {@link CorundumPlugin}s on the server. Note that loading and unloading plugins will add or remove them from this list,
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

    public void broadcast(String message) {
        super.addChatMessage(new ChatComponentText(message));

        List<? extends Entity> allEntities = super.getEntityWorld().loadedEntityList;

        for (Entity entity : allEntities) {
            if (entity.getClass() == EntityPlayer.class || entity.getClass() == EntityPlayerMP.class) {
                ((EntityPlayer) entity).addChatMessage(new ChatComponentText(message));
            }
        }
    }

    public Location getLocation() {
        return null;
    }

    @Override
    public String getName() {
        return getHostname();
    }

    @Override
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

    // server command handling
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
    public String getCommandSenderName() {
        return toString();
    }

    @Override
    public IChatComponent func_145748_c_() {
        return new ChatComponentText("§dServer");
    }

    @Override
    public void addChatMessage(IChatComponent chatComponent) {
        message(chatComponent.toString());
    }

    @Override
    public boolean canCommandSenderUseCommand(int commandLevel, String command) {
        return super.canCommandSenderUseCommand(commandLevel, command);
    }

    @Override
    public ChunkCoordinates getCommandSenderPosition() {
        return null;
    }

    @Override
    public World getEntityWorld() {
        return super.getEntityWorld();
    }
}
