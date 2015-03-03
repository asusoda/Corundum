package org.corundummc.biomes.overworld.taiga;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenTaiga;

import org.corundummc.biomes.overworld.taiga.TaigaesqueBiome.TaigaesqueBiomeType;

public class TaigaBiome extends TaigaesqueBiome<TaigaBiome, BiomeGenTaiga, TaigaBiome.TaigaBiomeType> {
    protected TaigaBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class TaigaBiomeType extends TaigaesqueBiomeType<TaigaBiomeType, BiomeGenTaiga, TaigaBiome> {
        public static final TaigaBiomeType TYPE = new TaigaBiomeType();

        private TaigaBiomeType() {
            super((BiomeGenTaiga) BiomeGenBase.taiga);
        }

        @Override
        public TaigaBiome fromLocation(Location location) {
            return new TaigaBiome(location);
        }
    }

    @Override
    public TaigaBiomeType getType() {
        return TaigaBiomeType.TYPE;
    }
}