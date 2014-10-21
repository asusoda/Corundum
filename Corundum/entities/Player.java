package Corundum.entities;

import java.util.UUID;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldServer;
import Corundum.exceptions.UnfinishedException;
import Corundum.items.Item;
import Corundum.utils.ListUtilities;
import Corundum.utils.interfaces.Commander;
import Corundum.utils.interfaces.Matchable;
import Corundum.utils.messaging.MessageReceiver;
import Corundum.world.Location;
import Corundum.world.World;

public class Player extends Mob implements Commander, ICommandSender, MessageReceiver, Matchable<Player> {
    private final EntityPlayerMP playerMC;

    public Player(EntityPlayerMP playerMC) {
        super(MobType.PLAYER, new Location(playerMC.lastTickPosX, playerMC.lastTickPosY, playerMC.lastTickPosZ, World.fromMCWorld((WorldServer) playerMC.worldObj)), Item
                .fromMCItems((ItemStack[]) ListUtilities.concatenate(playerMC.inventory.mainInventory, playerMC.inventory.armorInventory)));

        this.playerMC = playerMC;
    }

    public Location getLocation() {
        return new Location(playerMC.getCommandSenderPosition().posX, playerMC.getCommandSenderPosition().posY, playerMC.getCommandSenderPosition().posZ, Corundum.world.World
                .fromMCWorld((WorldServer) playerMC.worldObj));
    }

    public UUID getUUID() {
        return playerMC.getUniqueID();
    }

    // Commander overrides
    public void command(String command) {
        // TODO: make sure the command isn't executed if the Player doesn't have permission

        CommandBase
                .func_147176_a(this /* command executor */, command.split(" ") /* space-delimited arguments */, 0 /* the number of parameters to skip */, true /* TODO: I don't
                                                                                                                                                                * actually know
                                                                                                                                                                * what this
                                                                                                                                                                * does */);
    }

    public String getName() {
        return playerMC.getCommandSenderName();
    }

    @Override
    public void message(String message) {
        throw new UnfinishedException("Player.sendMessage()");
    }

    // ICommandSender implementations
    public void addChatMessage(IChatComponent message) {
        playerMC.addChatMessage(message);
    }

    public boolean canCommandSenderUseCommand(int permission_level, String command) {
        return playerMC.canCommandSenderUseCommand(permission_level, command);
    }

    public String getCommandSenderName() {
        return playerMC.getCommandSenderName();
    }

    public ChunkCoordinates getCommandSenderPosition() {
        return new ChunkCoordinates(playerMC.chunkCoordX, playerMC.chunkCoordY, playerMC.chunkCoordZ);
    }

    public net.minecraft.world.World getEntityWorld() {
        return playerMC.getEntityWorld();
    }

    public IChatComponent func_145748_c_() {
        return playerMC.func_145748_c_();
    }

    // Matchable override
    @Override
    public Object[] getSortPriorities() {
        return new Object[] { getUUID() };
    }

    // Object overrides
    @Override
    public boolean equals(Object object) {
        return object instanceof Player && playerMC.getUniqueID().equals(((Player) object).playerMC.getUniqueID());
    }

    @Override
    public String toString() {
        return getName();
    }
}
