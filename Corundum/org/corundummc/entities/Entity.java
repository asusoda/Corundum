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

package org.corundummc.entities;

import net.minecraft.world.WorldServer;

import org.apache.logging.log4j.core.config.plugins.ResolverUtil.Test;
import org.corundummc.entities.living.LivingEntity.LivingEntityType;
import org.corundummc.entities.living.LivingEntity.LivingEntityTypes;
import org.corundummc.entities.nonliving.NonLivingEntity.NonLivingEntityType;
import org.corundummc.entities.nonliving.NonLivingEntity.NonLivingEntityTypes;
import org.corundummc.entities.nonliving.projectiles.Snowball;
import org.corundummc.items.Item;
import org.corundummc.types.CreatableType;
import org.corundummc.types.Physical;
import org.corundummc.types.Typed;
import org.corundummc.world.Location;
import org.corundummc.world.World;

public abstract class Entity<S extends Entity<S, MC, T>, MC extends net.minecraft.entity.Entity, T extends Entity.EntityType<T, MC, S>> extends Typed<T> implements Physical {
    protected final MC entityMC;

    protected Location location;
    protected Velocity velocity;

    protected Entity(MC entityMC) {
        this.entityMC = entityMC;

        location = new Location(entityMC.posX, entityMC.posY, entityMC.posZ, World.fromMCWorld((WorldServer) entityMC.worldObj));
    }

    public static interface EntityTypes extends LivingEntityTypes, NonLivingEntityTypes {
        /* public static final EntityType PLAYER = LivingEntityTypes.PLAYER; public static final EntityType DROPPED_ITEM = NonLivingEntityTypes.DROPPED_ITEM; public static
         * final EntityType XP_ORB = NonLivingEntityTypes.XP_ORB; public static final EntityType LEAD = NonLivingEntityTypes.LEAD; public static final EntityType PAINTING =
         * NonLivingEntityTypes.PAINTING; public static final EntityType ARROW = NonLivingEntityTypes.ARROW; public static final EntityType SNOWBALL =
         * NonLivingEntityTypes.SNOWBALL; public static final EntityType LARGE_FIREBALL = NonLivingEntityTypes.LARGE_FIREBALL; public static final EntityType SMALL_FIREBALL =
         * NonLivingEntityTypes.SMALL_FIREBALL; public static final EntityType ENDER_PEARL = NonLivingEntityTypes.ENDER_PEARL; public static final EntityType EYE_OF_ENDER =
         * NonLivingEntityTypes.EYE_OF_ENDER; public static final EntityType SPLASH_POTION = NonLivingEntityTypes.SPLASH_POTION; public static final EntityType
         * BOTTLE_O_ENCHANTING = NonLivingEntityTypes.BOTTLE_O_ENCHANTING; public static final EntityType ITEM_FRAME = NonLivingEntityTypes.ITEM_FRAME; public static final
         * EntityType WITHER_SKULL = NonLivingEntityTypes.WITHER_SKULL; public static final EntityType EGG = NonLivingEntityTypes.EGG; public static final EntityType
         * PRIMED_TNT = NonLivingEntityTypes.PRIMED_TNT; public static final EntityType FALLING_BLOCK = NonLivingEntityTypes.FALLING_BLOCK; // public static final EntityType
         * ARMOR_STAND = NonLivingEntityTypes.ARMOR_STAND; public static final EntityType COMMAND_MINECART = NonLivingEntityTypes.COMMAND_MINECART; public static final
         * EntityType BOAT = NonLivingEntityTypes.BOAT; public static final EntityType PASSENGER_MINECART = NonLivingEntityTypes.PASSENGER_MINECART; public static final
         * EntityType STORAGE_MINECART = NonLivingEntityTypes.STORAGE_MINECART; public static final EntityType POWERED_MINECART = NonLivingEntityTypes.POWERED_MINECART; public
         * static final EntityType TNT_MINECART = NonLivingEntityTypes.TNT_MINECART; public static final EntityType HOPPER_MINECART = NonLivingEntityTypes.HOPPER_MINECART;
         * public static final EntityType SPAWNER_MINECART = NonLivingEntityTypes.SPAWNER_MINECART; public static final EntityType CREEPER = LivingEntityTypes.CREEPER; public
         * static final EntityType SKELETON = LivingEntityTypes.SKELETON; public static final EntityType WITHER_SKELETON = LivingEntityTypes.WITHER_SKELETON; public static
         * final EntityType SPIDER = LivingEntityTypes.SPIDER; public static final EntityType GIANT = LivingEntityTypes.GIANT; public static final EntityType ZOMBIE =
         * LivingEntityTypes.ZOMBIE; public static final EntityType ZOMBIFIED_VILLAGER = LivingEntityTypes.ZOMBIFIED_VILLAGER; public static final EntityType BABY_ZOMBIE =
         * LivingEntityTypes.BABY_ZOMBIE; public static final EntityType BABY_ZOMBIFIED = LivingEntityTypes.BABY_ZOMBIFIED; public static final EntityType SLIME =
         * LivingEntityTypes.SLIME; public static final EntityType GHAST = LivingEntityTypes.GHAST; public static final EntityType ZOMBIE_PIGMAN =
         * LivingEntityTypes.ZOMBIE_PIGMAN; public static final EntityType ENDERMAN = LivingEntityTypes.ENDERMAN; public static final EntityType CAVE_SPIDER =
         * LivingEntityTypes.CAVE_SPIDER; public static final EntityType SILVERFISH = LivingEntityTypes.SILVERFISH; public static final EntityType BLAZE =
         * LivingEntityTypes.BLAZE; public static final EntityType MAGMA_CUBE = LivingEntityTypes.MAGMA_CUBE; public static final EntityType ENDER_DRAGON =
         * LivingEntityTypes.ENDER_DRAGON; public static final EntityType WITHER = LivingEntityTypes.WITHER; public static final EntityType BAT = LivingEntityTypes.BAT; public
         * static final EntityType WITCH = LivingEntityTypes.WITCH; public static final EntityType ENDERMITE = LivingEntityTypes.ENDERMITE; public static final EntityType
         * GUARDIAN = LivingEntityTypes.GUARDIAN; public static final EntityType ELDER_GUARDIAN = LivingEntityTypes.ELDER_GUARDIAN; public static final EntityType PIG =
         * LivingEntityTypes.PIG; public static final EntityType SHEEP = LivingEntityTypes.SHEEP; public static final EntityType COW = LivingEntityTypes.COW; public static
         * final EntityType CHICKEN = LivingEntityTypes.CHICKEN; public static final EntityType SQUID = LivingEntityTypes.SQUID; public static final EntityType WOLF =
         * LivingEntityTypes.WOLF; public static final EntityType MOOSHROOM = LivingEntityTypes.MOOSHROOM; public static final EntityType SNOW_GOLEM =
         * LivingEntityTypes.SNOW_GOLEM; public static final EntityType OCELOT = LivingEntityTypes.OCELOT; public static final EntityType IRON_GOLEM =
         * LivingEntityTypes.IRON_GOLEM; public static final EntityType HORSE = LivingEntityTypes.HORSE; public static final EntityType RABBIT = LivingEntityTypes.RABBIT;
         * public static final EntityType KILLER_RABBIT = LivingEntityTypes.KILLER_RABBIT; public static final EntityType FARMER = LivingEntityTypes.FARMER; public static
         * final EntityType LIBRARIAN = LivingEntityTypes.LIBRARIAN; public static final EntityType PRIEST = LivingEntityTypes.PRIEST; public static final EntityType
         * BLACKSMITH = LivingEntityTypes.BLACKSMITH; public static final EntityType BUTCHER = LivingEntityTypes.BUTCHER; public static final EntityType ENDER_CRYSTAL =
         * NonLivingEntityTypes.ENDER_CRYSTAL; */
    }

    public static abstract class EntityType<S extends EntityType<S, MC, I>, MC extends net.minecraft.entity.Entity, I extends Entity<I, MC, S>> extends CreatableType<S, I> {
        // TODO: Minecraft has no EntityType equivalent object, so we need to find a way to retrieve type info; EntityList will probably help

        @SuppressWarnings("javadoc")
        protected EntityType(int id, int data) {
            super(id, data);

            addValueAs(EntityType.class);
        }

        // pseudo-enum utils
        /**  */
        public static EntityType getByID(int id) {
            return getByID(EntityType.class, id);
        }

        public static EntityType getByID(int id, int data) {
            return getByID(EntityType.class, id, data);
        }

        public static EntityType[] values() {
            return values(EntityType.class);
        }
    }

    @Override
    public String getCustomName() {
        // TODO
        return null;
    }

    public Item[] getDrops() {
        // TODO
        return null;
    }

    @Override
    public Location getLocation() {
        location.setX(entityMC.posX);
        location.setY(entityMC.posY);
        location.setZ(entityMC.posZ);
        location.setWorld(World.fromMCWorld((WorldServer) entityMC.worldObj));

        return location;
    }

    public Velocity getVelocity() {
        velocity.setX(entityMC.motionX);
        velocity.setY(entityMC.motionY);
        velocity.setZ(entityMC.motionZ);

        return velocity;
    }

    /** This method determines whether or not this {@link Entity} is one of the "unsaved {@link Entity Entities}". Unsaved {@link Entity Entities} differ from other
     * {@link Entity Entities} in that they have no I.D. or data value associated with them and they are not saved when the server is stopped. Examples include
     * {@link EntityType#PLAYER player Entities}, {@link EntityType#EGG thrown eggs}, and {@link EntityType#LIGHTNING_BOLT lightning bolts}.
     * 
     * @return <b>true</b> if this {@link Entity} is an "unsaved {@link Entity}"; <b>false</b> otherwise. */
    public boolean isUnsaved() {
        return getType().getID() == -1;
    }

    public S setLocation(Location location) {
        entityMC.posX = location.getX();
        entityMC.posY = location.getY();
        entityMC.posZ = location.getZ();
        entityMC.worldObj = location.getWorld().getMCWorld();
        /* TODO TEST: I noticed that Minecraft's Entity.setWorld() does not set the dimension I.D., and as it is now (as of 12/3/14), we pass null Worlds into Minecraft
         * constructors when creating new entities, so I thought it might be a good idea to put this in here to make sure the dimension I.D. is set */
        entityMC.dimension = location.getWorld().getMCWorld().provider.dimensionId;

        return (S) this;
    }

    public S setRotation(Rotation rotation) {
        entityMC.rotationPitch = rotation.getPitch();
        entityMC.rotationYaw = rotation.getYaw();

        return (S) this;
    }

    public S setVelocity(Velocity velocity) {
        // TODO TEST
        entityMC.motionX = velocity.getX();
        entityMC.motionY = velocity.getY();
        entityMC.motionZ = velocity.getZ();

        return (S) this;
    }

    public S spawn(Location location) {
        // TODO
        return (S) this;
    }

    public S teleport(Location location) {
        setLocation(location);

        return (S) this;
    }
}
