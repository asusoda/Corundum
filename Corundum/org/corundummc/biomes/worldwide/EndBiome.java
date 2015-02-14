package org.corundummc.biomes.worldwide;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenEnd;

import org.corundummc.biomes.Biome;
import org.corundummc.biomes.Biome.BiomeType;
import org.corundummc.biomes.worldwide.WorldwideBiome.WorldwideBiomeType;

public class EndBiome extends WorldwideBiome<EndBiome, BiomeGenEnd, EndBiome.EndBiomeType> {
    protected EndBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class EndBiomeType extends WorldwideBiomeType<EndBiomeType, BiomeGenEnd, EndBiome> {
        public static final EndBiomeType TYPE = new EndBiomeType();

        private EndBiomeType() {
            super((BiomeGenEnd) BiomeGenBase.sky /* yes, apparenly the "sky" biome became "The End" biome */);
        }

        @Override
        public EndBiome fromLocation(Location location) {
            return new EndBiome(location);
        }
    }

    @Override
    public EndBiomeType getType() {
        return EndBiomeType.TYPE;
    }
}