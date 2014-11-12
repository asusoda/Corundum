package Corundum.entities.nonliving;

import Corundum.entities.NonLiving;
import Corundum.world.Location;
import Corundum.world.Rotation;

/**
 * Base for non living rideable entities. This is for classes for entities that are both non living and can be ridden
 * without editing it's rideable NBT tag (ie ingame).
 */
public class RideableNonLiving extends NonLiving {
    public RideableNonLiving(EntityType nonLivingType, Location location) {
        super(nonLivingType == EntityType.BOAT || nonLivingType == EntityType.MINECART ? nonLivingType : EntityType.INAPPROPRIATE, location);
    }

    public RideableNonLiving(EntityType nonLivingType, Location location, Rotation rotation) {
        super(nonLivingType, location, rotation);
    }
}
