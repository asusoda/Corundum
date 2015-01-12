package org.corundummc.entities.living.mobs.animals.domestic;

import net.minecraft.entity.passive.EntitySheep;
import org.corundummc.entities.living.mobs.animals.domestic.DomesticAnimal.DomesticAnimalType;

public class Sheep extends DomesticAnimal<Sheep, EntitySheep, Sheep.SheepType> {
    public Sheep() {
        super(new EntitySheep(null));
    }

    protected Sheep(EntitySheep entityMC) {
        super(entityMC);
    }

    protected static class SheepType extends DomesticAnimalType<SheepType, EntitySheep, Sheep> {
        public static final SheepType TYPE = new SheepType();

        private SheepType() {
            super(91);
        }

        // overridden utilities
        @Override
        public Sheep create() {
            return new Sheep();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Sheep fromMC(EntitySheep entityMC) {
            return new Sheep(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public SheepType getType() {
        return SheepType.TYPE;
    }
}