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
        @Override
        public XPOrb create() {
            return new XPOrb();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public XPOrb fromMC(EntityXPOrb entityMC) {
            return new XPOrb(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public XPOrbType getType() {
        return XPOrbType.TYPE;
    }
}