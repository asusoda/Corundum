package org.corundummc.biomes.overworld.arboreal.forest.birch;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;

import org.corundummc.biomes.overworld.arboreal.forest.ForestedBiome;
import org.corundummc.biomes.overworld.arboreal.forest.birch.BirchForestBiome.BirchForestBiomeType;
import org.corundummc.biomes.overworld.arboreal.forest.birch.BirchForestMBiome.BirchForestMBiomeType;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft BiomeGenBase <tt>Object</tt> that this class's {@link BiomeType} represents.
 * @param <T>
 *            determines the type of {@link BiomeType} that represents the type of this class. */
public abstract class BirchForestedBiome<S extends BirchForestedBiome<S, MC, T>, MC extends BiomeGenBase, T extends BirchForestedBiome.BirchForestedBiomeType<T, MC, S>>
        extends ForestedBiome<S, MC, T> {

    protected BirchForestedBiome(Location location) {
        super(location);
    }

    public static interface BirchForestedBiomeTypes {
        public static final BirchForestBiomeType BIRCH_FOREST = BirchForestBiomeType.TYPE;
        public static final BirchForestMBiomeType BIRCH_FOREST_M = BirchForestMBiomeType.TYPE;
    }

    public abstract static class BirchForestedBiomeType<S extends BirchForestedBiomeType<S, MC, I>, MC extends BiomeGenBase, I extends BirchForestedBiome<I, MC, S>> extends
            ForestedBiomeType<S, MC, I> {
        protected BirchForestedBiomeType(MC biomeMC) {
            super(biomeMC);

            addValueAs(BirchForestedBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static BirchForestedBiomeType<?, ?, ?> getByID(int id) {
            return getByID(BirchForestedBiomeType.class, id);
        }

        public static BirchForestedBiomeType<?, ?, ?>[] values() {
            return values(BirchForestedBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}