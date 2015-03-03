package org.corundummc.biomes.overworld.taiga;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenTaiga;

import org.corundummc.biomes.overworld.taiga.TaigaesqueBiome.TaigaesqueBiomeType;

public class TaigaHillsBiome extends TaigaesqueBiome<TaigaHillsBiome, BiomeGenTaiga, TaigaHillsBiome.TaigaHillsBiomeType> {
    protected TaigaHillsBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class TaigaHillsBiomeType extends TaigaesqueBiomeType<TaigaHillsBiomeType, BiomeGenTaiga, TaigaHillsBiome> {
        public static final TaigaHillsBiomeType TYPE = new TaigaHillsBiomeType();

        private TaigaHillsBiomeType() {
            super((BiomeGenTaiga) BiomeGenBase.taigaHills);
        }

        @Override
        public TaigaHillsBiome fromLocation(Location location) {
            return new TaigaHillsBiome(location);
        }
    }

    @Override
    public TaigaHillsBiomeType getType() {
        return TaigaHillsBiomeType.TYPE;
    }
}