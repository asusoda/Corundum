package org.corundummc.biomes.overworld.arboreal.taiga;

import org.corundummc.biomes.interfaces.instances.ColdBiome;
import org.corundummc.biomes.interfaces.types.ColdBiomeType;
import org.corundummc.biomes.overworld.arboreal.taiga.TaigaHillsBiome.TaigaHillsBiomeType;
import org.corundummc.biomes.overworld.arboreal.taiga.TaigaesqueBiome.TaigaesqueBiomeType;
import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenTaiga;

public class ColdTaigaHillsBiome extends TaigaesqueBiome<ColdTaigaHillsBiome, BiomeGenTaiga, ColdTaigaHillsBiome.ColdTaigaHillsBiomeType> implements
        ColdBiome<TaigaHillsBiomeType> {
    protected ColdTaigaHillsBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class ColdTaigaHillsBiomeType extends TaigaesqueBiomeType<ColdTaigaHillsBiomeType, BiomeGenTaiga, ColdTaigaHillsBiome> implements
            ColdBiomeType<TaigaHillsBiomeType> {
        public static final ColdTaigaHillsBiomeType TYPE = new ColdTaigaHillsBiomeType();

        private ColdTaigaHillsBiomeType() {
            super((BiomeGenTaiga) BiomeGenBase.coldTaigaHills);
        }

        @Override
        public ColdTaigaHillsBiome fromLocation(Location location) {
            return new ColdTaigaHillsBiome(location);
        }

        @Override
        public TaigaHillsBiomeType getWarmRelative() {
            return TaigaHillsBiomeType.TYPE;
        }
    }

    @Override
    public TaigaHillsBiomeType getWarmRelativeType() {
        return getType().getWarmRelative();
    }

    @Override
    public ColdTaigaHillsBiomeType getType() {
        return ColdTaigaHillsBiomeType.TYPE;
    }
}