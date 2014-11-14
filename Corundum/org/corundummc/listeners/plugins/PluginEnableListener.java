package org.corundummc.listeners.plugins;

import org.corundummc.listeners.results.EventResult;
import org.corundummc.plugins.CorundumPlugin;

public interface PluginEnableListener {
    /** This listener method is called whenever a {@link CorundumPlugin} is enabled. When a plugin is enabled, its commands become usable and its listeners become active.
     * 
     * @param plugin
     *            is the plugin that is currently being enabled.
     * @return @return the {@link EventResult} describing any changes to this event's properties. */
    public EventResult onPluginEnable(CorundumPlugin plugin);
}
