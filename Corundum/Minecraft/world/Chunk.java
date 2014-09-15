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

package Corundum.Minecraft.world;

import Corundum.utils.myList.myList;

public class Chunk {
    private static myList<Chunk> loaded_chunks = new myList<Chunk>();

    private final int chunk_x, chunk_z;
    private final Block[][][] blocks = new Block[16][256][16];

    public Chunk(int chunk_x, int chunk_z) {
        this.chunk_x = chunk_x;
        this.chunk_z = chunk_z;
    }
}
