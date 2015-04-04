package org.corundummc.biomes.overworld.arboreal.forest;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenForest;

import org.corundummc.biomes.overworld.arboreal.forest.ForestedBiome.ForestedBiomeType;

public class ForestBiome extends ForestedBiome<ForestBiome, BiomeGenForest, ForestBiome.ForestBiomeType> {
    protected ForestBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class ForestBiomeType extends ForestedBiomeType<ForestBiomeType, BiomeGenForest, ForestBiome> {
        public static final ForestBiomeType TYPE = new ForestBiomeType();

        private ForestBiomeType() {
            super((BiomeGenForest) BiomeGenBase.forest);
        }

        @Override
        public ForestBiome fromLocation(Location location) {
            return new ForestBiome(location);
        }
    }

    @Override
    public ForestBiomeType getType() {
        return ForestBiomeType.TYPE;
    }
}