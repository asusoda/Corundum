package org.corundummc.entities.nonliving.drops;

import net.minecraft.entity.item.EntityItem;

public class DroppedItem extends Drop {
    protected DroppedItem(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }

    public DroppedItem() {
        this(new EntityItem(null));
    }
}
