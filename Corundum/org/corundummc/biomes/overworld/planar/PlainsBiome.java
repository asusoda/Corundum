package org.corundummc.biomes.overworld.planar;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenPlains;

import org.corundummc.biomes.overworld.planar.PlanarBiome.PlanarBiomeType;

public class PlainsBiome extends PlanarBiome<PlainsBiome, BiomeGenPlains, PlainsBiome.PlainsBiomeType> {
    protected PlainsBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class PlainsBiomeType extends PlanarBiomeType<PlainsBiomeType, BiomeGenPlains, PlainsBiome> {
        public static final PlainsBiomeType TYPE = new PlainsBiomeType();

        private PlainsBiomeType() {
            super((BiomeGenPlains) BiomeGenBase.plains);
        }

        @Override
        public PlainsBiome fromLocation(Location location) {
            return new PlainsBiome(location);
        }
    }

    @Override
    public PlainsBiomeType getType() {
        return PlainsBiomeType.TYPE;
    }
}