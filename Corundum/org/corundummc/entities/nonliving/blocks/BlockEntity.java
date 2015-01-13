package org.corundummc.entities.nonliving.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;

import org.corundummc.entities.nonliving.NonLivingEntity;
import org.corundummc.entities.nonliving.blocks.FallingBlock.FallingBlockType;
import org.corundummc.entities.nonliving.blocks.PrimedTNT.PrimedTNTType;

// TODO: when available, link "sand" in the Javadoc below
/** This class represents an {@link Entity} that represents some type of {@link Block} such as a {@link PrimedTNT primed T.N.T.} or a {@link FallingBlock falling block} like
 * sand.
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class BlockEntity<S extends BlockEntity<S, MC, T>, MC extends Entity, T extends BlockEntity.BlockEntityType<T, MC, S>> extends NonLivingEntity<S, MC, T> {
    protected BlockEntity(MC entityMC) {
        super(entityMC);
    }

    public static interface BlockEntityTypes {
        public static final FallingBlockType FALLING_BLOCK = FallingBlockType.TYPE;
        public static final PrimedTNTType PRIMED_TNT = PrimedTNTType.TYPE;
    }

    public abstract static class BlockEntityType<S extends BlockEntityType<S, MC, I>, MC extends Entity, I extends BlockEntity<I, MC, S>> extends
            NonLivingEntityType<S, MC, I> {
        protected BlockEntityType(int id) {
            super(id);

            addValueAs(BlockEntityType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        @SuppressWarnings("rawtypes")
        public static BlockEntityType getByID(int id) {
            return getByID(BlockEntityType.class, id);
        }

        @SuppressWarnings("rawtypes")
        public static BlockEntityType getByID(int id, int data) {
            return getByID(BlockEntityType.class, id, data);
        }

        @SuppressWarnings("rawtypes")
        public static BlockEntityType[] values() {
            return values(BlockEntityType.class);
        }
    }

    // type utilities

    // instance utilities
}
