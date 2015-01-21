package org.corundummc.listeners.plugins;

import org.corundummc.CorundumServer;
import org.corundummc.listeners.CancellableEvent;
import org.corundummc.listeners.CorundumListener;
import org.corundummc.listeners.Event;
import org.corundummc.listeners.ListenerCaller;
import org.corundummc.plugins.CorundumPlugin;

public interface PluginListener extends CorundumListener {
    public void onPluginEvent(PluginEvent event);

    public abstract static class PluginEvent<S extends PluginEvent<S>> extends CancellableEvent<S> {
        private final CorundumPlugin plugin;

        public PluginEvent(CorundumPlugin plugin) {
            this.plugin = plugin;
        }

        @SuppressWarnings("unchecked")
        @Override
        public S runOn(CorundumServer server) {
            super.runOn(server);

            runAs(server, PluginEvent.class, new ListenerCaller<S>() {
                @Override
                public void callListener(CorundumListener listener, S event) {
                    ((PluginListener) listener).onPluginEvent(event);
                }
            });

            return (S) this;
        }
    }
}
