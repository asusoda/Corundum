package Corundum.listeners.plugins;

import Corundum.CorundumPlugin;

public interface PluginEnableListener {
    /** This listener method is called whenever a {@link CorundumPlugin} is enabled. When a plugin is enabled, its commands become usable and its listeners become active.
     * 
     * @param plugin
     *            is the plugin that is currently being enabled.
     * @return <b>true</b> if the event that this call represents should be cancelled; <b>false</b> otherwise. */
    public boolean onPluginEnable(CorundumPlugin plugin);
}
