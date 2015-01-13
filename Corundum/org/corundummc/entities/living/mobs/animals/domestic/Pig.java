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

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Pig fromMC(EntityPig entityMC) {
            return new Pig(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public PigType getType() {
        return PigType.TYPE;
    }
}