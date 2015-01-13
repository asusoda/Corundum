package org.corundummc.entities.nonliving.vehicles.minecarts.containers;

import org.corundummc.entities.nonliving.vehicles.minecarts.Minecart;
import org.corundummc.entities.nonliving.vehicles.minecarts.containers.HopperMinecart.HopperMinecartType;
import org.corundummc.entities.nonliving.vehicles.minecarts.containers.StorageMinecart.StorageMinecartType;
import org.corundummc.items.Item;

import net.minecraft.entity.item.EntityMinecartContainer;

/** This class represents a {@link Minecart minecart} that is able to contain {@link Item item}s inside it like a {@link HopperMinecart hopper minecart} or a
 * {@link StorageMinecart storage minecart}.
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class ContainerMinecart<S extends ContainerMinecart<S, MC, T>, MC extends EntityMinecartContainer, T extends ContainerMinecart.ContainerMinecartType<T, MC, S>>
        extends Minecart<S, MC, T> /* TODO implements Container */{
    protected ContainerMinecart(MC entityMC) {
        super(entityMC);
    }

    public static interface ContainerMinecartTypes {
        public static final StorageMinecartType STORAGE_MINECART = StorageMinecartType.TYPE;
        public static final HopperMinecartType HOPPER_MINECART = HopperMinecartType.TYPE;
    }

    public abstract static class ContainerMinecartType<S extends ContainerMinecartType<S, MC, I>, MC extends EntityMinecartContainer, I extends ContainerMinecart<I, MC, S>>
            extends MinecartType<S, MC, I> {
        protected ContainerMinecartType(int id) {
            super(id);

            addValueAs(ContainerMinecartType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        @SuppressWarnings("rawtypes")
        public static ContainerMinecartType getByID(int id) {
            return getByID(ContainerMinecartType.class, id);
        }

        @SuppressWarnings("rawtypes")
        public static ContainerMinecartType getByID(int id, int data) {
            return getByID(ContainerMinecartType.class, id, data);
        }

        @SuppressWarnings("rawtypes")
        public static ContainerMinecartType[] values() {
            return values(ContainerMinecartType.class);
        }
    }

    // type utilities

    // instance utilities
}