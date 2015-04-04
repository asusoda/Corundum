package org.corundummc.biomes.overworld.arboreal.savanna;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenSavanna;

import org.corundummc.biomes.overworld.arboreal.savanna.SavannaesqueBiome.SavannaesqueBiomeType;

public class SavannaBiome extends SavannaesqueBiome<SavannaBiome, BiomeGenSavanna, SavannaBiome.SavannaBiomeType> {
    protected SavannaBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class SavannaBiomeType extends SavannaesqueBiomeType<SavannaBiomeType, BiomeGenSavanna, SavannaBiome> {
        public static final SavannaBiomeType TYPE = new SavannaBiomeType();

        private SavannaBiomeType() {
            super((BiomeGenSavanna) BiomeGenBase.savanna);
        }

        @Override
        public SavannaBiome fromLocation(Location location) {
            return new SavannaBiome(location);
        }
    }

    @Override
    public SavannaBiomeType getType() {
        return SavannaBiomeType.TYPE;
    }
}