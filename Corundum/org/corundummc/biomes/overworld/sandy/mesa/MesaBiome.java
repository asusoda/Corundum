package org.corundummc.biomes.overworld.sandy.mesa;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMesa;

import org.corundummc.biomes.overworld.sandy.mesa.MesaesqueBiome.MesaesqueBiomeType;

public class MesaBiome extends MesaesqueBiome<MesaBiome, BiomeGenMesa, MesaBiome.MesaBiomeType> {
    protected MesaBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class MesaBiomeType extends MesaesqueBiomeType<MesaBiomeType, BiomeGenMesa, MesaBiome> {
        public static final MesaBiomeType TYPE = new MesaBiomeType();

        private MesaBiomeType() {
            super((BiomeGenMesa) BiomeGenBase.field_150589_Z);
        }

        @Override
        public MesaBiome fromLocation(Location location) {
            return new MesaBiome(location);
        }
    }

    @Override
    public MesaBiomeType getType() {
        return MesaBiomeType.TYPE;
    }
}