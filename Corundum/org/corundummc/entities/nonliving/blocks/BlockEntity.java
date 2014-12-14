package org.corundummc.entities.nonliving.blocks;

import org.corundummc.entities.nonliving.NonLivingEntity;

public abstract class BlockEntity<T extends net.minecraft.entity.Entity> extends NonLivingEntity<T> {
    protected BlockEntity(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }

    /** This class is used to represent the different types of {@link BlockEntity}s.
     * 
     * @param <T>
     *            is a self-parameterization; <b><tt>T</b></tt> is the same type as the type of this instance. */
    public static class BlockEntityType<T extends NonLivingEntityType<T>> extends NonLivingEntityType<T> {
        @SuppressWarnings("rawtypes")
        public static final BlockEntityType<?> PRIMED_TNT = new BlockEntityType(20) {
            @Override
            public PrimedTNT create() {
                return new PrimedTNT();
            }
        };
        @SuppressWarnings("rawtypes")
        public static final BlockEntityType<?> FALLING_BLOCK = new BlockEntityType(21) {
            @Override
            public FallingBlock create() {
                return new FallingBlock();
            }
        };

        protected BlockEntityType(int id) {
            super(id);

            addValueAs(BlockEntity.class);
        }

        @Override
        public BlockEntity create() {
            return (BlockEntity) super.create();
        }

        // pseudo-enum utilities
        public static BlockEntityType<?> getByID(int id) {
            return getByID(BlockEntityType.class, id);
        }

        public static BlockEntityType<?> getByID(int id, int data) {
            return getByID(BlockEntityType.class, id, data);
        }

        public static BlockEntityType<?>[] values() {
            return values(BlockEntityType.class);
        }
    }
}
