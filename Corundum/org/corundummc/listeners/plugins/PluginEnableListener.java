package org.corundummc.listeners.plugins;

import org.corundummc.CorundumServer;
import org.corundummc.listeners.CorundumListener;
import org.corundummc.listeners.ListenerCaller;
import org.corundummc.listeners.plugins.PluginListener.PluginEvent;
import org.corundummc.plugins.CorundumPlugin;

public interface PluginEnableListener extends CorundumListener {
    /** This listener method is called whenever TODO
     * 
     * @param event
     *            is an {@link Event} representing TODO */
    public void onPluginEnable(PluginEnableEvent event);

    public static class PluginEnableEvent extends PluginEvent<PluginEnableEvent> {
        public PluginEnableEvent(CorundumPlugin plugin) {
            super(plugin);
        }

        @Override
        public PluginEnableEvent runOn(CorundumServer server) {
            super.runOn(server);

            return runAs(server, PluginEnableEvent.class, new ListenerCaller<PluginEnableEvent>() {
                @Override
                public void callListener(CorundumListener listener, PluginEnableEvent event) {
                    ((PluginEnableListener) listener).onPluginEnable(event);
                }
            });
        }
    }
}
