package org.corundummc.entities.nonliving.hanging;

import net.minecraft.entity.item.EntityItemFrame;

public class ItemFrame extends HangingEntity<EntityItemFrame> {

    protected ItemFrame(EntityItemFrame entityMC) {
        super(entityMC);
    }

    public ItemFrame() {
        super(new EntityItemFrame(null));
    }
}
