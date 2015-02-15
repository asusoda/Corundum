package org.corundummc.biomes.overworld.aquatic.oceanic;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenOcean;

import org.corundummc.biomes.overworld.aquatic.oceanic.OceanicBiome.OceanicBiomeType;

public class OceanBiome extends OceanicBiome<OceanBiome, OceanBiome.OceanBiomeType> {
    protected OceanBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class OceanBiomeType extends OceanicBiomeType<OceanBiomeType, OceanBiome> {
        public static final OceanBiomeType TYPE = new OceanBiomeType();

        private OceanBiomeType() {
            super((BiomeGenOcean) BiomeGenBase.ocean);
        }

        @Override
        public OceanBiome fromLocation(Location location) {
            return new OceanBiome(location);
        }
    }

    @Override
    public OceanBiomeType getType() {
        return OceanBiomeType.TYPE;
    }
}