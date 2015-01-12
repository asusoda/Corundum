package org.corundummc.entities.nonliving.projectiles.fireballs;

import org.corundummc.entities.nonliving.projectiles.Projectile;
import org.corundummc.entities.nonliving.projectiles.fireballs.Fireball.FireballType;

import net.minecraft.entity.projectile.EntityLargeFireball;

// TODO: link Ghasts in the Javadoc below when they're available
/** This class represents a large fireball {@link Projectile}. This type of {@link Fireball} is fired by Ghasts and explodes on impact. */
public class LargeFireball extends Fireball<LargeFireball, EntityLargeFireball, LargeFireball.LargeFireballType> {
    public LargeFireball() {
        super(new EntityLargeFireball(null));
    }

    protected LargeFireball(EntityLargeFireball entityMC) {
        super(entityMC);
    }

    protected static class LargeFireballType extends FireballType<LargeFireballType, EntityLargeFireball, LargeFireball> {
        public static final LargeFireballType TYPE = new LargeFireballType();

        private LargeFireballType() {
            super(12);
        }

        // overridden utilities
        @Override
        public boolean canExplode() {
            return true;
        }

        @Override
        public LargeFireball create() {
            return new LargeFireball();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public LargeFireball fromMC(EntityLargeFireball entityMC) {
            return new LargeFireball(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public LargeFireballType getType() {
        return LargeFireballType.TYPE;
    }
}