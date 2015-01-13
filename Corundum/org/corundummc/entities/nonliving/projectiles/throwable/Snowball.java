package org.corundummc.entities.nonliving.projectiles.throwable;

import net.minecraft.entity.projectile.EntitySnowball;

import org.corundummc.entities.nonliving.projectiles.Projectile;
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

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Snowball fromMC(EntitySnowball entityMC) {
            return new Snowball(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public SnowballType getType() {
        return SnowballType.TYPE;
    }
}