package org.corundummc.entities.nonliving.vehicles.minecarts;

import net.minecraft.entity.item.EntityMinecartEmpty;
import org.corundummc.entities.nonliving.vehicles.minecarts.Minecart.MinecartType;

public class PassengerMinecart extends Minecart<PassengerMinecart, EntityMinecartEmpty, PassengerMinecart.PassengerMinecartType> {
    public PassengerMinecart() {
        super(new EntityMinecartEmpty(null));
    }

    protected PassengerMinecart(EntityMinecartEmpty entityMC) {
        super(entityMC);
    }

    protected static class PassengerMinecartType extends MinecartType<PassengerMinecartType, EntityMinecartEmpty, PassengerMinecart> {
        public static final PassengerMinecartType TYPE = new PassengerMinecartType();

        private PassengerMinecartType() {
            super(42);
        }

        // overridden utilities
        @Override
        public PassengerMinecart create() {
            return new PassengerMinecart();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public PassengerMinecartType getType() {
        return PassengerMinecartType.TYPE;
    }
}