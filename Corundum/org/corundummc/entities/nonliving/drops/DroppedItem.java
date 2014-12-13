package org.corundummc.entities.nonliving.drops;

import net.minecraft.entity.item.EntityItem;

public class DroppedItem extends Drop {
    public DroppedItem() {
        super(new EntityItem(null));
    }

    protected DroppedItem(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }
}
