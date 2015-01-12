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

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public PrimedTNT fromMC(EntityTNTPrimed entityMC) {
            return new PrimedTNT(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public PrimedTNTType getType() {
        return PrimedTNTType.TYPE;
    }
}
