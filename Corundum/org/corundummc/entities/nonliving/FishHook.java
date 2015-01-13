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

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public FishHook fromMC(EntityFishHook entityMC) {
            return new FishHook(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public FishHookType getType() {
        return FishHookType.TYPE;
    }
}
