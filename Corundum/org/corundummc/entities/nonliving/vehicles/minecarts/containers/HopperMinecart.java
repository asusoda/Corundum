package org.corundummc.entities.nonliving.vehicles.minecarts.containers;

import net.minecraft.entity.item.EntityMinecartHopper;
import org.corundummc.entities.nonliving.vehicles.minecarts.containers.ContainerMinecart.ContainerMinecartType;

public class HopperMinecart extends ContainerMinecart<HopperMinecart, EntityMinecartHopper, HopperMinecart.HopperMinecartType> {
    public HopperMinecart() {
        super(new EntityMinecartHopper(null));
    }

    protected HopperMinecart(EntityMinecartHopper entityMC) {
        super(entityMC);
    }

    protected static class HopperMinecartType extends ContainerMinecartType<HopperMinecartType, EntityMinecartHopper, HopperMinecart> {
        public static final HopperMinecartType TYPE = new HopperMinecartType();

        private HopperMinecartType() {
            super(46);
        }

        // overridden utilities
    }

    // instance utilities

    // overridden utilities
    @Override
    public HopperMinecartType getType() {
        return HopperMinecartType.TYPE;
    }
}