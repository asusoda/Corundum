package org.corundummc.entities.living.mobs;

import org.corundummc.entities.living.LivingEntity;
import org.corundummc.entities.living.Player;
import org.corundummc.items.Item.ItemType;

/** This interface describes all {@link Mob mobs} that are capable of attacking other mobs. This includes not only {@link Monster monsters} that attack {@link Player players}
 * or other {@link LivingEntity living entities} on sight, but also ones that only attack if provoked such as {@link MonsterType#ZOMBIE_PIGMAN zombie pigmen}. This mostly
 * includes {@link Monster monsters}, but also includes the {@link RabbitType#KILLER_RABBIT killer rabbit} in Corundum versions for Minecraft 1.8+ servers. */
public interface HostileMob {
    /* This interface has to exist because of the KillerRabbit mob type. The killer rabbit is one of the "RabbitTypes" and Rabbits are types of Animals; therefore,
     * KillerRabbits cannot be Monsters. So, I made this interface so that the KillerRabbits can attack things just like Monsters do with the same methods and all and so that
     * all HostileMobs, including the KillerRabbits, can be categorized into one while still allowing the KillerRabbit to be a type of Rabbit. The Monster class implements
     * this interface, too, so all Monsters are still hostile. */

    /** This method tells this {@link HostileMob} to attack the given {@link LivingEntity} the same way this particular type of {@link HostileMob} would normally attack
     * something, e.g. {@link MonsterType#SKELETON skeletons} will shoot at it with their {@link ItemType#BOW bows}, {@link MonsterType#ZOMBIE zombies} will try to hit it,
     * {@link MonsterType#CREEPER creepers} will try to sneak up on it and explode, etc.
     * 
     * @param target
     *            is the {@link LivingEntity} that this {@link HostileMob} should attack. */
    public void attack(LivingEntity target);

    /** This method returns the {@link LivingEntity} that this {@link HostileMob} is currently attacking.
     * 
     * @return the {@link LivingEntity} that this {@link HostileMob} is currently attacking or <b>null</b> if it is not currently attacking anything. */
    public LivingEntity getTarget();
}
