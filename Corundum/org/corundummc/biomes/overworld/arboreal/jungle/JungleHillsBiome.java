package org.corundummc.biomes.overworld.arboreal.jungle;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenJungle;

import org.corundummc.biomes.overworld.arboreal.jungle.JungleyBiome.JungleyBiomeType;

public class JungleHillsBiome extends JungleyBiome<JungleHillsBiome, BiomeGenJungle, JungleHillsBiome.JungleHillsBiomeType> {
    protected JungleHillsBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class JungleHillsBiomeType extends JungleyBiomeType<JungleHillsBiomeType, BiomeGenJungle, JungleHillsBiome> {
        public static final JungleHillsBiomeType TYPE = new JungleHillsBiomeType();

        private JungleHillsBiomeType() {
            super((BiomeGenJungle) BiomeGenBase.jungleHills);
        }

        @Override
        public JungleHillsBiome fromLocation(Location location) {
            return new JungleHillsBiome(location);
        }
    }

    @Override
    public JungleHillsBiomeType getType() {
        return JungleHillsBiomeType.TYPE;
    }
}