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

public class Block {
    private BlockType type;
    private Location location;

    public enum BlockType {
        AIR, DIRT;  // TODO: finish

        // TODO TEMP: initializations should be done in a constructor
        private final boolean solid = true, can_be_broken_by_liquids = true, opaque = true;
        private final byte light_emission = 0;
    }
}
