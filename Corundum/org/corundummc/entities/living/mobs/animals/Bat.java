package org.corundummc.entities.living.mobs.animals;

import net.minecraft.entity.passive.EntityBat;
import org.corundummc.entities.living.mobs.animals.Animal.AnimalType;

public class Bat extends Animal<Bat, EntityBat, Bat.BatType> {
    public Bat() {
        super(new EntityBat(null));
    }

    protected Bat(EntityBat entityMC) {
        super(entityMC);
    }

    protected static class BatType extends AnimalType<BatType, EntityBat, Bat> {
        public static final BatType TYPE = new BatType();

        private BatType() {
            super(65);
        }

        // overridden utilities
        @Override
        public Bat create() {
            return new Bat();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public BatType getType() {
        return BatType.TYPE;
    }
}
