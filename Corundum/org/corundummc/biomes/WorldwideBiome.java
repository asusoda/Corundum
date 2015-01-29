package org.corundummc.biomes;

import org.corundummc.biomes.overworld.OverworldBiome.OverworldBiomeType;
import org.corundummc.world.Location;
import org.corundummc.world.World;
import org.corundummc.world.Zone;

import net.minecraft.world.biome.BiomeGenBase;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft BiomeGenBase <tt>Object</tt> that this class's {@link BiomeType} represents.
 * @param <T>
 *            determines the type of {@link BiomeType} that represents the type of this class. */
public abstract class WorldwideBiome<S extends WorldwideBiome<S, MC, T>, MC extends BiomeGenBase, T extends WorldwideBiome.WorldwideBiomeType<T, MC, S>> extends
        Biome<S, MC, T> {

    protected WorldwideBiome(Location location) {
        super(location);
    }

    public static interface WorldwideBiomeTypes {
        // TODO
    }

    public abstract static class WorldwideBiomeType<S extends WorldwideBiomeType<S, MC, I>, MC extends BiomeGenBase, I extends WorldwideBiome<I, MC, S>> extends
            BiomeType<S, MC, I> {
        protected WorldwideBiomeType(MC biomeMC) {
            super(biomeMC);

            addValueAs(OverworldBiomeType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static WorldwideBiomeType<?, ?, ?> getByID(int id) {
            return getByID(WorldwideBiomeType.class, id);
        }

        public static WorldwideBiomeType<?, ?, ?>[] values() {
            return values(WorldwideBiomeType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
    /* TODO TEMP CMT @Override */
    public boolean contains(Location location) {
        return this.location.getWorld().equals(location.getWorld());
    }

    @Override
    public Zone getCircumscribingZone() {
        return new Zone(new Location(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, getWorld()), new Location(Integer.MAX_VALUE, Integer.MAX_VALUE,
                Integer.MAX_VALUE, getWorld()));
    }
}
