package Corundum.entities.nonliving;

import Corundum.entities.NonLiving;
import Corundum.world.Location;

/**
 * Base for Fireballs.
 */
public class Fireball extends Projectile {
    public Fireball(EntityType fireballType, Location location) {
        super(fireballType == EntityType.BLAZE_FIREBALL || fireballType == EntityType.GHAST_FIREBALL ? fireballType : EntityType.INAPPROPRIATE, location);
    }

    public boolean getExplodes() {
        return this.getType() == EntityType.GHAST_FIREBALL;
    }
}
