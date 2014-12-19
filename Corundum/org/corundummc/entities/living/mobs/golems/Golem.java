package org.corundummc.entities.living.mobs.golems;

import org.corundummc.entities.living.mobs.Mob;
import org.corundummc.entities.living.mobs.Mob.MobType;

import net.minecraft.entity.monster.EntityGolem;

public class Golem<T extends EntityGolem> extends Mob<T> {

    protected Golem(T entityMC) {
        super(entityMC);
    }

    /** This class is used to represent the different types of {@link Golem}s.
     * 
     * @param <T>
     *            is a self-parameterization; <b><tt>T</b></tt> is the same type as the type of this instance. */
    public static class GolemType<T extends MobType<T>> extends MobType<T> {

        @SuppressWarnings("rawtypes")
        public static final GolemType<?> SNOW_GOLEM = new GolemType(61) {
            @Override
            public SnowGolem create() {
                return new SnowGolem();
            }
        };

        @SuppressWarnings("rawtypes")
        public static final GolemType<?> IRON_GOLEM = new GolemType(63) {
            @Override
            public IronGolem create() {
                return new IronGolem();
            }
        };

        protected GolemType(int id) {
            super(id, -1);

            addValueAs(Golem.class);
        }

        @Override
        public Golem create() {
            return (Golem) super.create();
        }

        // pseudo-enum utilities
        @SuppressWarnings("unchecked")
        public static GolemType<?> getByID(int id) {
            return getByID(GolemType.class, id);
        }

        @SuppressWarnings("unchecked")
        public static GolemType<?> getByID(int id, int data) {
            return getByID(GolemType.class, id, data);
        }

        @SuppressWarnings("unchecked")
        public static GolemType<?>[] values() {
            return values(GolemType.class);
        }
    }
}