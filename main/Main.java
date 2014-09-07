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

package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.swing.Timer;

import utils.interfaces.TickListener;
import Minecraft.myList;
import static utils.ListUtilities.contains;
import static utils.StringUtilities.aOrAn;

public class Main {
    public static final Timer tick_timer = new Timer(50, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {
            for (TickListener tick_listener : tick_listeners)
                tick_listener.tickPassed();
        }

    });

    public static Main instance;
    public static myList<TickListener> tick_listeners = new myList<TickListener>();

    /** This {@link myList}<<tt>String</tt>> keeps track of the players (or console, indicated by null) who are currently in debugging mode. */
    public static myList<UUID> debuggers = new myList<UUID>();

    public static void main(String[] args) {
        if (contains(args, "--debug")) {
            debuggers.add((UUID) null);
            debug("DEBUGGING MODE ACTIVE");
        }

        debug("STARTING MYCRAFT...");

    }

    public static enum MessageColor {
        RESET, BLACK, RED, GREEN, YELLOW, BLUE, PURPLE, CYAN, WHITE;

        public static final byte MIN_BESIDES_0 = 30;

        @Override
        public String toString() {
            return "\u001B[" + (ordinal() == 0 ? "0" : ordinal() + MIN_BESIDES_0 - 1) + "m";
        }
    }

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
