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
    }

    // instance utilities

    // overridden utilities
    @Override
    public BoatType getType() {
        return BoatType.TYPE;
    }
}
