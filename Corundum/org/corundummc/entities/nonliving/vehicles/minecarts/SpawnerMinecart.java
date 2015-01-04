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
    }

    // instance utilities

    // overridden utilities
    @Override
    public SpawnerMinecartType getType() {
        return SpawnerMinecartType.TYPE;
    }
}