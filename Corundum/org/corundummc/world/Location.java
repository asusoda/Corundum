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

package org.corundummc.world;

import org.corundummc.biomes.Biome;

public class Location {
    private double x, y, z;
    private World world;

    public Location(double x, double y, double z, World world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    public Biome<?, ?, ?> getBiome() {
        return Biome.fromLocation(this);
    }

    public int getBlockX() {
        return (int) x;
    }

    public int getBlockY() {
        return (int) y;
    }

    public int getBlockZ() {
        return (int) z;
    }

    public Chunk getChunk() {
        return new Chunk(world.MC().getChunkFromBlockCoords(getBlockX(), getBlockZ()));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public World getWorld() {
        return world;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public String toString(boolean use_block_coordinates) {
        // location format: ([x], [y], [z]) (facing ([pitch], [yaw])) in "[world]"
        String string = "(";
        if (use_block_coordinates)
            string += getBlockX() + ", " + getBlockY() + ", " + getBlockZ() + ") ";
        else
            string += getX() + ", " + getY() + ", " + getZ() + ") ";
        return string + "in \"" + world + "\"";
    }

    public String toString() {
        return toString(true);
    }
}
