package org.corundummc.entities.living;

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

/* TODO: it would be better to later make this Player class an interface and have two types of Player, an OnlinePlayer and an OfflinePlayer, since OnlinePlayers will have lots
 * of properties that OfflinePlayers will not */
public class Player extends PlayerEntity implements Commander, Matchable<Player> {
    private static myList<Player> players = new myList<>(), online_players = new myList<>(),
    // TODO: figure out where to find and how to use offline Player data
            offline_players = new myList<>();
    private static HashMap<UUID, Player> players_by_UUID = new HashMap<>();

    Player(EntityPlayerMP playerMC) {
        super(playerMC);
    }

    // inner classes
    /** This enum class represents the different "game modes" a {@link Player} can be in: {@link GameMode#SURVIVAL Survival Mode}, {@link GameMode#CREATIVE Creative Mode}, and
     * {@link GameMode#ADVENTURE Adventure Mode}.
     * 
     * @author REALDrummer */
    public static class GameMode extends IDedType<GameMode> {
        public static final GameMode SURVIVAL = new GameMode(), CREATIVE = new GameMode(), ADVENTURE = new GameMode(), SPECTATOR = new GameMode(), HARDCORE = new GameMode();

        private GameMode() {
            super(values() == null ? 0 : values().length);
        }

        // pseudo-enum utilities
        public static GameMode getByID(int id) {
            return getByID(GameMode.class, id);
        }

        public static GameMode[] values() {
            return values(GameMode.class);
        }
    }

    // static utilities
    public static Player getByName(String name) {
        /* This method takes advantage of the matching algorithms implemented for myLists, specifically the one for Strings. The myList sorting algorithm will use Player's
         * getSortPriorities(), which should return the player's name, to sort players in the list by their names. Then, the matching methods of myList will use the
         * MatchUtilities to find the "matching" name, which will be the shortest name that case-INsensitively starts with the given String. Also note that it will return the
         * shortest name, which is important because 1) shorter Strings come before longer ones when ordered lexicographically and 2) for autocompletion purposes, it's
         * important that the autocompleter return the shortest matching name so that if someone else has a name that starts with that String, the autocompleter can still find
         * the shorter one and not just constantly auto-complete to the longer one and give the user no way to specify the shorter name.
         * 
         * Bottom line: MyList can find the Player with a name that best "matches" the given name in O(lg(n)) time! See `MatchUtilities.match()` for more info. */
        return players.findMatch(name);
    }

    public static Player getByExactName(String name) {
        Player player = getByName(name);

        /* Player.getByName() actually finds the player with a name that "matches" the given name (see getByName() Javadoc), so only return the player if their name exactly
         * matches the given name; otherwise, return null. */
        if (player != null && player.getName().equals(name))
            return player;
        else
            return null;
    }

    public static Player getByUUID(UUID uuid) {
        return players_by_UUID.get(uuid);
    }

    public static myList<Player> getOfflinePlayers() {
        return offline_players;
    }

    public static myList<Player> getOnlinePlayers() {
        return online_players;
    }

    public static myList<Player> getPlayers() {
        return players;
    }

    // instance utilities
    @Override
    public void command(final String command) {
        final Player _this = this;

        EventResult eventResult = CorundumServer.getInstance().generateEvent(new ListenerCaller<CommandListener, EventResult>() {
            @Override
            public EventResult generateEvent(CommandListener listener, EventResult result) {
                return listener.onCommand(_this, command, result);
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

    public Location getLocation() {
        return new Location(getEntityMC().getCommandSenderPosition().posX, getEntityMC().getCommandSenderPosition().posY, getEntityMC().getCommandSenderPosition().posZ, World
                .fromMCWorld((WorldServer) getEntityMC().worldObj));
    }

    @Override
    public String getName() {
        return getEntityMC().getCommandSenderName();
    }

    /** This method returns the {@link UUID} associated with this {@link Player}. Every {@link Player} in Minecraft has a {@link UUID}, a
     * "<u>U</u>niversally <u>U</u>nique <u>ID</u>" to differentiate them from all other players even if they change their usernames. <i>You should use {@link UUID}s to
     * identify {@link Player}s, not their usernames, whenever possible!</i>
     * 
     * @return the {@link UUID} associated with this {@link Player}. */
    public UUID getUUID() {
        return getEntityMC().getUniqueID();
    }

    @Override
    public void message(String message) {
        this.addChatMessage(new ChatComponentText(message));
    }

    // overridden properties
    @Override
    public void addChatMessage(IChatComponent message) {
        getEntityMC().addChatMessage(message);
    }

    @Override
    public boolean canCommandSenderUseCommand(int permission_level, String command) {
        return getEntityMC().canCommandSenderUseCommand(permission_level, command);
    }

    public boolean isOp() {
        return CorundumServer.getInstance().getConfigurationManager().func_152596_g(this.getEntityMC().getGameProfile());
    }

    @Override
    public String getCommandSenderName() {
        return getEntityMC().getCommandSenderName();
    }

    @Override
    public ChunkCoordinates getCommandSenderPosition() {
        return getEntityMC().getCommandSenderPosition();
    }

    @Override
    public net.minecraft.world.World getEntityWorld() {
        return getEntityMC().getEntityWorld();
    }

    @Override
    public IChatComponent func_145748_c_() {
        return getEntityMC().func_145748_c_();
    }

    // data management overrides
    @Override
    public Object[] getSortPriorities() {
        return new Object[] { getName() };
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Player && getEntityMC().getUniqueID().equals(((Player) object).getEntityMC().getUniqueID());
    }

    @Override
    public String toString() {
        return getName();
    }
}
