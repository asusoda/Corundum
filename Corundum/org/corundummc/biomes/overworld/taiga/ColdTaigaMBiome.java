package org.corundummc.biomes.overworld.taiga;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;

import org.corundummc.biomes.interfaces.instances.ColdBiome;
import org.corundummc.biomes.interfaces.instances.MutatedBiome;
import org.corundummc.biomes.interfaces.types.ColdBiomeType;
import org.corundummc.biomes.interfaces.types.MutatedBiomeType;
import org.corundummc.biomes.overworld.taiga.ColdTaigaBiome.ColdTaigaBiomeType;
import org.corundummc.biomes.overworld.taiga.TaigaMBiome.TaigaMBiomeType;
import org.corundummc.biomes.overworld.taiga.TaigaesqueBiome.TaigaesqueBiomeType;
import org.corundummc.world.Location;

public class ColdTaigaMBiome extends TaigaesqueBiome<ColdTaigaMBiome, BiomeGenMutated, ColdTaigaMBiome.ColdTaigaMBiomeType> implements MutatedBiome<ColdTaigaBiomeType>,
        ColdBiome<TaigaMBiomeType> {
    protected ColdTaigaMBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class ColdTaigaMBiomeType extends TaigaesqueBiomeType<ColdTaigaMBiomeType, BiomeGenMutated, ColdTaigaMBiome> implements MutatedBiomeType<ColdTaigaBiomeType>,
            ColdBiomeType<TaigaMBiomeType> {
        public static final ColdTaigaMBiomeType TYPE = new ColdTaigaMBiomeType();

        private ColdTaigaMBiomeType() {
            super((BiomeGenMutated) BiomeGenBase.getBiome(158));
        }

        @Override
        public ColdTaigaMBiome fromLocation(Location location) {
            return new ColdTaigaMBiome(location);
        }

        @Override
        public ColdTaigaBiomeType getBase() {
            return ColdTaigaBiomeType.TYPE;
        }

        @Override
        public TaigaMBiomeType getWarmRelative() {
            return TaigaMBiomeType.TYPE;
        }
    }

    @Override
    public ColdTaigaBiomeType getBaseType() {
        return getType().getBase();
    }

    @Override
    public TaigaMBiomeType getWarmRelativeType() {
        return getType().getWarmRelative();
    }

    @Override
    public ColdTaigaMBiomeType getType() {
        return ColdTaigaMBiomeType.TYPE;
    }
}