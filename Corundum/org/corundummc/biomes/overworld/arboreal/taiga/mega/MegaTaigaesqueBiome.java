package org.corundummc.biomes.overworld.arboreal.taiga.mega;

import org.corundummc.biomes.overworld.arboreal.taiga.TaigaesqueBiome;
import org.corundummc.biomes.overworld.arboreal.taiga.mega.MegaSpruceTaigaBiome.MegaSpruceTaigaBiomeType;
import org.corundummc.biomes.overworld.arboreal.taiga.mega.MegaTaigaBiome.MegaTaigaBiomeType;
import org.corundummc.biomes.overworld.arboreal.taiga.mega.MegaTaigaHillsBiome.MegaTaigaHillsBiomeType;
import org.corundummc.biomes.overworld.arboreal.taiga.mega.RedwoodTaigaHillsBiome.RedwoodMegaTaigaHillsBiomeType;
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
public abstract class MegaTaigaesqueBiome<S extends MegaTaigaesqueBiome<S, MC, T>, MC extends BiomeGenBase, T extends MegaTaigaesqueBiome.MegaTaigaesqueBiomeType<T, MC, S>>
        extends TaigaesqueBiome<S, MC, T> {

    protected MegaTaigaesqueBiome(Location location) {
        super(location);
    }

    public static interface MegaTaigaesqueBiomeTypes {
        public static final MegaTaigaBiomeType MEGA_TAIGA = MegaTaigaBiomeType.TYPE;
        public static final MegaSpruceTaigaBiomeType MEGA_SPRUCE_TAIGA = MegaSpruceTaigaBiomeType.TYPE;
        public static final MegaTaigaHillsBiomeType MEGA_TAIGA_HILLS = MegaTaigaHillsBiomeType.TYPE;
        public static final RedwoodMegaTaigaHillsBiomeType REDWOOD_MEGA_TAIGA_HILLS = RedwoodMegaTaigaHillsBiomeType.TYPE;
    }

    public abstract static class MegaTaigaesqueBiomeType<S extends MegaTaigaesqueBiomeType<S, MC, I>, MC extends BiomeGenBase, I extends MegaTaigaesqueBiome<I, MC, S>>
            extends TaigaesqueBiomeType<S, MC, I> {
        protected MegaTaigaesqueBiomeType(MC biomeMC) {
            super(biomeMC);

            addValueAs(MegaTaigaesqueBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static MegaTaigaesqueBiomeType<?, ?, ?> getByID(int id) {
            return getByID(MegaTaigaesqueBiomeType.class, id);
        }

        public static MegaTaigaesqueBiomeType<?, ?, ?>[] values() {
            return values(MegaTaigaesqueBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}