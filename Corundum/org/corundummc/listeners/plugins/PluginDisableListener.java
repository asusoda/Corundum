package org.corundummc.listeners.plugins;

import org.corundummc.listeners.results.EventResult;
import org.corundummc.plugins.CorundumPlugin;

public interface PluginDisableListener {
    /** This listener method is called whenever a {@link CorundumPlugin} is disabled. When a plugin is disabled, its data is kept in the RAM and its code is still usable by
     * Corundum and other plugins, but its commands will not work and its listeners will not receive events.
     * 
     * @param plugin
     *            is the plugin that is currently being disabled.
     * @return the {@link EventResult} describing any changes to this event's properties. */
    public EventResult onPluginDisable(CorundumPlugin plugin);
}
