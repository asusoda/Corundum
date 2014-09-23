package Corundum.utils.interfaces;

import com.google.common.util.concurrent.Service.Listener;

import Corundum.Corundum;
import Corundum.CorundumListener;
import Corundum.CorundumPlugin;

public interface Commander {
    default public void command(String command) {
        CorundumListener.generateEvent(listener -> listener.onCommand(this, command));
    }
}
