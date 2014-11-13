package Corundum.entities.nonliving;

import Corundum.world.Location;

/** Base for Fireballs. */
public class Fireball extends Projectile {
    public Fireball(Location location) {
        // TODO: what are the differences between Blaze and Ghast fireballs and which should we default to?
        this(FireballType.BLAZE_FIREBALL, location);
    }

    public Fireball(FireballType fireballType, Location location) {
        super(fireballType, location);
    }

    public static class FireballType extends ProjectileType<FireballType> {
        @SuppressWarnings("unchecked")
        public static final FireballType BLAZE_FIREBALL = new FireballType((ProjectileType<FireballType>) ProjectileType.BLAZE_FIREBALL), GHAST_FIREBALL = new FireballType(
                (ProjectileType<FireballType>) ProjectileType.GHAST_FIREBALL);

        protected FireballType(ProjectileType<FireballType> parent) {
            super(parent);
        }
    }

    public boolean getExplodes() {
        return this.getType() == EntityType.GHAST_FIREBALL;
    }
}
