package Corundum.entities.nonliving;

import Corundum.entities.NonLiving;
import Corundum.world.Location;

/**
 * Base for non living rideable entities. This is for classes for entities that are both non living and can be ridden
 * without editing it's rideable NBT tag (ie ingame).
 */
public class RideableNonLiving extends NonLiving {
    public RideableNonLiving(NonLivingType nonLivingType, Location location) {
        super(nonLivingType == NonLivingType.BOAT || nonLivingType == NonLivingType.MINECART ? nonLivingType : null, location);
    }

    public RideableNonLiving(NonLivingType nonLivingType, Location location, float pitch, float yaw) {
        super(nonLivingType, location, pitch, yaw);
    }
}
