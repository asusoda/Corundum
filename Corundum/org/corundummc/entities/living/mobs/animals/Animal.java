package org.corundummc.entities.living.mobs.animals;

import net.minecraft.entity.EntityLiving;

import org.corundummc.entities.living.LivingEntity;
import org.corundummc.entities.living.mobs.Mob;
import org.corundummc.entities.living.mobs.animals.Bat.BatType;
import org.corundummc.entities.living.mobs.animals.Squid.SquidType;
import org.corundummc.entities.living.mobs.animals.domestic.DomesticAnimal.DomesticAnimalTypes;

// TODO: link Cow and Sheep
/** This class represents non-hostile {@link Mob}s akin to real animals such as {@link Bat}s and {@link Cow}s and {@link Sheep}.
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

    public static interface AnimalTypes extends DomesticAnimalTypes {
        public static final BatType BAT = BatType.TYPE;
        public static final SquidType SQUID = SquidType.TYPE;
    }

    public abstract static class AnimalType<S extends AnimalType<S, MC, I>, MC extends EntityLiving, I extends Animal<I, MC, S>> extends LivingEntityType<S, MC, I> {
        protected AnimalType(int id) {
            super(id, -1);

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