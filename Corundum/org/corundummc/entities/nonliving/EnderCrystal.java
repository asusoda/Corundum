package org.corundummc.entities.nonliving;

import org.corundummc.entities.nonliving.NonLivingEntity.NonLivingEntityType;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;

public class EnderCrystal extends NonLivingEntity<EnderCrystal, EntityEnderCrystal, EnderCrystal.EnderCrystalType> {
    public EnderCrystal() {
        super(new EntityEnderCrystal(null));
    }

    protected EnderCrystal(EntityEnderCrystal entityMC) {
        super(entityMC);
    }

    protected static class EnderCrystalType extends NonLivingEntityType<EnderCrystalType, EntityEnderCrystal, EnderCrystal> {
        public static final EnderCrystalType TYPE = new EnderCrystalType();

        private EnderCrystalType() {
            super(200);
        }

        // overridden utilities
        @Override
        public EnderCrystal create() {
            return new EnderCrystal();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public EnderCrystal fromMC(EntityEnderCrystal entityMC) {
            return new EnderCrystal(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public EnderCrystalType getType() {
        return EnderCrystalType.TYPE;
    }
}
