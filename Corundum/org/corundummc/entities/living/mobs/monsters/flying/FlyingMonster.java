package org.corundummc.entities.living.mobs.monsters.flying;

import net.minecraft.entity.EntityLiving;

import org.corundummc.entities.living.mobs.monsters.Monster;
import org.corundummc.entities.living.mobs.monsters.flying.Blaze.BlazeType;
import org.corundummc.entities.living.mobs.monsters.flying.Ghast.GhastType;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class FlyingMonster<S extends FlyingMonster<S, MC, T>, MC extends EntityLiving, T extends FlyingMonster.FlyingMonsterType<T, MC, S>> extends Monster<S, MC, T> {
    protected FlyingMonster(MC entityMC) {
        super(entityMC);
    }

    public static interface FlyingMonsterTypes {
        public static final BlazeType BLAZE = BlazeType.TYPE;
        public static final GhastType GHAST = GhastType.TYPE;
    }

    public abstract static class FlyingMonsterType<S extends FlyingMonsterType<S, MC, I>, MC extends EntityLiving, I extends FlyingMonster<I, MC, S>> extends
            MonsterType<S, MC, I> {
        protected FlyingMonsterType(int id) {
            super(id, -1);

            addValueAs(FlyingMonsterType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        @SuppressWarnings("rawtypes")
        public static FlyingMonsterType getByID(int id) {
            return getByID(FlyingMonsterType.class, id);
        }

        @SuppressWarnings("rawtypes")
        public static FlyingMonsterType getByID(int id, int data) {
            return getByID(FlyingMonsterType.class, id, data);
        }

        @SuppressWarnings("rawtypes")
        public static FlyingMonsterType[] values() {
            return values(FlyingMonsterType.class);
        }
    }

    // type utilities

    // instance utilities
}