package org.corundummc.biomes.overworld.arboreal.swampland;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;

import org.corundummc.biomes.interfaces.instances.MutatedBiome;
import org.corundummc.biomes.interfaces.types.MutatedBiomeType;
import org.corundummc.biomes.overworld.arboreal.swampland.SwamplandBiome.SwamplandBiomeType;
import org.corundummc.biomes.overworld.arboreal.swampland.SwampyBiome.SwampyBiomeType;
import org.corundummc.world.Location;

public class SwamplandMBiome extends SwampyBiome<SwamplandMBiome, BiomeGenMutated, SwamplandMBiome.SwamplandMBiomeType> implements MutatedBiome<SwamplandBiomeType> {
    protected SwamplandMBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class SwamplandMBiomeType extends SwampyBiomeType<SwamplandMBiomeType, BiomeGenMutated, SwamplandMBiome> implements MutatedBiomeType<SwamplandBiomeType> {
        public static final SwamplandMBiomeType TYPE = new SwamplandMBiomeType();

        private SwamplandMBiomeType() {
            super((BiomeGenMutated) BiomeGenBase.getBiome(134));
        }

        @Override
        public SwamplandMBiome fromLocation(Location location) {
            return new SwamplandMBiome(location);
        }

        @Override
        public SwamplandBiomeType getBase() {
            return SwamplandBiomeType.TYPE;
        }
    }

    @Override
    public SwamplandBiomeType getBaseType() {
        return getType().getBase();
    }

    @Override
    public SwamplandMBiomeType getType() {
        return SwamplandMBiomeType.TYPE;
    }
}