package org.corundummc.biomes.overworld.arboreal.jungle;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenJungle;

import org.corundummc.biomes.interfaces.instances.VariantBiome;
import org.corundummc.biomes.interfaces.types.VariantBiomeType;
import org.corundummc.biomes.overworld.arboreal.jungle.JungleBiome.JungleBiomeType;
import org.corundummc.biomes.overworld.arboreal.jungle.JungleyBiome.JungleyBiomeType;

public class JungleEdgeBiome extends JungleyBiome<JungleEdgeBiome, BiomeGenJungle, JungleEdgeBiome.JungleEdgeBiomeType> implements VariantBiome<JungleBiomeType> {
    protected JungleEdgeBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class JungleEdgeBiomeType extends JungleyBiomeType<JungleEdgeBiomeType, BiomeGenJungle, JungleEdgeBiome> implements VariantBiomeType<JungleBiomeType> {
        public static final JungleEdgeBiomeType TYPE = new JungleEdgeBiomeType();

        private JungleEdgeBiomeType() {
            super((BiomeGenJungle) BiomeGenBase.jungleEdge);
        }

        @Override
        public JungleEdgeBiome fromLocation(Location location) {
            return new JungleEdgeBiome(location);
        }

        @Override
        public JungleBiomeType getBase() {
            return JungleBiomeType.TYPE;
        }
    }

    @Override
    public JungleBiomeType getBaseType() {
        return JungleBiomeType.TYPE;
    }

    @Override
    public JungleEdgeBiomeType getType() {
        return JungleEdgeBiomeType.TYPE;
    }
}