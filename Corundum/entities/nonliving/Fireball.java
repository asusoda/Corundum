package Corundum.entities.nonliving;

import Corundum.entities.NonLiving;
import Corundum.world.Location;

/**
 * Base for Fireballs.
 */
public class Fireball extends Projectile {
    public Fireball(NonLivingType fireballType, Location location) {
        super(fireballType == NonLivingType.BLAZE_FIREBALL || fireballType == NonLivingType.GHAST_FIREBALL ? fireballType : NonLivingType.INNAPROPRIATE, location);
    }

    public boolean getExplodes() {
        return this.nonLivingType == NonLivingType.GHAST_FIREBALL;
    }
}
