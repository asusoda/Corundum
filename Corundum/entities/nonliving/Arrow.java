package Corundum.entities.nonliving;

import Corundum.entities.NonLiving;
import Corundum.world.Location;

/**
 * Class that represents an arrow entity, which is the projectile you get when fired from a bow.
 */
public class Arrow extends NonLiving {
    // Whether or not the arrow is touching the ground/ is stuck in a wall.
    private boolean isInGround;

    public Arrow(Location location) {
        super(NonLivingType.ARROW_PROJECTILE, location);
    }

    public Arrow(Location location, double motionX, double motionY, double motionZ) {
        this(location);
        this.setMotionX(motionX);
        this.setMotionY(motionY);
        this.setMotionZ(motionZ);
    }

    public void setInGround(boolean inGround) {
        this.isInGround = inGround;
    }

    public boolean getInGround() {
        return this.isInGround;
    }
}
