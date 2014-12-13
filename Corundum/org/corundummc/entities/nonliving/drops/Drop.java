package org.corundummc.entities.nonliving.drops;

import org.corundummc.entities.Entity;
import org.corundummc.entities.living.LivingEntity;
import org.corundummc.entities.nonliving.NonLivingEntity;

public abstract class Drop extends NonLivingEntity {

    protected Drop(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }

    /** This class is used to represent the different types of {@link Drop}s, i.e. {@link Entity Entities} that other {@link LivingEntity LivingEntities} can drop, usually when
     * killed. */
    public static class DropType extends NonLivingEntityType<DropType> {

        public static final DropType DROPPED_ITEM = new DropType(1) {
            @Override
            public DroppedItem create() {
                return new DroppedItem();
            }
        };
        public static final DropType XP_ORB = new DropType(2) {
            @Override
            public XPOrb create() {
                return new XPOrb();
            }
        };

        protected DropType(int id) {
            super(id);

            addValueAs(Drop.class);
        }

        @Override
        public Drop create() {
            return (Drop) super.create();
        }

        // pseudo-enum utilities
        @SuppressWarnings("unchecked")
        public static DropType getByID(int id) {
            return getByID(DropType.class, id);
        }

        @SuppressWarnings("unchecked")
        public static DropType getByID(int id, int data) {
            return getByID(DropType.class, id, data);
        }

        @SuppressWarnings("unchecked")
        public static DropType[] values() {
            return values(DropType.class);
        }
    }

}
