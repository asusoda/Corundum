package org.corundummc.biomes.overworld.arboreal.forest.birch;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenForest;

import org.corundummc.world.Location;
import org.corundummc.biomes.overworld.arboreal.forest.birch.BirchForestedBiome.BirchForestedBiomeType;

public class BirchForestBiome extends BirchForestedBiome<BirchForestBiome, BiomeGenForest, BirchForestBiome.BirchForestBiomeType> {
    protected BirchForestBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class BirchForestBiomeType extends BirchForestedBiomeType<BirchForestBiomeType, BiomeGenForest, BirchForestBiome> {
        public static final BirchForestBiomeType TYPE = new BirchForestBiomeType();

        private BirchForestBiomeType() {
            super((BiomeGenForest) BiomeGenBase.birchForest);
        }

        @Override
        public BirchForestBiome fromLocation(Location location) {
            return new BirchForestBiome(location);
        }
    }

    @Override
    public BirchForestBiomeType getType() {
        return BirchForestBiomeType.TYPE;
    }
}