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

import java.awt.Color;

public class Block {
    private BlockType type;
    private byte data;
    private Location location;

    /** This enum is used to represent the different types of {@link Block}s. This list of different types not only includes those types of blocks differentiated by different
     * I.D.s, but also many of those differentiated by different data values; for example, all different colors of wool blocks are listed individually despite the fact that
     * they all have the same I.D.
     * <hr>
     * The data used to construct this list can be found on <a href=http://minecraft-ids.grahamedgecombe.com/>this site</a>. All names listed in this site for each type of
     * block were used to name these {@link BlockType}s with some exceptions: <br>
     * <ul>
     * <li>wood blocks were renamed "LOG" to clarify the distinction between logs and planks</li>
     * <li>the dead shrub with I.D. 32 was renamed to "DEAD_BUSH" to distinguish between it and the dead shrub with I.D. 31 and data value 0</li>
     * <li>the grass plant was renamed "TALL_GRASS" for two reasons:<br>
     * <ul>
     * <li>Minecraft itself names it this way</li>
     * <li>the grass plant block needs to be differentiated from the solid grass blocks</li></li>
     * </ul>
     * <li>the colors "lime" and "green" were renamed to "LIGHT_GREEN" and "DARK_GREEN", respectively, to avoid confusion about whether "green" means light or dark green</li>
     * <li>the colors "gray" and "blue" were renamed to "DARK_GRAY" and "DARK_BLUE", respectively, to differentiate them clearly from the light gray and light blue colors</li>
     * <li>on and off versions of redstone materials such as redstone torches and lamps are prefixed by "POWERED" or "UNPOWERED"</li>
     * <li>the heavy and light weighted pressure plates are renamed to "GOLD" and "IRON" pressure plates, respectively, because more players identify them by their materials
     * than their usage</li>
     * <li>standing signs and posts are named "SIGN_POST" and "BANNER_POST" for the sake of concise clarity</li>
     * <li>the word "block" was dropped off many names since it's redundant in our case since {@link BlockType}s and {@link ItemType}s are separate anyway</li>
     * <li>"mushroom cap" blocks were renamed "GIANT_[color]_MUSHROOM"</li>
     * <li>the word "crops" was dropped off the name "wheat crops"</li>
     * <li>"wall-mounted" banners and signs dropped off the "-mounted" part, leaving "WALL_SIGN" and "WALL_BANNER"</li></ul> */
    public enum BlockType {
        AIR(0, 0),  // air must be initialized with both an I.D. and data value because it has no previous value to get I.D. and data info from!
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
        WHIET_CARPET(0),
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

        /** property-getting methods of <b><tt>blockMC</b></tt>:<br>
         * <i>NOTE:</i> "<tt>Material.</tt>" at the beginning of a method indicates that the property can be obtained through {@link net.minecraft.block.Block#getMaterial()}.
         * booleans: <br>
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
         * - <tt>Material.getMaterialMobility</tt>: 0 for most blocks (e.g. dirt), 1 if it cannot push other blocks (TODO: specifics?), 2 if it cannot be pushed (e.g. bedrock) <br>
         * - <tt>Block.getLightOpacity</tt>: the amount of light lost going through this block; 0 for anvils and enchantment tables, 1 for leaves and webs, 3 for water and
         * ice, 255 otherwise <br>
         * <b>float</b>s: <br>
         * - <tt>Block.getLightValue</tt>: the level of light emitted by this block; 1.0 for End portals, redstone lights, and lava, 0 otherwise<br>
         * - <tt>Block.isOpaqueCube</tt>: returns true only if the block takes the entire space of a block and it's opaque; I believe this to be redundant<br>
         * other <tt>Object</tt>s: <br>
         * - <tt>MapColor Material.getMaterialMapColor</tt></tt>: the color that is represented for this block on the map */
        private final net.minecraft.block.Block blockMC;

        private final byte id_minus_128, data;

        // sub-types are delimited by declaration of data with 0, then declaration of data <= the previous

        /** This constructor makes a BlockType based on the previous value's I.D. and data. If the previous value has no strictly associated data value (data value = -1), it
         * means that it has no sub-types (e.g. the different colors of wool or types of wood), so use the next I.D.; if it has a data value, give this BlockType the same I.D.
         * and the next data value. Essentially, "I.D. blocks" (blocks of multiple enum constants that all have the same I.D., but different data values) are delimited by the
         * use of the {@link #BlockType(int)} and {@link #BlockType(int, int)} constructors; declaring a new enum value with a data value less than the previous will end a
         * block and declaring one with a data value >= 0 will start a new block. */
        private BlockType() {
            BlockType previous_value = BlockType.values()[ordinal() - 1];
            if (previous_value.data == -1) {
                id_minus_128 = (byte) (previous_value.id_minus_128 + 1);
                data = -1;
            } else {
                id_minus_128 = previous_value.id_minus_128;
                data = (byte) (previous_value.data + 1);
            }

            // find the Block in the Minecraft blockRegistry with the given I.D.
            blockMC = (net.minecraft.block.Block) net.minecraft.block.Block.blockRegistry.getObjectForID(id_minus_128 + 128);
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

            // find the Block in the Minecraft blockRegistry with the given I.D.
            blockMC = (net.minecraft.block.Block) net.minecraft.block.Block.blockRegistry.getObjectForID(id_minus_128 + 128);
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

            // find the Block in the Minecraft blockRegistry with the given I.D.
            blockMC = (net.minecraft.block.Block) net.minecraft.block.Block.blockRegistry.getObjectForID(id);
        }

        /** This method returns the data value associated with this {@link BlockType}. The data value is an extra identifier in Minecraft used to differentiate between blocks
         * on a more specific level than I.D.s. If two blocks have the same I.D., but different data values, they could be the same kind of block, but be different sub-types;
         * for example, {@link BlockType#OAK_LOG oak logs} and {@link BlockType#SPRUCE_LOG spruce logs} are both types of wood logs and they both have the same I.D. and a lot
         * of the same properties, but their data values are different: oak logs have a data value of 0 while spruce logs have a data value of 1.
         * <hr>
         * <i>NOTE:</i> Minecraft often uses data values to indicate different things about the block it pertains to, including not just sub-types like what kind of slab it
         * is, but also things like the orientation of a log block (up/down, north/soth, or east/west). Therefore, not all blocks will have the same data values as their
         * {@link BlockType}s would indicate. However, there is a pattern in the way Minecraft handles orientation of blocks using their data values: all orientations of a
         * given block will have the same data value as its {@link BlockType} plus the number of subtypes for that block I.D. times some integer (i.e.
         * <tt>[{@link Block} data] = [{@link BlockType} data] + [number of different subtypes] * [some non-negative integer]</tt>).
         * <hr>
         * You can learn more about which kinds of blocks have which I.D. and data values at <a href=http://minecraft-ids.grahamedgecombe.com/>this site</a>.
         * 
         * @return the data value associated with this {@link BlockType} or -1 if this {@link BlockType} is not associated with a particular data value like
         *         {@link BlockType#GRASS grass blocks}. */
        public byte getData() {
            return data;
        }

        /** This method returns the I.D. that represents this {@link BlockType}. In Minecraft, every type of block is represented by a different I.D. You can see which I.D.s
         * represent which blocks on <a href=http://minecraft-ids.grahamedgecombe.com/>this site</a>.
         * <hr>
         * <i>NOTE:</i> Not all Corundum {@link BlockType}s have different I.D.s; some things, like {@link BlockType#STONE stone} and {@link BlockType#GRANITE granite}, have
         * the same I.D. and are differentiated by their data values (in this case, 0 and 1, respectively).
         * 
         * @return the block I.D. associated with this {@link BlockType}. */
        public short getID() {
            return (short) (id_minus_128 + 128);
        }

        /**This method returns the color that appears on a map to represent this {@link BlockType}. 
         * 
         * @return a standard Java {@link Color} object representing the color that this {@link BlockType} is represented by on a map.*/
        public Color getMapColor() {
            /* Mojang decided that they don't need to use three values for a color, but one big int in which the bits were basically concatenated from the three bytes
             * representing red, green, and blue values; this splits the int into three RGB values and puts it into a standard Color object */
            int color_value = blockMC.getMaterial().getMaterialMapColor().colorValue;
            return new Color(color_value & 0xFF0000, color_value & 0x00FF00, color_value & 0x0000FF);
        }

        /** This method determines whether or not this {@link BlockType} is a "sibling" of the given {@link BlockType}. Corundum considered two {@link BlockType}s "siblings" if
         * the have the same item I.D. like {@link BlockType#STONE stone}, {@link BlockType#GRANITE granite}, and {@link BlockType#DIORITE diorite}, which all have the I.D. 0
         * but have different data values to differentiate between them.
         * <hr>
         * You can see which I.D.s represent which blocks and which blocks have the same I.D. on <a href=http://minecraft-ids.grahamedgecombe.com/>this site</a>.
         * 
         * @param type
         *            is the {@link BlockType} to compare to this {@link BlockType} to see if they're "siblings".
         * @return <b>true</b> if this {@link BlockType} has the same I.D. as <b><tt>type</b></tt>; <b>false</b> otherwise. */
        public boolean isASiblingOf(BlockType type) {
            return type.id_minus_128 == id_minus_128;
        }

        /** This method determines whether or not this {@link BlockType} is able to be burned until broken. Note that most blocks can be lit on fire, but that does not
         * necessarily make them "burnable"; burnable blocks are the blocks that will burn until they break and often spread fire to other nearby blocks. For example,
         * {@link BlockType#LEAVES leaves}, {@link BlockType#OAK_PLANKS wooden planks}, and {@link BlockType#OAK_LOG logs} are all burnable, but {@link BlockType#WATER water},
         * {@link BlockType#DIRT dirt}, and {@link BlockType#STONE stone} are not.
         * 
         * @return <b>true</b> if this {@link BlockType} is burnable; <b>false</b> otherwise. */
        public boolean isBurnable() {
            return blockMC.getMaterial().getCanBurn();
        }

        /** This method determines whether or not a block of this {@link BlockType} is liquid. So far (as of 1.8), {@link BlockType#WATER water},
         * {@link BlockType#STATIONARY_WATER stationary water}, {@link BlockType#LAVA lava}, and {@link BlockType#STATIONARY_LAVA stationary lava} are the only liquid blocks in
         * Minecraft.
         * 
         * @return <b>true</b> if this {@link BlockType} is a liquid like water or lava; <b>false</b> otherwise. */
        public boolean isLiquid() {
            return blockMC.getMaterial().isLiquid();
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
    }
}
