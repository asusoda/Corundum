package org.corundummc.biomes.overworld.aquatic.oceanic;

import org.corundummc.biomes.overworld.aquatic.AquaticBiome;
import org.corundummc.biomes.overworld.aquatic.oceanic.DeepOceanBiome.DeepOceanBiomeType;
import org.corundummc.biomes.overworld.aquatic.oceanic.FrozenOceanBiome.FrozenOceanBiomeType;
import org.corundummc.biomes.overworld.aquatic.oceanic.OceanBiome.OceanBiomeType;
import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenOcean;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft BiomeGenBase <tt>Object</tt> that this class's {@link BiomeType} represents.
 * @param <T>
 *            determines the type of {@link BiomeType} that represents the type of this class. */
public abstract class OceanicBiome<S extends OceanicBiome<S, T>, T extends OceanicBiome.OceanicBiomeType<T, S>> extends AquaticBiome<S, BiomeGenOcean, T> {

    protected OceanicBiome(Location location) {
        super(location);
    }

    public static interface OceanicBiomeTypes {
        public static final OceanBiomeType OCEAN = OceanBiomeType.TYPE;
        public static final FrozenOceanBiomeType FROZEN_OCEAN = FrozenOceanBiomeType.TYPE;
        public static final DeepOceanBiomeType DEEP_OCEAN = DeepOceanBiomeType.TYPE;
    }

    public abstract static class OceanicBiomeType<S extends OceanicBiomeType<S, I>, I extends OceanicBiome<I, S>> extends AquaticBiomeType<S, BiomeGenOcean, I> {
        protected OceanicBiomeType(BiomeGenOcean biomeMC) {
            super(biomeMC);

            addValueAs(OceanicBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static OceanicBiomeType<?, ?> getByID(int id) {
            return getByID(OceanicBiomeType.class, id);
        }

        public static OceanicBiomeType<?, ?>[] values() {
            return values(OceanicBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}