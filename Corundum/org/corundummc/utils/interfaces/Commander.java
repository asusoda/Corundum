package org.corundummc.utils.interfaces;

import org.corundummc.utils.messaging.MessageReceiver;

public interface Commander extends MessageReceiver {
    public void command(String command);

    public String getName();
}