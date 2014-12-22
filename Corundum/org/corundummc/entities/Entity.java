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

import org.corundummc.entities.living.LivingEntity.LivingEntityType;
import org.corundummc.entities.nonliving.NonLivingEntity.NonLivingEntityType;
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

    public static abstract class EntityType<S extends EntityType<S, MC, I>, MC extends net.minecraft.entity.Entity, I extends Entity<I, MC, S>> extends CreatableType<S, I> {
        public static final EntityType PLAYER = LivingEntityType.PLAYER;
        public static final EntityType DROPPED_ITEM = NonLivingEntityType.DROPPED_ITEM;
        public static final EntityType XP_ORB = NonLivingEntityType.XP_ORB;
        public static final EntityType LEAD = NonLivingEntityType.LEAD;
        public static final EntityType PAINTING = NonLivingEntityType.PAINTING;
        public static final EntityType ARROW = NonLivingEntityType.ARROW;
        public static final EntityType SNOWBALL = NonLivingEntityType.SNOWBALL;
        public static final EntityType LARGE_FIREBALL = NonLivingEntityType.LARGE_FIREBALL;
        public static final EntityType SMALL_FIREBALL = NonLivingEntityType.SMALL_FIREBALL;
        public static final EntityType ENDER_PEARL = NonLivingEntityType.ENDER_PEARL;
        public static final EntityType EYE_OF_ENDER = NonLivingEntityType.EYE_OF_ENDER;
        public static final EntityType SPLASH_POTION = NonLivingEntityType.SPLASH_POTION;
        public static final EntityType BOTTLE_O_ENCHANTING = NonLivingEntityType.BOTTLE_O_ENCHANTING;
        public static final EntityType ITEM_FRAME = NonLivingEntityType.ITEM_FRAME;
        public static final EntityType WITHER_SKULL = NonLivingEntityType.WITHER_SKULL;
        public static final EntityType EGG = NonLivingEntityType.EGG;
        public static final EntityType TNT = NonLivingEntityType.TNT;
        public static final EntityType FALLING_BLOCK = NonLivingEntityType.FALLING_BLOCK;
        // public static final EntityType ARMOR_STAND = NonLivingEntityType.ARMOR_STAND;
        public static final EntityType COMMAND_MINECART = NonLivingEntityType.COMMAND_MINECART;
        public static final EntityType BOAT = NonLivingEntityType.BOAT;
        public static final EntityType PASSENGER_MINECART = NonLivingEntityType.PASSENGER_MINECART;
        public static final EntityType STORAGE_MINECART = NonLivingEntityType.STORAGE_MINECART;
        public static final EntityType POWERED_MINECART = NonLivingEntityType.POWERED_MINECART;
        public static final EntityType TNT_MINECART = NonLivingEntityType.TNT_MINECART;
        public static final EntityType HOPPER_MINECART = NonLivingEntityType.HOPPER_MINECART;
        public static final EntityType SPAWNER_MINECART = NonLivingEntityType.SPAWNER_MINECART;
        public static final EntityType CREEPER = LivingEntityType.CREEPER;
        public static final EntityType SKELETON = LivingEntityType.SKELETON;
        public static final EntityType WITHER_SKELETON = LivingEntityType.WITHER_SKELETON;
        public static final EntityType SPIDER = LivingEntityType.SPIDER;
        public static final EntityType GIANT = LivingEntityType.GIANT;
        public static final EntityType ZOMBIE = LivingEntityType.ZOMBIE;
        public static final EntityType ZOMBIFIED = LivingEntityType.ZOMBIFIED;
        public static final EntityType BABY_ZOMBIE = LivingEntityType.BABY_ZOMBIE;
        public static final EntityType BABY_ZOMBIFIED = LivingEntityType.BABY_ZOMBIFIED;
        public static final EntityType SLIME = LivingEntityType.SLIME;
        public static final EntityType GHAST = LivingEntityType.GHAST;
        public static final EntityType ZOMBIE_PIGMAN = LivingEntityType.ZOMBIE_PIGMAN;
        public static final EntityType ENDERMAN = LivingEntityType.ENDERMAN;
        public static final EntityType CAVE_SPIDER = LivingEntityType.CAVE_SPIDER;
        public static final EntityType SILVERFISH = LivingEntityType.SILVERFISH;
        public static final EntityType BLAZE = LivingEntityType.BLAZE;
        public static final EntityType MAGMA_CUBE = LivingEntityType.MAGMA_CUBE;
        public static final EntityType ENDER_DRAGON = LivingEntityType.ENDER_DRAGON;
        public static final EntityType WITHER = LivingEntityType.WITHER;
        public static final EntityType BAT = LivingEntityType.BAT;
        public static final EntityType WITCH = LivingEntityType.WITCH;
        public static final EntityType ENDERMITE = LivingEntityType.ENDERMITE;
        public static final EntityType GUARDIAN = LivingEntityType.GUARDIAN;
        public static final EntityType ELDER_GUARDIAN = LivingEntityType.ELDER_GUARDIAN;
        public static final EntityType PIG = LivingEntityType.PIG;
        public static final EntityType SHEEP = LivingEntityType.SHEEP;
        public static final EntityType COW = LivingEntityType.COW;
        public static final EntityType CHICKEN = LivingEntityType.CHICKEN;
        public static final EntityType SQUID = LivingEntityType.SQUID;
        public static final EntityType WOLF = LivingEntityType.WOLF;
        public static final EntityType MOOSHROOM = LivingEntityType.MOOSHROOM;
        public static final EntityType SNOW_GOLEM = LivingEntityType.SNOW_GOLEM;
        public static final EntityType OCELOT = LivingEntityType.OCELOT;
        public static final EntityType IRON_GOLEM = LivingEntityType.IRON_GOLEM;
        public static final EntityType HORSE = LivingEntityType.HORSE;
        public static final EntityType RABBIT = LivingEntityType.RABBIT;
        public static final EntityType KILLER_RABBIT = LivingEntityType.KILLER_RABBIT;
        public static final EntityType FARMER = LivingEntityType.FARMER;
        public static final EntityType LIBRARIAN = LivingEntityType.LIBRARIAN;
        public static final EntityType PRIEST = LivingEntityType.PRIEST;
        public static final EntityType BLACKSMITH = LivingEntityType.BLACKSMITH;
        public static final EntityType BUTCHER = LivingEntityType.BUTCHER;
        public static final EntityType ENDER_CRYSTAL = NonLivingEntityType.ENDER_CRYSTAL;

        // TODO: Minecraft has no EntityType equivalent object, so we need to find a way to retrieve type info; EntityList will probably help

        @SuppressWarnings("javadoc")
        protected EntityType(int id, int data) {
            super(id, data);

            addValueAs(EntityType.class);
        }

        // pseudo-enum utils
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
