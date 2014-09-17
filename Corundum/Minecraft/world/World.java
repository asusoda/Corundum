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

import java.io.File;

import net.minecraft.world.WorldServer;
import Corundum.UnfinishedException;

public class World {
    // TODO: public static final World MAIN_WORLD;

    private String name;
    private File folder;
    private WorldType type;
    private Location spawn_location;

    public enum WorldType {
        OVERWORLD, NETHER, END, CUSTOM;
    }

    static World fromMCWorld(WorldServer worldMC) {
        // TODO
        throw new UnfinishedException("World.fromMCWorld()");
    }
}
