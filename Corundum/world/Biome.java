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

import javax.swing.plaf.metal.OceanTheme;

import Corundum.entities.Mob.MobType;
import Corundum.utils.interfaces.IDedType;
import Corundum.world.Block.BlockType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.biome.BiomeOE;

public class Biome {
    private final BiomeType type;

    public Biome(BiomeType type) {
        this.type = type;
    }

    public BiomeType getBiomeType() {
        return this.type;
    }

    public class BiomeType extends IDedType<BiomeType> {
        public static final BiomeType OCEAN = new BiomeType(), PLAINS = new BiomeType(), DESERT = new BiomeType(), EXTREME_HILLS = new BiomeType(), FOREST = new BiomeType(),
                TAIGA = new BiomeType(), SWAMPLAND = new BiomeType(), RIVER = new BiomeType(), NETHER = new BiomeType(), END = new BiomeType(),
                FROZEN_OCEAN = new BiomeType(), FROZEN_RIVER = new BiomeType(), ICE_PLAINS = new BiomeType(), ICE_MOUNTAINS = new BiomeType(),
                MUSHROOM_ISLAND = new BiomeType(), MUSHROOM_ISLAND_SHORE = new BiomeType(), BEACH = new BiomeType(), DESERT_HILLS = new BiomeType(),
                FOREST_HILLS = new BiomeType(), TAIGA_HILLS = new BiomeType(), EXTREME_HILLS_EDGE = new BiomeType(), JUNGLE = new BiomeType(), JUNGLE_HILLS = new BiomeType(),
                JUNGLE_HILLS_EDGE = new BiomeType(), DEEP_OCEAN = new BiomeType(), STONE_BEACH = new BiomeType(), COLD_BEACH = new BiomeType(),
                BIRCH_FOREST = new BiomeType(), BIRCH_FOREST_HILLS = new BiomeType(), ROOFED_FOREST = new BiomeType(), COLD_TAIGA = new BiomeType(),
                COLD_TAIGA_HILLS = new BiomeType(), MEGA_TAIGA = new BiomeType(), MEGA_TAIGA_HILLS = new BiomeType(), EXTREME_HILLS_PLUS = new BiomeType(),
                SAVANNA = new BiomeType(), SAVANNA_PLATEAU = new BiomeType(), MESA = new BiomeType(), MESA_PLATEAU = new BiomeType(), MESA_PLATEAU_F = new BiomeType(),
                SUNFLOWER_PLAINS = new BiomeType(129), DESERT_M = new BiomeType(), EXTREME_HILLS_M = new BiomeType(), FLOWER_FOREST = new BiomeType(),
                TAIGA_M = new BiomeType(), SWAMPLAND_M = new BiomeType(), ICE_PLAINS_SPIKES = new BiomeType(140), JUNGLE_M = new BiomeType(149),
                JUNGLE_EDGE_M = new BiomeType(151), BIRCH_FOREST_M = new BiomeType(155), BIRCH_FOREST_HILLS_M = new BiomeType(), ROOFED_FOREST_M = new BiomeType(),
                COLD_TAIGA_M = new BiomeType(), MEGA_SPRUCE_TAIGA = new BiomeType(160), MEGA_SPRUCE_TAIGA_HILLS = new BiomeType(), EXTREME_HILLS_PLUS_M = new BiomeType(),
                SAVANNAH_M = new BiomeType(), SAVANNAH_PLATEAU_M = new BiomeType(), MESA_BRYCE = new BiomeType(), MESA_PLATEAU_F_M = new BiomeType(), MESA_PLATEAU_M;

        private BiomeGenBase biomeMC;

        private static final MobType[] defaultNaturalSpawningMobs = new MobType[] { MobType.ZOMBIE, MobType.BAT, MobType.CREEPER, MobType.SKELETON, MobType.ENDERMAN,
                MobType.CHICKEN, MobType.COW, MobType.PIG, MobType.SHEEP, MobType.SQUID, MobType.WITCH };

        private BiomeType() {
            super();

            // if no I.D. is given, assume the I.D. is the next I.D. after the previous (0 for the first ordinal)
            this.biomeMC = BiomeGenBase.func_150568_d(id);
        }

        /* DEV NOTES: This constructor should only be useful for variant biomes since their I.D.s skip values. Also, note that variant biome I.D.s are equal to their parent
         * biomes' I.D.s + 128 */
        private BiomeType(int id) {
            super(id);

            this.biomeMC = BiomeGenBase.func_150568_d(id);
        }

        public Color getColor() {
            return new Color(biomeMC.color);
        }

        public BlockType getFillerBlockType() {
            return BlockType.getByIDHelper(net.minecraft.block.Block.getIdFromBlock(biomeMC.fillerBlock));
        }

        public/* TODO: change return type to AnimalType when we have AnimalTypes */MobType[] getAnimals() {
            ArrayList<SpawnListEntry> animalsMC = BiomeOE.getAnimalsSpawnListEntries(biomeMC);

            /* TODO: find a way to get from SpawnListEntries to AnimalTypes; I think the only way to do this is to use the Class<Entity>s inside the SpawnListEntries that
             * represent the kind of entity they will spawn, but since all their names will be obfuscated during runtime, we'll probably have to add a parameter to each
             * EntityType for the Minecraft class that they represent */
            return null;
        }

        public BlockType getSurfaceBlockType() {
            return BlockType.getByIDHelper(net.minecraft.block.Block.getIdFromBlock(biomeMC.topBlock));
        }

        public float getTemperature() {
            return biomeMC.temperature;
        }

        public int getTreesPerChunk() {
            return BiomeOE.getTreesPerChunk(biomeMC);
        }

        public BiomeType getVariant() {
            return getByIDHelper(biomeMC.biomeID + 128);
        }

        public BiomeType getVariantParent() {
            return getByIDHelper(biomeMC.biomeID - 128);
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
        public static BiomeType getByIDHelper(int id) {
            return OCEAN.getByIDHelper(id);
        }

        @Override
        public short getID() {
            return (short) biomeMC.biomeID;
        }

        public short getData() {
            return -1;
        }
    }
}
