package Corundum.utils.interfaces;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import Corundum.listeners.CorundumListener;
import Corundum.utils.messaging.MessageReceiver;
import Corundum.world.Location;

public interface Commander extends MessageReceiver {
    public void command(String command);

    public String getName();
}