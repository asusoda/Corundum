package org.corundummc.biomes.overworld.arboreal.swampland;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenSwamp;

import org.corundummc.biomes.overworld.arboreal.swampland.SwampyBiome.SwampyBiomeType;

import org.corundummc.world.Location;

public class SwamplandBiome extends SwampyBiome<SwamplandBiome, BiomeGenSwamp, SwamplandBiome.SwamplandBiomeType> {
    protected SwamplandBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class SwamplandBiomeType extends SwampyBiomeType<SwamplandBiomeType, BiomeGenSwamp, SwamplandBiome> {
        public static final SwamplandBiomeType TYPE = new SwamplandBiomeType();

        private SwamplandBiomeType() {
            super((BiomeGenSwamp) BiomeGenBase.swampland);
        }

        @Override
        public SwamplandBiome fromLocation(Location location) {
            return new SwamplandBiome(location);
        }
    }

    @Override
    public SwamplandBiomeType getType() {
        return SwamplandBiomeType.TYPE;
    }
}