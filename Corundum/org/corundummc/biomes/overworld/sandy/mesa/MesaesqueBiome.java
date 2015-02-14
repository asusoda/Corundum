package org.corundummc.biomes.overworld.sandy.mesa;

import net.minecraft.world.biome.BiomeGenBase;

import org.corundummc.biomes.overworld.sandy.SandyBiome;
import org.corundummc.world.Location;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft BiomeGenBase <tt>Object</tt> that this class's {@link BiomeType} represents.
 * @param <T>
 *            determines the type of {@link BiomeType} that represents the type of this class. */
public abstract class MesaesqueBiome<S extends MesaesqueBiome<S, MC, T>, MC extends BiomeGenBase, T extends MesaesqueBiome.MesaesqueBiomeType<T, MC, S>> extends
        SandyBiome<S, MC, T> {

    protected MesaesqueBiome(Location location) {
        super(location);
    }

    public static interface MesaesqueBiomeTypes {
        // TODO
    }

    public abstract static class MesaesqueBiomeType<S extends MesaesqueBiomeType<S, MC, I>, MC extends BiomeGenBase, I extends MesaesqueBiome<I, MC, S>> extends
            SandyBiomeType<S, MC, I> {
        protected MesaesqueBiomeType(MC biomeMC) {
            super(biomeMC);

            addValueAs(MesaesqueBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static MesaesqueBiomeType<?, ?, ?> getByID(int id) {
            return getByID(MesaesqueBiomeType.class, id);
        }

        public static MesaesqueBiomeType<?, ?, ?>[] values() {
            return values(MesaesqueBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}