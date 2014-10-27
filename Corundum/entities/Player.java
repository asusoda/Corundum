package Corundum.entities;

import java.util.UUID;

import Corundum.Corundum;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandHandler;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldServer;
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

    @Override
    public Location getLocation() {
        return new Location(playerMC.getCommandSenderPosition().posX, playerMC.getCommandSenderPosition().posY, playerMC.getCommandSenderPosition().posZ, World
                .fromMCWorld((WorldServer) playerMC.worldObj));
    }

    public UUID getUUID() {
        return playerMC.getUniqueID();
    }

    // Commander overrides
    @Override
    public void command(String command) {
        if (this.canCommandSenderUseCommand(((CommandBase) Corundum.SERVER.getCommandManager().getCommands().get(command.split(" ")[0].replace("/", ""))).getRequiredPermissionLevel(), command)) {
            CommandBase.func_147176_a(
                    this /* command executor */, command.split(" ") /* space-delimited arguments */, 0 /* the number of parameters to skip */, true /* TODO: I don't
                                                                                                                                                                * actually know
                                                                                                                                                                * what this
                                                                                                                                                                * does */);
        }
    }

    @Override
    public String getName() {
        return playerMC.getCommandSenderName();
    }

    @Override
    public void message(String message) {
        this.addChatMessage(new ChatComponentText(message));
    }

    // ICommandSender implementations
    @Override
    public void addChatMessage(IChatComponent message) {
        playerMC.addChatMessage(message);
    }

    @Override
    public boolean canCommandSenderUseCommand(int permission_level, String command) {
        return playerMC.canCommandSenderUseCommand(permission_level, command);
    }

    @Override
    public String getCommandSenderName() {
        return playerMC.getCommandSenderName();
    }

    @Override
    public ChunkCoordinates getCommandSenderPosition() {
        return new ChunkCoordinates(playerMC.chunkCoordX, playerMC.chunkCoordY, playerMC.chunkCoordZ);
    }

    @Override
    public net.minecraft.world.World getEntityWorld() {
        return playerMC.getEntityWorld();
    }

    @Override
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
