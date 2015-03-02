package org.corundummc.biomes.overworld.sandy.beach;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBeach;

import org.corundummc.biomes.overworld.sandy.beach.BeachesqueBiome.BeachesqueBiomeType;

public class ColdBeachBiome extends BeachesqueBiome<ColdBeachBiome, BiomeGenBeach, ColdBeachBiome.ColdBeachBiomeType> {
    protected ColdBeachBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class ColdBeachBiomeType extends BeachesqueBiomeType<ColdBeachBiomeType, BiomeGenBeach, ColdBeachBiome> {
        public static final ColdBeachBiomeType TYPE = new ColdBeachBiomeType();

        private ColdBeachBiomeType() {
            super((BiomeGenBeach) BiomeGenBase.coldBeach);
        }

        @Override
        public ColdBeachBiome fromLocation(Location location) {
            return new ColdBeachBiome(location);
        }
    }

    @Override
    public ColdBeachBiomeType getType() {
        return ColdBeachBiomeType.TYPE;
    }
}