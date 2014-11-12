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

import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.WorldServer;
import Corundum.items.Item.ItemType;
import Corundum.utils.interfaces.IDTypedObject;
import Corundum.utils.interfaces.IDedType;
import Corundum.utils.interfaces.IDedTypeWithData;
import Corundum.world.Location;
import Corundum.world.World;

public abstract class Entity implements IDTypedObject {
    protected final net.minecraft.entity.Entity entityMC;

    public Entity(net.minecraft.entity.Entity entityMC) {
        this.entityMC = entityMC;
    }

    @Override
    public String getCustomName() {
        // TODO
        return null;
    }

    @Override
    public EntityType getType() {
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

    @Override
    public Location getLocation() {
        return new Location(entityMC.posX, entityMC.posY, entityMC.posZ, World.fromMCWorld((WorldServer) entityMC.worldObj));
    }
}
