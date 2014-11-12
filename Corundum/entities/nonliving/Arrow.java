package Corundum.entities.nonliving;

import Corundum.entities.Velocity;
import Corundum.world.Location;

/**
 * Class that represents an arrow entity, which is the projectile you get when fired from a bow.
 */
public class Arrow extends Projectile {
    // Whether or not the arrow is touching the ground/ is stuck in a wall.
    private boolean isInGround;

    public Arrow(Location location) {
        super(EntityType.ARROW_PROJECTILE, location);
    }

    public Arrow(Location location, Velocity velocity) {
        super(EntityType.ARROW_PROJECTILE, location, velocity);
    }

    public void setInGround(boolean inGround) {
        this.isInGround = inGround;
    }

    public boolean getInGround() {
        return this.isInGround;
    }
}
