package org.corundummc.biomes.overworld.mushroom;

import net.minecraft.world.biome.BiomeGenMushroomIsland;

import org.corundummc.biomes.overworld.OverworldBiome;
import org.corundummc.world.Location;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft BiomeGenBase <tt>Object</tt> that this class's {@link BiomeType} represents.
 * @param <T>
 *            determines the type of {@link BiomeType} that represents the type of this class. */
public abstract class MushroomLadenBiome<S extends MushroomLadenBiome<S, MC, T>, MC extends BiomeGenMushroomIsland, T extends MushroomLadenBiome.MushroomLadenBiomeType<T, MC, S>> extends
        OverworldBiome<S, MC, T> {

    protected MushroomLadenBiome(Location location) {
        super(location);
    }

    public static interface MushroomLadenBiomeTypes {
        // TODO
    }

    public abstract static class MushroomLadenBiomeType<S extends MushroomLadenBiomeType<S, MC, I>, MC extends BiomeGenMushroomIsland, I extends MushroomLadenBiome<I, MC, S>> extends
            OverworldBiomeType<S, MC, I> {
        protected MushroomLadenBiomeType(MC biomeMC) {
            super(biomeMC);

            addValueAs(MushroomLadenBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static MushroomLadenBiomeType<?, ?, ?> getByID(int id) {
            return getByID(MushroomLadenBiomeType.class, id);
        }

        public static MushroomLadenBiomeType<?, ?, ?>[] values() {
            return values(MushroomLadenBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}