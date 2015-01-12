package org.corundummc.entities.living.mobs.animals.domestic;

import net.minecraft.entity.passive.EntityAnimal;

import org.corundummc.entities.living.mobs.Ageable;
import org.corundummc.entities.living.mobs.animals.Animal;
import org.corundummc.entities.living.mobs.animals.domestic.Chicken.ChickenType;
import org.corundummc.entities.living.mobs.animals.domestic.Pig.PigType;
import org.corundummc.entities.living.mobs.animals.domestic.Sheep.SheepType;
import org.corundummc.entities.living.mobs.animals.domestic.bovine.Bovine.BovineTypes;
import org.corundummc.entities.living.mobs.animals.domestic.tameable.TameableAnimal.TameableAnimalTypes;

// TODO: import and link Cow, Wolf, Ocelot, and TamedAnimal in the Javadoc below
/** This class represents {@link Animal}s that can be bred and domesticated like {@link Bovine}s and {@link Wolf Wolves}. Please do not confuse this with {@link TameableAnimal}
 * s, which only includes {@link Animal}s that can be tamed and trained like {@link Wolf Wolves} (a.k.a. dogs) and {@link Ocelot}s (a.k.a. cats).
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class DomesticAnimal<S extends DomesticAnimal<S, MC, T>, MC extends EntityAnimal, T extends DomesticAnimal.DomesticAnimalType<T, MC, S>> extends
        Animal<S, MC, T> implements Ageable {
    protected DomesticAnimal(MC entityMC) {
        super(entityMC);
    }

    public static interface DomesticAnimalTypes extends BovineTypes, TameableAnimalTypes {
        public static final ChickenType CHICKEN = ChickenType.TYPE;
        public static final PigType PIG = PigType.TYPE;
        public static final SheepType SHEEP = SheepType.TYPE;
    }

    public abstract static class DomesticAnimalType<S extends DomesticAnimalType<S, MC, I>, MC extends EntityAnimal, I extends DomesticAnimal<I, MC, S>> extends
            AnimalType<S, MC, I> {
        protected DomesticAnimalType(int id) {
            super(id);

            addValueAs(DomesticAnimalType.class);
        }

        // abstract utilities
        /* As far as I can tell, the only place the breeding foods can be found in Minecraft code is when they are added to the A.I. tasks list in the constructors, but tasks
         * can only be added to this list, not retrieved, so I see no other option but to code this information ourselves */
        // TODO: public abstract BreedingFoodType[] getBreedingFoods();

        // TODO: public abstract TemptingItemType[] getTemptingFoods();

        // overridden utilities

        // pseudo-enum utilities
        @SuppressWarnings("rawtypes")
        public static DomesticAnimalType getByID(int id) {
            return getByID(DomesticAnimalType.class, id);
        }

        @SuppressWarnings("rawtypes")
        public static DomesticAnimalType getByID(int id, int data) {
            return getByID(DomesticAnimalType.class, id, data);
        }

        @SuppressWarnings("rawtypes")
        public static DomesticAnimalType[] values() {
            return values(DomesticAnimalType.class);
        }
    }

    // type utilities
    // TODO: public abstract BreedingFoodType[] getBreedingFoods() { return getType().getBreedingFoods(); }

    // TODO: public TemptingItemType[] getTemptingFoods() { return getType().getTemptingFoods(); }

    // instance utilities
    @Override
    public boolean isABaby() {
        return entityMC.isChild();
    }
}