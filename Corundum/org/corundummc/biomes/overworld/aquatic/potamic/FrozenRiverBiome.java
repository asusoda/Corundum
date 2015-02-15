package org.corundummc.biomes.overworld.aquatic.potamic;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenRiver;

import org.corundummc.biomes.interfaces.instances.ColdBiome;
import org.corundummc.biomes.interfaces.types.ColdBiomeType;
import org.corundummc.biomes.overworld.aquatic.potamic.PotamicBiome.PotamicBiomeType;
import org.corundummc.biomes.overworld.aquatic.potamic.RiverBiome.RiverBiomeType;

public class FrozenRiverBiome extends PotamicBiome<FrozenRiverBiome, FrozenRiverBiome.FrozenRiverBiomeType> implements ColdBiome<RiverBiomeType> {
    protected FrozenRiverBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class FrozenRiverBiomeType extends PotamicBiomeType<FrozenRiverBiomeType, FrozenRiverBiome> implements ColdBiomeType<RiverBiomeType> {
        public static final FrozenRiverBiomeType TYPE = new FrozenRiverBiomeType();

        private FrozenRiverBiomeType() {
            super((BiomeGenRiver) BiomeGenBase.frozenRiver);
        }

        @Override
        public FrozenRiverBiome fromLocation(Location location) {
            return new FrozenRiverBiome(location);
        }

        @Override
        public RiverBiomeType getWarmRelative() {
            return RiverBiomeType.TYPE;
        }
    }

    @Override
    public RiverBiomeType getWarmRelativeType() {
        return getType().getWarmRelative();
    }

    @Override
    public FrozenRiverBiomeType getType() {
        return FrozenRiverBiomeType.TYPE;
    }
}