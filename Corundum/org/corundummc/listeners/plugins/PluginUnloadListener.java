package org.corundummc.listeners.plugins;

import org.corundummc.listeners.results.EventResult;
import org.corundummc.plugins.CorundumPlugin;

public interface PluginUnloadListener {
    /** This listener method is called whenever a {@link CorundumPlugin} is unloaded. When a plugin is unloaded, it is first {@link PluginDisableListener#onPluginDisable(CorundumPlugin) disabled}
     * (if it had not been disabled already) and its data is removed from the RAM, freeing the jar file for modification or deletion, but making the plugin's code inaccessible
     * to Corundum and its plugins.
     * 
     * @param plugin
     *            is the plugin that is currently being unloaded.
     * @return @return the {@link EventResult} describing any changes to this event's properties. */
    public EventResult onPluginUnload(CorundumPlugin plugin);
}