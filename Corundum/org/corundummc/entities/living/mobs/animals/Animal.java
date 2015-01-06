package org.corundummc.entities.living.mobs.animals;

import net.minecraft.entity.EntityLiving;

import org.corundummc.entities.living.LivingEntity;
import org.corundummc.entities.living.mobs.Mob;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class Animal<S extends Animal<S, MC, T>, MC extends EntityLiving, T extends Animal.AnimalType<T, MC, S>> extends LivingEntity<S, MC, T> {
    protected Animal(MC entityMC) {
        super(entityMC);
    }

    public static interface AnimalTypes {
        // TODO
    }

    public abstract static class AnimalType<S extends AnimalType<S, MC, I>, MC extends EntityLiving, I extends Animal<I, MC, S>> extends LivingEntityType<S, MC, I> {
        protected AnimalType(int id, int data) {
            super(id, data);

            addValueAs(AnimalType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        @SuppressWarnings("rawtypes")
        public static AnimalType getByID(int id) {
            return getByID(AnimalType.class, id);
        }

        @SuppressWarnings("rawtypes")
        public static AnimalType getByID(int id, int data) {
            return getByID(AnimalType.class, id, data);
        }

        @SuppressWarnings("rawtypes")
        public static AnimalType[] values() {
            return values(AnimalType.class);
        }
    }

    // type utilities

    // instance utilities
}