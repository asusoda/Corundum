package org.corundummc.biomes.overworld.arboreal.jungle;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenJungle;

import org.corundummc.biomes.overworld.arboreal.jungle.JungleyBiome.JungleyBiomeType;

public class JungleBiome extends JungleyBiome<JungleBiome, BiomeGenJungle, JungleBiome.JungleBiomeType> {
    protected JungleBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class JungleBiomeType extends JungleyBiomeType<JungleBiomeType, BiomeGenJungle, JungleBiome> {
        public static final JungleBiomeType TYPE = new JungleBiomeType();

        private JungleBiomeType() {
            super((BiomeGenJungle) BiomeGenBase.jungle);
        }

        @Override
        public JungleBiome fromLocation(Location location) {
            return new JungleBiome(location);
        }
    }

    @Override
    public JungleBiomeType getType() {
        return JungleBiomeType.TYPE;
    }
}