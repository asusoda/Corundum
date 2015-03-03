package org.corundummc.biomes.overworld.taiga;

import org.corundummc.biomes.overworld.OverworldBiome;
import org.corundummc.biomes.overworld.taiga.ColdTaigaBiome.ColdTaigaBiomeType;
import org.corundummc.biomes.overworld.taiga.ColdTaigaHillsBiome.ColdTaigaHillsBiomeType;
import org.corundummc.biomes.overworld.taiga.ColdTaigaMBiome.ColdTaigaMBiomeType;
import org.corundummc.biomes.overworld.taiga.TaigaBiome.TaigaBiomeType;
import org.corundummc.biomes.overworld.taiga.TaigaHillsBiome.TaigaHillsBiomeType;
import org.corundummc.biomes.overworld.taiga.TaigaMBiome.TaigaMBiomeType;
import org.corundummc.biomes.overworld.taiga.mega.MegaTaigaesqueBiome.MegaTaigaesqueBiomeTypes;
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
public abstract class TaigaesqueBiome<S extends TaigaesqueBiome<S, MC, T>, MC extends BiomeGenBase, T extends TaigaesqueBiome.TaigaesqueBiomeType<T, MC, S>> extends
        OverworldBiome<S, MC, T> {

    protected TaigaesqueBiome(Location location) {
        super(location);
    }

    public static interface TaigaesqueBiomeTypes extends MegaTaigaesqueBiomeTypes {
        public static final TaigaBiomeType TAIGA = TaigaBiomeType.TYPE;
        public static final TaigaHillsBiomeType TAIGA_HILLS = TaigaHillsBiomeType.TYPE;
        public static final TaigaMBiomeType TAIGA_M = TaigaMBiomeType.TYPE;
        public static final ColdTaigaBiomeType COLD_TAIGA = ColdTaigaBiomeType.TYPE;
        public static final ColdTaigaHillsBiomeType COLD_TAIGA_HILLS = ColdTaigaHillsBiomeType.TYPE;
        public static final ColdTaigaMBiomeType COLD_TAIGA_M = ColdTaigaMBiomeType.TYPE;
    }

    public abstract static class TaigaesqueBiomeType<S extends TaigaesqueBiomeType<S, MC, I>, MC extends BiomeGenBase, I extends TaigaesqueBiome<I, MC, S>> extends
            OverworldBiomeType<S, MC, I> {
        protected TaigaesqueBiomeType(MC biomeMC) {
            super(biomeMC);

            addValueAs(TaigaesqueBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static TaigaesqueBiomeType<?, ?, ?> getByID(int id) {
            return getByID(TaigaesqueBiomeType.class, id);
        }

        public static TaigaesqueBiomeType<?, ?, ?>[] values() {
            return values(TaigaesqueBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}