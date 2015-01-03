package org.corundummc.entities.nonliving.drops;

import net.minecraft.entity.item.EntityXPOrb;
import org.corundummc.entities.nonliving.drops.Drop.DropType;

public class XPOrb extends Drop<XPOrb, EntityXPOrb, XPOrb.XPOrbType> {
    public XPOrb() {
        super(new EntityXPOrb(null));
    }

    protected XPOrb(EntityXPOrb entityMC) {
        super(entityMC);
    }

    protected static class XPOrbType extends DropType<XPOrbType, EntityXPOrb, XPOrb> {
        public static final XPOrbType TYPE = new XPOrbType();

        private XPOrbType() {
            super(2);
        }

        // overridden utilities
    }

    // instance utilities

    // overridden utilities
    @Override
    public XPOrbType getType() {
        return XPOrbType.TYPE;
    }
}