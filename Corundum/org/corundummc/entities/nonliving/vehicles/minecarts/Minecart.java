package org.corundummc.entities.nonliving.vehicles.minecarts;

import org.corundummc.entities.nonliving.NonLivingEntity;
import org.corundummc.entities.nonliving.NonLivingEntity.NonLivingEntityType;


/** This class represents all of the different types of {@link Minecart} */
public class Minecart extends NonLivingEntity {
    protected Minecart(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }

    public static class MinecartType<T extends NonLivingEntityType<T>> extends NonLivingEntityType<T> {

        @SuppressWarnings("rawtypes")
        public static final MinecartType<?> COMMAND_MINECART = new MinecartType(40);
        public static final NonLivingEntityType<?> MINECART = new MinecartType(42);
        public static final NonLivingEntityType<?> STORAGE_MINECART = new MinecartType();
        public static final NonLivingEntityType<?> POWERED_MINECART = new MinecartType();
        public static final NonLivingEntityType<?> TNT_MINECART = new MinecartType();
        public static final NonLivingEntityType<?> HOPPER_MINECART = new MinecartType();
        public static final NonLivingEntityType<?> SPAWNER_MINECART = new MinecartType();

        protected MinecartType() {
            super();
        }

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