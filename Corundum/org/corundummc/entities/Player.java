package org.corundummc.entities;

import java.util.HashMap;
import java.util.UUID;

import org.corundummc.CorundumServer;
import org.corundummc.listeners.CommandListener;
import org.corundummc.listeners.ListenerCaller;
import org.corundummc.listeners.results.EventResult;
import net.minecraft.command.CommandBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldServer;
import org.corundummc.types.IDedType;
import org.corundummc.utils.interfaces.Commander;
import org.corundummc.utils.interfaces.Matchable;
import org.corundummc.utils.myList.myList;
import org.corundummc.world.Location;
import org.corundummc.world.World;

public class Player /* TODO extends LivingEntity */implements Commander, Matchable<Player> {
    private static myList<Player> players = new myList<>();
    private static HashMap<String, Player> players_by_name = new HashMap<>();

    private final EntityPlayerMP playerMC;

    public Player(EntityPlayerMP playerMC) {
        this.playerMC = playerMC;
    }

    // inner classes
    /** This enum class represents the different "game modes" a {@link Player} can be in: {@link GameMode#SURVIVAL Survival Mode}, {@link GameMode#CREATIVE Creative Mode}, and
     * {@link GameMode#ADVENTURE Adventure Mode}.
     * 
     * @author REALDrummer */
    public static class GameMode extends IDedType<GameMode> {
        public static final GameMode SURVIVAL = new GameMode(), CREATIVE = new GameMode(), ADVENTURE = new GameMode();

        // pseudo-enum utilities
        public static GameMode getByID(int id) {
            return getByID(GameMode.class, id);
        }

        public static GameMode[] values() {
            return values(GameMode.class);
        }
    }

    // static utilities
    public static Player getByUUID(UUID uuid) {
        return players.findMatch(uuid);
    }

    // instance utilities
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

        EventResult eventResult = CorundumServer.getInstance().generateEvent(new ListenerCaller<CommandListener, EventResult>() {
            @Override
            public EventResult generateEvent(CommandListener listener, EventResult result) {
                return listener.onCommand(thePlayer, command, result);
            }
        });

        if (!eventResult.isCancelled()
                && this.canCommandSenderUseCommand(((CommandBase) CorundumServer.getInstance().getCommandManager().getCommands().get(command.split(" ")[0].replace("/", "")))
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

    public boolean isOp() {
        return CorundumServer.getInstance().isPlayerOp(this.playerMC.getGameProfile());
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
