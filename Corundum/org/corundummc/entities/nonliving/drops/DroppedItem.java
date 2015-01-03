package org.corundummc.entities.nonliving.drops;

import net.minecraft.entity.item.EntityItem;

import org.corundummc.entities.nonliving.drops.Drop.DropType;

public class DroppedItem extends Drop<DroppedItem, EntityItem, DroppedItem.DroppedItemType> {
    public DroppedItem() {
        super(new EntityItem(null));
    }

    protected DroppedItem(EntityItem entityMC) {
        super(entityMC);
    }

    protected static class DroppedItemType extends DropType<DroppedItemType, EntityItem, DroppedItem> {
        public static final DroppedItemType TYPE = new DroppedItemType();

        private DroppedItemType() {
            super(1);
        }

        // overridden utilities
    }

    // instance utilities

    // overridden utilities
    @Override
    public DroppedItemType getType() {
        return DroppedItemType.TYPE;
    }
}