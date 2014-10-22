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

import Corundum.entities.Mob.MobType;
import Corundum.world.Block.BlockType;
import net.minecraft.world.biome.BiomeGenBase;

public class Biome {
    // TODO TEMP: "final"s are commented to avoid compilation errors for now
    private/* final */BiomeType type;

    public static enum BiomeType {
        // TODO finish biomes and give them all constructors.
        OCEAN(false),
        PLAINS(false),
        DESERT(false),
        EXTREME_HILLS(true),
        FOREST(true),
        TAIGA(true),
        SWAMPLAND(true),
        RIVER(false),
        NETHER(false, MobType.MAGMA_CUBE, MobType.GHAST, MobType.ZOMBIE_PIGMAN),
        END(false, MobType.ENDERMAN, MobType.ENDERDRAGON),
        FROZEN_OCEAN(false),
        FROZEN_RIVER(false),
        ICE_PLAINS(true),
        ICE_MOUNTAINS(true),
        MUSHROOM_ISLAND(false, MobType.MOOSHROOM),
        MUSHROOM_ISLAND_SHORE(false, MobType.MOOSHROOM),
        BEACH(false),
        DESERT_HILLS(false),
        FOREST_HILLS(true),
        TAIGA_HILLS(true),
        EXTREME_HILLS_EDGE(true),
        JUNGLE(true),
        JUNGLE_HILLS(true),
        JUNGLE_HILLS_EDGE(true),
        DEEP_OCEAN(false),
        STONE_BEACH(false),
        COLD_BEACH(false),
        BIRCH_FOREST(true),
        BIRCH_FOREST_HILLS(true),
        ROOFED_FOREST(true),
        COLD_TAIGA(true),
        COLD_TAIGA_HILLS(true),
        MEGA_TAIGA(true),
        MEGA_TAIGA_HILLS(true),
        EXTREME_HILLS_PLUS(true),
        SAVANNA(true),
        SAVANNA_PLATEAU(true),
        MESA(false),
        MESA_PLATEAU(false),
        MESA_PLATEAU_F(false),
        ICE_PLAINS_SPIKES(140, false);

        /** This {@link MobType}<tt>[]</tt> includes all the mobs that can spawn naturally in a given {@link BiomeType}. This includes monsters that only spawn under certain
         * lighting conditions, but does not include mobs that spawn inside structures such as abandoned mineshafts (like {@link MobType#CAVE_SPIDER cave spiders}) or mobs that
         * spawn from spawners only (like {@link MobType#SILVERFISH silverfish}. */
        private final MobType[] naturalSpawningMobs;
        private final boolean hasTrees;
        private BiomeGenBase biomeMC;

        private static final MobType[] defaultNaturalSpawningMobs = new MobType[] { MobType.ZOMBIE, MobType.BAT, MobType.CREEPER, MobType.SKELETON, MobType.ENDERMAN,
                MobType.CHICKEN, MobType.COW, MobType.PIG, MobType.SHEEP, MobType.SQUID, MobType.WITCH };

        // TODO TEMP: I just put this here to eliminate errors temporarily
        private BiomeType() {
            hasTrees = true;
            naturalSpawningMobs = new MobType[0];
        }

        private BiomeType(boolean has_trees, MobType... naturally_spawning_mobs) {
            hasTrees = has_trees;
            naturalSpawningMobs = naturally_spawning_mobs;

            // if no I.D. is given, assume the I.D. is the same as the enum's ordinal
            biomeMC = getMCBiomeByID(ordinal());
        }

        /* DEV NOTES: This constructor should only be useful for variant biomes since their I.D.s skip values. Also, note that variant biome I.D.s are equal to their parent
         * biomes' I.D.s + 128 */
        private BiomeType(int biomeId, boolean hasTrees, MobType... naturalSpawningMobs) {
            this.hasTrees = hasTrees;
            this.naturalSpawningMobs = naturalSpawningMobs;

            this.biomeMC = getMCBiomeByID(biomeId);
        }

        private BiomeType(int id, boolean hasTrees) {
            this(id, hasTrees, MobType.ZOMBIE, MobType.BAT, MobType.CREEPER, MobType.SKELETON, MobType.ENDERMAN, MobType.CHICKEN, MobType.COW, MobType.PIG, MobType.SHEEP,
                    MobType.SQUID, MobType.WITCH);
        }

        private BiomeGenBase getMCBiomeByID(int id) {
            return BiomeGenBase.func_150568_d(id);
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

        public BiomeType getVariant() {
            return getByID(biomeMC.biomeID + 128);
        }

        public BiomeType getVariantParent() {
            return getByID(biomeMC.biomeID - 128);
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

        public boolean isVariant() {
            return biomeMC.biomeID >= 128;
        }

        /** This method retrieves the {@link BiomeType} with the given item I.D. value.
         * 
         * @param id
         *            is the item I.D. of the {@link BiomeType} you wish to locate.
         * @return the {@link BiomeType} that matches the given item I.D. or <b>null</b> if no {@link BiomeType} has the given I.D. */
        public static BiomeType getByID(int id) {
            // TODO: replace this linear search with a binary search algorithm
            if (id >= 0)
                for (BiomeType item_type : values())
                    if (item_type.biomeMC.biomeID == id)
                        return item_type;
            return null;
        }
    }
}
