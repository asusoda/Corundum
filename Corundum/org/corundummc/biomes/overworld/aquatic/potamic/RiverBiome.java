package org.corundummc.biomes.overworld.aquatic.potamic;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenRiver;

import org.corundummc.biomes.overworld.aquatic.potamic.PotamicBiome.PotamicBiomeType;

public class RiverBiome extends PotamicBiome<RiverBiome, RiverBiome.RiverBiomeType> {
    protected RiverBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class RiverBiomeType extends PotamicBiomeType<RiverBiomeType, RiverBiome> {
        public static final RiverBiomeType TYPE = new RiverBiomeType();

        private RiverBiomeType() {
            super((BiomeGenRiver) BiomeGenBase.river);
        }

        @Override
        public RiverBiome fromLocation(Location location) {
            return new RiverBiome(location);
        }
    }

    @Override
    public RiverBiomeType getType() {
        return RiverBiomeType.TYPE;
    }
}