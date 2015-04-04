package org.corundummc.biomes.overworld.arboreal.forest;

import org.corundummc.biomes.interfaces.instances.MutatedBiome;
import org.corundummc.biomes.interfaces.types.MutatedBiomeType;
import org.corundummc.world.Location;

import org.corundummc.biomes.overworld.arboreal.forest.ForestBiome.ForestBiomeType;
import org.corundummc.biomes.overworld.arboreal.forest.ForestedBiome.ForestedBiomeType;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;

public class FlowerForestBiome extends ForestedBiome<FlowerForestBiome, BiomeGenMutated, FlowerForestBiome.FlowerForestBiomeType> implements MutatedBiome<ForestBiomeType> {
    protected FlowerForestBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class FlowerForestBiomeType extends ForestedBiomeType<FlowerForestBiomeType, BiomeGenMutated, FlowerForestBiome> implements MutatedBiomeType<ForestBiomeType> {
        public static final FlowerForestBiomeType TYPE = new FlowerForestBiomeType();

        private FlowerForestBiomeType() {
            super((BiomeGenMutated) BiomeGenBase.getBiome(132));
        }

        @Override
        public FlowerForestBiome fromLocation(Location location) {
            return new FlowerForestBiome(location);
        }

        @Override
        public ForestBiomeType getBase() {
            return ForestBiomeType.TYPE;
        }
    }

    @Override
    public ForestBiomeType getBaseType() {
        return getType().getBase();
    }

    @Override
    public FlowerForestBiomeType getType() {
        return FlowerForestBiomeType.TYPE;
    }
}