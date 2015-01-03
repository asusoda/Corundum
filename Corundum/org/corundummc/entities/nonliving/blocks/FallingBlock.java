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
    }

    // instance utilities

    // overridden utilities
    @Override
    public FallingBlockType getType() {
        return FallingBlockType.TYPE;
    }
}
