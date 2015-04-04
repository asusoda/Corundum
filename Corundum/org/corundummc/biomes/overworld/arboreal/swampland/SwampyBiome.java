package org.corundummc.biomes.overworld.arboreal.swampland;

import org.corundummc.biomes.overworld.arboreal.ArborealBiome;
import org.corundummc.biomes.overworld.arboreal.swampland.SwamplandBiome.SwamplandBiomeType;
import org.corundummc.biomes.overworld.arboreal.swampland.SwamplandMBiome.SwamplandMBiomeType;
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
public abstract class SwampyBiome<S extends SwampyBiome<S, MC, T>, MC extends BiomeGenBase, T extends SwampyBiome.SwampyBiomeType<T, MC, S>> extends ArborealBiome<S, MC, T> {

    protected SwampyBiome(Location location) {
        super(location);
    }

    public static interface SwampyBiomeTypes {
        public static final SwamplandBiomeType SWAMPLAND = SwamplandBiomeType.TYPE;
        public static final SwamplandMBiomeType SWAMPLAND_M = SwamplandMBiomeType.TYPE;
    }

    public abstract static class SwampyBiomeType<S extends SwampyBiomeType<S, MC, I>, MC extends BiomeGenBase, I extends SwampyBiome<I, MC, S>> extends
            ArborealBiomeType<S, MC, I> {
        protected SwampyBiomeType(MC biomeMC) {
            super(biomeMC);

            addValueAs(SwampyBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static SwampyBiomeType<?, ?, ?> getByID(int id) {
            return getByID(SwampyBiomeType.class, id);
        }

        public static SwampyBiomeType<?, ?, ?>[] values() {
            return values(SwampyBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}