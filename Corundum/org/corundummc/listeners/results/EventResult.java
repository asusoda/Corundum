package org.corundummc.listeners.results;

public class EventResult {
    public boolean cancelled;
    public String server_message;

    public EventResult() {
        this(false, null);
    }

    public EventResult(boolean cancelled) {
        this(cancelled, null);
    }

    public EventResult(String server_message) {
        this(false, server_message);
    }

    public EventResult(boolean cancelled, String server_message) {
        this.cancelled = cancelled;
        this.server_message = server_message;
    }

    public void cancel() {
        cancelled = true;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public String getServerMessage() {
        return server_message;
    }

    public void setCancellation(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void setServerMessage(String server_message) {
        this.server_message = server_message;
    }

    public void toggleCancellation() {
        cancelled = !cancelled;
    }

    public void uncancel() {
        cancelled = false;
    }
}
