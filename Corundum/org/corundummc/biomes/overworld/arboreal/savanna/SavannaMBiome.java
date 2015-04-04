package org.corundummc.biomes.overworld.arboreal.savanna;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;

import org.corundummc.biomes.interfaces.instances.MutatedBiome;
import org.corundummc.biomes.interfaces.types.MutatedBiomeType;
import org.corundummc.biomes.overworld.arboreal.savanna.SavannaesqueBiome.SavannaesqueBiomeType;
import org.corundummc.biomes.overworld.arboreal.savanna.SavannaBiome.SavannaBiomeType;
import org.corundummc.world.Location;

public class SavannaMBiome extends SavannaesqueBiome<SavannaMBiome, BiomeGenMutated, SavannaMBiome.SavannaMBiomeType> implements MutatedBiome<SavannaBiomeType> {
    protected SavannaMBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class SavannaMBiomeType extends SavannaesqueBiomeType<SavannaMBiomeType, BiomeGenMutated, SavannaMBiome> implements MutatedBiomeType<SavannaBiomeType> {
        public static final SavannaMBiomeType TYPE = new SavannaMBiomeType();

        private SavannaMBiomeType() {
            super((BiomeGenMutated) BiomeGenBase.getBiome(163));
        }

        @Override
        public SavannaMBiome fromLocation(Location location) {
            return new SavannaMBiome(location);
        }

        @Override
        public SavannaBiomeType getBase() {
            return SavannaBiomeType.TYPE;
        }
    }

    @Override
    public SavannaBiomeType getBaseType() {
        return getType().getBase();
    }

    @Override
    public SavannaMBiomeType getType() {
        return SavannaMBiomeType.TYPE;
    }
}