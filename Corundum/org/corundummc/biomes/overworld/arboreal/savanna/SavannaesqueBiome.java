package org.corundummc.biomes.overworld.arboreal.savanna;

import org.corundummc.biomes.overworld.arboreal.ArborealBiome;
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
public abstract class SavannaesqueBiome<S extends SavannaesqueBiome<S, MC, T>, MC extends BiomeGenBase, T extends SavannaesqueBiome.SavannaesqueBiomeType<T, MC, S>> extends
        ArborealBiome<S, MC, T> {

    protected SavannaesqueBiome(Location location) {
        super(location);
    }

    public static interface SavannaesqueBiomeTypes {
        // TODO
    }

    public abstract static class SavannaesqueBiomeType<S extends SavannaesqueBiomeType<S, MC, I>, MC extends BiomeGenBase, I extends SavannaesqueBiome<I, MC, S>> extends
            ArborealBiomeType<S, MC, I> {
        protected SavannaesqueBiomeType(MC biomeMC) {
            super(biomeMC);

            addValueAs(SavannaesqueBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static SavannaesqueBiomeType<?, ?, ?> getByID(int id) {
            return getByID(SavannaesqueBiomeType.class, id);
        }

        public static SavannaesqueBiomeType<?, ?, ?>[] values() {
            return values(SavannaesqueBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}