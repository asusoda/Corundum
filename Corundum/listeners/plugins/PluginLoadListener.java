package Corundum.listeners.plugins;

import Corundum.listeners.CommandListener;
import Corundum.listeners.CorundumListener;
import Corundum.listeners.results.EventResult;
import Corundum.plugins.CorundumPlugin;

public interface PluginLoadListener extends CorundumListener {
    /** This listener method is called whenever a {@link CorundumPlugin} is loaded from a jar onto the server. Note that "loading" a plugin simply loads it into the RAM, making
     * its code accessible and giving it space in static memory; it does <i>not</i> mean that the plugin will be enabled, and it does not mean that its commands or listeners
     * will work.
     * 
     * @param plugin
     *            is the {@link CorundumPlugin} being loaded from its jar file to the server.
     * @param result
     *            is the {@link EventResult} from calling this method in other {@link CommandListener}s.
     * @return the {@link EventResult} describing any changes to this event's properties. */
    public EventResult onPluginLoad(CorundumPlugin plugin, EventResult result);
}
