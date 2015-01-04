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
    }

    @Override
    public SmallFireballType getType() {
        return SmallFireballType.TYPE;
    }
}
