package org.corundummc.biomes.overworld.aquatic.oceanic;

import org.corundummc.biomes.overworld.aquatic.oceanic.OceanicBiome.OceanicBiomeType;
import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenOcean;

public class DeepOceanBiome extends OceanicBiome<DeepOceanBiome, DeepOceanBiome.DeepOceanBiomeType> {
    protected DeepOceanBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class DeepOceanBiomeType extends OceanicBiomeType<DeepOceanBiomeType, DeepOceanBiome> {
        public static final DeepOceanBiomeType TYPE = new DeepOceanBiomeType();

        private DeepOceanBiomeType() {
            super((BiomeGenOcean) BiomeGenBase.field_150575_M);
        }

        @Override
        public DeepOceanBiome fromLocation(Location location) {
            return new DeepOceanBiome(location);
        }
    }

    @Override
    public DeepOceanBiomeType getType() {
        return DeepOceanBiomeType.TYPE;
    }
}