package org.corundummc.entities.living.mobs.animals.domestic.tameable.pet;

import net.minecraft.entity.passive.EntityWolf;

import org.corundummc.entities.living.mobs.animals.domestic.tameable.pet.Pet.PetType;

public class Wolf extends Pet<Wolf, EntityWolf, Wolf.WolfType> {
    public Wolf() {
        super(new EntityWolf(null));
    }

    protected Wolf(EntityWolf entityMC) {
        super(entityMC);
    }

    protected static class WolfType extends PetType<WolfType, EntityWolf, Wolf> {
        public static final WolfType TYPE = new WolfType();

        private WolfType() {
            super(95);
        }

        // overridden utilities
        @Override
        public Wolf create() {
            return new Wolf();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Wolf fromMC(EntityWolf entityMC) {
            return new Wolf(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public WolfType getType() {
        return WolfType.TYPE;
    }
}