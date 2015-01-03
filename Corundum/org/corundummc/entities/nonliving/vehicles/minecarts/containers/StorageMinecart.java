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
    }

    // instance utilities

    // overridden utilities
    @Override
    public StorageMinecartType getType() {
        return StorageMinecartType.TYPE;
    }
}
