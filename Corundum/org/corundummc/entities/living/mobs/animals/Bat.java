package org.corundummc.entities.living.mobs.animals;

import net.minecraft.entity.EntityList;
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

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        public Bat fromMC(EntityBat entityMC) {
            return new Bat(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public BatType getType() {
        return BatType.TYPE;
    }
}
