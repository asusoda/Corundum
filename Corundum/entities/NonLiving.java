package Corundum.entities;

import Corundum.world.Location;
import Corundum.world.Rotation;

/**
 * Class that represents an entity that isn't alive like Cows. Examples include arrows, item entities, boats, etc.
 * Also a base for the specialised non living Entity classes.
 */
public class NonLiving extends Entity {
    public NonLivingType nonLivingType;

    public NonLiving(NonLivingType type, Location location) {
        super(type.getEntityType(), location);
        this.nonLivingType = type;
    }

    public NonLiving(NonLivingType type, Location location, Rotation rotation) {
        super(type.getEntityType(), location, rotation);
    }

    public enum NonLivingType {
        BOAT(EntityType.BOAT, true),
        ITEM_ENTITY(EntityType.ITEM_ENTITY, false),
        MINECART(EntityType.MINECART, true),
        XPORB(EntityType.XPORB, true),
        ARROW_PROJECTILE(EntityType.ARROW_PROJECTILE, false),
        SNOWBALL_PROJECTILE(EntityType.SNOWBALL_PROJECTILE, false),
        CHICKEN_EGG_PROJECTILE(EntityType.CHICKEN_EGG_PROJECTILE, false),
        ENDER_PEARL_PROJECTILE(EntityType.ENDER_PEARL_PROJECTILE, false),
        EYE_OF_ENDER_HOVERING(EntityType.EYE_OF_ENDER_HOVERING, false),
        FIREWORK_ROCKET_LAUNCHED(EntityType.FIREWORK_ROCKET_LAUNCHED, false),
        PRIMED_TNT(EntityType.PRIMED_TNT, false),
        FALLING_SAND(EntityType.FALLING_SAND, false),
        FISHING_ROD_BOBBER(EntityType.FISHING_ROD_BOBBER, false),
        LIGHTNING_BOLT(EntityType.LIGHTNING_BOLT, false),
        PAINTING(EntityType.PAINTING, false),
        ITEM_FRAME(EntityType.ITEM_FRAME, false),
        GHAST_FIREBALL(EntityType.GHAST_FIREBALL, true),
        BLAZE_FIREBALL(EntityType.BLAZE_FIREBALL, false),
        ENDER_CRYSTAL(EntityType.ENDER_CRYSTAL, false),
        INNAPROPRIATE(EntityType.INNAPROPRIATE, false);
        // Field that represents whether or not the entity can be moved without environmental influence after being
        // spawned with any initial motion. This DOES NOT include items, as they are removed from the world when picked up.
        private boolean moveable;
        private EntityType entityType;

        private NonLivingType(EntityType type, boolean moveable) {
            this.entityType = type;
            this.moveable = moveable;
        }

        public EntityType getEntityType() {
            return this.entityType;
        }
    }
}
