package org.corundummc.entities.living.mobs.monsters.undead;

import net.minecraft.entity.EntityCreature;
import org.corundummc.entities.living.mobs.monsters.Monster;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class UndeadMonster<S extends UndeadMonster<S, MC, T>, MC extends EntityCreature, T extends UndeadMonster.UndeadMonsterType<T, MC, S>> extends
        Monster<S, MC, T> {
    protected UndeadMonster(MC entityMC) {
        super(entityMC);
    }

    public static interface UndeadMonsterTypes {
        // TODO
    }

    public abstract static class UndeadMonsterType<S extends UndeadMonsterType<S, MC, I>, MC extends EntityCreature, I extends UndeadMonster<I, MC, S>> extends
            MonsterType<S, MC, I> {
        protected UndeadMonsterType(int id, int data) {
            super(id, data);

            addValueAs(UndeadMonsterType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        public static UndeadMonsterType<?, ?, ?> getByID(int id) {
            return getByID(UndeadMonsterType.class, id);
        }

        public static UndeadMonsterType<?, ?, ?> getByID(int id, int data) {
            return getByID(UndeadMonsterType.class, id, data);
        }

        public static UndeadMonsterType<?, ?, ?>[] values() {
            return values(UndeadMonsterType.class);
        }
    }

    // type utilities

    // instance utilities
}