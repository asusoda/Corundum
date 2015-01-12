package org.corundummc.entities.living.mobs.animals.domestic.tameable;

import org.corundummc.entities.living.mobs.animals.Animal;
import org.corundummc.entities.living.mobs.animals.domestic.DomesticAnimal;
import org.corundummc.entities.living.mobs.animals.domestic.tameable.Horse.HorseType;
import org.corundummc.entities.living.mobs.animals.domestic.tameable.pet.Ocelot;
import org.corundummc.entities.living.mobs.animals.domestic.tameable.pet.Wolf;
import org.corundummc.entities.living.mobs.animals.domestic.tameable.pet.Pet.PetTypes;

import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;

// TODO: link FoodTypes in the Javadoc below
/** This class represents an {@link Animal} that can be tamed and controlled by feeding it a certain {@link FoodType} like {@link Wolf Wolves} (a.k.a. dogs), {@link Ocelot}s
 * (a.k.a. cats), or {@link Horse}s.
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class TameableAnimal<S extends TameableAnimal<S, MC, T>, MC extends EntityAnimal, T extends TameableAnimal.TameableAnimalType<T, MC, S>> extends
        DomesticAnimal<S, MC, T> {
    protected TameableAnimal(MC entityMC) {
        super(entityMC);
    }

    public static interface TameableAnimalTypes extends PetTypes {
        public static final HorseType HORSE = HorseType.TYPE;
    }

    public abstract static class TameableAnimalType<S extends TameableAnimalType<S, MC, I>, MC extends EntityAnimal, I extends TameableAnimal<I, MC, S>> extends
            DomesticAnimalType<S, MC, I> {
        protected TameableAnimalType(int id) {
            super(id);

            addValueAs(TameableAnimalType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        @SuppressWarnings("rawtypes")
        public static TameableAnimalType getByID(int id) {
            return getByID(TameableAnimalType.class, id);
        }

        @SuppressWarnings("rawtypes")
        public static TameableAnimalType getByID(int id, int data) {
            return getByID(TameableAnimalType.class, id, data);
        }

        @SuppressWarnings("rawtypes")
        public static TameableAnimalType[] values() {
            return values(TameableAnimalType.class);
        }
    }

    // type utilities

    // instance utilities
}