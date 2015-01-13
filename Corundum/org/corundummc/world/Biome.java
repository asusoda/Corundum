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

package org.corundummc.world;

import java.awt.Color;
import java.util.ArrayList;

import org.corundummc.entities.living.mobs.Mob.MobType;
import org.corundummc.entities.living.mobs.Mob.MobTypes;
import org.corundummc.entities.living.mobs.animals.Animal.AnimalType;
import org.corundummc.types.IDedType;
import org.corundummc.world.Block.BlockType;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;

public class Biome {
    private final BiomeType type;

    public Biome(BiomeType type) {
        this.type = type;
    }

    public static class BiomeType extends IDedType<BiomeType> {

        public static final BiomeType OCEAN = new BiomeType(), PLAINS = new BiomeType(), DESERT = new BiomeType(), EXTREME_HILLS = new BiomeType(), FOREST = new BiomeType(),
                TAIGA = new BiomeType(), SWAMPLAND = new BiomeType(), RIVER = new BiomeType(), NETHER = new BiomeType(), END = new BiomeType(),
                FROZEN_OCEAN = new BiomeType(), FROZEN_RIVER = new BiomeType(), ICE_PLAINS = new BiomeType(), ICE_MOUNTAINS = new BiomeType(),
                MUSHROOM_ISLAND = new BiomeType(), MUSHROOM_ISLAND_SHORE = new BiomeType(), BEACH = new BiomeType(), DESERT_HILLS = new BiomeType(),
                FOREST_HILLS = new BiomeType(), TAIGA_HILLS = new BiomeType(), EXTREME_HILLS_EDGE = new BiomeType(), JUNGLE = new BiomeType(), JUNGLE_HILLS = new BiomeType(),
                JUNGLE_HILLS_EDGE = new BiomeType(), DEEP_OCEAN = new BiomeType(), STONE_BEACH = new BiomeType(), COLD_BEACH = new BiomeType(),
                BIRCH_FOREST = new BiomeType(), BIRCH_FOREST_HILLS = new BiomeType(), ROOFED_FOREST = new BiomeType(), COLD_TAIGA = new BiomeType(),
                COLD_TAIGA_HILLS = new BiomeType(), MEGA_TAIGA = new BiomeType(), MEGA_TAIGA_HILLS = new BiomeType(), EXTREME_HILLS_PLUS = new BiomeType(),
                SAVANNA = new BiomeType(), SAVANNA_PLATEAU = new BiomeType(), MESA = new BiomeType(), MESA_PLATEAU = new BiomeType(), MESA_PLATEAU_FOREST = new BiomeType(),
                SUNFLOWER_PLAINS = new BiomeType(129), DESERT_MODIFIED = new BiomeType(), EXTREME_HILLS_MODIFIED = new BiomeType(), FLOWER_FOREST = new BiomeType(),
                TAIGA_MODIFIED = new BiomeType(), SWAMPLAND_MODIFIED = new BiomeType(), ICE_PLAINS_SPIKES = new BiomeType(140), JUNGLE_MODIFIED = new BiomeType(149),
                JUNGLE_EDGE_MODIFIED = new BiomeType(151), BIRCH_FOREST_MODIFIED = new BiomeType(155), BIRCH_FOREST_HILLS_MODIFIED = new BiomeType(),
                ROOFED_FOREST_MODIFIED = new BiomeType(), COLD_TAIGA_MODIFIED = new BiomeType(), MEGA_SPRUCE_TAIGA = new BiomeType(160),
                MEGA_SPRUCE_TAIGA_HILLS = new BiomeType(), EXTREME_HILLS_PLUS_MODIFIED = new BiomeType(), SAVANNAH_MODIFIED = new BiomeType(),
                SAVANNAH_PLATEAU_MODIFIED = new BiomeType(), MESA_BRYCE = new BiomeType(), MESA_PLATEAU_FOREST_MODIFIED = new BiomeType(),
                MESA_PLATEAU_MODIFIED = new BiomeType();

        private BiomeGenBase biomeMC;

        private static final MobType[] defaultNaturalSpawningMobs = new MobType[] { MobTypes.ZOMBIE, MobTypes.BAT, MobTypes.CREEPER, MobTypes.SKELETON, MobTypes.ENDERMAN,
                MobTypes.CHICKEN, MobTypes.COW, MobTypes.PIG, MobTypes.SHEEP, MobTypes.SQUID, MobTypes.WITCH };

        private BiomeType() {
            super(nextID(BiomeType.class));
        }

        private BiomeType(int id) {
            super(id);

            this.biomeMC = BiomeGenBase.func_150568_d(id);
        }

        public Color getColor() {
            return new Color(biomeMC.color);
        }

        public BlockType getFillerBlockType() {
            return BlockType.getByID(net.minecraft.block.Block.getIdFromBlock(biomeMC.fillerBlock));
        }

        public BlockType getSurfaceBlockType() {
            return BlockType.getByID(net.minecraft.block.Block.getIdFromBlock(biomeMC.topBlock));
        }

        public float getTemperature() {
            return biomeMC.temperature;
        }

        public BiomeType getVariant() {
            return getByID(getID() + 128);
        }

        public BiomeType getVariantParent() {
            return getByID(getID() - 128);
        }

        public boolean hasRain() {
            return biomeMC.getIntRainfall() > 0;
        }

        public boolean hasSnow() {
            return biomeMC.getEnableSnow();
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
            return getByID(BiomeType.class, id);
        }

        public static BiomeType[] values() {
            return values(BiomeType.class);
        }
    }

    public BiomeType getBiomeType() {
        return this.type;
    }
}
