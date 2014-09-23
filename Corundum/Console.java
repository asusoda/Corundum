package Corundum;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import Corundum.utils.interfaces.Commander;
import Corundum.utils.messaging.MessageColor;
import Corundum.utils.messaging.MessageReceiver;

/** This class represents the console, i.e. the terminal, command prompt, or GUI from which the server is centrally run.
 * <hr>
 * <b>NOTE:</b> This class is meant to be instantiated only once! From then on, all uses of this {@link Console} should be from {@link Corundum#CONSOLE}; do not make more than
 * one Console unless you want major bugs!
 * 
 * @author REALDrummer */
public class Console implements MessageReceiver, Commander {
    @Override
    public void sendMessage(String message) {
        LogManager.getLogger().info(MessageColor.translateMCChatCodesToANSICodes(message));
    }
}
