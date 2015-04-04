package org.corundummc.biomes.overworld.arboreal.extreme_hills;

import org.corundummc.biomes.interfaces.instances.VariantBiome;
import org.corundummc.biomes.overworld.arboreal.extreme_hills.ExtremeHillsBiome.ExtremeHillsBiomeType;
import org.corundummc.world.Location;

import org.corundummc.biomes.interfaces.types.VariantBiomeType;

import org.corundummc.biomes.overworld.arboreal.extreme_hills.ExtremelyHillyBiome.ExtremelyHillyBiomeType;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenHills;

public class ExtremeHillsEdgeBiome extends ExtremelyHillyBiome<ExtremeHillsEdgeBiome, BiomeGenHills, ExtremeHillsEdgeBiome.ExtremeHillsEdgeBiomeType> implements
        VariantBiome<ExtremeHillsBiomeType> {
    protected ExtremeHillsEdgeBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class ExtremeHillsEdgeBiomeType extends ExtremelyHillyBiomeType<ExtremeHillsEdgeBiomeType, BiomeGenHills, ExtremeHillsEdgeBiome> implements
            VariantBiomeType<ExtremeHillsBiomeType> {
        public static final ExtremeHillsEdgeBiomeType TYPE = new ExtremeHillsEdgeBiomeType();

        private ExtremeHillsEdgeBiomeType() {
            super((BiomeGenHills) BiomeGenBase.extremeHillsEdge);
        }

        @Override
        public ExtremeHillsEdgeBiome fromLocation(Location location) {
            return new ExtremeHillsEdgeBiome(location);
        }

        @Override
        public ExtremeHillsBiomeType getBase() {
            return ExtremeHillsBiomeType.TYPE;
        }
    }

    @Override
    public ExtremeHillsBiomeType getBaseType() {
        return getType().getBase();
    }

    @Override
    public ExtremeHillsEdgeBiomeType getType() {
        return ExtremeHillsEdgeBiomeType.TYPE;
    }
}