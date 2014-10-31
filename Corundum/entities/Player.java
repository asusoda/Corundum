package Corundum.entities;

import java.util.UUID;

import Corundum.Corundum;
import Corundum.listeners.CommandListener;
import Corundum.listeners.ListenerCaller;
import Corundum.listeners.results.EventResult;
import net.minecraft.command.CommandBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldServer;
import Corundum.items.Item;
import Corundum.utils.ListUtilities;
import Corundum.utils.interfaces.Commander;
import Corundum.utils.interfaces.IDedType;
import Corundum.utils.interfaces.Matchable;
import Corundum.world.Location;
import Corundum.world.World;

public class Player extends Mob implements Commander, Matchable<Player> {
    private final EntityPlayerMP playerMC;

    public Player(EntityPlayerMP playerMC) {
        super(MobType.PLAYER, new Location(playerMC.lastTickPosX, playerMC.lastTickPosY, playerMC.lastTickPosZ, World.fromMCWorld((WorldServer) playerMC.worldObj)), Item
                .fromMCItems((ItemStack[]) ListUtilities.concatenate(playerMC.inventory.mainInventory, playerMC.inventory.armorInventory)));

        this.playerMC = playerMC;
    }

    /** This enum class represents the different "game modes" a {@link Player} can be in: {@link GameMode#SURVIVAL Survival Mode}, {@link GameMode#CREATIVE Creative Mode}, and
     * {@link GameMode#ADVENTURE Adventure Mode}.
     * 
     * @author REALDrummer */
    public enum GameMode implements IDedType<GameMode> {
        SURVIVAL, CREATIVE, ADVENTURE;

        @Override
        public short getID() {
            return (short) ordinal();
        }

        /** This method returns the {@link GameMode} associated with the given I.D.
         * 
         * @param id
         *            is the I.D. of the {@link GameMode} to search for.
         * @return the {@link GameMode} with the give I.D. of <b>null</b> if no {@link GameMode} has the given I.D. */
        public static GameMode getByID(int id) {
            if (id < 0 || id >= values().length)
                return null;
            else
                return values()[id];
        }
    }

    @Override
    public Location getLocation() {
        return new Location(playerMC.getCommandSenderPosition().posX, playerMC.getCommandSenderPosition().posY, playerMC.getCommandSenderPosition().posZ, World
                .fromMCWorld((WorldServer) playerMC.worldObj));
    }

    /** This method returns the {@link UUID} associated with this {@link Player}. Every {@link Player} in Minecraft has a {@link UUID}, a
     * "<u>U</u>niversally <u>U</u>nique <u>ID</u>" to differentiate them from all other players even if they change their usernames. <i>You should use {@link UUID}s to
     * identify {@link Player}s, not their usernames, whenever possible!</i>
     * 
     * @return the {@link UUID} associated with this {@link Player}. */
    public UUID getUUID() {
        return playerMC.getUniqueID();
    }

    // Commander overrides
    @Override
    public void command(final String command) {
        final Player thePlayer = this;

        EventResult eventResult = Corundum.SERVER.generateEvent(new ListenerCaller<CommandListener, EventResult>() {
            @Override
            public EventResult generateEvent(CommandListener listener, EventResult result) {
                return listener.onCommand(thePlayer, command, result);
            }
        });

        if (!eventResult.isCancelled()
                && this.canCommandSenderUseCommand(((CommandBase) Corundum.SERVER.getCommandManager().getCommands().get(command.split(" ")[0].replace("/", "")))
                        .getRequiredPermissionLevel(), command)) {
            CommandBase
                    .func_147176_a(this /* command executor */, command.split(" ") /* space-delimited arguments */, 0 /* the number of parameters to skip */, true /* TODO: I
                                                                                                                                                                    * don't
                                                                                                                                                                    * actually
                                                                                                                                                                    * know what
                                                                                                                                                                    * this does */);
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

    // overridden properties
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
        return playerMC.getCommandSenderPosition();
    }

    @Override
    public net.minecraft.world.World getEntityWorld() {
        return playerMC.getEntityWorld();
    }

    @Override
    public IChatComponent func_145748_c_() {
        return playerMC.func_145748_c_();
    }

    // data management overrides
    @Override
    public Object[] getSortPriorities() {
        return new Object[] { getUUID() };
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Player && playerMC.getUniqueID().equals(((Player) object).playerMC.getUniqueID());
    }

    @Override
    public String toString() {
        return getName();
    }
}
