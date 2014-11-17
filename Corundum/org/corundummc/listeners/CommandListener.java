package org.corundummc.listeners;

import org.corundummc.entities.Player;
import org.corundummc.listeners.results.EventResult;
import org.corundummc.utils.interfaces.Commander;

/** This listner interface allows classes implementing it to handle "command" events, whcih occur when {@link Commander}s issue a server command. Server commands can be issued
 * via the console or through players' in-game chat using a "/".
 * 
 * @author REALDrummer */
public interface CommandListener extends CorundumListener {
    /** This listener method is called whenever a {@link Commander} such as a {@link Player} issues a server command. Commands always begin with a "/".
     * 
     * @param commander
     *            is the {@link Commander} that issued the server command.
     * @param command
     *            is the <tt>String</tt> describing the command issued.
     * @param result
     *            is the {@link EventResult} from calling this method in other {@link CommandListener}s.
     * @return <b>true</b> if the command should be allowed to proceed, i.e. proceeded and executed normally; <b>false</b> if the command should be cancelled and not executed. */
    public EventResult onCommand(Commander commander, String command, EventResult result);
}
