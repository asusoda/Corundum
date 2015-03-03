package org.corundummc.biomes.overworld.taiga;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenTaiga;

import org.corundummc.biomes.interfaces.instances.ColdBiome;
import org.corundummc.biomes.interfaces.types.ColdBiomeType;
import org.corundummc.biomes.overworld.taiga.TaigaBiome.TaigaBiomeType;
import org.corundummc.biomes.overworld.taiga.TaigaesqueBiome.TaigaesqueBiomeType;
import org.corundummc.world.Location;

public class ColdTaigaBiome extends TaigaesqueBiome<ColdTaigaBiome, BiomeGenTaiga, ColdTaigaBiome.ColdTaigaBiomeType> implements ColdBiome<TaigaBiomeType> {
    protected ColdTaigaBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class ColdTaigaBiomeType extends TaigaesqueBiomeType<ColdTaigaBiomeType, BiomeGenTaiga, ColdTaigaBiome> implements ColdBiomeType<TaigaBiomeType> {
        public static final ColdTaigaBiomeType TYPE = new ColdTaigaBiomeType();

        private ColdTaigaBiomeType() {
            super((BiomeGenTaiga) BiomeGenBase.coldTaiga);
        }

        @Override
        public ColdTaigaBiome fromLocation(Location location) {
            return new ColdTaigaBiome(location);
        }

        @Override
        public TaigaBiomeType getWarmRelative() {
            return TaigaBiomeType.TYPE;
        }
    }

    @Override
    public TaigaBiomeType getWarmRelativeType() {
        return getType().getWarmRelative();
    }

    @Override
    public ColdTaigaBiomeType getType() {
        return ColdTaigaBiomeType.TYPE;
    }
}