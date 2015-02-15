package org.corundummc.biomes.overworld.aquatic;

import org.corundummc.biomes.Biome;
import org.corundummc.biomes.overworld.OverworldBiome;
import org.corundummc.biomes.overworld.aquatic.oceanic.OceanicBiome.OceanicBiomeTypes;
import org.corundummc.biomes.overworld.aquatic.potamic.PotamicBiome.PotamicBiomeTypes;
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
public abstract class AquaticBiome<S extends AquaticBiome<S, MC, T>, MC extends BiomeGenBase, T extends AquaticBiome.AquaticBiomeType<T, MC, S>> extends
        OverworldBiome<S, MC, T> {

    protected AquaticBiome(Location location) {
        super(location);
    }

    public static interface AquaticBiomeTypes extends PotamicBiomeTypes, OceanicBiomeTypes {
        //
    }

    public abstract static class AquaticBiomeType<S extends AquaticBiomeType<S, MC, I>, MC extends BiomeGenBase, I extends AquaticBiome<I, MC, S>> extends
            OverworldBiomeType<S, MC, I> {
        protected AquaticBiomeType(MC biomeMC) {
            super(biomeMC);

            addValueAs(AquaticBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static AquaticBiomeType<?, ?, ?> getByID(int id) {
            return getByID(AquaticBiomeType.class, id);
        }

        public static AquaticBiomeType<?, ?, ?>[] values() {
            return values(AquaticBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}