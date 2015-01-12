package org.corundummc.entities.nonliving.projectiles.throwable;

import net.minecraft.entity.projectile.EntityPotion;

import org.corundummc.entities.nonliving.projectiles.Projectile;
import org.corundummc.entities.nonliving.projectiles.Projectile.ProjectileType;

public class SplashPotion extends Projectile<SplashPotion, EntityPotion, SplashPotion.SplashPotionType> {
    public SplashPotion() {
        super(new EntityPotion(null));
    }

    protected SplashPotion(EntityPotion entityMC) {
        super(entityMC);
    }

    protected static class SplashPotionType extends ProjectileType<SplashPotionType, EntityPotion, SplashPotion> {
        public static final SplashPotionType TYPE = new SplashPotionType();

        private SplashPotionType() {
            super(16);
        }

        // overridden utilities
        @Override
        public SplashPotion create() {
            return new SplashPotion();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public SplashPotion fromMC(EntityPotion entityMC) {
            return new SplashPotion(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public SplashPotionType getType() {
        return SplashPotionType.TYPE;
    }
}
