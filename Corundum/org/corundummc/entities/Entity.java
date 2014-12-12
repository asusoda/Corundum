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

import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.world.WorldServer;

import org.corundummc.entities.living.LivingEntity.LivingEntityType;
import org.corundummc.entities.nonliving.NonLivingEntity.NonLivingEntityType;
import org.corundummc.exceptions.CIE;
import org.corundummc.items.Item;
import org.corundummc.types.Creatable;
import org.corundummc.types.CreatableType;
import org.corundummc.types.Physical;
import org.corundummc.types.Typed;
import org.corundummc.types.IDedTypeWithData;
import org.corundummc.world.Location;
import org.corundummc.world.World;

public class Entity extends Creatable implements Physical {
    public final net.minecraft.entity.Entity entityMC;

    protected Entity(net.minecraft.entity.Entity entityMC) {
        this.entityMC = entityMC;
    }

    public Item[] getDrops() {
        // TODO
        return null;
    }

    public static abstract class EntityType<T extends EntityType<T>> extends CreatableType<EntityType<T>> {
        // TODO: replace all new EntityTypes here with subclass types where applicable

        public static final EntityType<?> PLAYER = LivingEntityType.PLAYER;
        public static final EntityType<?> DROPPED_ITEM = NonLivingEntityType.DROPPED_ITEM;
        public static final EntityType<?> XP_ORB = NonLivingEntityType.XP_ORB;
        public static final EntityType<?> LEAD = NonLivingEntityType.LEAD;
        public static final EntityType<?> PAINTING = NonLivingEntityType.PAINTING;
        public static final EntityType<?> ARROW = NonLivingEntityType.ARROW;
        public static final EntityType<?> SNOWBALL = NonLivingEntityType.SNOWBALL;
        public static final EntityType<?> LARGE_FIREBALL = NonLivingEntityType.LARGE_FIREBALL;
        public static final EntityType<?> SMALL_FIREBALL = NonLivingEntityType.SMALL_FIREBALL;
        public static final EntityType<?> ENDER_PEARL = NonLivingEntityType.ENDER_PEARL;
        public static final EntityType<?> EYE_OF_ENDER = NonLivingEntityType.EYE_OF_ENDER;
        public static final EntityType<?> SPLASH_POTION = NonLivingEntityType.SPLASH_POTION;
        public static final EntityType<?> BOTTLE_O_ENCHANTING = NonLivingEntityType.BOTTLE_O_ENCHANTING;
        public static final EntityType<?> ITEM_FRAME = NonLivingEntityType.ITEM_FRAME;
        public static final EntityType<?> WITHER_SKULL = NonLivingEntityType.WITHER_SKULL;
        public static final EntityType<?> EGG = NonLivingEntityType.EGG;
        public static final EntityType<?> TNT = NonLivingEntityType.TNT;
        public static final EntityType<?> FALLING_BLOCK = NonLivingEntityType.FALLING_BLOCK;
        // public static final EntityType<?> ARMOR_STAND = NonLivingEntityType.ARMOR_STAND;
        public static final EntityType<?> COMMAND_MINECART = NonLivingEntityType.COMMAND_MINECART;
        public static final EntityType<?> BOAT = NonLivingEntityType.BOAT;
        public static final EntityType<?> MINECART = NonLivingEntityType.MINECART;
        public static final EntityType<?> STORAGE_MINECART = NonLivingEntityType.STORAGE_MINECART;
        public static final EntityType<?> POWERED_MINECART = NonLivingEntityType.POWERED_MINECART;
        public static final EntityType<?> TNT_MINECART = NonLivingEntityType.TNT_MINECART;
        public static final EntityType<?> HOPPER_MINECART = NonLivingEntityType.HOPPER_MINECART;
        public static final EntityType<?> SPAWNER_MINECART = NonLivingEntityType.SPAWNER_MINECART;
        public static final EntityType<?> MOB = LivingEntityType.MOB;
        public static final EntityType<?> MONSTER = LivingEntityType.MONSTER;
        public static final EntityType<?> CREEPER = LivingEntityType.CREEPER;
        // skeletons
        public static final EntityType<?> SKELETON = LivingEntityType.SKELETON;
        public static final EntityType<?> WITHER_SKELETON = LivingEntityType.WITHER_SKELETON;
        // END skeletons
        public static final EntityType<?> SPIDER = LivingEntityType.SPIDER;
        public static final EntityType<?> GIANT = LivingEntityType.GIANT;
        // zombies
        // TODO: figure out if these data values are correct
        public static final EntityType<?> ZOMBIE = LivingEntityType.ZOMBIE;
        public static final EntityType<?> ZOMBIFIED_VILLAGER = LivingEntityType.ZOMBIFIED_VILLAGER;
        public static final EntityType<?> BABY_ZOMBIE = LivingEntityType.BABY_ZOMBIE;
        public static final EntityType<?> BABY_ZOMBIFIED_VILLAGER = LivingEntityType.BABY_ZOMBIFIED_VILLAGER;
        // END zombies
        public static final EntityType<?> SLIME = LivingEntityType.SLIME;
        public static final EntityType<?> GHAST = LivingEntityType.GHAST;
        public static final EntityType<?> ZOMBIE_PIGMAN = LivingEntityType.ZOMBIE_PIGMAN;
        public static final EntityType<?> ENDERMAN = LivingEntityType.ENDERMAN;
        public static final EntityType<?> CAVE_SPIDER = LivingEntityType.CAVE_SPIDER;
        public static final EntityType<?> SILVERFISH = LivingEntityType.SILVERFISH;
        public static final EntityType<?> BLAZE = LivingEntityType.BLAZE;
        public static final EntityType<?> MAGMA_CUBE = LivingEntityType.MAGMA_CUBE;
        public static final EntityType<?> ENDER_DRAGON = LivingEntityType.ENDER_DRAGON;
        public static final EntityType<?> WITHER = LivingEntityType.WITHER;
        public static final EntityType<?> BAT = LivingEntityType.BAT;
        public static final EntityType<?> WITCH = LivingEntityType.WITCH;
        public static final EntityType<?> ENDERMITE = LivingEntityType.ENDERMITE;
        // guardians
        public static final EntityType<?> GUARDIAN = LivingEntityType.GUARDIAN;
        public static final EntityType<?> ELDER_GUARDIAN = LivingEntityType.ELDER_GUARDIAN;
        // END guardians
        public static final EntityType<?> PIG = LivingEntityType.PIG;
        public static final EntityType<?> SHEEP = LivingEntityType.SHEEP;
        public static final EntityType<?> COW = LivingEntityType.COW;
        public static final EntityType<?> CHICKEN = LivingEntityType.CHICKEN;
        public static final EntityType<?> SQUID = LivingEntityType.SQUID;
        public static final EntityType<?> WOLF = LivingEntityType.WOLF;
        public static final EntityType<?> MOOSHROOM = LivingEntityType.MOOSHROOM;
        public static final EntityType<?> SNOW_GOLEM = LivingEntityType.SNOW_GOLEM;
        public static final EntityType<?> OCELOT = LivingEntityType.OCELOT;
        public static final EntityType<?> IRON_GOLEM = LivingEntityType.IRON_GOLEM;
        public static final EntityType<?> HORSE = LivingEntityType.HORSE;
        // rabbits
        // TODO: figure out if these data values are correct
        public static final EntityType<?> RABBIT = LivingEntityType.RABBIT;
        public static final EntityType<?> KILLER_RABBIT = LivingEntityType.KILLER_RABBIT;
        // END rabbits
        // villagers
        /* TODO: Figure out if these I.D.s are correct and how "careers" (more specific types within professions, e.g. a "FARMER" can be a farmer, fisherman, shepherd, of
         * fletcher) are handled. */
        public static final EntityType<?> FARMER_VILLAGER = LivingEntityType.FARMER_VILLAGER;
        public static final EntityType<?> LIBRARIAN_VILLAGER = LivingEntityType.LIBRARIAN_VILLAGER;
        public static final EntityType<?> PRIEST_VILLAGER = LivingEntityType.PRIEST_VILLAGER;
        public static final EntityType<?> BLACKSMITH_VILLAGER = LivingEntityType.BLACKSMITH_VILLAGER;
        public static final EntityType<?> BUTCHER_VILLAGER = LivingEntityType.BUTCHER_VILLAGER;
        // END villagers
        public static final EntityType<?> ENDER_CRYSTAL = NonLivingEntityType.ENDER_CRYSTAL;

        static {
            /* TODO: if debugging mode is on, do a check to see if all the EntityTypes are organized correctly; we need to somehow make sure that 1) all the values of any
             * given EntityType subclass has corresponding values with the same name in their parent class, 2) every type has their own overridden create() method (so their
             * create() method should not throw a custom CorundumException that we'll make parent type create() methods throw unles overridden) */
        }

        // TODO: Minecraft has no EntityType equivalent object, so we need to find a way to retrieve type info; EntityList will probably help

        /** This constructor makes a EntityType based on the previous value's I.D. and data. If the previous value has no strictly associated data value (data value = -1), it
         * means that it has no sub-types (e.g. the different colors of wool or types of wood), so use the next I.D.; if it has a data value, give this EntityType the same
         * I.D. and the next data value. Essentially, "I.D. items" (blocks of multiple enum constants that all have the same I.D., but different data values) are delimited by
         * the use of the {@link #EntityType(int)} and {@link #EntityType(int, int)} constructors; declaring a new enum value with a data value less than the previous will end
         * a block and declaring one with a data value >= 0 will start a new block. */
        protected EntityType() {
            super();

            addValueAs(EntityType.class);
        }

        /** This constructor makes a EntityType based on the previous value's I.D. and the given data. If the previous value's data value is <= <b><tt>data</b></tt>, then the
         * I.D. block is ending, so it increments the I.D.; otherwise, it will use the same I.D. as the previous value to continue the I.D. block. Essentially, "I.D. blocks"
         * (blocks of multiple enum constants that all have the same I.D., but different data values) are delimited by the use of the {@link #EntityType(int)} and
         * {@link #EntityType(int, int)} constructors; declaring a new enum value with a data value less than the previous will end a block and declaring one with a data value
         * >= 0 will start a new block.
         * 
         * @param data
         *            is the data value for this {@link EntityType}. */
        protected EntityType(int data) {
            super(data);

            addValueAs(EntityType.class);
        }

        /** This constructor makes a EntityType with the given I.D. and data. It's necessary for specifying I.D.s when Minecraft skips I.D.s.
         * 
         * @param id
         *            is the item I.D. that this {@link EntityType} is associated with.
         * @param data
         *            is the data value associated with this {@link EntityType}.
         * @see {@link #EntityType(int)} */
        protected EntityType(int id, int data) {
            super(id, data);

            addValueAs(EntityType.class);
        }

        // pseudo-enum utils
        public static EntityType<?> getByID(int id) {
            return getByID(EntityType.class, id);
        }

        public static EntityType<?> getByID(int id, int data) {
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

    @Override
    public Location getLocation() {
        return new Location(entityMC.posX, entityMC.posY, entityMC.posZ, World.fromMCWorld((WorldServer) entityMC.worldObj));
    }

    @Override
    public EntityType<?> getType() {
        /* because Minecraft decided to not use regular data values for different types of sibling entities, like skeletons and Wither skeletons, the data values will have to
         * be found based on different methods for different types of entities here */
        if (entityMC instanceof EntitySkeleton)
            if (((EntitySkeleton) entityMC).getSkeletonType() == 1)
                return EntityType.WITHER_SKELETON;
            else
                return EntityType.SKELETON;
        // TODO: add special cases for zombies/zombified villagers, (elder) guardians, (killer) rabbits, an villager professions and/or careers
        else
            /* NOTE: Entity.entityID is not the I.D. representing the Entity's type! It's actually the I.D. that helps track that specific Entity instance. */
            return EntityType.getByID(EntityList.getEntityID(entityMC), -1);
    }

    public Velocity getVelocity() {
        // TODO TEST
        return new Velocity(entityMC.motionX, entityMC.motionY, entityMC.motionZ, this);
    }

    /** This method determines whether or not this {@link Entity} is one of the "unsaved {@link Entity Entities}". Unsaved {@link Entity Entities} differ from other
     * {@link Entity Entities} in that they have no I.D. or data value associated with them and they are not saved when the server is stopped. Examples include
     * {@link EntityType#PLAYER player Entities}, {@link EntityType#EGG thrown eggs}, and {@link EntityType#LIGHTNING_BOLT lightning bolts}.
     * 
     * @return <b>true</b> if this {@link Entity} is an "unsaved {@link Entity}"; <b>false</b> otherwise. */
    public boolean isUnsaved() {
        return getType().getID() == -1;
    }

    public Entity setLocation(Location location) {
        entityMC.posX = location.getX();
        entityMC.posY = location.getY();
        entityMC.posZ = location.getZ();
        entityMC.worldObj = location.getWorld().getMCWorld();
        /* TODO TEST: I noticed that Minecraft's Entity.setWorld() does not set the dimension I.D., and as it is now (as of 12/3/14), we pass null Worlds into Minecraft
         * constructors when creating new entities, so I thought it might be a good idea to put this in here to make sure the dimension I.D. is set */
        entityMC.dimension = location.getWorld().getMCWorld().provider.dimensionId;

        return this;
    }

    public Entity setRotation(Rotation rotation) {
        entityMC.rotationPitch = rotation.getPitch();
        entityMC.rotationYaw = rotation.getYaw();

        return this;
    }

    public Entity setVelocity(Velocity velocity) {
        // TODO TEST
        entityMC.motionX = velocity.getX();
        entityMC.motionY = velocity.getY();
        entityMC.motionZ = velocity.getZ();

        return this;
    }

    public Entity spawn(Location location) {
        // TODO
        return this;
    }

    public Entity teleport(Location location) {
        setLocation(location);

        return this;
    }
}
