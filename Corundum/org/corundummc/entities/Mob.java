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

import net.minecraft.entity.monster.EntityMob;

import org.corundummc.exceptions.CorundumException;
import org.corundummc.hub.CIE;
import org.corundummc.items.Item;
import org.corundummc.world.Location;

public class Mob extends Entity {

    public Mob(EntityMob mobMC) {
        super(mobMC);
    }

    public Item[] getDrops() {
        // TODO
        return null;
    }

    public MobType getMobType() {
        // TODO
        return null;
    }

    /** This enum is used to represent the different types of {@link Mob}s. This list of different types not only includes those types of mobs differentiated by different
     * I.D.s, but also many of those differentiated by different data values; for example, {@link #ZOMBIE zombies} and {@link #ZOMBIFIED_VILLAGER zombified villagers} are both
     * represented as separate types despite the fact that they both have the same I.D. value. */
    public enum MobType {
        PLAYER(EntityType.PLAYER, false, true),
        PIG(EntityType.PIG, false, false),
        SKELETON(EntityType.SKELETON, true, false),
        ZOMBIE(EntityType.ZOMBIE, true, false),
        ZOMBIFIED_VILLAGER(EntityType.ZOMBIFIED_VILLAGER, true, false),
        ZOMBIE_PIGMAN(EntityType.ZOMBIE_PIGMAN, false, false),
        COW(EntityType.COW, false, false),
        CHICKEN(EntityType.CHICKEN, false, false),
        HORSE(EntityType.HORSE, false, false),
        SHEEP(EntityType.SHEEP, false, false),
        MOOSHROOM(EntityType.MOOSHROOM, false, false),
        FARMER_VILLAGER(EntityType.FARMER_VILLAGER, false, false),
        LIBRARIAN_VILLAGER(EntityType.LIBRARIAN_VILLAGER, false, false),
        PRIEST_VILLAGER(EntityType.PRIEST_VILLAGER, false, false),
        BLACKSMITH_VILLAGER(EntityType.BLACKSMITH_VILLAGER, false, false),
        BUTCHER_VILLAGER(EntityType.BUTCHER_VILLAGER, false, false),
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
        GHAST(EntityType.GHAST, false, true),
        ENDER_DRAGON(EntityType.ENDER_DRAGON, false, true),
        WITHER(EntityType.WITHER, false, true);

        // Used to get basic data about the mob, mainly used because I don't want to redo all of EntityType again.
        private final EntityType entityType;

        private MobType(EntityType entityType, boolean burnsInSunlight, boolean canFly) {
            this.entityType = entityType;
        }

        public EntityType getEntityType() {
            return this.entityType;
        }

    }
}
