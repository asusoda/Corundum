package org.corundummc.entities.nonliving;

import net.minecraft.entity.projectile.EntityFishHook;
import org.corundummc.entities.nonliving.NonLivingEntity.NonLivingEntityType;

public class FishHook extends NonLivingEntity<FishHook, EntityFishHook, FishHook.FishHookType> {
    public FishHook() {
        super(new EntityFishHook(null));
    }

    protected FishHook(EntityFishHook entityMC) {
        super(entityMC);
    }

    protected static class FishHookType extends NonLivingEntityType<FishHookType, EntityFishHook, FishHook> {
        public static final FishHookType TYPE = new FishHookType();

        private FishHookType() {
            super(-1);
        }

        // overridden utilities
        @Override
        public FishHook create() {
            return new FishHook();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public FishHookType getType() {
        return FishHookType.TYPE;
    }
}
