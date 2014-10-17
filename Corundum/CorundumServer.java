package Corundum;

import java.io.File;

import net.minecraft.server.dedicated.DedicatedServer;
import Corundum.utils.interfaces.Commander;
import Corundum.utils.messaging.MessageReceiver;
import Corundum.world.Location;

public class CorundumServer extends DedicatedServer implements MessageReceiver, Commander {
    /** This constructor creates a new {@link CorundumServer}, which extends Minecraft's {@link DedicatedServer} class, allowing it to change some of Minecraft's behaviors.
     * Through {@link DedicatedServer}'s constructor, it will also set {@link net.minecraft.server.MinecraftServer#mcServer} to this new server. <br>
     * <b><i><u>WARNING</b></i></u>: There should only ever be one of these! You can use its instance from {@link Corundum#SERVER}.
     *
     * @param file_path
     *            is the path of the file from which this server should be loaded. */
    public CorundumServer(String file_path) {
        // this DedicatedServer constructor sets up the server and sets MinecraftServer.mcServer to this
        super(new File(file_path));
    }

    public String getName() {
        return getHostname();
    }

    public Location getLocation() {
        return null;
    }

    public void sendMessage(String message) {
        logInfo(message);
    }

}
