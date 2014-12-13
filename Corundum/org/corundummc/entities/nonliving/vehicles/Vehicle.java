package org.corundummc.entities.nonliving.vehicles;

import org.corundummc.entities.nonliving.NonLivingEntity;
import org.corundummc.entities.nonliving.vehicles.minecarts.Minecart.MinecartType;

public class Vehicle extends NonLivingEntity {
    @SuppressWarnings("javadoc")
    protected Vehicle(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }

    /** This class is used to represent the different types of {@link Vehicle}s.
     * 
     * @param <T>
     *            is a self-parameterization; <b><tt>T</b></tt> is the same type as the type of this instance. */
    public static class VehicleType<T extends NonLivingEntityType<T>> extends NonLivingEntityType<T> {
        public static final VehicleType<?> BOAT = new VehicleType<>(41);
        public static final VehicleType<?> COMMAND_MINECART = MinecartType.COMMAND_MINECART;
        public static final VehicleType<?> HOPPER_MINECART = MinecartType.HOPPER_MINECART;
        public static final VehicleType<?> PASSENGER_MINECART = MinecartType.PASSENGER_MINECART;
        public static final VehicleType<?> POWERED_MINECART = MinecartType.POWERED_MINECART;
        public static final VehicleType<?> SPAWNER_MINECART = MinecartType.SPAWNER_MINECART;
        public static final VehicleType<?> STORAGE_MINECART = MinecartType.STORAGE_MINECART;
        public static final VehicleType<?> TNT_MINECART = MinecartType.TNT_MINECART;

        @SuppressWarnings("javadoc")
        protected VehicleType(int id) {
            super(id);

            addValueAs(Vehicle.class);
        }

        @Override
        public Vehicle create() {
            return (Vehicle) super.create();
        }

        // pseudo-enum utilities
        @SuppressWarnings("unchecked")
        public static VehicleType<?> getByID(int id) {
            return getByID(VehicleType.class, id);
        }

        @SuppressWarnings("unchecked")
        public static VehicleType<?> getByID(int id, int data) {
            return getByID(VehicleType.class, id, data);
        }

        @SuppressWarnings("unchecked")
        public static VehicleType<?>[] values() {
            return values(VehicleType.class);
        }
    }
}
