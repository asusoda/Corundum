package org.corundummc.entities.nonliving.drops;

import net.minecraft.entity.item.EntityXPOrb;

public class XPOrb extends Drop {
    protected XPOrb(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }

    public XPOrb() {
        this(new EntityXPOrb(null));
    }
}