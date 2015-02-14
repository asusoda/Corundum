package org.corundummc.biomes.overworld.sandy.desert;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenDesert;

import org.corundummc.biomes.overworld.sandy.desert.DesertesqueBiome.DesertesqueBiomeType;

public class DesertBiome extends DesertesqueBiome<DesertBiome, BiomeGenDesert, DesertBiome.DesertBiomeType> {
    protected DesertBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class DesertBiomeType extends DesertesqueBiomeType<DesertBiomeType, BiomeGenDesert, DesertBiome> {
        public static final DesertBiomeType TYPE = new DesertBiomeType();

        private DesertBiomeType() {
            super((BiomeGenDesert) BiomeGenBase.desert);
        }

        @Override
        public DesertBiome fromLocation(Location location) {
            return new DesertBiome(location);
        }
    }

    @Override
    public DesertBiomeType getType() {
        return DesertBiomeType.TYPE;
    }
}
