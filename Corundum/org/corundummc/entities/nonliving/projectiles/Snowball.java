package org.corundummc.entities.nonliving.projectiles;

import net.minecraft.entity.projectile.EntitySnowball;

import org.corundummc.entities.nonliving.projectiles.Projectile.ProjectileType;

public class Snowball extends Projectile<Snowball, EntitySnowball, Snowball.SnowballType> {
    public Snowball() {
        super(new EntitySnowball(null));
    }

    protected Snowball(EntitySnowball entityMC) {
        super(entityMC);
    }

    protected static class SnowballType extends ProjectileType<SnowballType, EntitySnowball, Snowball> {
        public static final SnowballType TYPE = new SnowballType();

        private SnowballType() {
            super(11);
        }

        // overridden utilities
        @Override
        public Snowball create() {
            return new Snowball();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public SnowballType getType() {
        return SnowballType.TYPE;
    }
}