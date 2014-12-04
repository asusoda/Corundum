package org.corundummc.entities.nonliving;

import org.corundummc.entities.Velocity;
import org.corundummc.world.Location;

/** Class that represents an arrow entity, which is the projectile you get when fired from a bow. */
public class Arrow extends Projectile {

    public Arrow() {
        super(ProjectileType.ARROW);
    }

    public boolean isInTheGround() {
        // TODO
        return false;
    }
}
