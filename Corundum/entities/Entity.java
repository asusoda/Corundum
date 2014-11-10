/** This code is property of the Corundum project managed under the Software Developers' Association of Arizona State University.
 *
 * Copying and use of this open-source code is permitted provided that the following requirements are met:
 *
 * - This code may not be used or distributed for private enterprise, including but not limited to personal or corporate profit. - Any products resulting from the copying,
 * use, or modification of this code may not claim endorsement by the Corundum project or any of its members or associates without written formal permission from the endorsing
 * party or parties. - This code may not be copied or used under any circumstances without the inclusion of this notice and mention of the contribution of the code by the
 * Corundum project. In source code form, this notice must be included as a comment as it is here; in binary form, proper documentation must be included with the final product
 * that includes this statement verbatim.
 *
 * @author REALDrummer */

package Corundum.entities;

import Corundum.exceptions.CorundumException;
import Corundum.world.Location;
import Corundum.world.Rotation;

public class Entity {
    private EntityType type;
    private Location location;
    private Rotation rotation;
    private float pitch = 0, yaw = 0;
    private double motionX = 0, motionY = 0, motionZ = 0;

    // Unfortunately, Minecraft has no way to get entities by coordinates, so storing an entity isn't quite doable.

    public Entity(EntityType type, Location location) {
        if (type != EntityType.INAPPROPRIATE) {
            this.type = type;
            this.location = location;
        } else {
            throw new BadEntityTypeException();
        }
    }

    public Entity(EntityType type, Location location, Rotation rotation) {
        this(type, location);
        this.rotation = rotation;
    }

    public EntityType getType() {
        return this.type;
    }

    public Location getLocation() {
        return this.location;
    }

    public Rotation getRotation() {
        return this.rotation;
    }

    public double getMotionX() {
        return this.motionX;
    }

    public double getMotionY() {
        return this.motionY;
    }

    public double getMotionZ() {
        return this.motionZ;
    }

    public void setMotionX(double motionX) {
        this.motionX = motionX;
    }

    public void setMotionY(double motionY) {
        this.motionY = motionY;
    }

    public void setMotionZ(double motionZ) {
        this.motionZ = motionZ;
    }

    public enum EntityType {
        PLAYER(false, false, false, true),
        PIG(false, false, true, false),
        COW(false, false, true, false),
        CHICKEN(false, false, true, false),
        HORSE(false, false, true, false),
        SHEEP(false, false, true, false),
        MOOSHROOM(false, false, true, false),
        VILLAGER(false, false, true, false),
        WOLF(false, false, true, true),
        // Ocelots are special as they are one of the few passive mobs able to despawn.
        OCELOT(false, true, true, false),
        SQUID(false, true, true, false),
        BAT(false, true, true, false),
        SKELETON(true, true, false, false),
        ZOMBIE(true, true, false, false),
        CREEPER(true, true, true, false),
        SPIDER(true, true, true, true),
        CAVE_SPIDER(true, true, true, true),
        ENDERMAN(true, true, true, true),
        ZOMBIE_PIGMAN(true, true, true, true),
        WITCH(true, true, true, false),
        BLAZE(true, true, true, false),
        SLIME(true, true, true, false),
        MAGMA_CUBE(true, true, true, false),
        GHAST(true, true, true, false),
        ENDERDRAGON(true, false, true, false),
        WITHER(true, false, true, false),
        // NON LIVING ENTITIES
        BOAT(false, false, false, false),
        ITEM_ENTITY(false, true, false, false),
        MINECART(false, false, false, false),
        XPORB(false, true, false, false),
        ARROW_PROJECTILE(false, true, false, false),
        SNOWBALL_PROJECTILE(false, false, false, false),
        CHICKEN_EGG_PROJECTILE(false, false, false, false),
        ENDER_PEARL_PROJECTILE(false, false, false, false),
        EYE_OF_ENDER_HOVERING(false, false, false, false),
        FIREWORK_ROCKET_LAUNCHED(false, true, false, false),
        PRIMED_TNT(false, true, false, false),
        FALLING_SAND(false, false, false, false),
        FISHING_ROD_BOBBER(false, false, false, false),
        LIGHTNING_BOLT(false, true, false, false),
        PAINTING(false, false, false, false),
        ITEM_FRAME(false, false, false, false),
        GHAST_FIREBALL(false, false, false, false),
        BLAZE_FIREBALL(false, false, false, false),
        ENDER_CRYSTAL(false, false, false, false),
        // Used in subclasses to error if a passed type is innapropriate.
        INAPPROPRIATE; // List does not include entities from 1.8.

        // Note, living applies to entities that are able to move by themselves, not mobs that aren't undead.
        // Also note that a despawnable entity is an entity that can exist in the world for an infinite amount of time
        // without external influence. Note that if they can despawn, it has to be 100% of the time.
        private boolean monster, despawns, living, neutral;

        private EntityType(boolean monster, boolean despawns, boolean living, boolean neutral) {
            this.monster = monster;
            this.despawns = despawns;
            this.living = living;
            this.neutral = neutral;
        }

        private EntityType() {
            // NOOP - This is for the NULL EntityType.
        }

        public boolean getIsMonster() {
            return this.monster;
        }

        public boolean getCanDespawn() {
            return this.despawns;
        }

        public boolean getIsLiving() {
            return this.living;
        }

        public boolean getIsNeutral() {
            return this.neutral;
        }
    }

    // Used to error if a type passed to a subclass is innapropriate for it's class logic, IE NonLivingType.ENDER_CRYSTAL
    // passed to a Projectile constructor.
    public class BadEntityTypeException extends CorundumException {
        public BadEntityTypeException(Object... additionalInformation) {
            super("A bad entity type was passed! This is usually EntityType/NonLivingType/etc.INAPPROPRIATE!", "an Entity object's constructor was " +
                    "passed a bad EntityType!");
        }
    }
}
