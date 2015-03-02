package org.corundummc.biomes.overworld.sandy.mesa;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMesa;

import org.corundummc.biomes.interfaces.instances.PlateauBiome;
import org.corundummc.biomes.interfaces.types.PlateauBiomeType;
import org.corundummc.biomes.overworld.sandy.mesa.MesaBiome.MesaBiomeType;
import org.corundummc.biomes.overworld.sandy.mesa.MesaesqueBiome.MesaesqueBiomeType;

public class MesaPlateauBiome extends MesaesqueBiome<MesaPlateauBiome, BiomeGenMesa, MesaPlateauBiome.MesaPlateauBiomeType> implements PlateauBiome<MesaBiomeType> {
    protected MesaPlateauBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class MesaPlateauBiomeType extends MesaesqueBiomeType<MesaPlateauBiomeType, BiomeGenMesa, MesaPlateauBiome> implements PlateauBiomeType<MesaBiomeType> {
        public static final MesaPlateauBiomeType TYPE = new MesaPlateauBiomeType();

        private MesaPlateauBiomeType() {
            super((BiomeGenMesa) BiomeGenBase.mesaPlateau);
        }

        @Override
        public MesaPlateauBiome fromLocation(Location location) {
            return new MesaPlateauBiome(location);
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
    public MesaPlateauBiomeType getType() {
        return MesaPlateauBiomeType.TYPE;
    }
}