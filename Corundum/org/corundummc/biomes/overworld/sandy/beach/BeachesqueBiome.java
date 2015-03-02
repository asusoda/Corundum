package org.corundummc.biomes.overworld.sandy.beach;

import org.corundummc.biomes.overworld.sandy.SandyBiome;
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
public abstract class BeachesqueBiome<S extends BeachesqueBiome<S, MC, T>, MC extends BiomeGenBase, T extends BeachesqueBiome.BeachesqueBiomeType<T, MC, S>> extends
        SandyBiome<S, MC, T> {

    protected BeachesqueBiome(Location location) {
        super(location);
    }

    public static interface BeachesqueBiomeTypes {
        // TODO
    }

    public abstract static class BeachesqueBiomeType<S extends BeachesqueBiomeType<S, MC, I>, MC extends BiomeGenBase, I extends BeachesqueBiome<I, MC, S>> extends
            SandyBiomeType<S, MC, I> {
        protected BeachesqueBiomeType(MC biomeMC) {
            super(biomeMC);

            addValueAs(BeachesqueBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static BeachesqueBiomeType<?, ?, ?> getByID(int id) {
            return getByID(BeachesqueBiomeType.class, id);
        }

        public static BeachesqueBiomeType<?, ?, ?>[] values() {
            return values(BeachesqueBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}