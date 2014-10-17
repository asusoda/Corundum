package Corundum.entities;

import Corundum.exceptions.UnfinishedException;
import Corundum.utils.interfaces.Commander;
import Corundum.utils.messaging.MessageReceiver;
import Corundum.world.Location;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IChatComponent;

public class Player extends Mob implements Commander, MessageReceiver {
    private final EntityPlayerMP playerMC;

    public Player(EntityPlayerMP playerMC) {
        this.playerMC = playerMC;
    }

    @Override
    public void addChatMessage(IChatComponent message) {
        playerMC.addChatMessage(message);
    }

    @Override
    public Location getLocation() {
        return new Location(playerMC.getCommandSenderPosition().posX, playerMC.getCommandSenderPosition().posY, playerMC.getCommandSenderPosition().posZ, Corundum.world.World
                .fromMCWorld((net.minecraft.world.WorldServer) playerMC.worldObj));
    }

    @Override
    public boolean canCommandSenderUseCommand(int permission_level, String command) {
        return playerMC.canCommandSenderUseCommand(permission_level, command);
    }

    @Override
    public IChatComponent func_145748_c_() {
        return playerMC.func_145748_c_();
    }

    @Override
    public String getName() {
        return playerMC.getCommandSenderName();
    }

    @Override
    public void sendMessage(String message) {
        throw new UnfinishedException("Player.sendMessage()");
    }

}
