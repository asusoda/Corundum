package org.corundummc.biomes.overworld.arboreal.savanna;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenSavanna;

import org.corundummc.biomes.overworld.arboreal.savanna.SavannaesqueBiome.SavannaesqueBiomeType;

import org.corundummc.biomes.overworld.arboreal.savanna.SavannaBiome.SavannaBiomeType;
import org.corundummc.biomes.interfaces.instances.VariantBiome;
import org.corundummc.biomes.interfaces.types.VariantBiomeType;

public class SavannaPlateauBiome extends SavannaesqueBiome<SavannaPlateauBiome, BiomeGenSavanna, SavannaPlateauBiome.SavannaPlateauBiomeType> implements
        VariantBiome<SavannaBiomeType> {
    protected SavannaPlateauBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class SavannaPlateauBiomeType extends SavannaesqueBiomeType<SavannaPlateauBiomeType, BiomeGenSavanna, SavannaPlateauBiome> implements
            VariantBiomeType<SavannaBiomeType> {
        public static final SavannaPlateauBiomeType TYPE = new SavannaPlateauBiomeType();

        private SavannaPlateauBiomeType() {
            super((BiomeGenSavanna) BiomeGenBase.savannaPlateau);
        }

        @Override
        public SavannaPlateauBiome fromLocation(Location location) {
            return new SavannaPlateauBiome(location);
        }

        @Override
        public SavannaBiomeType getBase() {
            return SavannaBiomeType.TYPE;
        }
    }

    @Override
    public SavannaBiomeType getBaseType() {
        return getType().getBase();
    }

    @Override
    public SavannaPlateauBiomeType getType() {
        return SavannaPlateauBiomeType.TYPE;
    }
}