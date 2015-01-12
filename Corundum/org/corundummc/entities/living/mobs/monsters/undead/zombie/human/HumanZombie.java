package org.corundummc.entities.living.mobs.monsters.undead.zombie.human;

import net.minecraft.entity.monster.EntityZombie;

import org.corundummc.entities.living.mobs.monsters.undead.zombie.ZombifiedMonster;
import org.corundummc.entities.living.mobs.monsters.undead.zombie.human.Zombie.ZombieType;
import org.corundummc.entities.living.mobs.monsters.undead.zombie.human.ZombifiedVillager.ZombifiedVillagerType;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class HumanZombie<S extends HumanZombie<S, T>, T extends HumanZombie.HumanZombieType<T, S>> extends ZombifiedMonster<S, EntityZombie, T> {
    protected HumanZombie(EntityZombie entityMC) {
        super(entityMC);
    }

    public static interface HumanZombieTypes {
        public static final ZombieType ZOMBIE = ZombieType.TYPE;
        public static final ZombifiedVillagerType ZOMBIFIED_VILLAGER = ZombifiedVillagerType.TYPE;
    }

    public abstract static class HumanZombieType<S extends HumanZombieType<S, I>, I extends HumanZombie<I, S>> extends ZombifiedMonsterType<S, EntityZombie, I> {
        protected HumanZombieType(int data) {
            super(54, data);

            addValueAs(HumanZombieType.class);
        }

        // abstract utilities

        // overridden utilities
        @Override
        public I fromMC(EntityZombie entityMC) {
            if (!entityMC.isVillager())
                return (I) new Zombie(entityMC);
            else
                return (I) new ZombifiedVillager(entityMC);
        }

        // pseudo-enum utilities
        public static HumanZombieType<?, ?> getByID(int id) {
            return getByID(HumanZombieType.class, id);
        }

        public static HumanZombieType<?, ?> getByID(int id, int data) {
            return getByID(HumanZombieType.class, id, data);
        }

        public static HumanZombieType<?, ?>[] values() {
            return values(HumanZombieType.class);
        }
    }

    // type utilities

    // instance utilities
}