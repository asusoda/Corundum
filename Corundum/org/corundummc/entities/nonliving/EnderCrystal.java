package org.corundummc.entities.nonliving;

import net.minecraft.entity.item.EntityEnderCrystal;

public class EnderCrystal extends NonLivingEntity<EntityEnderCrystal> {

    protected EnderCrystal(EntityEnderCrystal entityMC) {
        super(entityMC);
    }

    public EnderCrystal() {
        super(new EntityEnderCrystal(null));
    }
}
