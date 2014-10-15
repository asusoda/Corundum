package Corundum.utils.messaging;

import java.util.Calendar;
import Corundum.Corundum;
import Corundum.CorundumPlugin;
import Corundum.entities.Player;

public interface Messenger {
    /** This method sends the given message to the console, players, and logs that are in "verbose debugging mode". These messages can contain very detailed information
     * relevant to debugging, unlike the regular {@link #debug(String) debug() method} which should be given only basic debugging information. Players and the console can enter
     * or exit verbose debugging mode with a command. In addition, if "verbose debug logging" is active, the message will be logged in Corundum's log files.
     * 
     * @param message
     *            is the debug message to send to the verbosely debugging parties.
     * @see {@link #debug(String)} */
    default public void bloviate(String message) {
        if (getPlugin() == null)
            Corundum.secure("No CorundumPlugin's Messengers should return null for a plugin!");

        MessengerUtilities.bloviate(getPlugin(), message);
    }

    /** This method broadcasts the given message to the server, displaying it in the console, in all players' chats, and in the logs.
     * 
     * @param message
     *            is the message to be broadcasted to the server.
     * @see {@link Messenger#broadcast(String)} */
    default public void broadcast(String message) {
        if (getPlugin() == null)
            Corundum.secure("No CorundumPlugin's Messengers should return null for a plugin!");

        tellConsole(message);

        // TODO: when supportable, send the message to all players on the server
    }

    /** This method sends the given message to all the players (or console) that are currently in "debugging mode". These messages should contain basic important information
     * relevant to debugging, unlike the verbose {@link #bloviate(String) bloviate() method} which can output large amounts of very detailed information. Players and the
     * console can enter or exit debugging mode with a command. In addition, if "debug logging" is active, the message will be logged in Corundum's log files.
     * 
     * @param message
     *            is the debug message to send to the debugging parties.
     * 
     * @see {@link #bloviate(String)} */
    default public void debug(String message) {
        MessengerUtilities.debug(getPlugin(), message);
    }

    default public void tellConsole(String message) {
        MessengerUtilities.tellConsole(getPlugin(), message);
    }

    default public void tellPlayer(Player player, String message) {
        MessengerUtilities.tellPlayer(getPlugin(), player, message);
    }

    default public String timeStamp() {
        return timeStamp('-', ':');
    }

    default public String timeStamp(char date_separator, char time_separator) {
        return String.valueOf(Calendar.getInstance().get(Calendar.MONTH)) + date_separator + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + date_separator
                + (Calendar.getInstance().get(Calendar.YEAR) - 2000) + " " + (Calendar.getInstance().get(Calendar.HOUR) == 0 ? 12 : Calendar.getInstance().get(Calendar.HOUR))
                + time_separator + Calendar.getInstance().get(Calendar.MINUTE) + time_separator + Calendar.getInstance().get(Calendar.SECOND)
                + (Calendar.getInstance().get(Calendar.AM_PM) == Calendar.AM ? "a" : "p") + ".m.";
    }

    public CorundumPlugin getPlugin();
}