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
import java.lang.reflect.Field;

import Corundum.entities.Mob;
import Corundum.utils.myList.myList;
import net.minecraft.world.biome.BiomeGenBase;

public class Biome {
    // TODO TEMP: "final"s are commented to avoid compilation errors for now
    private/* final */BiomeType type;
    //Perhaps remove as chunks are stored in the world and not the actual biome?
    private/* final */myList<Chunk> chunks;

    public static enum BiomeType {
        //TODO finish biomes and give them all constructors.
        JUNGLE(true, true, false,), OCEAN, PLAINS, DESERT,
        EXTREME_HILLS(3, true, true, false, 0.02F, new Color(6316128), Block.BlockType.GRASS, Block.BlockType.STONE),
        FOREST,
        TAIGA(5, true, true, false, 0.05F, new Color(747097), Block.BlockType.GRASS, Block.BlockType.STONE),
        SWAMPLAND,
        RIVER, NETHER, END, FROZEN_OCEAN,
        FROZEN_RIVER(11, false, true, false, 0.0F, new Color(10526975), Block.BlockType.STILL_WATER, Block.BlockType.STONE),
        ICE_PLAINS(12, true, true, true, 0.0F, new Color(16777215), Block.BlockType.SNOW, Block.BlockType.STONE),
        ICE_PLAINS_SPIKES,
        ICE_MOUNTAINS, MUSHROOM_ISLAND,
        MUSHROOM_ISLAND_SHORE, BEACH, DESERT_HILLS,
        FOREST_HILLS, TAIGA_HILLS, EXTREME_HILLS_EDGE,
        JUNGLE_HILLS, JUNGLE_HILLS_EDGE, DEEP_OCEAN,
        STONE_BEACH,
        COLD_BEACH(26, false, true, true, 0.05F, new Color(16445632), Block.BlockType.SNOW, Block.BlockType.STONE),
        BIRCH_FOREST,
        BIRCH_FOREST_HILLS, ROOFED_FOREST,
        COLD_TAIGA(30, true, true, true, -0.5F, new Color(3233098), Block.BlockType.SNOW, Block.BlockType.STONE),
        COLD_TAIGA_HILLS, MEGA_TAIGA, MEGA_TAIGA_HILLS,
        EXTREM_HILLS_PLUS, SAVANNA, SAVANNA_PLATEAU,
        MESA, MESA_PLATEAU, MESA_PLATEAU_F;
        //TODO Make final.
        //naturalSpawningMobs are mobs that spawn naturally and aren't structure only. This usually means cave spiders don't count.
        private Mob.MobType[] naturalSpawningMobs;
        //Precipitate = Rain OR snow. While all biomes technically can rain and/or snow, some, like deserts have such
        // obscenely high heights that rain occurs that you can't see it. hasSnow only is true if the snow level is so
        // low that it is all over the ground.
        private boolean hasTrees, canPrecipitate, hasSnow;
        private Block.BlockType surfaceBlock, fillerBlock;
        private float temperature;
        private Color colour;
        private BiomeGenBase mcBiome;

        private static final Mob.MobType[] defaultNaturalSpawningMobs = new Mob.MobType[] {Mob.MobType.ZOMBIE, Mob.MobType.BAT, Mob.MobType.CREEPER, Mob.MobType.SKELETON, Mob.MobType.ENDERMAN, Mob.MobType.CHICKEN, Mob.MobType.COW, Mob.MobType.PIG, Mob.MobType.SHEEP, Mob.MobType.SQUID, Mob.MobType.WITCH};

        private BiomeType(int biomeId, boolean hasTrees, boolean canPrecipitate, boolean hasSnow, float temperature, Color colour, Block.BlockType surfaceBlock, Block.BlockType fillerBlock, Mob.MobType... naturalSpawningMobs) {
            this.hasTrees = hasTrees;
            this.canPrecipitate = canPrecipitate;
            this.hasSnow = hasSnow;
            this.temperature = temperature;
            this.colour = colour;
            this.naturalSpawningMobs = naturalSpawningMobs;
            this.surfaceBlock = surfaceBlock;
            this.fillerBlock = fillerBlock;
            this.mcBiome = getBiomeById(biomeId);
        }

        private BiomeType(int id, boolean hasTrees, boolean canPrecipitate, boolean hasSnow, float temperature, Color colour, Block.BlockType surfaceBlock, Block.BlockType fillerBlock) {
            this(id, hasTrees, canPrecipitate, hasSnow, temperature, colour, surfaceBlock, fillerBlock, Mob.MobType.ZOMBIE, Mob.MobType.BAT, Mob.MobType.CREEPER, Mob.MobType.SKELETON, Mob.MobType.ENDERMAN, Mob.MobType.CHICKEN, Mob.MobType.COW, Mob.MobType.PIG, Mob.MobType.SHEEP, Mob.MobType.SQUID, Mob.MobType.WITCH);
        }

        public BiomeGenBase getMcBiome() {
            return this.mcBiome;
        }

        public boolean getHasTrees() {
            return this.hasTrees;
        }

        public boolean getHasSnow() {
            return this.hasSnow;
        }

        public boolean getCanPrecipitate() {
            return this.canPrecipitate;
        }

        private BiomeGenBase getBiomeById(int id) {
            return BiomeGenBase.getBiomeGenArray()[id];
        }

        // TODO: 1. replace this
        /* private final Mob.MobType[] naturally_spawning_mobs; private final byte[] min_spawn_group_sizes, max_spawn_group_sizes; private final Color map_color, water_color;
         * private final float temperature, rainfall; private final boolean has_trees, has_rain, has_snow, can_spawn_squid; private final int min_height, max_height; private
         * final Block.BlockType surface_block, filler_block; */
    }
}
