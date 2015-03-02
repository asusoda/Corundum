package org.corundummc.biomes.overworld.planar;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;

import org.corundummc.biomes.interfaces.instances.MutatedBiome;
import org.corundummc.biomes.interfaces.types.MutatedBiomeType;
import org.corundummc.biomes.overworld.planar.PlainsBiome.PlainsBiomeType;
import org.corundummc.biomes.overworld.planar.PlanarBiome.PlanarBiomeType;

public class SunflowerPlainsBiome extends PlanarBiome<SunflowerPlainsBiome, BiomeGenMutated, SunflowerPlainsBiome.SunflowerPlainsBiomeType> implements
        MutatedBiome<PlainsBiomeType> {
    protected SunflowerPlainsBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class SunflowerPlainsBiomeType extends PlanarBiomeType<SunflowerPlainsBiomeType, BiomeGenMutated, SunflowerPlainsBiome> implements
            MutatedBiomeType<PlainsBiomeType> {
        public static final SunflowerPlainsBiomeType TYPE = new SunflowerPlainsBiomeType();

        private SunflowerPlainsBiomeType() {
            super((BiomeGenMutated) BiomeGenBase.getBiome(129));
        }

        @Override
        public SunflowerPlainsBiome fromLocation(Location location) {
            return new SunflowerPlainsBiome(location);
        }

        @Override
        public PlainsBiomeType getBase() {
            return PlainsBiomeType.TYPE;
        }
    }

    @Override
    public PlainsBiomeType getBaseType() {
        return getType().getBase();
    }

    @Override
    public SunflowerPlainsBiomeType getType() {
        return SunflowerPlainsBiomeType.TYPE;
    }
}