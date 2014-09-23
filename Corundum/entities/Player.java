package Corundum.entities;

import Corundum.exceptions.UnfinishedException;
import Corundum.utils.messaging.MessageReceiver;

public class Player extends Mob implements MessageReceiver {

    @Override
    public void sendMessage(String message) {
        throw new UnfinishedException("Player.sendMessage()");
    }

}
