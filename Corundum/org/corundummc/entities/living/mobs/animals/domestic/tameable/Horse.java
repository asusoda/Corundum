package org.corundummc.entities.living.mobs.animals.domestic.tameable;

import net.minecraft.entity.passive.EntityHorse;
import org.corundummc.entities.living.mobs.animals.domestic.tameable.Pet.PetType;

public class Horse extends Pet<Horse, EntityHorse, Horse.HorseType> {
    public Horse() {
        super(new EntityHorse(null));
    }

    protected Horse(EntityHorse entityMC) {
        super(entityMC);
    }

    protected static class HorseType extends PetType<HorseType, EntityHorse, Horse> {
        public static final HorseType TYPE = new HorseType();

        private HorseType() {
            super(100);
        }

        // overridden utilities
        @Override
        public Horse create() {
            return new Horse();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public HorseType getType() {
        return HorseType.TYPE;
    }
}