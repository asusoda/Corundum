package org.corundummc.entities.nonliving;

import net.minecraft.entity.projectile.EntityFishHook;

public class FishHook extends NonLivingEntity<EntityFishHook> {

    protected FishHook(EntityFishHook entityMC) {
        super(entityMC);
    }

    public FishHook() {
        super(new EntityFishHook(null));
    }
}
