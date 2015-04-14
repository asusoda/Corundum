package org.corundummc.entities.nonliving.drops;

import org.corundummc.blocks.Block;
import org.corundummc.entities.Entity;
import org.corundummc.entities.living.LivingEntity;
import org.corundummc.entities.nonliving.NonLivingEntity;
import org.corundummc.entities.nonliving.drops.DroppedItem.DroppedItemType;
import org.corundummc.entities.nonliving.drops.XPOrb.XPOrbType;

/** This class is used to represent the {@link Entity Entities} that can be dropped, e.g. by {@link Block}s when destroyed or by {@link LivingEntity LivingEntities} when
 * killed.
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class Drop<S extends Drop<S, MC, T>, MC extends net.minecraft.entity.Entity, T extends Drop.DropType<T, MC, S>> extends NonLivingEntity<S, MC, T> {
    protected Drop(MC entityMC) {
        super(entityMC);
    }

    public static interface DropTypes {
        public static final DroppedItemType DROPPED_ITEM = DroppedItemType.TYPE;
        public static final XPOrbType XP_ORB = XPOrbType.TYPE;
    }

    public abstract static class DropType<S extends DropType<S, MC, I>, MC extends net.minecraft.entity.Entity, I extends Drop<I, MC, S>> extends
            NonLivingEntityType<S, MC, I> {
        protected DropType(int id) {
            super(id);

            addValueAs(DropType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        @SuppressWarnings("rawtypes")
        public static DropType getByID(int id) {
            return getByID(DropType.class, id);
        }

        @SuppressWarnings("rawtypes")
        public static DropType getByID(int id, int data) {
            return getByID(DropType.class, id, data);
        }

        @SuppressWarnings("rawtypes")
        public static DropType[] values() {
            return values(DropType.class);
        }
    }

    // type utilities

    // instance utilities
}
