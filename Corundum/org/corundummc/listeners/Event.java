package org.corundummc.listeners;

import org.corundummc.CorundumServer;
import org.corundummc.exceptions.CIE;
import org.corundummc.listeners.plugins.PluginLoadListener;
import org.corundummc.utils.interfaces.SECURED;

public abstract class Event<S extends Event<S>> {
    private String server_message = null;

    @SECURED
    protected Event() {
        CorundumServer.secure("create its own event Object");
    }

    @SuppressWarnings("unchecked")
    protected <E extends Event<E>> S runAs(CorundumServer server, Class<? extends E> clazz, ListenerCaller<S> caller) {
        // run the event through all the appropriate listeners
        try {
            for (CorundumListener listener : server.getListeners(clazz))
                caller.callListener(listener, (S) this);
        } catch (ClassCastException exception) {
            throw new CIE("A ListenerCaller mismatched a CorundumListener and a CorundumEvent!", exception);
        }

        //

        return (S) this;
    }

    @SECURED
    public S runOn(CorundumServer server) {
        CorundumServer.secure("run an Event directly");

        if (server_message != null)
            server.broadcast(server_message);

        return (S) this;
    }

    public String getServerMessage() {
        return server_message;
    }

    @SuppressWarnings("unchecked")
    public S setServerMessage(String new_server_message) {
        server_message = new_server_message;

        return (S) this;
    }
}
