package org.corundummc.entities.nonliving.hanging;

import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.item.EntityItemFrame;
import org.corundummc.entities.nonliving.hanging.HangingEntity.HangingEntityType;

public class ItemFrame extends HangingEntity<ItemFrame, EntityItemFrame, ItemFrame.ItemFrameType> {
    public ItemFrame() {
        super(new EntityItemFrame(null));
    }

    protected ItemFrame(EntityItemFrame entityMC) {
        super(entityMC);
    }

    protected static class ItemFrameType extends HangingEntityType<ItemFrameType, EntityItemFrame, ItemFrame> {
        public static final ItemFrameType TYPE = new ItemFrameType();

        private ItemFrameType() {
            super(18);
        }

        // overridden utilities
        @Override
        public ItemFrame create() {
            return new ItemFrame();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public ItemFrame fromMC(EntityItemFrame entityMC) {
            return new ItemFrame(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public ItemFrameType getType() {
        return ItemFrameType.TYPE;
    }
}
