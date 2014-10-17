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

import Corundum.items.Item;
import Corundum.world.Location;

public class Mob extends Entity {
    /**
     * List of Items this mob can drop.
     */
    private Item[] drops;

    public Mob(MobType mobType, Location location, Item... drops) {
        super(mobType.getEntityType(), location);
        this.drops = drops;
    }

    public Mob(MobType mobType, Location location, float pitch, float yaw, Item... drops) {
        super(mobType.getEntityType(), location, pitch, yaw);
        this.drops = drops;
    }

    public enum MobType {
        PIG(EntityType.PIG),
        SKELETON(EntityType.SKELETON);  // TODO: 3. finish this list

        private final EntityType entityType;

        public boolean monster, despawns, neutral;

        private MobType(EntityType entityType) {
            this.entityType = entityType;
            this.monster = entityType.getIsMonster();
            this.despawns = entityType.getCanDespawn();
            this.neutral = entityType.getIsNeutral();
        }

        public EntityType getEntityType() {
            return this.entityType;
        }

        // TODO: 1. make final variables for all the information that needs to be kept for any mob type

        // TODO: 2. make a private constructor for MobTypes
    }
}
