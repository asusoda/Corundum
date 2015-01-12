package org.corundummc.entities.nonliving.blocks;

import net.minecraft.entity.item.EntityFallingBlock;
import org.corundummc.entities.nonliving.blocks.BlockEntity.BlockEntityType;

public class FallingBlock extends BlockEntity<FallingBlock, EntityFallingBlock, FallingBlock.FallingBlockType> {
    public FallingBlock() {
        super(new EntityFallingBlock(null));
    }

    protected FallingBlock(EntityFallingBlock entityMC) {
        super(entityMC);
    }

    protected static class FallingBlockType extends BlockEntityType<FallingBlockType, EntityFallingBlock, FallingBlock> {
        public static final FallingBlockType TYPE = new FallingBlockType();

        private FallingBlockType() {
            super(21);
        }

        // overridden utilities
        @Override
        public FallingBlock create() {
            return new FallingBlock();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public FallingBlock fromMC(EntityFallingBlock entityMC) {
            return new FallingBlock(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public FallingBlockType getType() {
        return FallingBlockType.TYPE;
    }
}
