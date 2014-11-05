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

import Corundum.utils.interfaces.HoldableType;
import Corundum.utils.interfaces.Matchable;
import Corundum.world.Biome.BiomeType;

public class Block {
    private BlockType type;
    private byte data;
    private Location location;

    /** This method retrieves the {@link Biome} that this block is located in.
     * 
     * @return the {@link Biome} that this block is in. */
    public Biome getBiome() {
        int posXInChunk = this.location.getBlockX() % 16;
        int posZInChunk = this.location.getBlockZ() % 16;
        return new Biome(getChunk().getBiomeMap()[posXInChunk][posZInChunk]);
    }

    public Chunk getChunk() {
        net.minecraft.world.chunk.Chunk mcChunk = this.getLocation().getWorld().getMCWorld().getChunkFromBlockCoords(this.location.getBlockX(), this.location.getBlockZ());
        return new Chunk(mcChunk);
    }

    public byte getData() {
        return data;
    }

    public Location getLocation() {
        return location;
    }

    public BlockType getType() {
        return type;
    }

    public void setData(byte data) {
        this.data = data;
        this.location.getWorld().setBlockData(this.location, data);
        // TODO: change the BlockType if necessary
        // TODO: send a packet to clients concerning the block change
    }

    public void setType(BlockType type) {
        this.type = type;
        // TODO: change the data accordingly, but try to do in such a way that the orientation of the block doesn't change
        // TODO: send a packet to clients concerning the block change
    }

    /** This enum is used to represent the different types of {@link Block}s. This list of different types not only includes those types of blocks differentiated by different
     * I.D.s, but also many of those differentiated by different data values; for example, all different colors of wool blocks are listed individually despite the fact that
     * they all have the same I.D. */
    public enum BlockType implements HoldableType<BlockType>, Matchable<BlockType> {
        AIR,  // the first value is handled by a base case in the constructor
        // stone types
        STONE(0),
        GRANITE,
        POLISHED_GRANITE,
        DIORITE,
        POLISHED_DIORITE,
        ANDESITE,
        POLISHED_ANDESITE,
        // END stone types
        GRASS(-1),
        // dirt types
        DIRT(0),
        COARSE_DIRT,
        PODZOL,
        // END dirt types
        COBBLESTONE(-1),
        // planks
        OAK_WOOD_PLANKS(0),
        SPRUCE_WOOD_PLANKS,
        BIRCH_WOOD_PLANKS,
        JUNGLE_WOOD_PLANKS,
        ACACIA_WOOD_PLANKS,
        DARK_OAK_WOOD_PLANKS,
        // END planks
        // saplings
        OAK_SAPLING(0),
        SPRUCE_SAPLING,
        BIRCH_SAPLING,
        JUNGLE_SAPLING,
        ACACIA_SAPLING,
        DARK_OAK_SAPLING,
        // END saplings
        BEDROCK(-1),
        FLOWING_WATER,
        STILL_WATER,
        FLOWING_LAVA,
        STILL_LAVA,
        // sand types
        SAND(0),
        RED_SAND(12),
        // END sand types
        GRAVEL(-1),
        GOLD_ORE,
        IRON_ORE,
        COAL_ORE,
        // logs
        OAK_LOG(0),
        SPRUCE_LOG,
        BIRCH_LOG,
        JUNGLE_LOG,
        // END logs
        // leaves
        OAK_LEAVES(0),
        SPRUCE_LEAVES,
        BIRCH_LEAVES,
        JUNGLE_LEAVES,
        // END leaves
        // sponges
        SPONGE(0),
        WET_SPONGE(19),
        // END sponges
        GLASS(-1),
        LAPIS_LAZULI_ORE,
        LAPIS_LAZULI_BLOCK,
        DISPENSER,
        // sandstone types
        SANDSTONE(0),
        CHISELED_SANDSTONE,
        SMOOTH_SANDSTONE,
        // END sandstone types
        NOTE_BLOCK(-1),
        BED,
        POWEREED_RAIL,
        DETECTOR_RAIL,
        STICKY_PISTON,
        COBWEB,
        // shrubbery
        DEAD_SHRUB(0),
        TALL_GRASS,
        FERN,
        // END shrubbery
        // TODO: is there a difference between this DEAD_BUSH (I.D. 31) and the DEAD_SHRUB (I.D. 31:0)?
        DEAD_BUSH(-1),
        PISTON,
        PISTON_ARM,
        // wool colors
        WHITE_WOOL(0),
        ORANGE_WOOL,
        MAGENTA_WOOL,
        LIGHT_BLUE_WOOL,
        YELLOW_WOOL,
        LIGHT_GREEN_WOOL,
        PINK_WOOL,
        DARK_GRAY_WOOL,
        LIGHT_GRAY_WOOL,
        CYAN_WOOL,
        PURPLE_WOOL,
        DARK_BLUE_WOOL,
        BROWN_WOOL,
        DARK_GREEN_WOOL,
        RED_WOOL,
        BLACK_WOOL,
        // END wool colors
        DANDELION(37, 0),  // yes, Minecraft I.D.s skip 36; I don't know why
        // small flowers
        POPPY(0),
        BLUE_ORCHID,
        ALLIUM,
        AZURE_BLUET,
        RED_TULIP,
        ORANGE_TULIP,
        WHITE_TULIP,
        PINK_TULIP,
        OXEYE_DAISY,
        // END small flowers
        BROWN_MUSHROOM(-1),
        RED_MUSHROOM,
        GOLD_BLOCK,
        IRON_BLOCK,
        // double slabs
        DOUBLE_STONE_SLAB(0),
        DOUBLE_SANDSTONE_SLAB,
        DOUBLE_OAK_SLAB,
        DOUBLE_COBBLESTONE_SLAB,
        DOUBLE_BRICK_SLAB,
        DOUBLE_STONE_BRICK_SLAB,
        DOUBLE_NETHER_BRICK_SLAB,
        DOUBLE_SQUARTZ_SLAB,
        // END double slabs
        // slabs
        STONE_SLAB(0),
        SANDSTONE_SLAB,
        OAK_SLAB,
        COBBLESTONE_SLAB,
        BRICK_SLAB,
        STONE_BRICK_SLAB,
        NETHER_BRICK_SLAB,
        QUARTZ_SLAB,
        // END slabs
        BRICKS(-1),
        TNT,
        BOOKSHELF,
        MOSS_STONE,
        OBSIDIAN,
        TORCH,
        FIRE,
        MONSTER_SPAWNER,
        OAK_STAIRS,
        CHEST,
        REDSTONE_WIRE,
        DIAMOND_ORE,
        DIAMOND_BLOCK,
        CRAFTING_TABLE,
        WHEAT,
        FARMLAND,
        FURNACE,
        BURNING_FURNACE,
        SIGN_POST,
        OAK_DOOR,
        LADDER,
        RAIL,
        COBBLESTONE_STAIRS,
        WALL_SIGN,
        LEVER,
        STONE_PRESSURE_PLATE,
        IRON_DOOR,
        WOODEN_PRESSURE_PLATE,
        REDSTONE_ORE,
        GLOWING_REDSTONE_ORE,
        UNPOWERED_REDSTONE_TORCH,
        POWERED_REDSTONE_TORCH,
        STONE_BUTTON,
        SNOW,
        ICE,
        SNOW_BLOCK,
        CACTUS,
        CLAY,
        SUGAR_CANES,
        JUKEBOX,
        OAK_FENCE,
        PUMPKIN,
        NETHERRACK,
        SOUL_SAND,
        GLOWSTONE,
        NETHER_PORTAL,
        JACK_O_LANTERN,
        CAKE,
        UNPOWERED_REDSTONE_REPEATER,
        POWERED_REDSTONE_REPEATER,
        // stained glass
        WHITE_STAINED_GLASS(0),
        ORANGE_STAINED_GLASS,
        MAGENTA_STAINED_GLASS,
        LIGHT_BLUE_STAINED_GLASS,
        YELLOW_STAINED_GLASS,
        LIGHT_GREEN_STAINED_GLASS,
        PINK_STAINED_GLASS,
        DARK_GRAY_STAINED_GLASS,
        LIGHT_GRAY_STAINED_GLASS,
        CYAN_STAINED_GLASS,
        PURPLE_STAINED_GLASS,
        DARK_BLUE_STAINED_GLASS,
        BROWN_STAINED_GLASS,
        DARK_GREEN_STAINED_GLASS,
        RED_STAINED_GLASS,
        BLACK_STAINED_GLASS,
        // END stained glass
        WOODEN_TRAPDOOR(-1),
        // monster eggs
        STONE_MONSTER_EGG(0),
        COBBLESTONE_MONSTER_EGG,
        STONE_BRICK_MONSTER_EGG,
        MOSSY_STONE_BRICK_MONSTER_EGG,
        CRACKED_STONE_BRICK_MONSTER_EGG,
        CHISELED_STONE_BRICK_MONSTER_EGG,
        // END monster eggs
        // stone bricks
        STONE_BRICKS(0),
        MOSSY_STONE_BRICKS,
        CRACKED_STONE_BRICKS,
        CHISELED_STONE_BRICKS,
        // END stone bricks
        GIANT_RED_MUSHROOM(-1),
        GIANT_BROWN_MUSHROOM,
        IRON_BARS,
        GLASS_PANE,
        MELON,
        PUMPKIN_STEM,
        MELON_STEM,
        VINES,
        OAK_FENCE_GATE,
        BRICK_STAIRS,
        STONE_BRICK_STAIRS,
        MYCELIUM,
        LILY_PAD,
        NETHER_BRICK,
        NETHER_BRICK_FENCE,
        NETHER_BRICK_STAIRS,
        NETHER_WARTS,
        ENCHANTMENT_TABLE,
        BREWING_STAND,
        CAULDRON,
        END_PORTAL,
        END_PORTAL_FRAME,
        END_STONE,
        DRAGON_EGG,
        UNPOWERED_REDSTONE_LAMP,
        POWERED_REDSTONE_LAMP,
        // double wood slabs
        DOUBLE_OAK_WOOD_SLAB(0),
        DOUBLE_SPRUCE_WOOD_SLAB,
        DOUBLE_BIRCH_WOOD_SLAB,
        DOUBLE_JUNGLE_WOOD_SLAB,
        DOUBLE_ACACIA_WOOD_SLAB,
        DOUBLE_DARK_OAK_WOOD_SLAB,
        // END double wood slabs
        // wood slabs
        OAK_WOOD_SLAB(0),
        SPRUCE_WOOD_SLAB,
        BIRCH_WOOD_SLAB,
        JUNGLE_WOOD_SLAB,
        ACACIA_WOOD_SLAB,
        DARK_OAK_WOOD_SLAB,
        // END wood slabs
        COCOA(-1),
        SANDSTONE_STAIRS,
        EMERALD_ORE,
        ENDER_CHEST,
        TRIPWIRE_HOOK,
        TRIPWIRE,
        EMERALD_BLOCK,
        SPRUCE_WOOD_STAIRS,
        BIRCH_WOOD_STAIRS,
        JUNGLE_WOOD_STAIRS,
        COMMAND_BLOCK,
        BEACON,
        // cobblestone walls
        COBBLESTONE_WALL(0),
        MOSSY_COBBLESTONE_WALLS(1),
        // END cobblestone walls
        FLOWER_POT(-1),
        CARROTS,
        POTATOES,
        WOODEN_BUTTON,
        MOB_HEAD,
        ANVIL,
        TRAPPED_CHEST,
        GOLD_PRESSURE_PLATE,
        IRON_PRESSURE_PLATE,
        UNPOWERED_REDSTONE_COMPARATOR,
        POWERED_REDSTONE_COMPARATOR,
        DAYLIGHT_SENSOR,
        REDSTONE_BLOCK,
        QUARTZ_ORE,
        HOPPER,
        // quartz
        QUARTZ_BLOCK(0),
        CHISELED_QUARTZ_BLOCK,
        PILLAR_QUARTZ_BLOCK,
        // END quartz
        QUARTZ_STAIRS(-1),
        ACTIVATOR_RAIL,
        DROPPER,
        // stained clay
        WHITE_STAINED_CLAY(0),
        ORANGE_STAINED_CLAY,
        MAGENTA_STAINED_CLAY,
        LIGHT_BLUE_STAINED_CLAY,
        YELLOW_STAINED_CLAY,
        LIGHT_GREEN_STAINED_CLAY,
        PINK_STAINED_CLAY,
        DARK_GRAY_STAINED_CLAY,
        LIGHT_GRAY_STAINED_CLAY,
        CYAN_STAINED_CLAY,
        PURPLE_STAINED_CLAY,
        DARK_BLUE_STAINED_CLAY,
        BROWN_STAINED_CLAY,
        DARK_GREEN_STAINED_CLAY,
        RED_STAINED_CLAY,
        BLACK_STAINED_CLAY,
        // END stained clay
        // stained glass panes
        WHITE_STAINED_GLASS_PANE(0),
        ORANGE_STAINED_GLASS_PANE,
        MAGENTA_STAINED_GLASS_PANE,
        LIGHT_BLUE_STAINED_GLASS_PANE,
        YELLOW_STAINED_GLASS_PANE,
        LIGHT_GREEN_STAINED_GLASS_PANE,
        PINK_STAINED_GLASS_PANE,
        DARK_GRAY_STAINED_GLASS_PANE,
        LIGHT_GRAY_STAINED_GLASS_PANE,
        CYAN_STAINED_GLASS_PANE,
        PURPLE_STAINED_GLASS_PANE,
        DARK_BLUE_STAINED_GLASS_PANE,
        BROWN_STAINED_GLASS_PANE,
        GREEN_STAINED_GLASS_PANE,
        RED_STAINED_GLASS_PANE,
        BLACK_STAINED_GLASS_PANE,
        // END stained glass panes
        // more leaves
        ACACIA_LEAVES(0),
        DARK_OAK_LEAVES,
        // END more leaves
        // more logs
        ACACIA_LOG(0),
        DARK_OAK_LOG,
        // END more logs
        SLIME_BLOCK(-1),
        BARRIER,
        IRON_TRAPDOOR,
        // prismarine types
        PRISMARINE(0),
        PRISMARINE_BRICKS,
        DARK_PRISMARINE,
        // END prismarine types
        SEA_LENTERN(-1),
        HAY_BALE,
        // carpet
        WHITE_CARPET(0),
        ORANGE_CARPET,
        MAGENTA_CARPET,
        LIGHT_BLUE_CARPET,
        YELLOW_CARPET,
        LIGHT_GREEN_CARPET,
        PINK_CARPET,
        DARK_GRAY_CARPET,
        LIGHT_GRAY_CARPET,
        CYAN_CARPET,
        PURPLE_CARPET,
        DARK_BLUE_CARPET,
        BROWN_CARPET,
        DARK_GREEN_CARPET,
        RED_CARPET,
        BLACK_CARPET,
        // END carpet
        HARDENED_CLAY(-1),
        COAL_BLOCK,
        PACKED_ICE,
        // large plants
        SUNFLOWER(0),
        LILAC,
        DOUBLE_TALL_GRASS,
        LARGE_FERN,
        ROSE_BUSH,
        PEONY,
        // END large plants
        BANNER_POST(-1),
        WALL_BANNER,
        INVERTED_DAYLIGHT_SENSOR,
        // red sandstone
        RED_SANDSTONE(0),
        SMOOTH_RED_SANDSTONE,
        CHISELED_RED_SANDSTONE,
        // END red sandstone
        RED_SANDSTONE_STAIRS,
        DOUBLE_RED_SANDSTONE_SLAB,
        RED_SANDSTONE_SLAB,
        SPRUCE_FENCE_GATE,
        BIRCH_FENCE_GATE,
        JUNGLE_FENCE_GATE,
        DARK_OAK_FENCE_GATE,
        ACACIA_FENCE_GATE,
        SPRUCE_FENCE,
        BIRCH_FENCE,
        JUNGLE_FENCE,
        DARK_OAK_FENCE,
        ACACIA_FENCE,
        SPRUCE_DOOR,
        BIRCH_DOOR,
        JUNGLE_DOOR,
        ACACIA_DOOR,
        DARK_OAK_DOOR;

        /** <b><i>DEV NOTES:</b></i><br>
         * property-getting methods of <b><tt>blockMC</b></tt>:<br>
         * <i>NOTE:</i> "<tt>Material.</tt>" at the beginning of a method indicates that the property can be obtained through {@link net.minecraft.block.Block#getMaterial()}. <br>
         * <b>boolean</b>s: <br>
         * - <tt>Material.isReplaceable</tt>: tells if block placement can cover it; snow, tall grass, and water are replaceable, but stone, signs, and torches are not <br>
         * - <tt>Material.isLiquid</tt>: tells if the block is liquid; only water and lava (stationary and regular) are liquid for now <br>
         * - <tt>Material.isSolid</tt>: tells if the block is solid; stone and grass are solid, but water and signs are not <br>
         * - <tt>Material.getCanBurn</tt>: tells if fire can burn the block or not; wood-based things can, stone-based things can't; TODO: what about grass blocks that change
         * to dirt? <br>
         * - <tt>Material.blockMovement</tt>: tells if the block blocks entity movement; NOTE: this is exactly the same as isSolid() in all cases as far as I can tell <br>
         * - <tt>Material.isOpaque</tt>: tells whether or not the block lets light through; TODO: is it necessary? by default in Block, it's equivalent to !isTranslucent &&
         * !blocksMovement <br>
         * - <tt>Material.isToolNotRequired</tt>: tells whether or not a tool is required to acquire drops from this; +: dirt, sand, and signs; -: stone, cobblestone, and
         * brewing stands <br>
         * - <tt>Material.isAdventureModeExempt</tt>: true if it can always be mined in Adventure Mode <br>
         * - <tt>Material.getCanBlockGrass</tt>: tells if the presence of this block atop a dirt block can stop grass from spreading to that dirt; TODO: why does block take
         * the ! of it? <br>
         * <b>int</b>s: <br>
         * - <tt>Material.getMaterialMobility</tt>: 0 for most blocks (e.g. dirt), 1 if it cannot push other blocks, but pistons can push over them (e.g. torches), 2 if it
         * cannot be pushed at all (e.g. bedrock) <br>
         * - <tt>Block.getLightOpacity</tt>: the amount of light lost going through this block; 0 for anvils and enchantment tables, 1 for leaves and webs, 3 for water and
         * ice, 255 otherwise <br>
         * <b>float</b>s: <br>
         * - <tt>Block.getLightValue</tt>: the level of light emitted by this block; 1.0 for End portals, redstone lights, and lava, 0 otherwise<br>
         * - <tt>Block.isOpaqueCube</tt>: returns true only if the block takes the entire space of a block and it's opaque; I believe this to be redundant<br>
         * other <tt>Object</tt>s: <br>
         * - <tt>MapColor Material.getMaterialMapColor</tt></tt>: the color that is represented for this block on the map */
        private final net.minecraft.block.Block blockMC;

        /** <b><i>DEV NOTES:</b></i><br>
         * This <b>byte</b> needs to be here because Minecraft does not keep track of a {@link net.minecraft.block.Block}'s I.D.; at least, I can't figure out where it's
         * stored.<br>
         * Also, the I.D. is stored -128 to utilize the full range of the <b>byte</b>. {@link BlockType} I.D.s are between 0 and 255, but Java <b>byte</b>s are between -128
         * and 127; this subtraction allows us to hold any {@link BlockType} I.D. in the space of a <b>byte</b> instead of a <b>short</b>. */
        private final byte id_minus_128;
        /** <b><i>DEV NOTES:</b></i><br>
         * {@link BlockType}s with -1 for a data value represent types that have no siblings, e.g. {@link BlockType#SLIME_BLOCK slime blocks} or {@link BlockType#HOPPER
         * hoppers} and as opposed to {@link BlockType}s like {@link BlockType#OAK_LOG oak logs}. */
        private final byte data;

        // sub-types are delimited by declaration of data with 0, then declaration of data <= the previous

        // constructors
        /** This constructor makes a BlockType based on the previous value's I.D. and data. If the previous value has no strictly associated data value (data value = -1), it
         * means that it has no sub-types (e.g. the different colors of wool or types of wood), so use the next I.D.; if it has a data value, give this BlockType the same I.D.
         * and the next data value. Essentially, "I.D. blocks" (blocks of multiple enum constants that all have the same I.D., but different data values) are delimited by the
         * use of the {@link #BlockType(int)} and {@link #BlockType(int, int)} constructors; declaring a new enum value with a data value less than the previous will end a
         * block and declaring one with a data value >= 0 will start a new block. */
        private BlockType() {
            // if this is the first block (AIR), use a base case
            if (ordinal() == 0) {
                id_minus_128 = -128;
                data = -1;
            } // otherwise, infer the I.D. and data values using the previous type
            else {
                BlockType previous_value = values()[ordinal() - 1];
                /* if the previous data was -1, we are not in an I.D. block, so increment I.D. and default data to -1 */
                if (previous_value.data == -1) {
                    id_minus_128 = (byte) (previous_value.id_minus_128 + 1);
                    data = -1;
                } /* if the previous data value was not -1, we're in an I.D. block, so use the same I.D. as the previous and increment data */
                else {
                    id_minus_128 = previous_value.id_minus_128;
                    data = (byte) (previous_value.data + 1);
                }
            }

            // find the Block with the given I.D.
            blockMC = net.minecraft.block.Block.getBlockById(id_minus_128 + 128);
        }

        /** This constructor makes a BlockType based on the previous value's I.D. and the given data. If the previous value's data value is <= <b><tt>data</b></tt>, then the
         * I.D. block is ending, so it increments the I.D.; otherwise, it will use the same I.D. as the previous value to continue the I.D. block. Essentially, "I.D. blocks"
         * (blocks of multiple enum constants that all have the same I.D., but different data values) are delimited by the use of the {@link #BlockType(int)} and
         * {@link #BlockType(int, int)} constructors; declaring a new enum value with a data value less than the previous will end a block and declaring one with a data value
         * >= 0 will start a new block.
         * 
         * @param data
         *            is the data value for this {@link BlockType}. */
        private BlockType(int data) {
            this.data = (byte) data;

            BlockType previous_value = BlockType.values()[ordinal() - 1];
            // if data <= the previous's data, it indicates the start of a new I.D. block, so increment the I.D. of the previous value
            if (data <= previous_value.data)
                id_minus_128 = (byte) (previous_value.id_minus_128 + 1);
            // otherwise, this is the continuation of an I.D. block, so use the same I.D.
            else
                id_minus_128 = previous_value.id_minus_128;

            // find the Block with the given I.D.
            blockMC = net.minecraft.block.Block.getBlockById(id_minus_128 + 128);
        }

        /** This constructor makes a BlockType with the given I.D. and data. It's necessary for specifying I.D.s when Minecraft skips I.D.s.
         * 
         * @param id
         *            is the block I.D. that this {@link BlockType} is associated with.
         * @param data
         *            is the data value associated with this {@link BlockType}.
         * @see {@link #BlockType(int)} */
        private BlockType(int id, int data) {
            id_minus_128 = (byte) (id - 128);
            this.data = (byte) data;

            // find the Block with the given I.D.
            blockMC = net.minecraft.block.Block.getBlockById(id_minus_128 + 128);
        }

        // class-specific methods
        /** This method determines whether or not a block of this {@link BlockType} can stop grass from spreading onto nearby dirt if placed on top of the dirt. For example, if
         * you place {@link BlockType#STONE stone} on top of dirt, that dirt cannot grow grass even if there is well-lit grass right next to it; on the other hand,
         * {@link BlockType#AIR air} cannot stop grass from spreading onto dirt.
         * 
         * @return <b>true</b> if this {@link BlockType} can stop the spread of grass to dirt; <b>false</b> otherwise. */
        public boolean canBlockGrass() {
            return blockMC.getMaterial().getCanBlockGrass();
        }

        /** This method returns the {@link BoundingBox} that describes the shape of this block. Some blocks have normal bounds and are shaped like full-size cubes, like
         * {@link BlockType#GRASS grass} and {@link BlockType#STONE stone}; others like {@link BlockType#GLASS_PANE glass panes} and {@link BlockType#ANVIL anvils} don't take
         * on a perfectly 1x1x1 cubic shape.
         * 
         * @return a {@link BoundingBox} describing the shape of this {@link BlockType}. */
        public BoundingBox getBoundingBox() {
            return new BoundingBox((float) blockMC.getBlockBoundsMinX(), (float) blockMC.getBlockBoundsMinY(), (float) blockMC.getBlockBoundsMinZ(), (float) blockMC
                    .getBlockBoundsMaxX(), (float) blockMC.getBlockBoundsMaxY(), (float) blockMC.getBlockBoundsMaxZ());
        }

        /** This method returns the amount of light emitted by this type of block as a number between 0 and 1. Most blocks like {@link BlockType#GRASS grass} and
         * {@link BlockType#STONE stone} don't emit any light, so they have a light level of 0; some other blocks like {@link BlockType#POWERED_REDSTONE_LAMP powered redstone
         * lamps} will emit lots of light (light level 1) and others like {@link BlockType#POWERED_REDSTONE_TORCH powered redstone torches} can emit some low light (light level
         * 0.5 in this case).
         * 
         * @return a number between 0 and 1 representing the amount of light emitted by this block. */
        public float getLightEmission() {
            return blockMC.getLightValue();
        }

        /** This method returns the color that appears on a map to represent this {@link BlockType}.
         * 
         * @return a standard Java {@link Color} object representing the color that this {@link BlockType} is represented by on a map. */
        public Color getMapColor() {
            /* Mojang decided that they don't need to use three values for a color, but one big int in which the bits were basically concatenated from the three bytes
             * representing red, green, and blue values; this splits the int into three RGB values and puts it into a standard Color object */
            int color_value = blockMC.getMaterial().getMaterialMapColor().colorValue;
            return new Color(color_value & 0xFF0000, color_value & 0x00FF00, color_value & 0x0000FF);
        }

        /** This method returns the opacity of a block of this {@link BlockType} as a number between 0 and 255 (where 255 is opaque like {@link BlockType#STONE stone} and 0 is
         * completely clear like {@link BlockType#GLASS}).
         * 
         * @return a number between 0 and 255 representing the opacity of this {@link BlockType}. */
        public short getOpacity() {
            return (short) blockMC.getLightOpacity();
        }

        /** This method determines whether or not this {@link BlockType} can be mined and its drops obtained without a tool in Adventure Mode.
         * 
         * @return <b>true</b> if its drops can be obtained without the use of a tool in Adventure Mode; <b>false</b> otherwise. */
        public boolean isAdventureModeExempt() {
            return blockMC.getMaterial().isAdventureModeExempt();
        }

        /** This method determines whether of not a block is broken when it is pushed by a piston. For example, {@link BlockType#TORCH torches} are broken when they are pushed,
         * while {@link BlockType#STONE stone} blocks are easily pushed without breaking and {@link BlockType#BEDROCK bedrock} cannot be pushed at all.
         * 
         * @return <b>true</b> if this {@link BlockType} can be pushed, but will break when it is pushed; <b>false</b> otherwise.
         * @see {@link #isPushable()} and {@link #isImmobile()} */
        public boolean isBrokenWhenPushed() {
            return blockMC.getMaterial().getMaterialMobility() == 1;
        }

        /** This method determines whether or not a block of this {@link BlockType} is able to be burned until broken. Note that most blocks can be lit on fire, but that does
         * not necessarily make them "burnable"; burnable blocks are the blocks that will burn until they break and often spread fire to other nearby blocks. For example,
         * {@link BlockType#LEAVES leaves}, {@link BlockType#OAK_PLANKS wooden planks}, and {@link BlockType#OAK_LOG logs} are all burnable, but {@link BlockType#WATER water},
         * {@link BlockType#DIRT dirt}, and {@link BlockType#STONE stone} are not.
         * 
         * @return <b>true</b> if this {@link BlockType} is burnable; <b>false</b> otherwise. */
        public boolean isBurnable() {
            return blockMC.getMaterial().getCanBurn();
        }

        /** This method determines whether or not a block of this {@link BlockType} is a "full cube", meaning that its {@link BoundingBox} takes the whole space of a cube.
         * 
         * @return <b>true</b> if this block is a normally rendered cube. */
        public boolean isFullCube() {
            return blockMC.getBlockBoundsMinX() == 0 && blockMC.getBlockBoundsMinY() == 0 && blockMC.getBlockBoundsMinZ() == 0 && blockMC.getBlockBoundsMaxX() == 1
                    && blockMC.getBlockBoundsMaxY() == 1 && blockMC.getBlockBoundsMaxZ() == 1;
        }

        /** This method determines whether or not a block of this {@link BlockType} is able to be pushed by a piston (or pulled by a sticky piston). For example,
         * {@link BlockType#BEDROCK bedrock} cannot be pushed by pistons while {@link BlockType#STONE stone} blocks can be pushed easily and {@link BlockType#TORCH torches} can
         * be pushed, but will break and not push the block after them.
         * 
         * @return <b>true</b> if this {@link BlockType} cannot be pushed or pulled by pistons; <b>false</b> otherwise. */
        public boolean isImmobile() {
            return blockMC.getMaterial().getMaterialMobility() == 2;
        }

        /** This method determines whether or not a block of this {@link BlockType} is liquid. So far (as of 1.8), {@link BlockType#WATER water},
         * {@link BlockType#STATIONARY_WATER stationary water}, {@link BlockType#LAVA lava}, and {@link BlockType#STATIONARY_LAVA stationary lava} are the only liquid blocks in
         * Minecraft.
         * 
         * @return <b>true</b> if this {@link BlockType} is a liquid like water or lava; <b>false</b> otherwise. */
        public boolean isLiquid() {
            return blockMC.getMaterial().isLiquid();
        }

        /** This method determines whether or not a block is able to be pushed by a piston (or pulled by a sticky piston) without being broken. For example,
         * {@link BlockType#STONE stone} blocks are easily pushed without breaking while {@link BlockType#TORCH torches} can be pushed, but break off when they are pushed, and
         * {@link BlockType#BEDROCK bedrock} cannot be pushed at all.
         * 
         * @return <b>true</b> if this {@link BlockType} can be pushed without breaking; <b>false</b> otherwise.
         * @see {@link #isBrokenWhenPushed()} and {@link #isImmobile()} */
        public boolean isPushable() {
            return blockMC.getMaterial().getMaterialMobility() == 0;
        }

        /** This method determines whether or not a block of this {@link BlockType} is "replaceable", meaning a new block can be placed where this block is without breaking
         * this block first. This would return <b>true</b> for things like {@link BlockType#TALL_GRASS tall grass} and {@link BlockType#WATER water}, but not for things like
         * {@link BlockType#STONE stone} and {@link BlockType#GRASS grass blocks}.
         * 
         * @return <b>true</b> if this {@link BlockType} can be replaced without being broken first; <b>false</b> otherwise. */
        public boolean isReplaceable() {
            return blockMC.getMaterial().isReplaceable();
        }

        /** This method determines whether or not a block of this {@link BlockType} is solid. Solid blocks are blocks that you can't walk through; {@link BlockType#GRASS grass
         * blocks}, {@link BlockType#STONE stone}, and {@link BlockType#ANVIL anvils} are solid, but {@link BlockType#TALL_GRASS tall grass}, {@link BlockType#SIGN_POST signs},
         * and {@link BlockType#WATER water} are not.
         * 
         * @return <b>true</b> if this {@link BlockType} is solid; <b>false</b> otherwise. */
        public boolean isSolid() {
            return blockMC.getMaterial().isSolid();
        }

        /** This method determines whether or not a block of this {@link BlockType} requires a tool to be used to be able to obtain any drops from it. For example,
         * {@link BlockType#STONE stone} blocks will not drop anything unless a tool (in this case, a pickaxe) is used to break it; on the other hand, {@link BlockType#DIRT
         * dirt} can drop dirt even if a player uses their bare hands to dig it out.
         * 
         * @return <b>true</b> if this {@link BlockType} will not drop anything if broken without a tool; <b>false</b> otherwise. */
        public boolean requiresTool() {
            return !blockMC.getMaterial().isToolNotRequired();
        }

        // overridden properties
        @Override
        public short getData() {
            return data;
        }

        public short getID() {
            return (short) (id_minus_128 + 128);
        }

        @Override
        public byte getMaxStackSize() {
            // ALL blocks stack to 64; it's items that can vary
            return (byte) 64;
        }

        public BlockType[] getSiblings() {
            // since BlockTypes are organized by I.D. and data value, all BlockTypes with the same I.D.s will be together
            // first, find the first and last BlockTypes with this same I.D.
            int first_sibling_ordinal = ordinal(), last_sibling_ordinal_plus1 = ordinal() + 1;
            while (first_sibling_ordinal > 0 && BlockType.values()[first_sibling_ordinal - 1].id_minus_128 == id_minus_128)
                first_sibling_ordinal--;
            while (last_sibling_ordinal_plus1 <= BlockType.values().length && BlockType.values()[last_sibling_ordinal_plus1].id_minus_128 == id_minus_128)
                last_sibling_ordinal_plus1++;

            // create an array of the appropriate size
            BlockType[] results = new BlockType[last_sibling_ordinal_plus1 - first_sibling_ordinal];

            /* fill in the array with the contents between first_sibling_ordinal and lasT_sibling_ordinal_plus1 (including the BlockType at ordinal first_sibling_ordinal, but
             * NOT including the BlockType at ordinal last_sibling_ordinal_plus1) */
            for (int i = first_sibling_ordinal; i < last_sibling_ordinal_plus1; i++)
                results[i] = BlockType.values()[i];

            return results;
        }

        public boolean isASiblingOf(BlockType type) {
            return type.id_minus_128 == id_minus_128;
        }

        // static utilities
        /** This method retrieves the {@link BlockType} with the given item I.D. value.
         * 
         * @param id
         *            is the item I.D. of the {@link BlockType} you wish to locate.
         * @return the {@link BlockType} with the lowest data value that matches the given item I.D. (This item will almost certainly have a data value of 0 or -1.) */
        public static BlockType getByID(int id) {
            return getByID(id, -1);
        }

        /** This method retrieves the {@link BlockType} with the given item I.D. and data values.
         * 
         * @param id
         *            is the item I.D. of the {@link BlockType} you wish to locate.
         * @param data
         *            is the data value for the item you wish to locate. A negative value is considered a "wild card", meaning that is will consider the given data value
         *            irrelevant and match the item with the given I.D. and the lowest available data value (almost always 0 or -1).
         * @return the {@link BlockType} that matches the given item I.D. and data value or <b>null</b> if no {@link BiomeType} has the given I.D. and data value. */
        public static BlockType getByID(int id, int data) {
            // TODO: replace this linear search with a binary search algorithm
            if (id >= 0) {
                short id_minus_128 = (short) (id - 128);

                for (BlockType item_type : values())
                    if (item_type.id_minus_128 == id_minus_128 && (data < 0 || item_type.data == data))
                        return item_type;
            }
            return null;
        }

        public net.minecraft.block.Block getBlockMC() {
            return this.blockMC;
        }

        // data management overrides
        /** This method returns the name of this {@link BlockType} formatted nicely for messages. This formatting includes lowercasing, replacing underscores with spaces, and
         * capitalizing the first letters of certain {@link BlockType}s. */
        @Override
        public String toString() {
            return super.toString().toLowerCase().replaceAll("_", " ");
        }

        @Override
        public Object[] getSortPriorities() {
            return new Object[] { (short) (id_minus_128 + 128), data };
        }
    }
}
