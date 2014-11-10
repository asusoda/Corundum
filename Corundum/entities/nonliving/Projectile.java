package Corundum.entities.nonliving;

import Corundum.entities.NonLiving;
import Corundum.world.Location;

/**
 * Base for projectile non-living entities
 */
public class Projectile extends NonLiving {
    public Projectile(EntityType type, Location location) {
        super(type, location);
    }

    public Projectile(EntityType type, Location location, double motionX, double motionY, double motionZ) {
        this(type, location);
        this.setMotionX(motionX);
        this.setMotionY(motionY);
        this.setMotionZ(motionZ);
    }
}
