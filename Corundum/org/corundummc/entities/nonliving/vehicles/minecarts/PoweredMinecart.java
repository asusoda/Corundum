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
    }

    // instance utilities

    // overridden utilities
    @Override
    public PoweredMinecartType getType() {
        return PoweredMinecartType.TYPE;
    }
}