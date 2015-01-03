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
    }

    // instance utilities

    // overridden utilities
    @Override
    public EnderCrystalType getType() {
        return EnderCrystalType.TYPE;
    }
}
