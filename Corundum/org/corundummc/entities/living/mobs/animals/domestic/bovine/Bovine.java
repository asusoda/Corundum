package org.corundummc.entities.living.mobs.animals.domestic.bovine;

import net.minecraft.entity.passive.EntityCow;

import org.corundummc.entities.living.mobs.animals.domestic.DomesticAnimal;
import org.corundummc.entities.living.mobs.animals.domestic.bovine.Cow.CowType;
import org.corundummc.entities.living.mobs.animals.domestic.bovine.Mooshroom.MooshroomType;

/** This class represents both different types of cows: normal {@link Cow}s and {@link Mooshroom}s.
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class Bovine<S extends Bovine<S, MC, T>, MC extends EntityCow, T extends Bovine.BovineType<T, MC, S>> extends DomesticAnimal<S, MC, T> {
    protected Bovine(MC entityMC) {
        super(entityMC);
    }

    public static interface BovineTypes {
        public static final CowType COW = CowType.TYPE;
        public static final MooshroomType MOOSHROOM = MooshroomType.TYPE;
    }

    public abstract static class BovineType<S extends BovineType<S, MC, I>, MC extends EntityCow, I extends Bovine<I, MC, S>> extends DomesticAnimalType<S, MC, I> {
        protected BovineType(int id) {
            super(id);

            addValueAs(BovineType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        @SuppressWarnings("rawtypes")
        public static BovineType getByID(int id) {
            return getByID(BovineType.class, id);
        }

        @SuppressWarnings("rawtypes")
        public static BovineType getByID(int id, int data) {
            return getByID(BovineType.class, id, data);
        }

        @SuppressWarnings("rawtypes")
        public static BovineType[] values() {
            return values(BovineType.class);
        }
    }

    // type utilities

    // instance utilities
}