package org.corundummc.entities.nonliving.hanging;

import net.minecraft.entity.item.EntityPainting;

public class Painting extends HangingEntity<EntityPainting> {

    protected Painting(EntityPainting entityMC) {
        super(entityMC);
    }

    public Painting() {
        super(new EntityPainting(null));
    }
}
