package org.corundummc.biomes.overworld.planar;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenSnow;

import org.corundummc.biomes.interfaces.instances.ColdBiome;
import org.corundummc.biomes.interfaces.types.ColdBiomeType;
import org.corundummc.biomes.overworld.planar.PlainsBiome.PlainsBiomeType;
import org.corundummc.biomes.overworld.planar.PlanarBiome.PlanarBiomeType;

public class IcePlainsBiome extends PlanarBiome<IcePlainsBiome, BiomeGenSnow, IcePlainsBiome.IcePlainsBiomeType> implements ColdBiome<PlainsBiomeType> {
    protected IcePlainsBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class IcePlainsBiomeType extends PlanarBiomeType<IcePlainsBiomeType, BiomeGenSnow, IcePlainsBiome> implements ColdBiomeType<PlainsBiomeType> {
        public static final IcePlainsBiomeType TYPE = new IcePlainsBiomeType();

        private IcePlainsBiomeType() {
            super((BiomeGenSnow) BiomeGenBase.icePlains);
        }

        @Override
        public IcePlainsBiome fromLocation(Location location) {
            return new IcePlainsBiome(location);
        }

        @Override
        public PlainsBiomeType getWarmRelative() {
            return PlainsBiomeType.TYPE;
        }
    }

    @Override
    public IcePlainsBiomeType getType() {
        return IcePlainsBiomeType.TYPE;
    }

    @Override
    public PlainsBiomeType getWarmRelativeType() {
        return getType().getWarmRelative();
    }
}