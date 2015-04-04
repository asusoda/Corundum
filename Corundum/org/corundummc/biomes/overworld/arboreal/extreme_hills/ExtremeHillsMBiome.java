package org.corundummc.biomes.overworld.arboreal.extreme_hills;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;

import org.corundummc.biomes.interfaces.instances.MutatedBiome;
import org.corundummc.biomes.interfaces.types.MutatedBiomeType;
import org.corundummc.biomes.overworld.arboreal.extreme_hills.ExtremeHillsBiome.ExtremeHillsBiomeType;
import org.corundummc.biomes.overworld.arboreal.extreme_hills.ExtremelyHillyBiome.ExtremelyHillyBiomeType;
import org.corundummc.world.Location;

public class ExtremeHillsMBiome extends ExtremelyHillyBiome<ExtremeHillsMBiome, BiomeGenMutated, ExtremeHillsMBiome.ExtremeHillsMBiomeType> implements
        MutatedBiome<ExtremeHillsBiomeType> {
    protected ExtremeHillsMBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class ExtremeHillsMBiomeType extends ExtremelyHillyBiomeType<ExtremeHillsMBiomeType, BiomeGenMutated, ExtremeHillsMBiome> implements
            MutatedBiomeType<ExtremeHillsBiomeType> {
        public static final ExtremeHillsMBiomeType TYPE = new ExtremeHillsMBiomeType();

        private ExtremeHillsMBiomeType() {
            super((BiomeGenMutated) BiomeGenBase.getBiome(131));
        }

        @Override
        public ExtremeHillsMBiome fromLocation(Location location) {
            return new ExtremeHillsMBiome(location);
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
    public ExtremeHillsMBiomeType getType() {
        return ExtremeHillsMBiomeType.TYPE;
    }
}