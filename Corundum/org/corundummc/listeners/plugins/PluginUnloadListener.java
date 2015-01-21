package org.corundummc.listeners.plugins;

import org.corundummc.CorundumServer;
import org.corundummc.listeners.CorundumListener;
import org.corundummc.listeners.ListenerCaller;
import org.corundummc.listeners.plugins.PluginListener.PluginEvent;
import org.corundummc.plugins.CorundumPlugin;

public interface PluginUnloadListener extends CorundumListener {
    /** This listener method is called whenever TODO
     * 
     * @param event
     *            is an {@link Event} representing TODO */
    public void onPluginUnload(PluginUnloadEvent event);

    public static class PluginUnloadEvent extends PluginEvent<PluginUnloadEvent> {
        public PluginUnloadEvent(CorundumPlugin plugin) {
            super(plugin);
        }

        @Override
        public PluginUnloadEvent runOn(CorundumServer server) {
            super.runOn(server);

            return runAs(server, PluginUnloadEvent.class, new ListenerCaller<PluginUnloadEvent>() {
                @Override
                public void callListener(CorundumListener listener, PluginUnloadEvent event) {
                    ((PluginUnloadListener) listener).onPluginUnload(event);
                }
            });
        }
    }
}
