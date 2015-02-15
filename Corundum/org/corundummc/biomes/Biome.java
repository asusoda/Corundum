/** This code is property of the Corundum project managed under the Software Developers' Association of Arizona State University.
 *
 * Copying and use of this open-source code is permitted provided that the following requirements are met:
 *
 * - This code may not be used or distributed for private enterprise, including but not limited to personal or corporate profit. - Any products resulting from the copying,
 * use, or modification of this code may not claim endorsement by the Corundum project or any of its members or associates without written formal permission from the endorsing
 * party or parties. - This code may not be copied or used under any circumstances without the inclusion of this notice and mention of the contribution of the code by the
 * Corundum project. In source code form, this notice must be included as a comment as it is here; in binary form, proper documentation must be included with the final product
 * that includes this statement verbatim.
 *
 * @author REALDrummer */

package org.corundummc.biomes;

import org.corundummc.biomes.overworld.OverworldBiome.OverworldBiomeTypes;
import org.corundummc.biomes.worldwide.WorldwideBiome.WorldwideBiomeTypes;
import org.corundummc.entities.Entity;
import org.corundummc.utils.types.IDedType;
import org.corundummc.utils.types.Typed;
import org.corundummc.world.Block;
import org.corundummc.world.Location;
import org.corundummc.world.World;
import org.corundummc.world.Zone;

import net.minecraft.world.biome.BiomeGenBase;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft BiomeGenBase <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link BiomeType} that represents the type of this class. */
public abstract class Biome<S extends Biome<S, MC, T>, MC extends BiomeGenBase, T extends Biome.BiomeType<T, MC, S>> extends Typed<T> {
    /** Minecraft has no Biome object; the best way I thought to represent a Biome without using lots of C.P.U. and memory is to just store the location of one block in the
     * biome; this could allow us to calculate whether or not other points are in this same biome and other properties will likely come from the biome's type */
    protected final Location location;

    protected Zone circumscribing_zone = null; /* stores the circumscribing zone after the first call of getCircumscribingZone() so that later calls to that method don't take
                                                * up more memory and C.P.U. */

    protected Biome(Location location) {
        this.location = location;
    }

    public static interface BiomeTypes extends WorldwideBiomeTypes, OverworldBiomeTypes {
        //
    }

    public abstract static class BiomeType<S extends BiomeType<S, MC, I>, MC extends BiomeGenBase, I extends Biome<I, MC, S>> extends IDedType<S> {
        protected MC biomeMC;

        protected BiomeType(MC biomeMC) {
            super(biomeMC.biomeID);

            this.biomeMC = biomeMC;

            addValueAs(BiomeType.class);
        }

        // abstract utilities
        public abstract I fromLocation(Location location);

        // overridden utilities
        @Override
        public String getName() {
            return biomeMC.biomeName.replaceAll("(?<[a-z])(?=[A-Z])", " ");
        }

        // static utilities
        /** <b><u>This method may be ignored by plugin developers; it is not likely to be of use to you.</b></u><br>
         * This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link net.minecraft.entity.Entity Minecraft Entity}.
         * 
         * @param biomeMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @SuppressWarnings("unchecked")
        public static <MC extends BiomeGenBase> BiomeType<?, MC, ?> fromMC(MC biomeMC) {
            return (BiomeType<?, MC, ?>) BiomeType.getByID(biomeMC.biomeID);
        }

        // pseudo-enum utilities
        public static BiomeType<?, ?, ?> getByID(int id) {
            return getByID(BiomeType.class, id);
        }

        public static BiomeType<?, ?, ?>[] values() {
            return values(BiomeType.class);
        }
    }

    // private utilities
    private class EdgeFollower {
        /** This <b>int</b> represents the x-coordinate at which the edge following begins. This is <i>not</i> necessarily the same as the x-coordinate of the location for this
         * {@link Biome} because the x may be incremented to find the first edge to start following the edge. */
        private final int start_x;
        /** This <b>int</b> represents the z-coordinate at which the edge following begins; since we only move in the x-direction when finding the edge, this z-coordinate will
         * be the same as the location of this {@link Biome}. */
        private final int start_z;

        /* This keeps track of the x- and z-coordinates of the block that the edge following first travels to from the start location; this is important to avoid an edge case
         * described in the edge-following function. */
        private Integer second_x = null, second_z = null;

        /* This keeps track of the x- and z-coordinates that the edge-following function is currently visiting. */
        private int x, z;
        /* This keeps track of the x- and z-coordinates that the edge-following algorithm last visited. */
        private int last_x, last_z;

        /* These will be used in the for loops in the recursive edge-following function; I just initialized them here to save on stack memory. */
        private int next_x, next_z;

        /* This keeps track of the results, the lowest and highest x- and z-coordinates found while following the edge. */
        private int lowest_x, lowest_z, highest_x, highest_z;

        EdgeFollower() {
            // first, find the edge by moving in the positive x direction until we hit a new biome
            start_z = location.getBlockZ();
            int temp_start_x = location.getBlockX();
            while (isCorrectBiomeType(temp_start_x, start_z))
                temp_start_x++;
            start_x = temp_start_x;

            // start x and z and the start x and z
            x = start_x;
            z = start_z;
            // initialize last x and z to the coordinates of the block 1 past the start block in the positive x direction
            last_x = x + 1;
            last_z = z;
            // initialize lowest and highest x and z values to the start x and z values
            lowest_x = start_x;
            highest_x = start_x;
            lowest_z = start_z;
            highest_z = start_z;

            /* start following the edge recursively up/left by checking for blocks of the same biome starting from the last block explored in a counter-clockwise manner */
            followTheEdge();
        }

        private void followTheEdge() {
            /* if we've reached the start again, we MIGHT be done */
            if (x == start_x && z == start_z && second_x != null && second_z != null) {
                /* edge case!: the start x and z followed one edge, but if there was a one-block corridor at the start location, it will only follow the edge one direction and
                 * the other part of the biome connected by the one-block corridor will not be searched; therefore, we need to check for any parts of the biome that were not
                 * covered by the parsing so far by looking for blocks of the same biome type that were not to the up/left of the second and last parsed blocks */
                findNext();
                /* if the next block found is the second block searched from the start, there are no more edges to search; otherwise, we missed a portion of the biome that
                 * connects at our start location via a one-block corridor, so we need to follow the edge some more */
                if (next_x != second_x || next_z != second_z) {
                    last_x = x;
                    last_z = z;
                    x = next_x;
                    z = next_z;
                    followTheEdge();
                }

                /* this check above allows the recursion to check every edge starting at the start x and z until it gets back to the first one it followed; then, it's done */

                return;
            }

            // no base cases or edge cases? Great! find the next and follow it
            findNext();

            // if the second x- and z-coordinates are still null, assign them the next values
            if (second_x == null) {
                /* edge case!: if second x- and z-coordinates are not found and the next block given is not of the same biome type, it means no adjacent blocks are of the same
                 * biome and we have a one-block biome! */
                if (!isCorrectBiomeType(next_x, next_z))
                    /* nothing more needs to be done; the highest and lowest x- and z-coordinates will be the same as the start x- and z-coordinates */
                    return;
                else {
                    // if we don't have a one-block biome, set the second x- and z-coordinates
                    second_x = next_x;
                    second_z = next_z;
                }
            }

            // update the highest and lowest x- and z-coordinates as needed
            if (next_x < lowest_x)
                lowest_x = next_x;
            else if (next_x > highest_x)
                highest_x = next_x;
            if (next_z < lowest_z)
                lowest_z = next_z;
            else if (next_z > highest_z)
                highest_z = next_z;

            // follow the edge to the next x- and z-coordinates
            last_x = x;
            last_z = z;
            x = next_x;
            z = next_z;
            followTheEdge();
        }

        /** This method uses the current x- and z-coordinates to find the first adjacent block of the same biome by checking in a counter-clockwise manner starting from (but
         * not including) the last x- and z-coordinates; the results are stored in {@link #next_x} and {@link #next_z}. */
        private void findNext() {
            /* initialize the next x- and z-coordinates to the last x- and z-coordinates; they will be incremented clockwise about (x, z) before the first check so it will
             * check the last x- and z-coordinates, allowing it to return the last block if that's the only way back to the rest of the biome in a one-block corridor dead end */
            next_x = last_x;
            next_z = last_z;

            for (int i = 0; i < 8 /* there are 8 1x1 block columns adjacent to this column (including diagonals) */; i++) {
                // iterate to the next (x, z) coordinate pair
                /* the (x, z) coordinate pairs for the 1x1 block columns adjacent to the center block follow a cycle in the counter clockwise direction where the value shown
                 * is relative to the center block:
                 * x: 1 0-1-1-1 0 1 1
                 * z: 1 1 1 0-1-1-1 0;
                 * the difference between these values also cycles:
                 * x: 0-1-1 0 0 1 1 0
                 * z: 1 0 0-1-1 0 0 1
                 * this difference cycle is used in a series of if statements below to determine the next x- and z-coordinates to check */
                if (next_x - x != -1 && next_z - z == 1)
                    next_x--;
                else if (next_x - x == -1 && next_z - z != -1)
                    next_z--;
                else if (next_x - x != 1 && next_z - z == -1)
                    next_x++;
                else
                    next_z++;

                /* if this current 1x1 column is of the right biome, return it immediately through next_x and next_z */
                if (isCorrectBiomeType(next_x, next_z))
                    return;
            }
        }

        private boolean isCorrectBiomeType(int x, int z) {
            return location.getWorld().MC().getChunkFromBlockCoords(x, z).getBiomeArray()[x * 16 + z] == getTypeID();
        }

        // result getters
        public int getLowestX() {
            return lowest_x;
        }

        public int getHighestX() {
            return highest_x;
        }

        public int getLowestZ() {
            return lowest_z;
        }

        public int getHighestZ() {
            return highest_z;
        }
    }

    // static utilities

    // type utilities

    // instance utilities
    /* TODO: public boolean contains(Location location): make an algorithm that tries to find a path connecting this.location with the given location only through the same
     * type of biome; I've haeard that an algorithm called A-Star may be our best bet and we can optimize this with a few simple facts like 1) if it's outside the
     * circumscribing zone, it's not in the biome (but only do that if the circumscribing zone has already been calculated) and 2) if the block's biome isn't the same type of
     * biome, it's clearly not the same biome */

    /** This method returns the {@link Location} used to initialize this {@link Biome} <tt>Object</tt>. This {@link Location} is inside the {@link Biome} somewhere, but is not
     * chosen randomly. This can be useful if you just need any {@link Location} inside this {@link Biome} and you don't need it to be random.
     * 
     * @return the {@link Location} used to initialize this {@link Biome}. */
    public Location getLocation() {
        return location;
    }

    // TODO: link "desert" and "river" biomes and "build height" (World.getBuildHeight()) below
    /** This method returns a zone that "circumscribes" this contiguous {@link Biome}. In other words, it returns a zone that just barely fits around the whole entire
     * {@link Biome}, so every {@link Block} in the {@link Biome} is in the zone, though not every {@link Block} in the {@link Zone} is part of the {@link Biome}. <br>
     * <br>
     * One {@link Biome} is a contiguous area of the same {@link BiomeType}; that means if a river biome goes all the way across a desert and cuts it into two pieces, those
     * are
     * considered two <i>separate</i> desert biomes. <br>
     * <br>
     * Because {@link Biome}s reach from the bottom of the {@link World} to the top, all circumscribing zones' y-coordinates go from the minimum value of an integer (see
     * {@link Integer#MIN_VALUE}) to the maximum value of an integer (see {@link Integer#MAX_VALUE}) to cover all possible y-coordinates, including those below the bottom of
     * the {@link World} and above the {@link World}'s build height. <br>
     * <br>
     * <b><i>NOTE:</b></i> Finding the edges of a {@link Biome} requires a very resource intensive algorithm. This means that if you have one {@link Biome}, feel free to
     * call this method as much as you want; after the first call, it calculates and stores the result so that subsequent calls don't need to recalculate the {@link Zone}.
     * However, be warned: avoid making a bunch of {@link Biome}s and get all their circumscribing zones because it will take lots and lots and lots of calculations!
     * 
     * @return a {@link Zone} that perfectly circumscribes this contiguous {@link Biome}. */
    public Zone getCircumscribingZone() {
        // first, if the circumscribing zone has already been calculated, don't calculate it again
        if (circumscribing_zone != null)
            return circumscribing_zone;

        /* use the EdgeFollower Object to find and follow the edge of the biome, keeping track of the highest and lowest x- and z-coordinates it encounters */
        EdgeFollower results = new EdgeFollower();

        // finally, put the results into a Zone
        return circumscribing_zone =
                new Zone(new Location(results.getLowestX(), Integer.MIN_VALUE, results.getLowestZ(), location.getWorld()), new Location(results.getHighestX(),
                        Integer.MAX_VALUE, results.getHighestZ(), location.getWorld()));
    }

    /* TODO: getRandomLocation() to get a random location in this biome; I recommend picking a random spot in the circumscribing zone and seeing if that point is inside this
     * biome; NOTE: I recommend first implementing Zone.getRandomPoint() */

    public World getWorld() {
        return getLocation().getWorld();
    }
}
