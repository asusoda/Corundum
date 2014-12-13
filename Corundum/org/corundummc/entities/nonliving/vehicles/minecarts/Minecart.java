package org.corundummc.entities.nonliving.vehicles.minecarts;

import org.corundummc.entities.nonliving.NonLivingEntity;
import org.corundummc.entities.nonliving.vehicles.Vehicle.VehicleType;

/** This class represents all of the different types of {@link Minecart} */
public class Minecart extends NonLivingEntity {
    @SuppressWarnings("javadoc")
    protected Minecart(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }

    public static class MinecartType<T extends NonLivingEntityType<T>> extends VehicleType<T> {
        public static final MinecartType<?> COMMAND_MINECART = new MinecartType(40) {
            @Override
            public CommandMinecart create() {
                return new CommandMinecart();
            }
        };
        public static final MinecartType<?> HOPPER_MINECART = new MinecartType(46) {
            public HopperMinecart create() {
                return new HopperMinecart();
            }
        };
        public static final MinecartType<?> PASSENGER_MINECART = new MinecartType(42) {
            public PassengerMinecart create() {
                return new PassengerMinecart();
            }
        };
        public static final MinecartType<?> POWERED_MINECART = new MinecartType(44) {
            public PoweredMinecart create() {
                return new PoweredMinecart();
            }
        };
        public static final MinecartType<?> SPAWNER_MINECART = new MinecartType(47) {
            public SpawnerMinecart create() {
                return new SpawnerMinecart();
            }
        };
        public static final MinecartType<?> STORAGE_MINECART = new MinecartType(43) {
            public StorageMinecart create() {
                return new StorageMinecart();
            }
        };
        public static final MinecartType<?> TNT_MINECART = new MinecartType(45) {
            public TNTMinecart create() {
                return new TNTMinecart();
            }
        };

        @SuppressWarnings("javadoc")
        protected MinecartType(int id) {
            super(id);
        }

        // pseudo-enum utilities
        @SuppressWarnings("unchecked")
        public static MinecartType<?> getByID(int id) {
            return getByID(MinecartType.class, id);
        }

        @SuppressWarnings("unchecked")
        public static MinecartType<?> getByID(int id, int data) {
            return getByID(MinecartType.class, id, data);
        }

        @SuppressWarnings("unchecked")
        public static MinecartType<?>[] values() {
            return values(MinecartType.class);
        }
    }
}