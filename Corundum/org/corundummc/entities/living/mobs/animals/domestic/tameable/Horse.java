package org.corundummc.entities.living.mobs.animals.domestic.tameable;

import net.minecraft.entity.passive.EntityHorse;
import org.corundummc.entities.living.mobs.animals.domestic.tameable.TameableAnimal.TameableAnimalType;

public class Horse extends TameableAnimal<Horse, EntityHorse, Horse.HorseType> {
    public Horse() {
        super(new EntityHorse(null));
    }

    protected Horse(EntityHorse entityMC) {
        super(entityMC);
    }

    protected static class HorseType extends TameableAnimalType<HorseType, EntityHorse, Horse> {
        public static final HorseType TYPE = new HorseType();

        private HorseType() {
            super(100);
        }

        // overridden utilities
        @Override
        public Horse create() {
            return new Horse();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Horse fromMC(EntityHorse entityMC) {
            return new Horse(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public HorseType getType() {
        return HorseType.TYPE;
    }
}