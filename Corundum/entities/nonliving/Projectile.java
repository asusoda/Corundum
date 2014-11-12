package Corundum.entities.nonliving;

import Corundum.entities.NonLiving;
import Corundum.entities.Velocity;
import Corundum.world.Location;

/**
 * Base for projectile non-living entities
 */
public class Projectile extends NonLiving {
    public Projectile(EntityType type, Location location) {
        super(type, location);
    }

    public Projectile(EntityType type, Location location, Velocity velocity) {
        this(type, location);
        this.setVelocity(velocity);
    }
}
