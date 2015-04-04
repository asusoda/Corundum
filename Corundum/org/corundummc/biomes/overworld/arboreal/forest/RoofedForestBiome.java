package org.corundummc.biomes.overworld.arboreal.forest;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenForest;

import org.corundummc.biomes.overworld.arboreal.forest.ForestedBiome.ForestedBiomeType;
import org.corundummc.world.Location;

public class RoofedForestBiome extends ForestedBiome<RoofedForestBiome, BiomeGenForest, RoofedForestBiome.RoofedForestBiomeType> {
    protected RoofedForestBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class RoofedForestBiomeType extends ForestedBiomeType<RoofedForestBiomeType, BiomeGenForest, RoofedForestBiome> {
        public static final RoofedForestBiomeType TYPE = new RoofedForestBiomeType();

        private RoofedForestBiomeType() {
            super((BiomeGenForest) BiomeGenBase.roofedForest);
        }

        @Override
        public RoofedForestBiome fromLocation(Location location) {
            return new RoofedForestBiome(location);
        }
    }

    @Override
    public RoofedForestBiomeType getType() {
        return RoofedForestBiomeType.TYPE;
    }
}