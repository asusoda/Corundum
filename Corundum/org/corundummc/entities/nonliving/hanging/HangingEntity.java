package org.corundummc.entities.nonliving.hanging;

import org.corundummc.entities.Entity;
import org.corundummc.entities.nonliving.NonLivingEntity;

import net.minecraft.entity.EntityHanging;

/** This class represents {@link Entity Entities} that are made to hang on walls, including {@link Painting}s and {@link ItemFrame Item Frame}s.
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class HangingEntity<S extends HangingEntity<S, MC, T>, MC extends EntityHanging, T extends HangingEntity.HangingEntityType<T, MC, S>> extends
        NonLivingEntity<S, MC, T> {
    protected HangingEntity(MC entityMC) {
        super(entityMC);
    }

    public static interface HangingEntityTypes {
        public static final PaintingType PAINTING = PaintingType.TYPE;
        public static final ItemFrameType ITEM_FRAME = ItemFrameType.TYPE;
    }

    public abstract static class HangingEntityType<S extends HangingEntityType<S, MC, I>, MC extends EntityHanging, I extends HangingEntity<I, MC, S>> extends
            NonLivingEntityType<S, MC, I> {
        protected HangingEntityType(int id) {
            super(id);

            addValueAs(HangingEntityType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        @SuppressWarnings("rawtypes")
        public static HangingEntityType getByID(int id) {
            return getByID(HangingEntityType.class, id);
        }

        @SuppressWarnings("rawtypes")
        public static HangingEntityType getByID(int id, int data) {
            return getByID(HangingEntityType.class, id, data);
        }

        @SuppressWarnings("rawtypes")
        public static HangingEntityType[] values() {
            return values(HangingEntityType.class);
        }
    }

    // type utilities

    // instance utilities
}
