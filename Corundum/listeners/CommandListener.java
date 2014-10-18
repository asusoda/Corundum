package Corundum.listeners;

import Corundum.utils.interfaces.Commander;

public interface CommandListener extends CorundumListener {
    public boolean onCommand(Commander commander, String command);
}
