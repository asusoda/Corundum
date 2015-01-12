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

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Chicken fromMC(EntityChicken entityMC) {
            return new Chicken(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public ChickenType getType() {
        return ChickenType.TYPE;
    }
}