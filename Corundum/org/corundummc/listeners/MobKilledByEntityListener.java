package org.corundummc.listeners;

import org.corundummc.entities.Entity;
import org.corundummc.entities.living.mobs.Mob;

public interface MobKilledByEntityListener {
    /** This listener method is called whenever a {@link Mob} such as a sheep or a skeleton is killed by an {@link Entity}, which can also be a {@link Mob} or could also be
     * another damage-causing entity such as an arrow.
     * 
     * @param mob
     *            is the {@link Mob} that was killed by <b><tt>killer</b></tt>.
     * @param killer
     *            is the {@link Entity} that killed <b><tt>mob</b></tt>.
     * 
     * @return <b>true</b> if the event that this call represents should be cancelled; <b>false</b> otherwise. */
    public boolean onMobKilledByEntity(Mob mob, Entity killer);
}