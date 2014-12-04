package org.corundummc.entities.nonliving;

import org.corundummc.world.Location;

/** Base for Fireballs. */
public class Fireball extends Projectile {
    protected Fireball(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }

    public static class FireballType extends ProjectileType<FireballType> {
        @SuppressWarnings("unchecked")
        public static final FireballType BLAZE_FIREBALL = new FireballType((ProjectileType<FireballType>) ProjectileType.SMALL_FIREBALL), GHAST_FIREBALL = new FireballType(
                (ProjectileType<FireballType>) ProjectileType.LARGE_FIREBALL);

        protected FireballType(ProjectileType<FireballType> parent) {
            super(parent);
        }
    }

    public boolean getExplodes() {
        return this.getType() == EntityType.GHAST_FIREBALL;
    }
}
