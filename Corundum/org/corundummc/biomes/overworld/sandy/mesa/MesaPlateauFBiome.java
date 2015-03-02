package org.corundummc.biomes.overworld.sandy.mesa;

import org.corundummc.biomes.interfaces.instances.PlateauBiome;
import org.corundummc.biomes.interfaces.types.PlateauBiomeType;
import org.corundummc.biomes.overworld.sandy.mesa.MesaBiome.MesaBiomeType;
import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMesa;

import org.corundummc.biomes.overworld.sandy.mesa.MesaesqueBiome.MesaesqueBiomeType;

public class MesaPlateauFBiome extends MesaesqueBiome<MesaPlateauFBiome, BiomeGenMesa, MesaPlateauFBiome.MesaPlateauFBiomeType> implements PlateauBiome<MesaBiomeType> {
    protected MesaPlateauFBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class MesaPlateauFBiomeType extends MesaesqueBiomeType<MesaPlateauFBiomeType, BiomeGenMesa, MesaPlateauFBiome> implements PlateauBiomeType<MesaBiomeType> {
        public static final MesaPlateauFBiomeType TYPE = new MesaPlateauFBiomeType();

        private MesaPlateauFBiomeType() {
            super((BiomeGenMesa) BiomeGenBase.mesaPlateau_F);
        }

        @Override
        public MesaPlateauFBiome fromLocation(Location location) {
            return new MesaPlateauFBiome(location);
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
    public MesaPlateauFBiomeType getType() {
        return MesaPlateauFBiomeType.TYPE;
    }
}