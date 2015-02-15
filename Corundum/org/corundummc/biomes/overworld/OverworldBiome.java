package org.corundummc.biomes.overworld;

import org.corundummc.biomes.Biome;
import org.corundummc.biomes.overworld.aquatic.AquaticBiome.AquaticBiomeTypes;
import org.corundummc.biomes.overworld.sandy.SandyBiome.SandyBiomeTypes;
import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;

public abstract class OverworldBiome<S extends OverworldBiome<S, MC, T>, MC extends BiomeGenBase, T extends OverworldBiome.OverworldBiomeType<T, MC, S>> extends
        Biome<S, MC, T> {

    protected OverworldBiome(Location location) {
        super(location);
    }

    public static interface OverworldBiomeTypes extends AquaticBiomeTypes, SandyBiomeTypes {
        // TODO
    }

    public abstract static class OverworldBiomeType<S extends OverworldBiomeType<S, MC, I>, MC extends BiomeGenBase, I extends OverworldBiome<I, MC, S>> extends
            BiomeType<S, MC, I> {
        protected OverworldBiomeType(MC biomeMC) {
            super(biomeMC);

            addValueAs(OverworldBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static OverworldBiomeType<?, ?, ?> getByID(int id) {
            return getByID(OverworldBiomeType.class, id);
        }

        public static OverworldBiomeType<?, ?, ?>[] values() {
            return values(OverworldBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}
