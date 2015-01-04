package org.corundummc.entities.nonliving.blocks;

import net.minecraft.entity.item.EntityTNTPrimed;
import org.corundummc.entities.nonliving.blocks.BlockEntity.BlockEntityType;

public class PrimedTNT extends BlockEntity<PrimedTNT, EntityTNTPrimed, PrimedTNT.PrimedTNTType> {
    public PrimedTNT() {
        super(new EntityTNTPrimed(null));
    }

    protected PrimedTNT(EntityTNTPrimed entityMC) {
        super(entityMC);
    }

    protected static class PrimedTNTType extends BlockEntityType<PrimedTNTType, EntityTNTPrimed, PrimedTNT> {
        public static final PrimedTNTType TYPE = new PrimedTNTType();

        private PrimedTNTType() {
            super(20);
        }

        // overridden utilities
        @Override
        public PrimedTNT create() {
            return new PrimedTNT();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public PrimedTNTType getType() {
        return PrimedTNTType.TYPE;
    }
}
