package org.corundummc.biomes.overworld.taiga.mega;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenTaiga;

import org.corundummc.biomes.overworld.taiga.mega.MegaTaigaesqueBiome.MegaTaigaesqueBiomeType;
import org.corundummc.world.Location;

public class MegaTaigaHillsBiome extends MegaTaigaesqueBiome<MegaTaigaHillsBiome, BiomeGenTaiga, MegaTaigaHillsBiome.MegaTaigaHillsBiomeType> {
    protected MegaTaigaHillsBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class MegaTaigaHillsBiomeType extends MegaTaigaesqueBiomeType<MegaTaigaHillsBiomeType, BiomeGenTaiga, MegaTaigaHillsBiome> {
        public static final MegaTaigaHillsBiomeType TYPE = new MegaTaigaHillsBiomeType();

        private MegaTaigaHillsBiomeType() {
            super((BiomeGenTaiga) BiomeGenBase.megaTaigaHills);
        }

        @Override
        public MegaTaigaHillsBiome fromLocation(Location location) {
            return new MegaTaigaHillsBiome(location);
        }
    }

    @Override
    public MegaTaigaHillsBiomeType getType() {
        return MegaTaigaHillsBiomeType.TYPE;
    }
}