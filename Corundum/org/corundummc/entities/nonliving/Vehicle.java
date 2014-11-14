package org.corundummc.entities.nonliving;

import org.corundummc.entities.Entity;
import org.corundummc.entities.NonLivingEntity;
import org.corundummc.items.Item.ItemType;
import org.corundummc.world.Location;

/** This class represents {@link Entity entities} that can be ridden in vanilla Minecraft (e.g. {@link VehicleType#MINECART minecarts}) <i>without</i> the use of a
 * {@link ItemType#SADDLE saddle}. */
public class Vehicle extends NonLivingEntity {
    public Vehicle(VehicleType type, Location location) {
        super(type, location);
    }

    public static class VehicleType<T extends NonLivingEntityType<T>> extends NonLivingEntityType<T> {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public static final VehicleType<?> MINECART = new VehicleType(NonLivingEntityType.MINECART);

        protected VehicleType(NonLivingEntityType<T> parent) {
            super(parent);
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