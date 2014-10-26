package Corundum.listeners;

import Corundum.utils.interfaces.Commander;
import Corundum.listeners.results.EventResult;

/** This interface's sole purpose is to act in correspondence with <a href=http://docs.oracle.com/javase/tutorial/java/javaOO/ lambdaexpressions.html>Java 8's lambda
 * expressions</a> or anonymous classes for use in the {@link #generateEvent(CorundumListener) generateEvent() method}. <i>Please may no attention to the interface behind the
 * {@link CorundumListener} curtain.</i>
 * 
 * @param <T>
 *            is the type of {@link CorundumListener} that this {@link ListenerCaller} is able to generate an event for.
 * @param <R>
 *            is the type of {@link EventResult} that this event type returns. */
public interface ListenerCaller<T extends CorundumListener, R extends EventResult> {
    /** This method calls the appropriate listener method(s) when an event occurs with the appropriate parameters.
     * 
     * @param listener
     *            is the {@link CorundumListener} which this {@link ListenerCaller} will be calling a listener method from. For example, a {@link ListenerCaller}<
     *            {@link CommandListener}> should call {@link CommandListener#onCommand(Commander, String) onCommand()} in the given {@link CommandListener} with the
     *            parameters associated with the event, i.e. the {@link Commander} that executed the command and the command itself (as a <tt>String</tt>).
     * @param result
     *            is the {@link EventResult} from previous calls of this event to other {@link CommandListener}s. This <b><tt>result</b></tt> should be passed as the final
     *            parameter to the listener method. <b><i>NOTE:</b></i> This method needs to create an instance of the appropriate {@link EventResult} when <b>
     *            <tt>result</b></tt> is <b>null</b>.
     * @return an {@link EventResult} <tt>Object</tt> describing any modifications to any properties of the event, including whether or not the event was cancelled if the
     *         event can be cancelled. */
    public R generateEvent(T listener, R result);
}