package org.corundummc.entities.nonliving.vehicles;

import net.minecraft.entity.Entity;

import org.corundummc.entities.nonliving.NonLivingEntity;
import org.corundummc.entities.nonliving.vehicles.Boat.BoatType;
import org.corundummc.entities.nonliving.vehicles.minecarts.Minecart.MinecartType;
import org.corundummc.entities.nonliving.vehicles.minecarts.Minecart.MinecartTypes;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class Vehicle<S extends Vehicle<S, MC, T>, MC extends Entity, T extends Vehicle.VehicleType<T, MC, S>> extends NonLivingEntity<S, MC, T> {
    protected Vehicle(MC entityMC) {
        super(entityMC);
    }

    public static interface VehicleTypes extends MinecartTypes {
        public static final BoatType BOAT = BoatType.TYPE;
    }

    public abstract static class VehicleType<S extends VehicleType<S, MC, I>, MC extends Entity, I extends Vehicle<I, MC, S>> extends NonLivingEntityType<S, MC, I> {
        protected VehicleType(int id) {
            super(id);

            addValueAs(VehicleType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        @SuppressWarnings("rawtypes")
        public static VehicleType getByID(int id) {
            return getByID(VehicleType.class, id);
        }

        @SuppressWarnings("rawtypes")
        public static VehicleType getByID(int id, int data) {
            return getByID(VehicleType.class, id, data);
        }

        @SuppressWarnings("rawtypes")
        public static VehicleType[] values() {
            return values(VehicleType.class);
        }
    }

    // type utilities

    // instance utilities
}