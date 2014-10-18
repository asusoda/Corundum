package Corundum.listeners;

/** This interface's sole purpose is to act in correspondence with <a href=http://docs.oracle.com/javase/tutorial/java/javaOO/ lambdaexpressions.html>Java 8's lambda
 * expressions</a> or anonymous classes for use in the {@link #generateEvent(CorundumListener) generateEvent() method}. <i>Please may no attention to the interface beind the
 * {@link CorundumListener} curtain.</i>
 * 
 * @param <T>
 *            is the type of {@link CorundumListener} that this {@link ListenerCaller} is able to generate an event for. */
public interface ListenerCaller<T extends CorundumListener> {
    public boolean generateEvent(T listener);
}