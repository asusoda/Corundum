package org.corundummc.biomes.overworld.arboreal.jungle;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;

import org.corundummc.biomes.interfaces.instances.MutatedBiome;
import org.corundummc.biomes.interfaces.types.MutatedBiomeType;
import org.corundummc.biomes.overworld.arboreal.jungle.JungleEdgeBiome.JungleEdgeBiomeType;
import org.corundummc.biomes.overworld.arboreal.jungle.JungleyBiome.JungleyBiomeType;
import org.corundummc.world.Location;

public class JungleEdgeMBiome extends JungleyBiome<JungleEdgeMBiome, BiomeGenMutated, JungleEdgeMBiome.JungleEdgeMBiomeType> implements MutatedBiome<JungleEdgeBiomeType> {
    protected JungleEdgeMBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class JungleEdgeMBiomeType extends JungleyBiomeType<JungleEdgeMBiomeType, BiomeGenMutated, JungleEdgeMBiome> implements MutatedBiomeType<JungleEdgeBiomeType> {
        public static final JungleEdgeMBiomeType TYPE = new JungleEdgeMBiomeType();

        private JungleEdgeMBiomeType() {
            super((BiomeGenMutated) BiomeGenBase.getBiome(151));
        }

        @Override
        public JungleEdgeMBiome fromLocation(Location location) {
            return new JungleEdgeMBiome(location);
        }

        @Override
        public JungleEdgeBiomeType getBase() {
            return JungleEdgeBiomeType.TYPE;
        }
    }

    @Override
    public JungleEdgeBiomeType getBaseType() {
        return getType().getBase();
    }

    @Override
    public JungleEdgeMBiomeType getType() {
        return JungleEdgeMBiomeType.TYPE;
    }
}