package org.corundummc.biomes.overworld.arboreal.jungle;

import org.corundummc.biomes.interfaces.instances.MutatedBiome;
import org.corundummc.biomes.interfaces.types.MutatedBiomeType;
import org.corundummc.biomes.overworld.arboreal.jungle.JungleBiome.JungleBiomeType;
import org.corundummc.biomes.overworld.arboreal.jungle.JungleyBiome.JungleyBiomeType;
import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;

public class JungleMBiome extends JungleyBiome<JungleMBiome, BiomeGenMutated, JungleMBiome.JungleMBiomeType> implements MutatedBiome<JungleBiomeType> {
    protected JungleMBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class JungleMBiomeType extends JungleyBiomeType<JungleMBiomeType, BiomeGenMutated, JungleMBiome> implements MutatedBiomeType<JungleBiomeType> {
        public static final JungleMBiomeType TYPE = new JungleMBiomeType();

        private JungleMBiomeType() {
            super((BiomeGenMutated) BiomeGenBase.getBiome(149));
        }

        @Override
        public JungleMBiome fromLocation(Location location) {
            return new JungleMBiome(location);
        }

        @Override
        public JungleBiomeType getBase() {
            return JungleBiomeType.TYPE;
        }
    }

    @Override
    public JungleBiomeType getBaseType() {
        return getType().getBase();
    }

    @Override
    public JungleMBiomeType getType() {
        return JungleMBiomeType.TYPE;
    }
}