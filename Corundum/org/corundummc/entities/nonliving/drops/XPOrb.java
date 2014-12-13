package org.corundummc.entities.nonliving.drops;

import net.minecraft.entity.item.EntityXPOrb;

public class XPOrb extends Drop {
    public XPOrb() {
        super(new EntityXPOrb(null));
    }

    protected XPOrb(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }
}
