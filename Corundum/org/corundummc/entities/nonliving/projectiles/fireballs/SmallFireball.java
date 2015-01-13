package org.corundummc.entities.nonliving.projectiles.fireballs;

import org.corundummc.entities.nonliving.projectiles.fireballs.Fireball.FireballType;

import net.minecraft.entity.projectile.EntitySmallFireball;

public class SmallFireball extends Fireball<SmallFireball, EntitySmallFireball, SmallFireball.SmallFireballType> {
    public SmallFireball() {
        super(new EntitySmallFireball(null));
    }

    public SmallFireball(EntitySmallFireball entityMC) {
        super(entityMC);
    }

    protected static class SmallFireballType extends FireballType<SmallFireballType, EntitySmallFireball, SmallFireball> {
        public static final SmallFireballType TYPE = new SmallFireballType();

        private SmallFireballType() {
            super(12);
        }

        // utilities
        @Override
        public boolean canExplode() {
            return false;
        }

        @Override
        public SmallFireball create() {
            return new SmallFireball();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public SmallFireball fromMC(EntitySmallFireball entityMC) {
            return new SmallFireball(entityMC);
        }
    }

    @Override
    public SmallFireballType getType() {
        return SmallFireballType.TYPE;
    }
}
