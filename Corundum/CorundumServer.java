package Corundum;

import java.io.File;
import java.net.SecureCacheResponse;

import org.apache.logging.log4j.Logger;

import Corundum.exceptions.CorundumSecurityException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;

public class CorundumServer extends DedicatedServer {
    /** This constructor creates a new {@link CorundumServer}, which extends Minecraft's {@link DedicatedServer} class, allowing it to change some of Minecraft's behaviors.
     * Through {@link DedicatedServer}'s constructor, it will also set {@link MinecraftServer#mcServer} to this new server. <br>
     * <b><i><u>WARNING</b></i></u>: There should only ever be one of these! You can use its instance from {@link Corundum#SERVER}.
     * 
     * @param file
     *            is the file from which this server should be loaded. */
    public CorundumServer(File file) {
        // this DedicatedServer constructor sets up the server and sets MinecraftServer.mcServer to this
        super(file);
    }

    
}
