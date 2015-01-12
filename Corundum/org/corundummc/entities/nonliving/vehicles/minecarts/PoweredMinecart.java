package org.corundummc.entities.nonliving.vehicles.minecarts;

import net.minecraft.entity.item.EntityMinecartFurnace;
import org.corundummc.entities.nonliving.vehicles.minecarts.Minecart.MinecartType;

public class PoweredMinecart extends Minecart<PoweredMinecart, EntityMinecartFurnace, PoweredMinecart.PoweredMinecartType> {
    public PoweredMinecart() {
        super(new EntityMinecartFurnace(null));
    }

    protected PoweredMinecart(EntityMinecartFurnace entityMC) {
        super(entityMC);
    }

    protected static class PoweredMinecartType extends MinecartType<PoweredMinecartType, EntityMinecartFurnace, PoweredMinecart> {
        public static final PoweredMinecartType TYPE = new PoweredMinecartType();

        private PoweredMinecartType() {
            super(44);
        }

        // overridden utilities
        @Override
        public PoweredMinecart create() {
            return new PoweredMinecart();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public PoweredMinecart fromMC(EntityMinecartFurnace entityMC) {
            return new PoweredMinecart(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public PoweredMinecartType getType() {
        return PoweredMinecartType.TYPE;
    }
}