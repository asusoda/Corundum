package org.corundummc.biomes.overworld.sandy.mesa;

import org.corundummc.biomes.interfaces.instances.PlateauBiome;
import org.corundummc.biomes.interfaces.types.PlateauBiomeType;
import org.corundummc.biomes.overworld.sandy.mesa.MesaBiome.MesaBiomeType;
import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMesa;

import org.corundummc.biomes.overworld.sandy.mesa.MesaesqueBiome.MesaesqueBiomeType;

public class MesaPlateauFBiome extends MesaesqueBiome<MesaPlateauFBiome, BiomeGenMesa, MesaPlateauFBiome.MesaPlateauFType> implements PlateauBiome<MesaBiomeType> {
    protected MesaPlateauFBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class MesaPlateauFType extends MesaesqueBiomeType<MesaPlateauFType, BiomeGenMesa, MesaPlateauFBiome> implements PlateauBiomeType<MesaBiomeType> {
        public static final MesaPlateauFType TYPE = new MesaPlateauFType();

        private MesaPlateauFType() {
            super((BiomeGenMesa) BiomeGenBase.field_150607_aa);
        }

        @Override
        public MesaPlateauFBiome fromLocation(Location location) {
            return new MesaPlateauFBiome(location);
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
    public MesaPlateauFType getType() {
        return MesaPlateauFType.TYPE;
    }
}