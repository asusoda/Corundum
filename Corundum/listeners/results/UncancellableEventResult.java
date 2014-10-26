package Corundum.listeners.results;

import Corundum.exceptions.CorundumException;

public class UncancellableEventResult extends EventResult {
    public UncancellableEventResult() {
        super(false);
    }

    public UncancellableEventResult(String server_message) {
        super(false, server_message);
    }

    @Override
    public final void cancel() {
        throw new CancelledTheUncancellableException(server_message);
    }

    @Override
    public final boolean isCancelled() {
        return false;
    }

    @Override
    public final void setCancellation(@SuppressWarnings("unused") boolean cancelled) {
        throw new CancelledTheUncancellableException(server_message);
    }

    @Override
    public final void toggleCancellation() {
        throw new CancelledTheUncancellableException(server_message);
    }

    @Override
    public final void uncancel() {
        // do nothing
    }

    /** This {@link CorundumException} represents an error that occurs when a plugin attempts to cancel an event that is not cancellable. For example, players' disconnections
     * from the server cannot be cancelled; clients cannot be forced to stay connected to a server!
     * 
     * @author REALDrummer */
    public class CancelledTheUncancellableException extends CorundumException {
        private static final long serialVersionUID = 3911826905408715409L;

        /** This {@link CorundumException} represents an error that occurs when a plugin attempts to cancel an event that is not cancellable. For example, players'
         * disconnections from the server cannot be cancelled; clients cannot be forced to stay connected to a server!
         * 
         * @param server_message
         *            is the message that was associated with the {@link UncancellableEventResult} at the time it caused this error. */
        public CancelledTheUncancellableException(String server_message) {
            super("A plugin attempted to cancel an uncancellable event!", "attempt to cancel an uncancellable event", "server message = \"" + server_message + "\"");
        }

    }
}
