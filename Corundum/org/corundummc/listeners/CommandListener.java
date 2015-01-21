package org.corundummc.listeners;

import org.corundummc.entities.living.Player;
import org.corundummc.utils.interfaces.Commander;

/** This listner interface allows classes implementing it to handle "command" events, whcih occur when {@link Commander}s issue a server command. Server commands can be issued
 * via the console or through players' in-game chat using a "/".
 * 
 * @author REALDrummer */
public interface CommandListener extends CorundumListener {
    /** This listener method is called whenever a {@link Commander} such as a {@link Player} issues a server command. Commands always begin with a "/".
     * 
     * @param event
     *            is the {@link CommandEvent} <tt>Object</tt> representing the sending of this command. */
    public void onCommand(CommandEvent event);

    public static class CommandEvent extends CancellableEvent<CommandEvent> {
        private final Commander commander;
        private String command;

        public CommandEvent(Commander commander, String command) {
            this.commander = commander;
            this.command = command;
        }
    }
}
