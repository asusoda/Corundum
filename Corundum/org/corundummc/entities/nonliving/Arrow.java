package org.corundummc.entities.nonliving;

import org.corundummc.entities.Velocity;
import org.corundummc.world.Location;

/** Class that represents an arrow entity, which is the projectile you get when fired from a bow. */
public class Arrow extends Projectile {

    public Arrow(Location location) {
        super(ProjectileType.ARROW, location);
    }

    public Arrow(Location location, Velocity velocity) {
        super(ProjectileType.ARROW, location);

        setVelocity(velocity);
    }

    public boolean isInTheGround() {
        // TODO
        return false;
    }
}
