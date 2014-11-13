package Corundum.utils.messaging;

import java.util.Calendar;
import java.util.UUID;

import Corundum.Corundum;
import Corundum.entities.Player;
import Corundum.plugins.CorundumPlugin;
import Corundum.utils.myList.myList;
import net.minecraft.util.ChatComponentText;

public class MessengerUtilities {
    /** This {@link myList} keeps track of the players who are currently in debugging mode; <b>null</b> repesents {@link Corundum#CONSOLE the console} while non-<b>null</b>
     * {@link UUID}s represent players. Note that {@link #verbose_debuggers verbose debuggers} also appear in this list in addition to appearing in {@link #verbose_debuggers
     * the verbose debuggers list}. */
    private static myList<UUID> debuggers = new myList<UUID>();
    /** This {@link myList} keeps track of the players who are currently in verbose debugging mode; <b>null</b> represents {@link Corundum#CONSOLE the console} while
     * non-<b>null</b> {@link UUID}s represent players. Note that all players (+ {@link Corundum#CONSOLE console}) who are in verbose debugging mode are also in regular
     * debugging mode and are also in the {@link #debuggers debuggers list}. */
    private static myList<UUID> verbose_debuggers = new myList<UUID>();

    // messenger configuration getters and setters
    public static myList<UUID> getDebuggers() {
        return debuggers;
    }

    public static myList<UUID> getVerboseDebuggers() {
        return verbose_debuggers;
    }

    // non-plugin-specific messaging utilities
    /** This method sends the given message to the console, players, and logs that are in "verbose debugging mode". These messages can contain very detailed information
     * relevant to debugging, unlike the regular {@link #debug(String) debug() method} which should be given only basic debugging information. Players and the console can enter
     * or exit verbose debugging mode with a command. In addition, if "verbose debug logging" is active, the message will be logged in Corundum's log files.
     * 
     * @param message
     *            is the debug message to send to the verbosely debugging parties.
     * @see {@link Messenger#bloviate(String)}, {@link #setVerboseLogging(boolean)}, and {@link #debug(CorundumPlugin, String)} */
    public static void bloviate(String message) {
        bloviate(null, message);
    }

    /** This method broadcasts the given message to the server, displaying it in the console, in all players' chats, and in the logs.
     * 
     * @param message
     *            is the message to be broadcasted to the server.
     * @see {@link Messenger#broadcast(String)} */
    public static void broadcast(String message) {
        broadcast(null, message);
    }

    /** This method sends the given message to all the players (or console) that are currently in "debugging mode". These messages should contain basic important information
     * relevant to debugging, unlike the verbose {@link #bloviate(String) bloviate() method} which can output large amounts of very detailed information. Players and the
     * console can enter or exit debugging mode with a command. In addition, if "debug logging" is active, the message will be logged in Corundum's log files.
     * 
     * @param message
     *            is the debug message to send to the debugging parties.
     * 
     * @see {@link Messenger#debug(String)}, {@link #setVerboseDebugLogging(boolean)}, and {@link #bloviate(CorundumPlugin, String)} */
    public static void debug(String message) {
        debug(null, message);
    }

    public static void tellConsole(String message) {
        tellConsole(null, message);
    }

    public static void tellPlayer(Player player, String message) {
        player.addChatMessage(new ChatComponentText(message));
    }

    // plugin-specific messaging utilities
    /** This method sends the given message to the console, players, and logs that are in "verbose debugging mode". These messages can contain very detailed information
     * relevant to debugging, unlike the regular {@link #debug(String) debug() method} which should be given only basic debugging information. Players and the console can enter
     * or exit verbose debugging mode with a command. In addition, if "verbose debug logging" is active, the message will be logged in Corundum's log files.
     * 
     * @param plugin
     *            is the plugin that is responsible for sending the message or <b>null</b> if it comes from Corundum itself.
     * @param message
     *            is the debug message to send to the verbosely debugging parties.
     * @see {@link Messenger#bloviate(String)}, {@link #setVerboseLogging(boolean)}, and {@link #debug(CorundumPlugin, String)} */
    public static void bloviate(CorundumPlugin plugin, String message) {
        Corundum.secure();

        if (verbose_debuggers.size() == 0)
            return;

        // send the message to all verbose debuggers
        for (UUID uuid : verbose_debuggers) {
            if (uuid == null)
                tellConsole(plugin, message);
            else
                tellPlayer(plugin, Player.getByUUID(uuid), message);
        }
    }

    /** This method broadcasts the given message to the server, displaying it in the console, in all players' chats, and in the logs.
     * 
     * @param plugin
     *            is the plugin that is responsible for sending the message or <b>null</b> if it comes from Corundum itself.
     * @param message
     *            is the message to be broadcasted to the server.
     * @see {@link Messenger#broadcast(String)} */
    public static void broadcast(CorundumPlugin plugin, String message) {
        Corundum.SERVER.broadcast(plugin.getPrefix() + message);
    }

    /** This method sends the given message to all the players (or console) that are currently in "debugging mode". These messages should contain basic important information
     * relevant to debugging, unlike the verbose {@link #bloviate(String) bloviate() method} which can output large amounts of very detailed information. Players and the
     * console can enter or exit debugging mode with a command. In addition, if "debug logging" is active, the message will be logged in Corundum's log files.
     * 
     * @param plugin
     *            is the plugin that is responsible for sending the message or <b>null</b> if it comes from Corundum itself.
     * 
     * @param message
     *            is the debug message to send to the debugging parties.
     * 
     * @see {@link Messenger#debug(String)}, {@link #setVerboseDebugLogging(boolean)}, and {@link #bloviate(CorundumPlugin, String)} */
    public static void debug(CorundumPlugin plugin, String message) {
        if (debuggers.size() == 0)
            return;
        if (debuggers.contains((UUID) null)) {
            tellConsole(plugin, message);
            if (debuggers.size() == 1)
                return;
        }

        // TODO: when it can be supported, send debug messages to all debugging players
    }

    public static void tellConsole(CorundumPlugin plugin, String message) {
        Corundum.SERVER.message(plugin.getPrefix() + message);
    }

    public static void tellPlayer(CorundumPlugin plugin, Player player, String message) {
        player.message(plugin.getPrefix() + message);
    }

    // other messaging-related utilities
    public static String timeStamp() {
        return timeStamp("-", ":");
    }

    public static String timeStamp(String date_separator, String time_separator) {
        return String.valueOf(Calendar.getInstance().get(Calendar.MONTH)) + date_separator + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + date_separator
                + (Calendar.getInstance().get(Calendar.YEAR) - 2000) + " " + (Calendar.getInstance().get(Calendar.HOUR) == 0 ? 12 : Calendar.getInstance().get(Calendar.HOUR))
                + time_separator + Calendar.getInstance().get(Calendar.MINUTE) + time_separator + Calendar.getInstance().get(Calendar.SECOND)
                + (Calendar.getInstance().get(Calendar.AM_PM) == Calendar.AM ? "a" : "p") + ".m.";
    }
}
