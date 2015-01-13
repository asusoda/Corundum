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
        @Override
        public DroppedItem create() {
            return new DroppedItem();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public DroppedItem fromMC(EntityItem entityMC) {
            return new DroppedItem(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public DroppedItemType getType() {
        return DroppedItemType.TYPE;
    }
}