package org.corundummc.biomes.overworld.sandy.mesa;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMesa;

import org.corundummc.biomes.interfaces.instances.VariantBiome;
import org.corundummc.biomes.interfaces.types.VariantBiomeType;
import org.corundummc.biomes.overworld.sandy.mesa.MesaBiome.MesaBiomeType;
import org.corundummc.biomes.overworld.sandy.mesa.MesaesqueBiome.MesaesqueBiomeType;

public class MesaBryceBiome extends MesaesqueBiome<MesaBryceBiome, BiomeGenMesa, MesaBryceBiome.MesaBryceBiomeType> implements VariantBiome<MesaBiomeType> {
    protected MesaBryceBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class MesaBryceBiomeType extends MesaesqueBiomeType<MesaBryceBiomeType, BiomeGenMesa, MesaBryceBiome> implements VariantBiomeType<MesaBiomeType> {
        public static final MesaBryceBiomeType TYPE = new MesaBryceBiomeType();

        private MesaBryceBiomeType() {
            super((BiomeGenMesa) BiomeGenBase.func_150568_d(163));
        }

        @Override
        public MesaBryceBiome fromLocation(Location location) {
            return new MesaBryceBiome(location);
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
    public MesaBryceBiomeType getType() {
        return MesaBryceBiomeType.TYPE;
    }
}