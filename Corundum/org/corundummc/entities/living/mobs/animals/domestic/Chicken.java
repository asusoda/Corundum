package org.corundummc.entities.living.mobs.animals.domestic;

import net.minecraft.entity.passive.EntityChicken;
import org.corundummc.entities.living.mobs.animals.domestic.DomesticAnimal.DomesticAnimalType;

public class Chicken extends DomesticAnimal<Chicken, EntityChicken, Chicken.ChickenType> {
    public Chicken() {
        super(new EntityChicken(null));
    }

    protected Chicken(EntityChicken entityMC) {
        super(entityMC);
    }

    protected static class ChickenType extends DomesticAnimalType<ChickenType, EntityChicken, Chicken> {
        public static final ChickenType TYPE = new ChickenType();

        private ChickenType() {
            super(93);
        }

        // overridden utilities
        @Override
        public Chicken create() {
            return new Chicken();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public ChickenType getType() {
        return ChickenType.TYPE;
    }
}