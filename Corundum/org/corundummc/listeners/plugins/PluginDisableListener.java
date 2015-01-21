package org.corundummc.listeners.plugins;

import org.corundummc.CorundumServer;
import org.corundummc.listeners.CorundumListener;
import org.corundummc.listeners.ListenerCaller;
import org.corundummc.listeners.plugins.PluginListener.PluginEvent;
import org.corundummc.plugins.CorundumPlugin;

public interface PluginDisableListener extends CorundumListener {
    /** This listener method is called whenever TODO
     * 
     * @param event
     *            is an {@link Event} representing TODO */
    public void onPluginDisable(PluginDisableEvent event);

    public static class PluginDisableEvent extends PluginEvent<PluginDisableEvent> {
        public PluginDisableEvent(CorundumPlugin plugin) {
            super(plugin);
        }

        @Override
        public PluginDisableEvent runOn(CorundumServer server) {
            super.runOn(server);

            return runAs(server, PluginDisableEvent.class, new ListenerCaller<PluginDisableEvent>() {
                @Override
                public void callListener(CorundumListener listener, PluginDisableEvent event) {
                    ((PluginDisableListener) listener).onPluginDisable(event);
                }
            });
        }
    }
}
