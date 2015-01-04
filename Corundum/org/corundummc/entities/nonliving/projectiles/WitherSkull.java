package org.corundummc.entities.nonliving.projectiles;

import net.minecraft.entity.projectile.EntityWitherSkull;
import org.corundummc.entities.nonliving.projectiles.Projectile.ProjectileType;

public class WitherSkull extends Projectile<WitherSkull, EntityWitherSkull, WitherSkull.WitherSkullType> {
    public WitherSkull() {
        super(new EntityWitherSkull(null));
    }

    protected WitherSkull(EntityWitherSkull entityMC) {
        super(entityMC);
    }

    protected static class WitherSkullType extends ProjectileType<WitherSkullType, EntityWitherSkull, WitherSkull> {
        public static final WitherSkullType TYPE = new WitherSkullType();

        private WitherSkullType() {
            super(19);
        }

        // overridden utilities
        @Override
        public WitherSkull create() {
            return new WitherSkull();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public WitherSkullType getType() {
        return WitherSkullType.TYPE;
    }
}
