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

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public PassengerMinecart fromMC(EntityMinecartEmpty entityMC) {
            return new PassengerMinecart(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public PassengerMinecartType getType() {
        return PassengerMinecartType.TYPE;
    }
}