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

package org.corundummc.entities.living.mobs;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityMob;

import org.corundummc.entities.Entity;
import org.corundummc.entities.LivingEntity.LivingEntityType;
import org.corundummc.items.Item;
import org.corundummc.types.Creatable;

public class Mob extends Entity {

    public Mob(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }

    public Item[] getDrops() {
        // TODO
        return null;
    }

    /** This class is used to represent the different types of {@link Mob}s. This list of different types not only includes those types of mobs differentiated by different
     * I.D.s, but also many of those differentiated by different data values; for example, {@link #FARMER_VILLAGER farmer villagers} and {@link #LIBRARIAN_VILLAGER librarian
     * villager} are both represented as separate types despite the fact that they both have the same I.D. value.
     * 
     * @param <T>
     *            is a self-parameterization; <b><tt>T</b></tt> is the same type as the type of this instance. */
    public static class MobType<T extends LivingEntityType<T>> extends LivingEntityType<T> {

        @SuppressWarnings({ "rawtypes", "unchecked" })
        public static final MobType<?> FARMER_VILLAGER = VillagerType.FARMER_VILLAGER, LIBRARIAN_VILLAGER = VillagerType.LIBRARIAN_VILLAGER;

        protected MobType() {
            super();
        }

        protected MobType(int data) {
            super(data);
        }

        protected MobType(int id, int data) {
            super(id, data);
        }

        @Override
        public Mob create() {
            return new Mob(EntityList.createEntityByID(getID(), null));
        }

        // pseudo-enum utilities
        @SuppressWarnings("unchecked")
        public static MobType<?> getByID(int id) {
            return getByID(MobType.class, id);
        }

        @SuppressWarnings("unchecked")
        public static MobType<?> getByID(int id, int data) {
            return getByID(MobType.class, id, data);
        }

        @SuppressWarnings("unchecked")
        public static MobType<?>[] values() {
            return values(MobType.class);
        }
    }
}
