package Corundum.listeners.plugins;

import Corundum.CorundumPlugin;

public interface PluginUnloadListener {
    /** This listener method is called whenever a {@link CorundumPlugin} is unloaded. When a plugin is unloaded, it is first {@link #onPluginDisable(CorundumPlugin) disabled}
     * (if it had not been disabled already) and its data is removed from the RAM, freeing the jar file for modification or deletion, but making the plugin's code inaccessible
     * to Corundum and its plugins.
     * 
     * @param plugin
     *            is the plugin that is currently being unloaded.
     * @return <b>true</b> if the event that this call represents should be cancelled; <b>false</b> otherwise. */
    public boolean onPluginUnload(CorundumPlugin plugin);
}