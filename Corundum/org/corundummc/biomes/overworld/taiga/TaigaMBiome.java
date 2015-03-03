package org.corundummc.biomes.overworld.taiga;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;

import org.corundummc.biomes.interfaces.instances.MutatedBiome;
import org.corundummc.biomes.interfaces.types.MutatedBiomeType;
import org.corundummc.biomes.overworld.taiga.TaigaBiome.TaigaBiomeType;
import org.corundummc.biomes.overworld.taiga.TaigaesqueBiome.TaigaesqueBiomeType;

public class TaigaMBiome extends TaigaesqueBiome<TaigaMBiome, BiomeGenMutated, TaigaMBiome.TaigaMBiomeType> implements MutatedBiome<TaigaBiomeType> {
    protected TaigaMBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class TaigaMBiomeType extends TaigaesqueBiomeType<TaigaMBiomeType, BiomeGenMutated, TaigaMBiome> implements MutatedBiomeType<TaigaBiomeType> {
        public static final TaigaMBiomeType TYPE = new TaigaMBiomeType();

        private TaigaMBiomeType() {
            super((BiomeGenMutated) BiomeGenBase.getBiome(133));
        }

        @Override
        public TaigaMBiome fromLocation(Location location) {
            return new TaigaMBiome(location);
        }

        @Override
        public TaigaBiomeType getBase() {
            return TaigaBiomeType.TYPE;
        }
    }

    @Override
    public TaigaBiomeType getBaseType() {
        return getType().getBase();
    }

    @Override
    public TaigaMBiomeType getType() {
        return TaigaMBiomeType.TYPE;
    }
}