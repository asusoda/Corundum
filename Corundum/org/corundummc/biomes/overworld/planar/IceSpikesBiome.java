package org.corundummc.biomes.overworld.planar;

import net.minecraft.world.biome.BiomeGenBase;

import org.corundummc.biomes.overworld.planar.PlanarBiome.PlanarBiomeType;
import org.corundummc.biomes.overworld.planar.SunflowerPlainsBiome.SunflowerPlainsBiomeType;

import net.minecraft.world.biome.BiomeGenMutated;

import org.corundummc.biomes.interfaces.instances.ColdBiome;
import org.corundummc.biomes.interfaces.instances.MutatedBiome;
import org.corundummc.biomes.interfaces.types.ColdBiomeType;
import org.corundummc.biomes.interfaces.types.MutatedBiomeType;
import org.corundummc.biomes.overworld.planar.IcePlainsBiome.IcePlainsBiomeType;
import org.corundummc.world.Location;

public class IceSpikesBiome extends PlanarBiome<IceSpikesBiome, BiomeGenMutated, IceSpikesBiome.IceSpikesBiomeType> implements MutatedBiome<IcePlainsBiomeType>,
        ColdBiome<SunflowerPlainsBiomeType> {
    protected IceSpikesBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class IceSpikesBiomeType extends PlanarBiomeType<IceSpikesBiomeType, BiomeGenMutated, IceSpikesBiome> implements MutatedBiomeType<IcePlainsBiomeType>,
            ColdBiomeType<SunflowerPlainsBiomeType> {
        public static final IceSpikesBiomeType TYPE = new IceSpikesBiomeType();

        private IceSpikesBiomeType() {
            super((BiomeGenMutated) BiomeGenBase.getBiome(140));
        }

        @Override
        public IceSpikesBiome fromLocation(Location location) {
            return new IceSpikesBiome(location);
        }

        @Override
        public IcePlainsBiomeType getBase() {
            return IcePlainsBiomeType.TYPE;
        }

        @Override
        public SunflowerPlainsBiomeType getWarmRelative() {
            return SunflowerPlainsBiomeType.TYPE;
        }
    }

    @Override
    public IcePlainsBiomeType getBaseType() {
        return getType().getBase();
    }

    @Override
    public SunflowerPlainsBiomeType getWarmRelativeType() {
        return getType().getWarmRelative();
    }

    @Override
    public IceSpikesBiomeType getType() {
        return IceSpikesBiomeType.TYPE;
    }
}