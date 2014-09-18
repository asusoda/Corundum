package Corundum;

import Corundum.Minecraft.entities.Entity;
import Corundum.Minecraft.entities.Mob;

/** This interface includes a variety of methods that will be called within all {@link CorundumListener}s registered with Corundum. Each method's name and arguments describe an
 * event that can occur within Minecraft game such as a {@link Mob} (e.g. a sheep) being killed by an {@link Entity} (e.g. an arrow or another mob). By implementing this
 * interface and overriding one or more of these methods, plugin programmers can run custom code when the indicated event occurs. Each method in this interface is pre-defined
 * as an empty method, meaning it does nothing unless overridden.
 *
 * 
 * @author REALDrummer */
@SuppressWarnings("unused")
public interface CorundumListener {
    /** This method is called whenever a {@link Mob} such as a sheep or a skeleton is killed by an {@link Entity}, which can also be a {@link Mob} or could also be another
     * damage-causing entity such as an arrow.
     * 
     * @param mob
     *            is the {@link Mob} that was killed by <b><tt>killer</b></tt>.
     * @param killer
     *            is the {@link Entity} that killed <b><tt>mob</b></tt>. */
    default public void onMobKilledByEntity(Mob mob, Entity killer) {
        // do nothing
    }
}
