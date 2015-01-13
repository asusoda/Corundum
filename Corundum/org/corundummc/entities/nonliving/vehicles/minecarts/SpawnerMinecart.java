package org.corundummc.entities.nonliving.vehicles.minecarts;

import net.minecraft.entity.ai.EntityMinecartMobSpawner;
import org.corundummc.entities.nonliving.vehicles.minecarts.Minecart.MinecartType;

public class SpawnerMinecart extends Minecart<SpawnerMinecart, EntityMinecartMobSpawner, SpawnerMinecart.SpawnerMinecartType> {
    public SpawnerMinecart() {
        super(new EntityMinecartMobSpawner(null));
    }

    protected SpawnerMinecart(EntityMinecartMobSpawner entityMC) {
        super(entityMC);
    }

    protected static class SpawnerMinecartType extends MinecartType<SpawnerMinecartType, EntityMinecartMobSpawner, SpawnerMinecart> {
        public static final SpawnerMinecartType TYPE = new SpawnerMinecartType();

        private SpawnerMinecartType() {
            super(47);
        }

        // overridden utilities
        @Override
        public SpawnerMinecart create() {
            return new SpawnerMinecart();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public SpawnerMinecart fromMC(EntityMinecartMobSpawner entityMC) {
            return new SpawnerMinecart(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public SpawnerMinecartType getType() {
        return SpawnerMinecartType.TYPE;
    }
}