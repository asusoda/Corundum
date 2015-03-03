package org.corundummc.biomes.overworld.taiga.mega;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;

import org.corundummc.biomes.interfaces.instances.MutatedBiome;
import org.corundummc.biomes.interfaces.types.MutatedBiomeType;
import org.corundummc.biomes.overworld.taiga.mega.MegaTaigaBiome.MegaTaigaBiomeType;
import org.corundummc.biomes.overworld.taiga.mega.MegaTaigaesqueBiome.MegaTaigaesqueBiomeType;
import org.corundummc.world.Location;

public class MegaSpruceTaigaBiome extends MegaTaigaesqueBiome<MegaSpruceTaigaBiome, BiomeGenMutated, MegaSpruceTaigaBiome.MegaSpruceTaigaBiomeType> implements
        MutatedBiome<MegaTaigaBiomeType> {
    protected MegaSpruceTaigaBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class MegaSpruceTaigaBiomeType extends MegaTaigaesqueBiomeType<MegaSpruceTaigaBiomeType, BiomeGenMutated, MegaSpruceTaigaBiome> implements
            MutatedBiomeType<MegaTaigaBiomeType> {
        public static final MegaSpruceTaigaBiomeType TYPE = new MegaSpruceTaigaBiomeType();

        private MegaSpruceTaigaBiomeType() {
            super((BiomeGenMutated) BiomeGenBase.getBiome(160));
        }

        @Override
        public MegaSpruceTaigaBiome fromLocation(Location location) {
            return new MegaSpruceTaigaBiome(location);
        }

        @Override
        public MegaTaigaBiomeType getBase() {
            return MegaTaigaBiomeType.TYPE;
        }
    }

    @Override
    public MegaTaigaBiomeType getBaseType() {
        return getType().getBase();
    }

    @Override
    public MegaSpruceTaigaBiomeType getType() {
        return MegaSpruceTaigaBiomeType.TYPE;
    }
}