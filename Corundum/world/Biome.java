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

import java.awt.*;

import Corundum.entities.Mob;
import Corundum.utils.myList.myList;

public class Biome {
    // TODO TEMP: "final"s are commented to avoid compilation errors for now
    private/* final */BiomeType type;
    private/* final */myList<Chunk> chunks;

    public static enum BiomeType {
        JUNGLE(true, true, false,), OCEAN, PLAINS, DESERT,
        EXTREME_HILLS, FOREST, TAIGA, SWAMPLAND,
        RIVER, NETHER, END, FROZEN_OCEAN,
        FROZEN_RIVER(false, true, false, 0.0F, new Color(10526975), Block.BlockType.STILL_WATER, Block.BlockType.STONE),
        ICE_PLAINS(true, true, true, 0.0F, Block.BlockType.SNOW, Block.BlockType.STONE), ICE_MOUNTAINS, MUSHROOM_ISLAND,
        MUSHROOM_ISLAND_SHORE, BEACH, DESERT_HILLS,
        FOREST_HILLS, TAIGA_HILLS, EXTREME_HILLS_EDGE,
        JUNGLE_HILLS, JUNGLE_HILLS_EDGE, DEEP_OCEAN,
        STONE_BEACH, COLD_BEACH, BIRCH_FOREST,
        BIRCH_FOREST_HILLS, ROOFED_FOREST, COLD_TAIGA,
        COLD_TAIGA_HILLS, MEGA_TAIGA, MEGA_TAIGA_HILLS,
        EXTREM_HILLS_PLUS, SAVANNA, SAVANNA_PLATEAU,
        MESA, MESA_PLATEAU, MESA_PLATEAU_F;
        //TODO Make final.
        private Mob.MobType[] naturalSpawningMobs;
        //Precipitate = Rain OR snow. While all biomes technically can rain and/or snow, some, like deserts have such
        // obscenely high heights that rain occurs that you can't see it. hasSnow only is true if the snow level is so
        // low that it is all over the ground.
        private boolean hasTrees, canPrecipitate, hasSnow;
        private Block.BlockType surfaceBlock, fillerBlock;
        private float temperature;
        private Color colour;

        private BiomeType(boolean hasTrees, boolean canPrecipitate, boolean hasSnow, float temperature, Color colour, Block.BlockType surfaceBlock, Block.BlockType fillerBlock, Mob.MobType... naturalSpawningMobs) {
            this.hasTrees = hasTrees;
            this.canPrecipitate = canPrecipitate;
            this.hasSnow = hasSnow;
            this.temperature = temperature;
            this.colour = colour;
            this.naturalSpawningMobs = naturalSpawningMobs;
            this.surfaceBlock = surfaceBlock;
            this.fillerBlock = fillerBlock;
        }

        // TODO: 1. replace this
        /* private final Mob.MobType[] naturally_spawning_mobs; private final byte[] min_spawn_group_sizes, max_spawn_group_sizes; private final Color map_color, water_color;
         * private final float temperature, rainfall; private final boolean has_trees, has_rain, has_snow, can_spawn_squid; private final int min_height, max_height; private
         * final Block.BlockType surface_block, filler_block; */

        // TODO: BiomeType constructor
    }
}
