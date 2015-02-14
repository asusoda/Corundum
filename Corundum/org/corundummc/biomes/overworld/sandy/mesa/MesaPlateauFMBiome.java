package org.corundummc.biomes.overworld.sandy.mesa;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;

import org.corundummc.biomes.interfaces.instances.MutatedBiome;
import org.corundummc.biomes.interfaces.types.MutatedBiomeType;
import org.corundummc.biomes.overworld.sandy.mesa.MesaBiome.MesaBiomeType;
import org.corundummc.biomes.overworld.sandy.mesa.MesaesqueBiome.MesaesqueBiomeType;
import org.corundummc.world.Location;

public class MesaPlateauFMBiome extends MesaesqueBiome<MesaPlateauFMBiome, BiomeGenMutated, MesaPlateauFMBiome.MesaPlateauFMBiomeType> implements MutatedBiome<MesaBiomeType> {
    protected MesaPlateauFMBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class MesaPlateauFMBiomeType extends MesaesqueBiomeType<MesaPlateauFMBiomeType, BiomeGenMutated, MesaPlateauFMBiome> implements MutatedBiomeType<MesaBiomeType> {
        public static final MesaPlateauFMBiomeType TYPE = new MesaPlateauFMBiomeType();

        private MesaPlateauFMBiomeType() {
            super((BiomeGenMutated) BiomeGenBase.func_150568_d(166));
        }

        @Override
        public MesaPlateauFMBiome fromLocation(Location location) {
            return new MesaPlateauFMBiome(location);
        }

        @Override
        public MesaBiomeType getBaseBiomeType() {
            return MesaBiomeType.TYPE;
        }
    }

    @Override
    public MesaBiomeType getBaseBiomeType() {
        return getType().getBaseBiomeType();
    }

    @Override
    public MesaPlateauFMBiomeType getType() {
        return MesaPlateauFMBiomeType.TYPE;
    }
}