package org.corundummc.biomes.overworld.arboreal;

import org.corundummc.biomes.overworld.OverworldBiome;
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
public abstract class ArborealBiome<S extends ArborealBiome<S, MC, T>, MC extends BiomeGenBase, T extends ArborealBiome.ArborealBiomeType<T, MC, S>> extends
        OverworldBiome<S, MC, T> {

    protected ArborealBiome(Location location) {
        super(location);
    }

    public static interface ArborealBiomeTypes {
        // TODO
    }

    public abstract static class ArborealBiomeType<S extends ArborealBiomeType<S, MC, I>, MC extends BiomeGenBase, I extends ArborealBiome<I, MC, S>> extends
            OverworldBiomeType<S, MC, I> {
        protected ArborealBiomeType(MC biomeMC) {
            super(biomeMC);

            addValueAs(ArborealBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static ArborealBiomeType<?, ?, ?> getByID(int id) {
            return getByID(ArborealBiomeType.class, id);
        }

        public static ArborealBiomeType<?, ?, ?>[] values() {
            return values(ArborealBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}