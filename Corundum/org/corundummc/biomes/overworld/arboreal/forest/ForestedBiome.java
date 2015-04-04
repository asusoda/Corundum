package org.corundummc.biomes.overworld.arboreal.forest;

import org.corundummc.biomes.overworld.arboreal.ArborealBiome;
import org.corundummc.biomes.overworld.arboreal.forest.FlowerForestBiome.FlowerForestBiomeType;
import org.corundummc.biomes.overworld.arboreal.forest.ForestBiome.ForestBiomeType;
import org.corundummc.biomes.overworld.arboreal.forest.ForestHillsBiome.ForestHillsBiomeType;
import org.corundummc.biomes.overworld.arboreal.forest.RoofedForestBiome.RoofedForestBiomeType;
import org.corundummc.biomes.overworld.arboreal.forest.birch.BirchForestedBiome.BirchForestedBiomeTypes;
import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft BiomeGenBase <tt>Object</tt> that this class's {@link BiomeType} represents.
 * @param <T>
 *            determines the type of {@link BiomeType} that represents the type of this class. */
public abstract class ForestedBiome<S extends ForestedBiome<S, MC, T>, MC extends BiomeGenBase, T extends ForestedBiome.ForestedBiomeType<T, MC, S>> extends
        ArborealBiome<S, MC, T> {

    protected ForestedBiome(Location location) {
        super(location);
    }

    public static interface ForestedBiomeTypes extends BirchForestedBiomeTypes {
        public static final ForestBiomeType FOREST = ForestBiomeType.TYPE;
        public static final ForestHillsBiomeType FOREST_HILLS = ForestHillsBiomeType.TYPE;
        public static final RoofedForestBiomeType ROOFED_FOREST = RoofedForestBiomeType.TYPE;
        public static final FlowerForestBiomeType FLOWER_FOREST = FlowerForestBiomeType.TYPE;
    }

    public abstract static class ForestedBiomeType<S extends ForestedBiomeType<S, MC, I>, MC extends BiomeGenBase, I extends ForestedBiome<I, MC, S>> extends
            ArborealBiomeType<S, MC, I> {
        protected ForestedBiomeType(MC biomeMC) {
            super(biomeMC);

            addValueAs(ForestedBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static ForestedBiomeType<?, ?, ?> getByID(int id) {
            return getByID(ForestedBiomeType.class, id);
        }

        public static ForestedBiomeType<?, ?, ?>[] values() {
            return values(ForestedBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}