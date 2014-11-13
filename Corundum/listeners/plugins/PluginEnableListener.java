package Corundum.listeners.plugins;

import Corundum.listeners.results.EventResult;
import Corundum.plugins.CorundumPlugin;

public interface PluginEnableListener {
    /** This listener method is called whenever a {@link CorundumPlugin} is enabled. When a plugin is enabled, its commands become usable and its listeners become active.
     * 
     * @param plugin
     *            is the plugin that is currently being enabled.
     * @return @return the {@link EventResult} describing any changes to this event's properties. */
    public EventResult onPluginEnable(CorundumPlugin plugin);
}
