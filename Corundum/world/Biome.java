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
import java.util.ArrayList;

import Corundum.entities.Mob.MobType;
import Corundum.utils.ListUtilities;
import Corundum.exceptions.CorundumException;
import Corundum.world.Block.BlockType;
import net.minecraft.nbt.NBTBase;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.biome.BiomeOE;

public class Biome {
    // TODO TEMP: "final"s are commented to avoid compilation errors for now
    private/* final */BiomeType type;

    public static enum BiomeType {
        // TODO finish biomes and give them all constructors.
        OCEAN,
        PLAINS,
        DESERT,
        EXTREME_HILLS,
        FOREST,
        TAIGA,
        SWAMPLAND,
        RIVER,
        NETHER,
        END,
        FROZEN_OCEAN,
        FROZEN_RIVER,
        ICE_PLAINS,
        ICE_MOUNTAINS,
        MUSHROOM_ISLAND,
        MUSHROOM_ISLAND_SHORE,
        BEACH,
        DESERT_HILLS,
        FOREST_HILLS,
        TAIGA_HILLS,
        EXTREME_HILLS_EDGE,
        JUNGLE,
        JUNGLE_HILLS,
        JUNGLE_HILLS_EDGE,
        DEEP_OCEAN,
        STONE_BEACH,
        COLD_BEACH,
        BIRCH_FOREST,
        BIRCH_FOREST_HILLS,
        ROOFED_FOREST,
        COLD_TAIGA,
        COLD_TAIGA_HILLS,
        MEGA_TAIGA,
        MEGA_TAIGA_HILLS,
        EXTREME_HILLS_PLUS,
        SAVANNA,
        SAVANNA_PLATEAU,
        MESA,
        MESA_PLATEAU,
        MESA_PLATEAU_F,
        ICE_PLAINS_SPIKES(140);

        private BiomeGenBase biomeMC;

        private static final MobType[] defaultNaturalSpawningMobs = new MobType[] { MobType.ZOMBIE, MobType.BAT, MobType.CREEPER, MobType.SKELETON, MobType.ENDERMAN,
                MobType.CHICKEN, MobType.COW, MobType.PIG, MobType.SHEEP, MobType.SQUID, MobType.WITCH };

        private BiomeType() {
            // if no I.D. is given, assume the I.D. is the same as the enum's ordinal
            this.biomeMC = BiomeGenBase.func_150568_d(ordinal());
        }

        /* DEV NOTES: This constructor should only be useful for variant biomes since their I.D.s skip values. Also, note that variant biome I.D.s are equal to their parent
         * biomes' I.D.s + 128 */
        private BiomeType(int id) {
            this.biomeMC = BiomeGenBase.func_150568_d(id);
            BiomeGenBase gottenBiome = BiomeGenBase.func_150568_d(id);

            if (gottenBiome == BiomeGenBase.ocean && !(id == 0)) {
                CorundumException.err("Error getting an MC biome by it's id!", "Attempted to get a MC biome with a nonexistant id!", "Passed ID (nonexistant): " + id);
            }

            return gottenBiome;
        }

        public Color getColor() {
            return new Color(biomeMC.color);
        }

        public BlockType getFillerBlockType() {
            return BlockType.getByID(net.minecraft.block.Block.getIdFromBlock(biomeMC.fillerBlock));
        }

        public/* TODO: change return type to AnimalType when we have AnimalTypes */MobType[] getAnimals() {
            ArrayList<SpawnListEntry> animalsMC = BiomeOE.getAnimalsSpawnListEntries(biomeMC);

            /* TODO: find a way to get from SpawnListEntries to AnimalTypes; I think the only way to do this is to use the Class<Entity>s inside the SpawnListEntries that
             * represent the kind of entity they will spawn, but since all their names will be obfuscated during runtime, we'll probably have to add a parameter to each
             * EntityType for the Minecraft class that they represent */
            return null;
        }

        public BlockType getSurfaceBlockType() {
            return BlockType.getByID(net.minecraft.block.Block.getIdFromBlock(biomeMC.topBlock));
        }

        public float getTemperature() {
            return biomeMC.temperature;
        }

        public int getTreesPerChunk() {
            return BiomeOE.getTreesPerChunk(biomeMC);
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
            return BiomeOE.getTreesPerChunk(biomeMC) > 0;
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
