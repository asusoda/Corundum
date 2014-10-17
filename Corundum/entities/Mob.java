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

import Corundum.exceptions.CIE;
import Corundum.exceptions.CorundumException;
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
        private boolean monster, despawns, neutral;
        //The minimum light level for the mob to spawn.
        private int minLightLevelForSpawn;

        //Reuses EntityType for reduced necessary args.
        private MobType(EntityType entityType) {
            this.entityType = entityType;

            if (entityType.getIsLiving()) {
                this.monster = entityType.getIsMonster();
                this.despawns = entityType.getCanDespawn();
                this.neutral = entityType.getIsNeutral();
            } else {
                //Perhaps it should be a CIE?
                CorundumException.err("Error creating a MobType enum!", "The EntityType passed for the MobType is for a non-living entity! As Mobs are always living entities, this shouldn't happen! Enums are only editable by code or Reflection/ASM, so it's likely one of the dev's fault (probably Niadel's, this sort of derpitude is right up his alley)!", new Object[0]);
            }
        }

        public boolean getIsMonster() {
            return this.monster;
        }

        public boolean getCanDespawn() {
            return this.despawns;
        }

        public boolean getIsNeutral() {
            return this.neutral;
        }

        public EntityType getEntityType() {
            return this.entityType;
        }

        // TODO: 1. make final variables for all the information that needs to be kept for any mob type

        // TODO: 2. make a private constructor for MobTypes
    }
}
