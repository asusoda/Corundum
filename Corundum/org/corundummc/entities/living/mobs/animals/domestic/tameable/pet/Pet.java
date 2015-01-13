package org.corundummc.entities.living.mobs.animals.domestic.tameable.pet;

import net.minecraft.entity.passive.EntityTameable;

import org.corundummc.entities.living.mobs.animals.domestic.tameable.TameableAnimal;
import org.corundummc.entities.living.mobs.animals.domestic.tameable.pet.Ocelot.OcelotType;
import org.corundummc.entities.living.mobs.animals.domestic.tameable.pet.Wolf.WolfType;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class Pet<S extends Pet<S, MC, T>, MC extends EntityTameable, T extends Pet.PetType<T, MC, S>> extends TameableAnimal<S, MC, T> {
    protected Pet(MC entityMC) {
        super(entityMC);
    }

    public static interface PetTypes {
        public static final OcelotType OCELOT = OcelotType.TYPE;
        public static final WolfType WOLF = WolfType.TYPE;
    }

    public abstract static class PetType<S extends PetType<S, MC, I>, MC extends EntityTameable, I extends Pet<I, MC, S>> extends TameableAnimalType<S, MC, I> {
        protected PetType(int id) {
            super(id);

            addValueAs(PetType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        @SuppressWarnings("rawtypes")
        public static PetType getByID(int id) {
            return getByID(PetType.class, id);
        }

        @SuppressWarnings("rawtypes")
        public static PetType getByID(int id, int data) {
            return getByID(PetType.class, id, data);
        }

        @SuppressWarnings("rawtypes")
        public static PetType[] values() {
            return values(PetType.class);
        }
    }

    // type utilities

    // instance utilities
}