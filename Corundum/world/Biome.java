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
import Corundum.entities.Mob.MobType;
import Corundum.utils.myList.myList;
import Corundum.world.Block.BlockType;
import net.minecraft.world.biome.BiomeGenBase;

public class Biome {
    // TODO TEMP: "final"s are commented to avoid compilation errors for now
    private/* final */BiomeType type;
    // Perhaps remove as chunks are stored in the world and not the actual biome?
    private/* final */myList<Chunk> chunks;

    public static enum BiomeType {
        // TODO finish biomes and give them all constructors.
        JUNGLE(0, true),
        OCEAN,
        PLAINS,
        DESERT,
        EXTREME_HILLS(3, true),
        FOREST,
        TAIGA(5, true),
        SWAMPLAND,
        RIVER,
        NETHER,
        END,
        FROZEN_OCEAN,
        FROZEN_RIVER(11, false),
        ICE_PLAINS(12, true),
        ICE_PLAINS_SPIKES,
        ICE_MOUNTAINS,
        MUSHROOM_ISLAND,
        MUSHROOM_ISLAND_SHORE,
        BEACH,
        DESERT_HILLS,
        FOREST_HILLS,
        TAIGA_HILLS,
        EXTREME_HILLS_EDGE,
        JUNGLE_HILLS,
        JUNGLE_HILLS_EDGE,
        DEEP_OCEAN,
        STONE_BEACH,
        COLD_BEACH(26, false),
        BIRCH_FOREST,
        BIRCH_FOREST_HILLS,
        ROOFED_FOREST,
        COLD_TAIGA(30, true),
        COLD_TAIGA_HILLS,
        MEGA_TAIGA,
        MEGA_TAIGA_HILLS,
        EXTREM_HILLS_PLUS,
        SAVANNA,
        SAVANNA_PLATEAU,
        MESA,
        MESA_PLATEAU,
        MESA_PLATEAU_F;
        // TODO Make final.
        // naturalSpawningMobs are mobs that spawn naturally and aren't structure only. This usually means cave spiders don't count.
        private final MobType[] naturalSpawningMobs;
        // Precipitate = Rain OR snow. While all biomes technically can rain and/or snow, some, like deserts have such
        // obscenely high heights that rain occurs that you can't see it. hasSnow only is true if the snow level is so
        // low that it is all over the ground.
        private final boolean hasTrees;
        private BiomeGenBase biomeMC;

        private static final MobType[] defaultNaturalSpawningMobs = new MobType[] { MobType.ZOMBIE, MobType.BAT, MobType.CREEPER, MobType.SKELETON, MobType.ENDERMAN,
                MobType.CHICKEN, MobType.COW, MobType.PIG, MobType.SHEEP, MobType.SQUID, MobType.WITCH };

        // TODO TEMP: I just put this here to eliminate errors temporarily
        private BiomeType() {
            hasTrees = true;
            naturalSpawningMobs = new MobType[0];
        }

        private BiomeType(int biomeId, boolean hasTrees, MobType... naturalSpawningMobs) {
            this.hasTrees = hasTrees;
            this.naturalSpawningMobs = naturalSpawningMobs;

            this.biomeMC = getBiomeByID(biomeId);
        }

        private BiomeType(int id, boolean hasTrees) {
            this(id, hasTrees, MobType.ZOMBIE, MobType.BAT, MobType.CREEPER, MobType.SKELETON, MobType.ENDERMAN, MobType.CHICKEN, MobType.COW, MobType.PIG, MobType.SHEEP,
                    MobType.SQUID, MobType.WITCH);
        }

        public Color getColor() {
            return new Color(biomeMC.color);
        }

        public BlockType getFillerBlockType() {
            return BlockType.getByID(net.minecraft.block.Block.getIdFromBlock(biomeMC.fillerBlock));
        }

        public BiomeGenBase getMCBiome() {
            return this.biomeMC;
        }

        public BlockType getSurfaceBlockType() {
            return BlockType.getByID(net.minecraft.block.Block.getIdFromBlock(biomeMC.topBlock));
        }

        public float getTemperature() {
            return biomeMC.temperature;
        }

        public boolean hasRain() {
            return biomeMC.getIntRainfall() > 0;
        }

        public boolean hasSnow() {
            return biomeMC.getEnableSnow();
        }

        public boolean hasTrees() {
            return this.hasTrees;
        }

        private BiomeGenBase getBiomeByID(int id) {
            // TODO TEMP RPLC: sadly, there seems to be no BiomeGenBase.getBiomeGenArray()
            // return BiomeGenBase.getBiomeGenArray()[id];
            return null;
        }
    }
}
