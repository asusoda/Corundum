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
    }

    // instance utilities

    // overridden utilities
    @Override
    public ItemFrameType getType() {
        return ItemFrameType.TYPE;
    }
}
