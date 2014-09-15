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

import java.awt.*;

import Corundum.Minecraft.entities.Mob;
import Corundum.utils.myList.myList;

public class Biome {
    // TODO TEMP: "final"s are commented to avoid compilation errors for now
    private/* final */BiomeType biome;
    private/* final */myList<Chunk> chunks;

    public enum BiomeType {
        JUNGLE(false), TUNDRA(true); // TODO: 3. finish this list

        // TODO: 1. replace this
        private final Mob.MobType[] naturally_spawning_mobs;
        private final byte[] min_spawn_group_sizes, max_spawn_group_sizes;
        private final Color map_color, water_color;
        private final float temperature, rainfall;
        private final boolean has_trees, has_rain, has_snow, can_spawn_squid;
        private final int min_height, max_height;
        private final Block.BlockType surface_block, filler_block;

        private BiomeType(boolean snowy /* TODO */) {
            // TODO
        }
    }
}
