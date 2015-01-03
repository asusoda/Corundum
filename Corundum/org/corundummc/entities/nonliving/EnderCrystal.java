package org.corundummc.entities.nonliving;

import org.corundummc.entities.nonliving.NonLivingEntity.NonLivingEntityType;

import net.minecraft.entity.item.EntityEnderCrystal;

public class EnderCrystal extends NonLivingEntity<EnderCrystal, EntityEnderCrystal, EnderCrystal.EnderCrystalType> {

    protected EnderCrystal(EntityEnderCrystal entityMC) {
        super(entityMC);
    }

    public EnderCrystal() {
        super(new EntityEnderCrystal(null));
    }

    public static final class EnderCrystalType extends NonLivingEntityType<EnderCrystalType, EntityEnderCrystal, EnderCrystal> {
        public static final EnderCrystalType INSTANCE = new EnderCrystalType();

        protected EnderCrystalType() {
            super(200);
        }
    }

    @Override
    public EnderCrystalType getType() {
        return EnderCrystalType.INSTANCE;
    }
}
