package Corundum.utils.interfaces;

import net.minecraft.command.ICommandSender;
import Corundum.utils.messaging.MessageReceiver;

public interface Commander extends MessageReceiver, ICommandSender {
    public void command(String command);

    public String getName();
}