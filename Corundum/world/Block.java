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

package Corundum.world;

import java.awt.Color;

import Corundum.utils.interfaces.HoldableType;
import Corundum.utils.interfaces.IDedType;
import Corundum.utils.interfaces.IDedTypeWithData;
import Corundum.utils.interfaces.Matchable;
import Corundum.world.Biome.BiomeType;

public class Block {
    private BlockType type;
    private byte data;
    private Location location;

    /** This method retrieves the {@link Biome} that this block is located in.
     * 
     * @return the {@link Biome} that this block is in. */
    public Biome getBiome() {
        int posXInChunk = this.location.getBlockX() % 16;
        int posZInChunk = this.location.getBlockZ() % 16;
        return new Biome(getChunk().getBiomeMap()[posXInChunk][posZInChunk]);
    }

    public Chunk getChunk() {
        net.minecraft.world.chunk.Chunk mcChunk = this.getLocation().getWorld().getMCWorld().getChunkFromBlockCoords(this.location.getBlockX(), this.location.getBlockZ());
        return new Chunk(mcChunk);
    }

    public byte getData() {
        return data;
    }

    public Location getLocation() {
        return location;
    }

    public BlockType getType() {
        return type;
    }

    public void setData(byte data) {
        this.data = data;
        this.location.getWorld().setBlockData(this.location, data);
        // TODO: change the BlockType if necessary
        // TODO: send a packet to clients concerning the block change
    }

    public void setType(BlockType type) {
        this.type = type;
        // TODO: change the data accordingly, but try to do in such a way that the orientation of the block doesn't change
        // TODO: send a packet to clients concerning the block change
    }
}
