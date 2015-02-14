package org.corundummc.biomes.overworld.sandy;

import org.corundummc.biomes.overworld.OverworldBiome;
import org.corundummc.biomes.overworld.OverworldBiome.OverworldBiomeType;
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
public abstract class SandyBiome<S extends SandyBiome<S, MC, T>, MC extends BiomeGenBase, T extends SandyBiome.SandyBiomeType<T, MC, S>> extends OverworldBiome<S, MC, T> {

    protected SandyBiome(Location location) {
        super(location);
    }

    public static interface SandyBiomeTypes {
        // TODO
    }

    public abstract static class SandyBiomeType<S extends SandyBiomeType<S, MC, I>, MC extends BiomeGenBase, I extends SandyBiome<I, MC, S>> extends
            OverworldBiomeType<S, MC, I> {
        protected SandyBiomeType(MC biomeMC) {
            super(biomeMC);

            addValueAs(SandyBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static SandyBiomeType<?, ?, ?> getByID(int id) {
            return getByID(SandyBiomeType.class, id);
        }

        public static SandyBiomeType<?, ?, ?>[] values() {
            return values(SandyBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}
