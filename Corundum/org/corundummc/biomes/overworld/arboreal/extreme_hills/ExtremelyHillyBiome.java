package org.corundummc.biomes.overworld.arboreal.extreme_hills;

import org.corundummc.biomes.overworld.arboreal.ArborealBiome;
import org.corundummc.biomes.overworld.arboreal.extreme_hills.ExtremeHillsBiome.ExtremeHillsBiomeType;
import org.corundummc.biomes.overworld.arboreal.extreme_hills.ExtremeHillsEdgeBiome.ExtremeHillsEdgeBiomeType;
import org.corundummc.biomes.overworld.arboreal.extreme_hills.plus.ExtremelyHillyPlusBiome.ExtremelyHillyPlusyBiomeTypes;
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
public abstract class ExtremelyHillyBiome<S extends ExtremelyHillyBiome<S, MC, T>, MC extends BiomeGenBase, T extends ExtremelyHillyBiome.ExtremelyHillyBiomeType<T, MC, S>>
        extends ArborealBiome<S, MC, T> {

    protected ExtremelyHillyBiome(Location location) {
        super(location);
    }

    public static interface ExtremelyHillyBiomeTypes extends ExtremelyHillyPlusyBiomeTypes {
        public static final ExtremeHillsBiomeType EXTREME_HILLS = ExtremeHillsBiomeType.TYPE;
        public static final ExtremeHillsBiomeType EXTREME_HILLS_BIOME = ExtremeHillsBiomeType.TYPE;
        public static final ExtremeHillsEdgeBiomeType EXTREME_HILLS_EDGE = ExtremeHillsEdgeBiomeType.TYPE;
    }

    public abstract static class ExtremelyHillyBiomeType<S extends ExtremelyHillyBiomeType<S, MC, I>, MC extends BiomeGenBase, I extends ExtremelyHillyBiome<I, MC, S>>
            extends ArborealBiomeType<S, MC, I> {
        protected ExtremelyHillyBiomeType(MC biomeMC) {
            super(biomeMC);

            addValueAs(ExtremelyHillyBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static ExtremelyHillyBiomeType<?, ?, ?> getByID(int id) {
            return getByID(ExtremelyHillyBiomeType.class, id);
        }

        public static ExtremelyHillyBiomeType<?, ?, ?>[] values() {
            return values(ExtremelyHillyBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}