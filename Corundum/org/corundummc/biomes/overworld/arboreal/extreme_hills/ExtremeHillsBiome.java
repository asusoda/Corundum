package org.corundummc.biomes.overworld.arboreal.extreme_hills;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenHills;

import org.corundummc.biomes.overworld.arboreal.extreme_hills.ExtremelyHillyBiome.ExtremelyHillyBiomeType;
import org.corundummc.world.Location;

public class ExtremeHillsBiome extends ExtremelyHillyBiome<ExtremeHillsBiome, BiomeGenHills, ExtremeHillsBiome.ExtremeHillsBiomeType> {
    protected ExtremeHillsBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class ExtremeHillsBiomeType extends ExtremelyHillyBiomeType<ExtremeHillsBiomeType, BiomeGenHills, ExtremeHillsBiome> {
        public static final ExtremeHillsBiomeType TYPE = new ExtremeHillsBiomeType();

        private ExtremeHillsBiomeType() {
            super((BiomeGenHills) BiomeGenBase.extremeHills);
        }

        @Override
        public ExtremeHillsBiome fromLocation(Location location) {
            return new ExtremeHillsBiome(location);
        }
    }

    @Override
    public ExtremeHillsBiomeType getType() {
        return ExtremeHillsBiomeType.TYPE;
    }
}