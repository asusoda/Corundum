package org.corundummc.listeners.plugins;

import org.corundummc.CorundumServer;
import org.corundummc.listeners.CorundumListener;
import org.corundummc.listeners.Event;
import org.corundummc.listeners.ListenerCaller;
import org.corundummc.listeners.plugins.PluginListener.PluginEvent;
import org.corundummc.plugins.CorundumPlugin;

public interface PluginLoadListener extends CorundumListener {
    /** This listener method is called whenever a {@link CorundumPlugin} is loaded from a jar onto the server. Note that "loading" a plugin simply loads it into the RAM, making
     * its code accessible and giving it space in static memory; it does <i>not</i> mean that the plugin will be enabled, and it does not mean that its commands or listeners
     * will work.
     * 
     * @param event
     *            is an {@link Event} representing the loading of this {@link CorundumPlugin}. */
    public void onPluginLoad(PluginLoadEvent event);

    public static class PluginLoadEvent extends PluginEvent<PluginLoadEvent> {
        public PluginLoadEvent(CorundumPlugin plugin) {
            super(plugin);
        }

        @Override
        public PluginLoadEvent runOn(CorundumServer server) {
            super.runOn(server);

            return runAs(server, PluginLoadEvent.class, new ListenerCaller<PluginLoadEvent>() {
                @Override
                public void callListener(CorundumListener listener, PluginLoadEvent event) {
                    ((PluginLoadListener) listener).onPluginLoad(event);
                }
            });
        }
    }
}
