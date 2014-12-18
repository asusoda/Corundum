package org.corundummc.entities.nonliving.blocks;

import net.minecraft.entity.item.EntityTNTPrimed;

public class PrimedTNT extends BlockEntity<EntityTNTPrimed> {

    protected PrimedTNT(EntityTNTPrimed entityMC) {
        super(entityMC);
    }

    public PrimedTNT() {
        this(new EntityTNTPrimed(null));
    }
}
