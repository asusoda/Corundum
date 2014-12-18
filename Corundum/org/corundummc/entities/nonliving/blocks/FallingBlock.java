package org.corundummc.entities.nonliving.blocks;

import net.minecraft.entity.item.EntityFallingBlock;

public class FallingBlock extends BlockEntity<EntityFallingBlock> {

    protected FallingBlock(EntityFallingBlock entityMC) {
        super(entityMC);
    }

    public FallingBlock() {
        this(new EntityFallingBlock(null));
    }
}
