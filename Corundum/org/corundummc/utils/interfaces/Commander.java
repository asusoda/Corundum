package org.corundummc.utils.interfaces;

import net.minecraft.command.ICommandSender;
import org.corundummc.utils.messaging.MessageReceiver;

public interface Commander extends MessageReceiver, ICommandSender {
    public void command(String command);

    public String getName();
}