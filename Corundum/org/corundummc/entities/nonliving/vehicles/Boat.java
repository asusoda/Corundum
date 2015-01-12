package org.corundummc.entities.nonliving.vehicles;

import net.minecraft.entity.item.EntityBoat;
import org.corundummc.entities.nonliving.vehicles.Vehicle.VehicleType;

public class Boat extends Vehicle<Boat, EntityBoat, Boat.BoatType> {
    public Boat() {
        super(new EntityBoat(null));
    }

    protected Boat(EntityBoat entityMC) {
        super(entityMC);
    }

    protected static class BoatType extends VehicleType<BoatType, EntityBoat, Boat> {
        public static final BoatType TYPE = new BoatType();

        private BoatType() {
            super(41);
        }

        // overridden utilities
        @Override
        public Boat create() {
            return new Boat();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Boat fromMC(EntityBoat entityMC) {
            return new Boat(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public BoatType getType() {
        return BoatType.TYPE;
    }
}
