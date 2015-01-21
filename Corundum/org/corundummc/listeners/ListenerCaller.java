package org.corundummc.listeners;

import org.corundummc.utils.interfaces.Commander;

/** This interface's sole purpose is to act in correspondence with <a href=http://docs.oracle.com/javase/tutorial/java/javaOO/ lambdaexpressions.html>Java 8's lambda
 * expressions</a> or anonymous classes for use in the {@link CorundumServer#callListener(ListenerCaller) generateEvent() method}. <i>Please may no attention to the interface
 * behind the {@link CorundumListener} curtain.</i>
 * 
 * @param <E>
 *            is the type of {@link Event} that this {@link ListenerCaller} runs.
 * @param <L>
 *            is the type of {@link CorundumListener} that this {@link ListenerCaller} can call. */
public interface ListenerCaller<E extends Event<E>> {
    /** This method calls the appropriate listener method(s) when an event occurs with the appropriate parameters.
     * 
     * @param listener
     *            is the {@link CorundumListener} which this {@link ListenerCaller} will be calling a listener method from. For example, a {@link ListenerCaller}<
     *            {@link CommandListener}> should call {@link CommandListener#onCommand(Commander, String, EventResult) onCommand()} in the given {@link CommandListener} with
     *            the parameters associated with the event, i.e. the {@link Commander} that executed the command and the command itself (as a <tt>String</tt>).
     * @param event
     *            is the {@link Event} that will be used to call <b><tt>listener</b></tt>. */
    public void callListener(CorundumListener listener, E event);
}