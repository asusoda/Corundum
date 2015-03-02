package org.corundummc.biomes.overworld.sandy.desert;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;

import org.corundummc.biomes.overworld.sandy.desert.DesertBiome.DesertBiomeType;
import org.corundummc.biomes.overworld.sandy.desert.DesertesqueBiome.DesertesqueBiomeType;
import org.corundummc.world.Location;
import org.corundummc.biomes.interfaces.instances.MutatedBiome;
import org.corundummc.biomes.interfaces.types.MutatedBiomeType;

public class DesertMBiome extends DesertesqueBiome<DesertMBiome, BiomeGenMutated, DesertMBiome.DesertMBiomeType> implements MutatedBiome<DesertBiomeType> {
    protected DesertMBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class DesertMBiomeType extends DesertesqueBiomeType<DesertMBiomeType, BiomeGenMutated, DesertMBiome> implements MutatedBiomeType<DesertBiomeType> {
        public static final DesertMBiomeType TYPE = new DesertMBiomeType();

        private DesertMBiomeType() {
            super((BiomeGenMutated) BiomeGenBase.getBiome(130));
        }

        @Override
        public DesertMBiome fromLocation(Location location) {
            return new DesertMBiome(location);
        }

        @Override
        public DesertBiomeType getBase() {
            return DesertBiomeType.TYPE;
        }
    }

    @Override
    public DesertBiomeType getBaseType() {
        return getType().getBase();
    }

    @Override
    public DesertMBiomeType getType() {
        return DesertMBiomeType.TYPE;
    }
}