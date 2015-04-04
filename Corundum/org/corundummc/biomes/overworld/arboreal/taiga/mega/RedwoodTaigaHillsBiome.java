package org.corundummc.biomes.overworld.arboreal.taiga.mega;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;

import org.corundummc.biomes.interfaces.instances.MutatedBiome;
import org.corundummc.biomes.interfaces.types.MutatedBiomeType;
import org.corundummc.biomes.overworld.arboreal.taiga.TaigaesqueBiome;
import org.corundummc.biomes.overworld.arboreal.taiga.TaigaesqueBiome.TaigaesqueBiomeType;
import org.corundummc.biomes.overworld.arboreal.taiga.mega.MegaTaigaHillsBiome.MegaTaigaHillsBiomeType;
import org.corundummc.world.Location;

public class RedwoodTaigaHillsBiome extends TaigaesqueBiome<RedwoodTaigaHillsBiome, BiomeGenMutated, RedwoodTaigaHillsBiome.RedwoodMegaTaigaHillsBiomeType> implements
        MutatedBiome<MegaTaigaHillsBiomeType> {
    protected RedwoodTaigaHillsBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class RedwoodMegaTaigaHillsBiomeType extends TaigaesqueBiomeType<RedwoodMegaTaigaHillsBiomeType, BiomeGenMutated, RedwoodTaigaHillsBiome> implements
            MutatedBiomeType<MegaTaigaHillsBiomeType> {
        public static final RedwoodMegaTaigaHillsBiomeType TYPE = new RedwoodMegaTaigaHillsBiomeType();

        private RedwoodMegaTaigaHillsBiomeType() {
            super((BiomeGenMutated) BiomeGenBase.getBiome(161));
        }

        @Override
        public RedwoodTaigaHillsBiome fromLocation(Location location) {
            return new RedwoodTaigaHillsBiome(location);
        }

        @Override
        public MegaTaigaHillsBiomeType getBase() {
            return MegaTaigaHillsBiomeType.TYPE;
        }
    }

    @Override
    public MegaTaigaHillsBiomeType getBaseType() {
        return getType().getBase();
    }

    @Override
    public RedwoodMegaTaigaHillsBiomeType getType() {
        return RedwoodMegaTaigaHillsBiomeType.TYPE;
    }
}