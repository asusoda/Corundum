package org.corundummc.biomes.overworld.arboreal.forest;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenForest;

import org.corundummc.biomes.overworld.arboreal.forest.ForestedBiome.ForestedBiomeType;

public class ForestHillsBiome extends ForestedBiome<ForestHillsBiome, BiomeGenForest, ForestHillsBiome.ForestHillsBiomeType> {
    protected ForestHillsBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class ForestHillsBiomeType extends ForestedBiomeType<ForestHillsBiomeType, BiomeGenForest, ForestHillsBiome> {
        public static final ForestHillsBiomeType TYPE = new ForestHillsBiomeType();

        private ForestHillsBiomeType() {
            super((BiomeGenForest) BiomeGenBase.forestHills);
        }

        @Override
        public ForestHillsBiome fromLocation(Location location) {
            return new ForestHillsBiome(location);
        }
    }

    @Override
    public ForestHillsBiomeType getType() {
        return ForestHillsBiomeType.TYPE;
    }
}