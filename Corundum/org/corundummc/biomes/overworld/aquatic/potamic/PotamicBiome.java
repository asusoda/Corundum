package org.corundummc.biomes.overworld.aquatic.potamic;

import org.corundummc.biomes.overworld.aquatic.AquaticBiome;
import org.corundummc.biomes.overworld.aquatic.potamic.FrozenRiverBiome.FrozenRiverBiomeType;
import org.corundummc.biomes.overworld.aquatic.potamic.RiverBiome.RiverBiomeType;
import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenRiver;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft BiomeGenBase <tt>Object</tt> that this class's {@link BiomeType} represents.
 * @param <T>
 *            determines the type of {@link BiomeType} that represents the type of this class. */
public abstract class PotamicBiome<S extends PotamicBiome<S, T>, T extends PotamicBiome.PotamicBiomeType<T, S>> extends AquaticBiome<S, BiomeGenRiver, T> {

    protected PotamicBiome(Location location) {
        super(location);
    }

    public static interface PotamicBiomeTypes {
        public static final RiverBiomeType RIVER = RiverBiomeType.TYPE;
        public static final FrozenRiverBiomeType FROZEN_RIVER = FrozenRiverBiomeType.TYPE;
    }

    public abstract static class PotamicBiomeType<S extends PotamicBiomeType<S, I>, I extends PotamicBiome<I, S>> extends AquaticBiomeType<S, BiomeGenRiver, I> {
        protected PotamicBiomeType(BiomeGenRiver biomeMC) {
            super(biomeMC);

            addValueAs(PotamicBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static PotamicBiomeType<?, ?> getByID(int id) {
            return getByID(PotamicBiomeType.class, id);
        }

        public static PotamicBiomeType<?, ?>[] values() {
            return values(PotamicBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}