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

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public WitherSkull fromMC(EntityWitherSkull entityMC) {
            return new WitherSkull(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public WitherSkullType getType() {
        return WitherSkullType.TYPE;
    }
}
