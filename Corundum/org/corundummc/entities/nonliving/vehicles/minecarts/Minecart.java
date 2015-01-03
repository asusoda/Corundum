package org.corundummc.entities.nonliving.vehicles.minecarts;

import net.minecraft.entity.item.EntityMinecart;

import org.corundummc.entities.nonliving.NonLivingEntity;
import org.corundummc.entities.nonliving.vehicles.Vehicle;
import org.corundummc.entities.nonliving.vehicles.Vehicle.VehicleType;
import org.corundummc.entities.nonliving.vehicles.minecarts.PassengerMinecart.PassengerMinecartType;
import org.corundummc.entities.nonliving.vehicles.minecarts.containers.ContainerMinecart.ContainerMinecartTypes;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class Minecart<S extends Minecart<S, MC, T>, MC extends EntityMinecart, T extends Minecart.MinecartType<T, MC, S>> extends Vehicle<S, MC, T> {
    protected Minecart(MC entityMC) {
        super(entityMC);
    }

    public static interface MinecartTypes extends ContainerMinecartTypes {
        public static final PassengerMinecartType PASSENGER_MINECART = PassengerMinecartType.TYPE;
        // TODO
    }

    public abstract static class MinecartType<S extends MinecartType<S, MC, I>, MC extends EntityMinecart, I extends Minecart<I, MC, S>> extends VehicleType<S, MC, I> {
        protected MinecartType(int id) {
            super(id);

            addValueAs(MinecartType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        @SuppressWarnings("rawtypes")
        public static MinecartType getByID(int id) {
            return getByID(MinecartType.class, id);
        }

        @SuppressWarnings("rawtypes")
        public static MinecartType getByID(int id, int data) {
            return getByID(MinecartType.class, id, data);
        }

        @SuppressWarnings("rawtypes")
        public static MinecartType[] values() {
            return values(MinecartType.class);
        }
    }

    // type utilities

    // instance utilities
}