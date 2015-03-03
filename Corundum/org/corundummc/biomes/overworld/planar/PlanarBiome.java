package org.corundummc.biomes.overworld.planar;

import org.corundummc.biomes.overworld.OverworldBiome;
import org.corundummc.biomes.overworld.planar.IcePlainsBiome.IcePlainsBiomeType;
import org.corundummc.biomes.overworld.planar.IceSpikesBiome.IceSpikesBiomeType;
import org.corundummc.biomes.overworld.planar.PlainsBiome.PlainsBiomeType;
import org.corundummc.biomes.overworld.planar.SunflowerPlainsBiome.SunflowerPlainsBiomeType;
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
public abstract class PlanarBiome<S extends PlanarBiome<S, MC, T>, MC extends BiomeGenBase, T extends PlanarBiome.PlanarBiomeType<T, MC, S>> extends OverworldBiome<S, MC, T> {

    protected PlanarBiome(Location location) {
        super(location);
    }

    public static interface PlanarTypes {
        public static final PlainsBiomeType PLAINS = PlainsBiomeType.TYPE;
        public static final SunflowerPlainsBiomeType SUNFLOWER_PLAINS = SunflowerPlainsBiomeType.TYPE;
        public static final IcePlainsBiomeType ICE_PLAINS = IcePlainsBiomeType.TYPE;
        public static final IceSpikesBiomeType ICE_SPIKES = IceSpikesBiomeType.TYPE;
    }

    public abstract static class PlanarBiomeType<S extends PlanarBiomeType<S, MC, I>, MC extends BiomeGenBase, I extends PlanarBiome<I, MC, S>> extends
            OverworldBiomeType<S, MC, I> {
        protected PlanarBiomeType(MC biomeMC) {
            super(biomeMC);

            addValueAs(PlanarBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static PlanarBiomeType<?, ?, ?> getByID(int id) {
            return getByID(PlanarBiomeType.class, id);
        }

        public static PlanarBiomeType<?, ?, ?>[] values() {
            return values(PlanarBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}