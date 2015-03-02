package org.corundummc.biomes.overworld.sandy.beach;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenStoneBeach;

import org.corundummc.biomes.overworld.sandy.beach.BeachesqueBiome.BeachesqueBiomeType;

public class StoneBeachBiome extends BeachesqueBiome<StoneBeachBiome, BiomeGenStoneBeach, StoneBeachBiome.StoneBeachBiomeType> {
    protected StoneBeachBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class StoneBeachBiomeType extends BeachesqueBiomeType<StoneBeachBiomeType, BiomeGenStoneBeach, StoneBeachBiome> {
        public static final StoneBeachBiomeType TYPE = new StoneBeachBiomeType();

        private StoneBeachBiomeType() {
            super((BiomeGenStoneBeach) BiomeGenBase.stoneBeach);
        }

        @Override
        public StoneBeachBiome fromLocation(Location location) {
            return new StoneBeachBiome(location);
        }
    }

    @Override
    public StoneBeachBiomeType getType() {
        return StoneBeachBiomeType.TYPE;
    }
}