package org.corundummc.biomes.overworld.sandy.desert;

import org.corundummc.biomes.overworld.sandy.SandyBiome;
import org.corundummc.biomes.overworld.sandy.desert.DesertBiome.DesertBiomeType;
import org.corundummc.biomes.overworld.sandy.desert.DesertHillsBiome.DesertHillsBiomeType;
import org.corundummc.biomes.overworld.sandy.desert.DesertMBiome.DesertMBiomeType;
import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenDesert;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft BiomeGenBase <tt>Object</tt> that this class's {@link BiomeType} represents.
 * @param <T>
 *            determines the type of {@link BiomeType} that represents the type of this class. */
public abstract class DesertesqueBiome<S extends DesertesqueBiome<S, MC, T>, MC extends BiomeGenBase, T extends DesertesqueBiome.DesertesqueBiomeType<T, MC, S>> extends
        SandyBiome<S, MC, T> {

    protected DesertesqueBiome(Location location) {
        super(location);
    }

    public static interface DesertesqueBiomeTypes {
        public static final DesertBiomeType DESERT = DesertBiomeType.TYPE;
        public static final DesertHillsBiomeType DESERT_HILLS = DesertHillsBiomeType.TYPE;
        public static final DesertMBiomeType DESERT_M = DesertMBiomeType.TYPE;
    }

    public abstract static class DesertesqueBiomeType<S extends DesertesqueBiomeType<S, MC, I>, MC extends BiomeGenBase, I extends DesertesqueBiome<I, MC, S>> extends
            SandyBiomeType<S, MC, I> {
        protected DesertesqueBiomeType(MC biomeMC) {
            super(biomeMC);

            addValueAs(DesertesqueBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static DesertesqueBiomeType<?, ?, ?> getByID(int id) {
            return getByID(DesertesqueBiomeType.class, id);
        }

        public static DesertesqueBiomeType<?, ?, ?>[] values() {
            return values(DesertesqueBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}