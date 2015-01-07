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
import net.minecraft.world.WorldServer;

import org.apache.logging.log4j.core.config.plugins.ResolverUtil.Test;
import org.corundummc.entities.living.LivingEntity.LivingEntityType;
import org.corundummc.entities.living.LivingEntity.LivingEntityTypes;
import org.corundummc.entities.nonliving.NonLivingEntity.NonLivingEntityType;
import org.corundummc.entities.nonliving.NonLivingEntity.NonLivingEntityTypes;
import org.corundummc.entities.nonliving.projectiles.Snowball;
import org.corundummc.items.Item;
import org.corundummc.types.CreatableType;
import org.corundummc.types.Nameable;
import org.corundummc.types.Physical;
import org.corundummc.types.Typed;
import org.corundummc.world.Location;
import org.corundummc.world.World;

public abstract class Entity<S extends Entity<S, MC, T>, MC extends net.minecraft.entity.Entity, T extends Entity.EntityType<T, MC, S>> extends Typed<T> implements Physical {
    protected final MC entityMC;

    protected Entity(MC entityMC) {
        this.entityMC = entityMC;
    }

    public static interface EntityTypes extends LivingEntityTypes, NonLivingEntityTypes {
        // TODO
    }

    public static abstract class EntityType<S extends EntityType<S, MC, I>, MC extends net.minecraft.entity.Entity, I extends Entity<I, MC, S>> extends CreatableType<S, I> {
        // TODO: Minecraft has no EntityType equivalent object, so we need to find a way to retrieve type info; EntityList will probably help

        @SuppressWarnings("javadoc")
        protected EntityType(int id, int data) {
            super(id, data);

            addValueAs(EntityType.class);
        }

        public String getName() {
            return EntityList.getStringFromID(getID());
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

        /** This method returns the name, I.D., and data value of this {@link EntityType} in a message-friendly <tt>String</tt> in one of two formats shown below:<br>
         * If the data value for this {@link EntityType} is irrelevant to its identity as this type (indicated by a data value of -1), the data value is not included with the
         * name and I.D. as shown below:
         * 
         * <pre>
         * [name] ([I.D.])
         * </pre>
         * 
         * If the data value is relevant to the identity of this {@link EntityType}, i.e. changing the data value can change the {@link EntityType}, then the data value is
         * included with the name and I.D. separated by a colon as shown below:
         * 
         * <pre>
         * [name] ([I.D.]:[data])
         * </pre> */
        @Override
        public String toString() {
            return getName() + " (" + getID() + (getData() == -1 ? "" : ":" + getData()) + ")";
        }
    }

    public Item[] getDrops() {
        // TODO
        return null;
    }

    @Override
    public Location getLocation() {
        return new Location(entityMC.posX, entityMC.posY, entityMC.posZ, World.fromMCWorld((WorldServer) entityMC.worldObj));
    }

    public Velocity getVelocity() {
        return new Velocity(entityMC.motionX, entityMC.motionY, entityMC.motionZ);
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
