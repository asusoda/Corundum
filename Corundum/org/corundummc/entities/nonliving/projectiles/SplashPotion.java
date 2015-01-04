package org.corundummc.entities.nonliving.projectiles;

import net.minecraft.entity.projectile.EntityPotion;
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
    }

    // instance utilities

    // overridden utilities
    @Override
    public SplashPotionType getType() {
        return SplashPotionType.TYPE;
    }
}
