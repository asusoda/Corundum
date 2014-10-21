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

import net.minecraft.server.gui.PlayerListComponent;
import Corundum.exceptions.CorundumException;
import Corundum.items.Item;
import Corundum.world.Location;

public class Mob extends Entity {
    private Item[] drops;
    private MobType mobType;

    public Mob(MobType mobType, Location location, Item... drops) {
        super(mobType.getEntityType(), location);
        this.drops = drops;
        this.mobType = mobType;
    }

    public Item[] getDrops() {
        return this.drops;
    }

    public MobType getMobType() {
        return mobType;
    }

    public enum MobType {
        //Basically the same as Entity.EntityType, but without the non-living entities and the entities have more data
        // stored about them.
        PLAYER(EntityType.PLAYER, false, true),
        PIG(EntityType.PIG, false, false),
        SKELETON(EntityType.SKELETON, true, false),
        ZOMBIE(EntityType.ZOMBIE, true, false),
        ZOMBIE_PIGMAN(EntityType.ZOMBIE_PIGMAN, false, false),
        COW(EntityType.COW, false, false),
        CHICKEN(EntityType.CHICKEN, false, false),
        HORSE(EntityType.HORSE, false, false),
        SHEEP(EntityType.SHEEP, false, false),
        MOOSHROOM(EntityType.MOOSHROOM, false, false),
        VILLAGER(EntityType.VILLAGER, false, false),
        WOLF(EntityType.WOLF, false, false),
        OCELOT(EntityType.OCELOT, false, false),
        SQUID(EntityType.SQUID, false, false),
        BAT(EntityType.BAT, false, true),
        CREEPER(EntityType.CREEPER, false, false),
        SPIDER(EntityType.SPIDER, false, false),
        CAVE_SPIDER(EntityType.CAVE_SPIDER, false, false),
        ENDERMAN(EntityType.ENDERMAN, false, false),
        WITCH(EntityType.WITCH, false, false),
        BLAZE(EntityType.BLAZE, false, true),
        SLIME(EntityType.SLIME, false, false),
        MAGMA_CUBE(EntityType.MAGMA_CUBE, false, false),
        ENDERDRAGON(EntityType.ENDERDRAGON, false, true),
        WITHER(EntityType.WITHER, false, true);  // TODO: 3. finish this list

        //Used to get basic data about the mob, mainly used because I don't want to redo all of EntityType again.
        private final EntityType entityType;
        private boolean neutral, monster, despawns, burnsInSunlight, canFly;

        private MobType(EntityType entityType, boolean burnsInSunlight, boolean canFly) {
            this.entityType = entityType;

            if (this.entityType.getIsLiving()) {
                this.neutral = this.entityType.getIsNeutral();
                this.monster = this.entityType.getIsMonster();
                this.despawns = this.entityType.getCanDespawn();
                this.burnsInSunlight = burnsInSunlight;
                this.canFly = canFly;
            } else {
                CorundumException.err("Error creating a MobType!", "Attempted to instantiate a MobType with a non-living EntityType! This is illogical, as mobs are living entities and should not happen!");
            }
        }

        public EntityType getEntityType() {
            return this.entityType;
        }

        public boolean getIsNeutral() {
            return this.neutral;
        }

        public boolean getIsMonster() {
            return this.monster;
        }

        public boolean getCanDespawn() {
            return this.despawns;
        }

        public boolean getBurnsInSunlight() {
            return this.burnsInSunlight;
        }

        public boolean getCanFly() {
            return this.canFly;
        }

        // TODO: 1. make final variables for all the information that needs to be kept for any mob type
    }
}
