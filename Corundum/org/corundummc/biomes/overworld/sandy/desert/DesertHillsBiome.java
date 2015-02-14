package org.corundummc.biomes.overworld.sandy.desert;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenDesert;

import org.corundummc.biomes.interfaces.instances.HillBiome;
import org.corundummc.biomes.interfaces.types.HillBiomeType;
import org.corundummc.biomes.overworld.sandy.desert.DesertBiome.DesertBiomeType;
import org.corundummc.biomes.overworld.sandy.desert.DesertesqueBiome.DesertesqueBiomeType;

public class DesertHillsBiome extends DesertesqueBiome<DesertHillsBiome, BiomeGenDesert, DesertHillsBiome.DesertHillsBiomeType> implements HillBiome<DesertBiomeType> {
    protected DesertHillsBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class DesertHillsBiomeType extends DesertesqueBiomeType<DesertHillsBiomeType, BiomeGenDesert, DesertHillsBiome> implements HillBiomeType<DesertBiomeType> {
        public static final DesertHillsBiomeType TYPE = new DesertHillsBiomeType();

        private DesertHillsBiomeType() {
            super((BiomeGenDesert) BiomeGenBase.desertHills);
        }

        @Override
        public DesertHillsBiome fromLocation(Location location) {
            return new DesertHillsBiome(location);
        }

        @Override
        public DesertBiomeType getBaseBiomeType() {
            return DesertBiomeType.TYPE;
        }
    }

    @Override
    public DesertBiomeType getBaseBiomeType() {
        return getType().getBaseBiomeType();
    }

    @Override
    public DesertHillsBiomeType getType() {
        return DesertHillsBiomeType.TYPE;
    }
}