package Corundum;

import Corundum.entities.Entity;
import Corundum.entities.Mob;
import Corundum.exceptions.CorundumException;
import Corundum.utils.interfaces.Commander;

/** This interface includes a variety of methods that will be called within all {@link CorundumListener}s registered with Corundum. Each method's name and arguments describe an
 * event that can occur within Minecraft game such as a {@link Mob} (e.g. a sheep) being killed by an {@link Entity} (e.g. an arrow or another mob). By implementing this
 * interface and overriding one or more of these methods, plugin programmers can run custom code when the indicated event occurs. Each method in this interface is pre-defined
 * as an empty method, meaning it does nothing unless overridden.
 *
 * 
 * @author REALDrummer */
public interface CorundumListener {
    // event handling
    /** This interface's sole purpose is to act in correspondence with <a href=http://docs.oracle.com/javase/tutorial/java/javaOO/ lambdaexpressions.html>Java 8's lambda
     * expressions</a> for use in the {@link #generateEvent(CorundumListener) generateEvent() method}. <i>Please may no attention to the interface beind the
     * {@link CorundumListener} curtain.</i> */
    public static interface ListenerCaller {
        public boolean generateEvent(CorundumListener listener);
    }

    /** This method is used to generate an event that is run through all {@link CorundumListener}s on the server. <b><i>NOTE:</b></i> this method is intended for use by
     * Corundum, not plugin developers; we highly recommend that plugin developers generate events by actually causing the event to happen.
     * 
     * @param caller
     *            is a {@link ListenerCaller} that calls the appropriate listener method with arguments that properly represent the event. This method is set up to work
     *            perfectly with <a href=http://docs.oracle.com/javase/tutorial/java/javaOO/ lambdaexpressions.html>Java 8's lambda expressions</a>, kind of like this:<br>
     *            <tt>CorundumListener.generateEvent(listener -> listener.onPluginLoad(plugin))</tt>
     * @return <b>true</b> if a cancellation of the event was requested; <b>false</b> otherwise. */
    public static CorundumListener generateEvent(ListenerCaller caller) {
        for (CorundumPlugin plugin : Corundum.plugins)
            if (plugin.isEnabled())
                for (CorundumListener listener : plugin.getListeners())
                    try {
                        /* if the listener method called by the event returned true, return this listener to indicate that this CorundumListener cancelled the event */
                        if (caller.generateEvent(listener))
                            return listener;
                    } catch (CorundumException exception) {
                        exception.err();
                    }

        /* if the event gets through all the listener methods and doesn't get cancelled, return null to indicate that no listeners cancelled the event */
        return null;
    }

    // entity listener methods
    /** This listener method is called whenever a {@link Mob} such as a sheep or a skeleton is killed by an {@link Entity}, which can also be a {@link Mob} or could also be
     * another damage-causing entity such as an arrow.
     * 
     * @param mob
     *            is the {@link Mob} that was killed by <b><tt>killer</b></tt>.
     * @param killer
     *            is the {@link Entity} that killed <b><tt>mob</b></tt>.
     * 
     * @return <b>true</b> if the event that this call represents should be cancelled; <b>false</b> otherwise. */
    default public boolean onMobKilledByEntity(Mob mob, Entity killer) {
        // do nothing by default
        return false;
    }

    // admin-related listener methods
    default public boolean onCommand(Commander commander, String command) {
        // do nothing by default
        return false;
    }

    /** This listener method is called whenever a {@link CorundumPlugin} is loaded from a jar onto the server. Note that "loading" a plugin simply loads it into the RAM, making
     * its code accessible and giving it space in static memory; it does <i>not</i> mean that the plugin will be enabled, and it does not mean that its commands or listeners
     * will work.
     * 
     * @param plugin
     *            is the {@link CorundumPlugin} being loaded from its jar file to the server.
     * @return <b>true</b> if the event that this call represents should be cancelled; <b>false</b> otherwise. */
    default public boolean onPluginLoad(CorundumPlugin plugin) {
        // do nothing by default
        return false;
    }

    /** This listener method is called whenever a {@link CorundumPlugin} is enabled. When a plugin is enabled, its commands become usable and its listeners become active.
     * 
     * @param plugin
     *            is the plugin that is currently being enabled.
     * @return <b>true</b> if the event that this call represents should be cancelled; <b>false</b> otherwise. */
    default public boolean onPluginEnable(CorundumPlugin plugin) {
        // do nothing by default
        return false;
    }

    /** This listener method is called whenever a {@link CorundumPlugin} is disabled. When a plugin is disabled, its data is kept in the RAM and its code is still usable by
     * Corundum and other plugins, but its commands will not work and its listeners will not receive events.
     * 
     * @param plugin
     *            is the plugin that is currently being disabled.
     * @return <b>true</b> if the event that this call represents should be cancelled; <b>false</b> otherwise. */
    default public boolean onPluginDisable(CorundumPlugin plugin) {
        // do nothing by default
        return false;
    }

    /** This listener method is called whenever a {@link CorundumPlugin} is unloaded. When a plugin is unloaded, it is first {@link #onPluginDisable(CorundumPlugin) disabled}
     * (if it had not been disabled already) and its data is removed from the RAM, freeing the jar file for modification or deletion, but making the plugin's code inaccessible
     * to Corundum and its plugins.
     * 
     * @param plugin
     *            is the plugin that is currently being unloaded.
     * @return <b>true</b> if the event that this call represents should be cancelled; <b>false</b> otherwise. */
    default public boolean onPluginUnload(CorundumPlugin plugin) {
        // do nothing by default
        return false;
    }

    // abstract getters
    public CorundumPlugin getPlugin();
}