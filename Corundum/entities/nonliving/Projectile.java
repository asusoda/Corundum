package Corundum.entities.nonliving;

import Corundum.entities.NonLiving;
import Corundum.world.Location;

/**
 * Base for projectile non-living entities
 */
public class Projectile extends NonLiving {
    public Projectile(NonLivingType type, Location location) {
        super(type, location);
    }

    public Projectile(NonLivingType type, Location location, double motionX, double motionY, double motionZ) {
        this(type, location);
        this.setMotionX(motionX);
        this.setMotionY(motionY);
        this.setMotionZ(motionZ);
    }
}
