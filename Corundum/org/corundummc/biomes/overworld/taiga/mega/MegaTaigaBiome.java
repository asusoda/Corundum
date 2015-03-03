package org.corundummc.biomes.overworld.taiga.mega;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenTaiga;

import org.corundummc.biomes.overworld.taiga.mega.MegaTaigaesqueBiome.MegaTaigaesqueBiomeType;

public class MegaTaigaBiome extends MegaTaigaesqueBiome<MegaTaigaBiome, BiomeGenTaiga, MegaTaigaBiome.MegaTaigaBiomeType> {
    protected MegaTaigaBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class MegaTaigaBiomeType extends MegaTaigaesqueBiomeType<MegaTaigaBiomeType, BiomeGenTaiga, MegaTaigaBiome> {
        public static final MegaTaigaBiomeType TYPE = new MegaTaigaBiomeType();

        private MegaTaigaBiomeType() {
            super((BiomeGenTaiga) BiomeGenBase.megaTaiga);
        }

        @Override
        public MegaTaigaBiome fromLocation(Location location) {
            return new MegaTaigaBiome(location);
        }
    }

    @Override
    public MegaTaigaBiomeType getType() {
        return MegaTaigaBiomeType.TYPE;
    }
}