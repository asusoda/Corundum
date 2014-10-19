package Corundum.listeners.plugins;

import Corundum.CorundumPlugin;

public interface PluginDisableListener {
    /** This listener method is called whenever a {@link CorundumPlugin} is disabled. When a plugin is disabled, its data is kept in the RAM and its code is still usable by
     * Corundum and other plugins, but its commands will not work and its listeners will not receive events.
     * 
     * @param plugin
     *            is the plugin that is currently being disabled.
     * @return <b>true</b> if the event that this call represents should be cancelled; <b>false</b> otherwise. */
    public boolean onPluginDisable(CorundumPlugin plugin);
}
