package Corundum.entities;

import Corundum.exceptions.UnfinishedException;
import Corundum.utils.interfaces.Commander;
import Corundum.utils.messaging.MessageReceiver;
import Corundum.world.Location;

public class Player extends Mob implements Commander, MessageReceiver {
    private final net.minecraft.entity.player.EntityPlayerMP playerMC;

    public Player(net.minecraft.entity.player.EntityPlayerMP playerMC) {
        this.playerMC = playerMC;
    }

    public void addChatMessage(net.minecraft.util.IChatComponent message) {
        playerMC.addChatMessage(message);
    }

    public Location getLocation() {
        return new Location(playerMC.getCommandSenderPosition().posX, playerMC.getCommandSenderPosition().posY, playerMC.getCommandSenderPosition().posZ, Corundum.world.World
                .fromMCWorld((net.minecraft.world.WorldServer) playerMC.worldObj));
    }

    public boolean canCommandSenderUseCommand(int permission_level, String command) {
        return playerMC.canCommandSenderUseCommand(permission_level, command);
    }

    public net.minecraft.util.IChatComponent func_145748_c_() {
        return playerMC.func_145748_c_();
    }

    public String getName() {
        return playerMC.getCommandSenderName();
    }

    @Override
    public void sendMessage(String message) {
        throw new UnfinishedException("Player.sendMessage()");
    }

}
