package org.corundummc.biomes.overworld.arboreal.extreme_hills.plus;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;

import org.corundummc.world.Location;
import org.corundummc.biomes.interfaces.instances.MutatedBiome;
import org.corundummc.biomes.interfaces.types.MutatedBiomeType;
import org.corundummc.biomes.overworld.arboreal.extreme_hills.plus.ExtremeHillsPlusBiome.ExtremeHillsPlusBiomeType;
import org.corundummc.biomes.overworld.arboreal.extreme_hills.plus.ExtremelyHillyPlusBiome.ExtremelyHillyPlusyBiomeType;

public class ExtremeHillsPlusMBiome extends ExtremelyHillyPlusBiome<ExtremeHillsPlusMBiome, BiomeGenMutated, ExtremeHillsPlusMBiome.ExtremeHillsPlusMBiomeType> implements
        MutatedBiome<ExtremeHillsPlusBiomeType> {
    protected ExtremeHillsPlusMBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class ExtremeHillsPlusMBiomeType extends ExtremelyHillyPlusyBiomeType<ExtremeHillsPlusMBiomeType, BiomeGenMutated, ExtremeHillsPlusMBiome> implements
            MutatedBiomeType<ExtremeHillsPlusBiomeType> {
        public static final ExtremeHillsPlusMBiomeType TYPE = new ExtremeHillsPlusMBiomeType();

        private ExtremeHillsPlusMBiomeType() {
            super((BiomeGenMutated) BiomeGenBase.getBiome(162));
        }

        @Override
        public ExtremeHillsPlusMBiome fromLocation(Location location) {
            return new ExtremeHillsPlusMBiome(location);
        }

        @Override
        public ExtremeHillsPlusBiomeType getBase() {
            return ExtremeHillsPlusBiomeType.TYPE;
        }
    }

    @Override
    public ExtremeHillsPlusBiomeType getBaseType() {
        return getType().getBase();
    }

    @Override
    public ExtremeHillsPlusMBiomeType getType() {
        return ExtremeHillsPlusMBiomeType.TYPE;
    }
}