package org.corundummc.entities.living.mobs.golems;

import org.corundummc.entities.living.mobs.Mob;
import org.corundummc.entities.living.mobs.Mob.MobType;
import org.corundummc.entities.living.mobs.golems.IronGolem.IronGolemType;
import org.corundummc.entities.living.mobs.golems.SnowGolem.SnowGolemType;

import net.minecraft.entity.monster.EntityGolem;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class Golem<S extends Golem<S, MC, T>, MC extends EntityGolem, T extends Golem.GolemType<T, MC, S>> extends Mob<S, MC, T> {
    protected Golem(MC entityMC) {
        super(entityMC);
    }

    public static interface GolemTypes {
        public static final IronGolemType IRON_GOLEM = IronGolemType.TYPE;
        public static final SnowGolemType SNOW_GOLEM = SnowGolemType.TYPE;
    }

    public abstract static class GolemType<S extends GolemType<S, MC, I>, MC extends EntityGolem, I extends Golem<I, MC, S>> extends MobType<S, MC, I> {
        protected GolemType(int id, int data) {
            super(id, data);

            addValueAs(GolemType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        @SuppressWarnings("rawtypes")
        public static GolemType getByID(int id) {
            return getByID(GolemType.class, id);
        }

        @SuppressWarnings("rawtypes")
        public static GolemType getByID(int id, int data) {
            return getByID(GolemType.class, id, data);
        }

        @SuppressWarnings("rawtypes")
        public static GolemType[] values() {
            return values(GolemType.class);
        }
    }

    // type utilities

    // instance utilities
}