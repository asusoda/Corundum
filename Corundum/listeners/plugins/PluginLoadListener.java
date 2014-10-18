package Corundum.listeners.plugins;

import Corundum.CorundumPlugin;
import Corundum.listeners.CorundumListener;

public interface PluginLoadListener extends CorundumListener {
    /** This listener method is called whenever a {@link CorundumPlugin} is loaded from a jar onto the server. Note that "loading" a plugin simply loads it into the RAM, making
     * its code accessible and giving it space in static memory; it does <i>not</i> mean that the plugin will be enabled, and it does not mean that its commands or listeners
     * will work.
     * 
     * @param plugin
     *            is the {@link CorundumPlugin} being loaded from its jar file to the server.
     * @return <b>true</b> if the event that this call represents should be cancelled; <b>false</b> otherwise. */
    public boolean onPluginLoad(CorundumPlugin plugin);
}
