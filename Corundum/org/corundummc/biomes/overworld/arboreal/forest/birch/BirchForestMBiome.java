package org.corundummc.biomes.overworld.arboreal.forest.birch;

import org.corundummc.biomes.interfaces.types.MutatedBiomeType;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;

import org.corundummc.biomes.overworld.arboreal.forest.birch.BirchForestedBiome.BirchForestedBiomeType;
import org.corundummc.biomes.overworld.arboreal.forest.birch.BirchForestBiome.BirchForestBiomeType;

import org.corundummc.biomes.interfaces.instances.MutatedBiome;
import org.corundummc.world.Location;

public class BirchForestMBiome extends BirchForestedBiome<BirchForestMBiome, BiomeGenMutated, BirchForestMBiome.BirchForestMBiomeType> implements
        MutatedBiome<BirchForestBiomeType> {
    protected BirchForestMBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class BirchForestMBiomeType extends BirchForestedBiomeType<BirchForestMBiomeType, BiomeGenMutated, BirchForestMBiome> implements
            MutatedBiomeType<BirchForestBiomeType> {
        public static final BirchForestMBiomeType TYPE = new BirchForestMBiomeType();

        private BirchForestMBiomeType() {
            super((BiomeGenMutated) BiomeGenBase.getBiome(155));
        }

        @Override
        public BirchForestMBiome fromLocation(Location location) {
            return new BirchForestMBiome(location);
        }

        @Override
        public BirchForestBiomeType getBase() {
            return BirchForestBiomeType.TYPE;
        }
    }

    @Override
    public BirchForestBiomeType getBaseType() {
        return getType().getBase();
    }

    @Override
    public BirchForestMBiomeType getType() {
        return BirchForestMBiomeType.TYPE;
    }
}