/** This code is property of the Corundum project managed under the Software Developers' Association of Arizona State University.
 *
 * Copying and use of this open-source code is permitted provided that the following requirements are met:
 *
 * - This code may not be used or distributed for private enterprise, including but not limited to personal or corporate profit. - Any products resulting from the copying,
 * use, or modification of this code may not claim endorsement by the Corundum project or any of its members or associates without written formal permission from the endorsing
 * party or parties. - This code may not be copied or used under any circumstances without the inclusion of this notice and mention of the contribution of the code by the
 * Corundum project. In source code form, this notice must be included as a comment as it is here; in binary form, proper documentation must be included with the final product
 * that includes this statement verbatim.
 * 
 * @author REALDrummer */

package Corundum;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.Timer;

import Corundum.utils.interfaces.TickListener;
import Corundum.utils.myList.myList;
import static Corundum.utils.ListUtilities.contains;

/** This is the main (executable) class of Corundum. ...Does anything more need to be said?
 * 
 * @author REALDrummer */
public class Main {
    /** This list contians all the currently loaded {@link CorundumPlugin}s on the server. Note that loading and unloading plugins will add or remove them from this list,
     * respectively, but enabling or disabling them will <i>not</i> affect this list. */
    public static myList<CorundumPlugin> plugins = new myList<CorundumPlugin>();
    /** This {@link Timer} does off every Minecraft tick (1/20 of a second or 50ms). Using one single global tick timer accessible from anywhere in the program is more
     * efficient and straightforward than using tick timers for different entities.
     * 
     * Every tick, the tick timer calls the {@link TickListener#tickPassed() tickPassed()} method for all the {@link TickListeners} registered. {@link TickListeners} can be
     * registered */
    public static final Timer tick_timer = new Timer(50, new ActionListener() {

        @Override
        public void actionPerformed(@SuppressWarnings("unused") ActionEvent event) {
            for (TickListener tick_listener : tick_listeners)
                tick_listener.tickPassed();
        }

    });
    /** This {@link myList} of {@link TickListener}s represents all the {@link TickListeners} registered with Corundum. Each one of these {@link TickListener}s will be called
     * using their {@link TickListener#tickPassed() tickPassed()} methods every Minecraft tick (1/20 of a second or 50ms) as determineed by the {@link Main#tick_timer global
     * tick timer}. */
    public static myList<TickListener> tick_listeners = new myList<TickListener>();
    /** This {@link myList}<<tt>String</tt>> keeps track of the players (or console, indicated by null) who are currently in debugging mode. */
    public static myList<UUID> debuggers = new myList<UUID>();
    public static boolean debug_logging = false;

    /** This is the main method of Corundum. When Corundum is started, this is the method that is called to start the program.
     * 
     * @param arguments
     *            is the list of <tt>String</tt> arguments given in the command to start this program. These arguments are given through a command line like the Windows
     *            command prompt or Unix terminal. */
    public static void main(String[] arguments) {
        if (contains(arguments, "--debug")) {
            debuggers.add((UUID) null);
            debug("DEBUGGING MODE ACTIVE");
        } else if (contains(arguments, "--log-debug")) {
            debug_logging = true;
            debug("DEBUG LOGGING MODE ACTIVE");
        }

        debug("STARTING CORUNDUM...");

    }

    /** This method sends the given message to all the players (or console) that are currently in "debugging mode". Players and the console can enter or exit debugging mode
     * with a command. In addition, if "debug logging" is active, the message will be logged in Corundum's debug log file.
     * 
     * @param message
     *            is the debug message to send to the debugging parties. */
    public static void debug(String message) {
        if (debuggers.size() == 0)
            return;
        if (debuggers.contains((UUID) null)) {
            System.out.println(message);
            if (debuggers.size() == 1)
                return;
        }

        // TODO TEMP CMT: uncomment when we can support it
        /* for (Player player : getServer().getOnlinePlayers()) if (debuggers.contains(player.getUniqueId())) player.sendMessage(getColor() + message); */
    }
}
