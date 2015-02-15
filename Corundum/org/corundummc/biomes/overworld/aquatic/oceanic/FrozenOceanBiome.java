package org.corundummc.biomes.overworld.aquatic.oceanic;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenOcean;

import org.corundummc.biomes.interfaces.instances.ColdBiome;
import org.corundummc.biomes.interfaces.types.ColdBiomeType;
import org.corundummc.biomes.overworld.aquatic.oceanic.OceanicBiome;
import org.corundummc.biomes.overworld.aquatic.oceanic.OceanBiome.OceanBiomeType;
import org.corundummc.biomes.overworld.aquatic.oceanic.OceanicBiome.OceanicBiomeType;

public class FrozenOceanBiome extends OceanicBiome<FrozenOceanBiome, FrozenOceanBiome.FrozenOceanBiomeType> implements ColdBiome<OceanBiomeType> {
    protected FrozenOceanBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class FrozenOceanBiomeType extends OceanicBiomeType<FrozenOceanBiomeType, FrozenOceanBiome> implements ColdBiomeType<OceanBiomeType> {
        public static final FrozenOceanBiomeType TYPE = new FrozenOceanBiomeType();

        private FrozenOceanBiomeType() {
            super((BiomeGenOcean) BiomeGenBase.frozenOcean);
        }

        @Override
        public FrozenOceanBiome fromLocation(Location location) {
            return new FrozenOceanBiome(location);
        }

        @Override
        public OceanBiomeType getWarmRelative() {
            return OceanBiomeType.TYPE;
        }
    }

    @Override
    public OceanBiomeType getWarmRelativeType() {
        return getType().getWarmRelative();
    }

    @Override
    public FrozenOceanBiomeType getType() {
        return FrozenOceanBiomeType.TYPE;
    }
}