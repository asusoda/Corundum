package org.corundummc.biomes.overworld.arboreal.extreme_hills.plus;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;

import org.corundummc.biomes.overworld.arboreal.extreme_hills.ExtremelyHillyBiome;

import org.corundummc.biomes.overworld.arboreal.extreme_hills.ExtremelyHillyBiome.ExtremelyHillyBiomeType;

public class ExtremeHillsPlusBiome extends ExtremelyHillyBiome<ExtremeHillsPlusBiome, BiomeGenBase, ExtremeHillsPlusBiome.ExtremeHillsPlusBiomeType> {
    protected ExtremeHillsPlusBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class ExtremeHillsPlusBiomeType extends ExtremelyHillyBiomeType<ExtremeHillsPlusBiomeType, BiomeGenBase, ExtremeHillsPlusBiome> {
        public static final ExtremeHillsPlusBiomeType TYPE = new ExtremeHillsPlusBiomeType();

        private ExtremeHillsPlusBiomeType() {
            super((BiomeGenBase) BiomeGenBase.extremeHillsPlus);
        }

        @Override
        public ExtremeHillsPlusBiome fromLocation(Location location) {
            return new ExtremeHillsPlusBiome(location);
        }
    }

    @Override
    public ExtremeHillsPlusBiomeType getType() {
        return ExtremeHillsPlusBiomeType.TYPE;
    }
}