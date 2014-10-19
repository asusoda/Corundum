package Corundum.utils.interfaces;

import Corundum.utils.messaging.MessageReceiver;

public interface Commander extends MessageReceiver {
    public void command(String command);

    public String getName();
}