package org.corundummc.entities.nonliving.hanging;

import net.minecraft.entity.EntityHanging;

import org.corundummc.entities.nonliving.NonLivingEntity;

public abstract class HangingEntity<T extends EntityHanging> extends NonLivingEntity<T> {

    protected HangingEntity(EntityHanging entityMC) {
        super(entityMC);
    }

    /** This class is used to represent the different types of {@link HangingEntity}s. <br>
     * <br>
     * This list of different types not only includes those types of mobs differentiated by different I.D.s, but also many of those differentiated by different data values;
     * for example, {@link #object object2} and {@link #object3 object4} are both represented as separate types despite the fact that they both have the same I.D. value.
     * 
     * @param <T>
     *            is a self-parameterization; <b><tt>T</b></tt> is the same type as the type of this instance. */
    public static class HangingEntityType<T extends HangingEntityType<T>> extends NonLivingEntityType<T> {

        @SuppressWarnings("rawtypes")
        public static final HangingEntityType<?> PAINTING = new HangingEntityType(9) {
            @Override
            public Painting create() {
                return new Painting();
            }
        };
        @SuppressWarnings("rawtypes")
        public static final HangingEntityType<?> ITEM_FRAME = new HangingEntityType(18) {
            @Override
            public ItemFrame create() {
                return new ItemFrame();
            }
        };

        protected HangingEntityType(int id) {
            super(id);

            addValueAs(HangingEntity.class);
        }

        @Override
        public HangingEntity create() {
            return (HangingEntity) super.create();
        }

        // pseudo-enum utilities
        @SuppressWarnings("unchecked")
        public static HangingEntityType<?> getByID(int id) {
            return getByID(HangingEntityType.class, id);
        }

        @SuppressWarnings("unchecked")
        public static HangingEntityType<?> getByID(int id, int data) {
            return getByID(HangingEntityType.class, id, data);
        }

        @SuppressWarnings("unchecked")
        public static HangingEntityType<?>[] values() {
            return values(HangingEntityType.class);
        }
    }

}
