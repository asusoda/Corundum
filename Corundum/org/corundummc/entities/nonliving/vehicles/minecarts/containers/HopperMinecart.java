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
        @Override
        public HopperMinecart create() {
            return new HopperMinecart();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public HopperMinecart fromMC(EntityMinecartHopper entityMC) {
            return new HopperMinecart(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public HopperMinecartType getType() {
        return HopperMinecartType.TYPE;
    }
}