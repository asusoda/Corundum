package org.corundummc.biomes.overworld.sandy.mesa;

import org.corundummc.biomes.interfaces.instances.MutatedBiome;
import org.corundummc.biomes.interfaces.types.MutatedBiomeType;
import org.corundummc.biomes.overworld.sandy.mesa.MesaBiome.MesaBiomeType;
import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;

import org.corundummc.biomes.overworld.sandy.mesa.MesaesqueBiome.MesaesqueBiomeType;

public class MesaPlateauMBiome extends MesaesqueBiome<MesaPlateauMBiome, BiomeGenMutated, MesaPlateauMBiome.MesaPlateauMBiomeType> implements MutatedBiome<MesaBiomeType> {
    protected MesaPlateauMBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class MesaPlateauMBiomeType extends MesaesqueBiomeType<MesaPlateauMBiomeType, BiomeGenMutated, MesaPlateauMBiome> implements MutatedBiomeType<MesaBiomeType> {
        public static final MesaPlateauMBiomeType TYPE = new MesaPlateauMBiomeType();

        private MesaPlateauMBiomeType() {
            super((BiomeGenMutated) BiomeGenBase.func_150568_d(167));
        }

        @Override
        public MesaPlateauMBiome fromLocation(Location location) {
            return new MesaPlateauMBiome(location);
        }

        @Override
        public MesaBiomeType getBase() {
            return MesaBiomeType.TYPE;
        }
    }

    @Override
    public MesaBiomeType getBaseType() {
        return getType().getBase();
    }

    @Override
    public MesaPlateauMBiomeType getType() {
        return MesaPlateauMBiomeType.TYPE;
    }
}