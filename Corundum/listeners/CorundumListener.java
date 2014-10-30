package Corundum.listeners;

import Corundum.Corundum;
import Corundum.CorundumPlugin;
import Corundum.entities.Entity;
import Corundum.entities.Mob;

/** This interface is the parent of each one of Corundum's "Listener" interfaces. Each one of Corundum's "Listener" interfaces includes one or more methods that are called each
 * time a specific type of event occurs; for example, whenever a mob is killed by another entity, the method {@link MobKilledByEntityListener#onMobKilledByEntity(Mob, Entity)}
 * method is called in each enabled {@link CorundumPlugin}'s {@link MobKilledByEntityListener}(s) until all active {@link MobKilledByEntityListener}(s) are called or one of
 * the called {@link MobKilledByEntityListener}s cancels the event by returning <b>false</b>.
 * <hr>
 * 
 * All of Corundum's "Listener" interfaces can be found in the {@link Corundum.listeners} package.
 *
 * 
 * @author REALDrummer */
public interface CorundumListener {
    // nothing goes in here
}