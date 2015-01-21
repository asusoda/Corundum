package org.corundummc.listeners;

import org.corundummc.CorundumServer;

public abstract class CancellableEvent<S extends CancellableEvent<S>> extends Event<S> {
    private boolean cancelled;

    protected CancellableEvent() {
        super();

        cancelled = false;
    }

    @SuppressWarnings("unchecked")
    public S cancel() {
        cancelled = true;
        return (S) this;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    @SuppressWarnings("unchecked")
    public S setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
        return (S) this;
    }

    @SuppressWarnings("unchecked")
    public S restore() {
        cancelled = false;
        return (S) this;
    }

    @Override
    public S runOn(CorundumServer server) {
        CorundumServer.secure("run an Event directly");

        if (getServerMessage() != null && !isCancelled() /* unlike with regular events, do not broadcast the server message if the event is cancelled */)
            server.broadcast(getServerMessage());

        return (S) this;
    }
}
