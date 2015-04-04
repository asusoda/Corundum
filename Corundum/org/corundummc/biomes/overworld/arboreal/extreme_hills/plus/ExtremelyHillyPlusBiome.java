package org.corundummc.biomes.overworld.arboreal.extreme_hills.plus;

import org.corundummc.biomes.overworld.arboreal.extreme_hills.ExtremelyHillyBiome;
import org.corundummc.biomes.overworld.arboreal.extreme_hills.ExtremelyHillyBiome.ExtremelyHillyBiomeType;
import org.corundummc.biomes.overworld.arboreal.extreme_hills.plus.ExtremeHillsPlusBiome.ExtremeHillsPlusBiomeType;
import org.corundummc.biomes.overworld.arboreal.extreme_hills.plus.ExtremeHillsPlusMBiome.ExtremeHillsPlusMBiomeType;
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
public abstract class ExtremelyHillyPlusBiome<S extends ExtremelyHillyPlusBiome<S, MC, T>, MC extends BiomeGenBase, T extends ExtremelyHillyPlusBiome.ExtremelyHillyPlusyBiomeType<T, MC, S>>
        extends ExtremelyHillyBiome<S, MC, T> {

    protected ExtremelyHillyPlusBiome(Location location) {
        super(location);
    }

    public static interface ExtremelyHillyPlusyBiomeTypes {
        public static final ExtremeHillsPlusBiomeType EXTREME_HILLS_PLUS = ExtremeHillsPlusBiomeType.TYPE;
        public static final ExtremeHillsPlusMBiomeType EXTREME_HILLS_PLUS_M = ExtremeHillsPlusMBiomeType.TYPE;
    }

    public abstract static class ExtremelyHillyPlusyBiomeType<S extends ExtremelyHillyPlusyBiomeType<S, MC, I>, MC extends BiomeGenBase, I extends ExtremelyHillyPlusBiome<I, MC, S>>
            extends ExtremelyHillyBiomeType<S, MC, I> {
        protected ExtremelyHillyPlusyBiomeType(MC biomeMC) {
            super(biomeMC);

            addValueAs(ExtremelyHillyPlusyBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static ExtremelyHillyPlusyBiomeType<?, ?, ?> getByID(int id) {
            return getByID(ExtremelyHillyPlusyBiomeType.class, id);
        }

        public static ExtremelyHillyPlusyBiomeType<?, ?, ?>[] values() {
            return values(ExtremelyHillyPlusyBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}