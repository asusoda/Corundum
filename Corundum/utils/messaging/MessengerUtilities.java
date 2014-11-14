package Corundum.utils.messaging;

import java.util.Calendar;
import java.util.UUID;

import Corundum.entities.Player;
import Corundum.plugins.CorundumPlugin;
import Corundum.utils.myList.myList;
import net.minecraft.util.ChatComponentText;

// TODO: merge this class into CorundumServer and make the debugger lists non-static for each CorundumServer
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
