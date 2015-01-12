package org.corundummc.entities.nonliving.vehicles.minecarts.containers;

import net.minecraft.entity.item.EntityMinecartChest;
import org.corundummc.entities.nonliving.vehicles.minecarts.containers.ContainerMinecart.ContainerMinecartType;

public class StorageMinecart extends ContainerMinecart<StorageMinecart, EntityMinecartChest, StorageMinecart.StorageMinecartType> {
    public StorageMinecart() {
        super(new EntityMinecartChest(null));
    }

    protected StorageMinecart(EntityMinecartChest entityMC) {
        super(entityMC);
    }

    protected static class StorageMinecartType extends ContainerMinecartType<StorageMinecartType, EntityMinecartChest, StorageMinecart> {
        public static final StorageMinecartType TYPE = new StorageMinecartType();

        private StorageMinecartType() {
            super(43);
        }

        // overridden utilities
        @Override
        public StorageMinecart create() {
            return new StorageMinecart();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public StorageMinecart fromMC(EntityMinecartChest entityMC) {
            return new StorageMinecart(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public StorageMinecartType getType() {
        return StorageMinecartType.TYPE;
    }
}
