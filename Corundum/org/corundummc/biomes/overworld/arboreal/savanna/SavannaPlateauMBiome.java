package org.corundummc.biomes.overworld.arboreal.savanna;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;

import org.corundummc.biomes.interfaces.instances.MutatedBiome;
import org.corundummc.biomes.interfaces.instances.VariantBiome;
import org.corundummc.biomes.interfaces.types.MutatedBiomeType;
import org.corundummc.biomes.interfaces.types.VariantBiomeType;
import org.corundummc.biomes.overworld.arboreal.savanna.SavannaBiome.SavannaBiomeType;
import org.corundummc.biomes.overworld.arboreal.savanna.SavannaPlateauBiome.SavannaPlateauBiomeType;
import org.corundummc.biomes.overworld.arboreal.savanna.SavannaesqueBiome.SavannaesqueBiomeType;
import org.corundummc.world.Location;

public class SavannaPlateauMBiome extends SavannaesqueBiome<SavannaPlateauMBiome, BiomeGenMutated, SavannaPlateauMBiome.SavannaPlateauMBiomeType> implements
        MutatedBiome<SavannaPlateauBiomeType> {
    protected SavannaPlateauMBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class SavannaPlateauMBiomeType extends SavannaesqueBiomeType<SavannaPlateauMBiomeType, BiomeGenMutated, SavannaPlateauMBiome> implements
            MutatedBiomeType<SavannaPlateauBiomeType> {
        public static final SavannaPlateauMBiomeType TYPE = new SavannaPlateauMBiomeType();

        private SavannaPlateauMBiomeType() {
            super((BiomeGenMutated) BiomeGenBase.getBiome(164));
        }

        @Override
        public SavannaPlateauMBiome fromLocation(Location location) {
            return new SavannaPlateauMBiome(location);
        }

        @Override
        public SavannaPlateauBiomeType getBase() {
            return SavannaPlateauBiomeType.TYPE;
        }
    }

    @Override
    public SavannaPlateauBiomeType getBaseType() {
        return getType().getBase();
    }

    @Override
    public SavannaPlateauMBiomeType getType() {
        return SavannaPlateauMBiomeType.TYPE;
    }
}