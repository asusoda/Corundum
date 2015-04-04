package org.corundummc.biomes.overworld.arboreal.jungle;

import org.corundummc.biomes.overworld.arboreal.ArborealBiome;
import org.corundummc.biomes.overworld.arboreal.ArborealBiome.ArborealBiomeType;
import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft BiomeGenBase <tt>Object</tt> that this class's {@link BiomeType} represents.
 * @param <T>
 *            determines the type of {@link BiomeType} that represents the type of this class. */
public abstract class JungleyBiome<S extends JungleyBiome<S, MC, T>, MC extends BiomeGenBase, T extends JungleyBiome.JungleyBiomeType<T, MC, S>> extends
        ArborealBiome<S, MC, T> {

    protected JungleyBiome(Location location) {
        super(location);
    }

    public static interface JungleyBiomeTypes {
        // TODO
    }

    public abstract static class JungleyBiomeType<S extends JungleyBiomeType<S, MC, I>, MC extends BiomeGenBase, I extends JungleyBiome<I, MC, S>> extends
            ArborealBiomeType<S, MC, I> {
        protected JungleyBiomeType(MC biomeMC) {
            super(biomeMC);

            addValueAs(JungleyBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static JungleyBiomeType<?, ?, ?> getByID(int id) {
            return getByID(JungleyBiomeType.class, id);
        }

        public static JungleyBiomeType<?, ?, ?>[] values() {
            return values(JungleyBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}