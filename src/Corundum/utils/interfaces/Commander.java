package Corundum.utils.interfaces;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import Corundum.CorundumListener;
import Corundum.utils.messaging.MessageReceiver;
import Corundum.world.Location;

public interface Commander extends ICommandSender, MessageReceiver {
    default public void command(String command) {
        CorundumListener.generateEvent(listener -> listener.onCommand(this, command));
    }

    default public IChatComponent func_145748_c_() {
        // TODO
        return null;
    }

    default public ChunkCoordinates getCommandSenderPosition() {
        Location location = getLocation();

        return new ChunkCoordinates(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public Location getLocation();

    default public String getCommandSenderName() {
        return getName();
    }

    public String getName();

    default public World getEntityWorld() {
        return getLocation().getWorld().getMCWorld();
    }

    default public void addChatMessage(IChatComponent p_145747_1_) {
        // TODO Auto-generated method stub
    }

    default public boolean canCommandSenderUseCommand(int p_70003_1_, String p_70003_2_) {
        // TODO Auto-generated method stub
        return false;
    }
}
