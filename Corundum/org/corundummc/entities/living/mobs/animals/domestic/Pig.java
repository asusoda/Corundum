package org.corundummc.entities.living.mobs.animals.domestic;

import net.minecraft.entity.passive.EntityPig;
import org.corundummc.entities.living.mobs.animals.domestic.DomesticAnimal.DomesticAnimalType;

public class Pig extends DomesticAnimal<Pig, EntityPig, Pig.PigType> {
    public Pig() {
        super(new EntityPig(null));
    }

    protected Pig(EntityPig entityMC) {
        super(entityMC);
    }

    protected static class PigType extends DomesticAnimalType<PigType, EntityPig, Pig> {
        public static final PigType TYPE = new PigType();

        private PigType() {
            super(90);
        }

        // overridden utilities
        @Override
        public Pig create() {
            return new Pig();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public PigType getType() {
        return PigType.TYPE;
    }
}