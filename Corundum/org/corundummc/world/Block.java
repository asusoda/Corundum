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

import net.minecraft.block.BlockButton;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;

import org.corundummc.utils.types.Typed;
import org.corundummc.biomes.Biome;
import org.corundummc.exceptions.CorundumException;
import org.corundummc.hub.CorundumThread;
import org.corundummc.utils.interfaces.MCEquivalent;
import org.corundummc.utils.types.HoldableType;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;

public abstract class Block<S extends Block<S, MC, T>, MC extends net.minecraft.block.Block, T extends Block.BlockType<T, MC, S>> extends Typed<T> {
    private Location location;

    protected Block(Location location) {
        this.location = location;

        /* TODO: if the Block at this location isn't the same type as this block, make it so */
    }

    /** This enum is used to represent the different types of {@link Block}s. This list of different types not only includes those types of blocks differentiated by different
     * I.D.s, but also many of those differentiated by different data values; for example, all different colors of wool blocks are listed individually despite the fact that
     * they all have the same I.D.
     * 
     * @param <S>
     *            is a self-parameterization; this type should be the same type as this class.
     * @param <MC>
     *            determines the type of Minecraft Block <tt>Object</tt> that this class represents.
     * @param <I>
     *            determines the type of {@link Block} that represents the type of this class. */
    public abstract static class BlockType<S extends BlockType<S, MC, I>, MC extends net.minecraft.block.Block, I extends Block<I, MC, S>> extends HoldableType<S> implements
            MCEquivalent<MC> {

        /* public static final BlockType AIR = new BlockType(0, -1), // stone types STONE = new BlockType(0), GRANITE = new BlockType(), POLISHED_GRANITE = new BlockType(),
         * DIORITE = new BlockType(), POLISHED_DIORITE = new BlockType(), ANDESITE = new BlockType(), POLISHED_ANDESITE = new BlockType(), // END stone types GRASS = new
         * BlockType(-1), // dirt types DIRT = new BlockType(0), COARSE_DIRT = new BlockType(), PODZOL = new BlockType(), // END dirt types COBBLESTONE = new BlockType(-1), //
         * planks OAK_WOOD_PLANKS = new BlockType(0), SPRUCE_WOOD_PLANKS = new BlockType(), BIRCH_WOOD_PLANKS = new BlockType(), JUNGLE_WOOD_PLANKS = new BlockType(),
         * ACACIA_WOOD_PLANKS = new BlockType(), DARK_OAK_WOOD_PLANKS = new BlockType(), // END planks // saplings OAK_SAPLING = new BlockType(0), SPRUCE_SAPLING = new
         * BlockType(), BIRCH_SAPLING = new BlockType(), JUNGLE_SAPLING = new BlockType(), ACACIA_SAPLING = new BlockType(), DARK_OAK_SAPLING = new BlockType(), // END
         * saplings BEDROCK = new BlockType(-1), FLOWING_WATER = new BlockType(), STILL_WATER = new BlockType(), FLOWING_LAVA = new BlockType(), STILL_LAVA = new BlockType(),
         * // sand types SAND = new BlockType(0), RED_SAND = new BlockType(12), // END sand types GRAVEL = new BlockType(-1), GOLD_ORE = new BlockType(), IRON_ORE = new
         * BlockType(), COAL_ORE = new BlockType(), // logs OAK_LOG = new BlockType(0), SPRUCE_LOG = new BlockType(), BIRCH_LOG = new BlockType(), JUNGLE_LOG = new
         * BlockType(), // END logs // leaves OAK_LEAVES = new BlockType(0), SPRUCE_LEAVES = new BlockType(), BIRCH_LEAVES = new BlockType(), JUNGLE_LEAVES = new BlockType(),
         * // END leaves // sponges SPONGE = new BlockType(0), WET_SPONGE = new BlockType(19), // END sponges GLASS = new BlockType(-1), LAPIS_LAZULI_ORE = new BlockType(),
         * LAPIS_LAZULI_BLOCK = new BlockType(), DISPENSER = new BlockType(), // sandstone types SANDSTONE = new BlockType(0), CHISELED_SANDSTONE = new BlockType(),
         * SMOOTH_SANDSTONE = new BlockType(), // END sandstone types NOTE_BLOCK = new BlockType(-1), BED = new BlockType(), POWEREED_RAIL = new BlockType(), DETECTOR_RAIL =
         * new BlockType(), STICKY_PISTON = new BlockType(), COBWEB = new BlockType(), // shrubbery DEAD_SHRUB = new BlockType(0), TALL_GRASS = new BlockType(), FERN = new
         * BlockType(), // END shrubbery // TODO: is there a difference between this DEAD_BUSH (I.D. 31) and the DEAD_SHRUB (I.D. 31:0)? DEAD_BUSH = new BlockType(-1), PISTON
         * = new BlockType(), PISTON_ARM = new BlockType(), // wool colors WHITE_WOOL = new BlockType(0), ORANGE_WOOL = new BlockType(), MAGENTA_WOOL = new BlockType(),
         * LIGHT_BLUE_WOOL = new BlockType(), YELLOW_WOOL = new BlockType(), LIGHT_GREEN_WOOL = new BlockType(), PINK_WOOL = new BlockType(), DARK_GRAY_WOOL = new BlockType(),
         * LIGHT_GRAY_WOOL = new BlockType(), CYAN_WOOL = new BlockType(), PURPLE_WOOL = new BlockType(), DARK_BLUE_WOOL = new BlockType(), BROWN_WOOL = new BlockType(),
         * DARK_GREEN_WOOL = new BlockType(), RED_WOOL = new BlockType(), BLACK_WOOL = new BlockType(), // END wool colors DANDELION = new BlockType(37, 0), // yes, Minecraft
         * I.D.s skip 36; I don't know why // small flowers POPPY = new BlockType(0), BLUE_ORCHID = new BlockType(), ALLIUM = new BlockType(), AZURE_BLUET = new BlockType(),
         * RED_TULIP = new BlockType(), ORANGE_TULIP = new BlockType(), WHITE_TULIP = new BlockType(), PINK_TULIP = new BlockType(), OXEYE_DAISY = new BlockType(), // END
         * small flowers BROWN_MUSHROOM = new BlockType(-1), RED_MUSHROOM = new BlockType(), GOLD_BLOCK = new BlockType(), IRON_BLOCK = new BlockType(), // double slabs
         * DOUBLE_STONE_SLAB = new BlockType(0), DOUBLE_SANDSTONE_SLAB = new BlockType(), DOUBLE_OAK_SLAB = new BlockType(), DOUBLE_COBBLESTONE_SLAB = new BlockType(),
         * DOUBLE_BRICK_SLAB = new BlockType(), DOUBLE_STONE_BRICK_SLAB = new BlockType(), DOUBLE_NETHER_BRICK_SLAB = new BlockType(), DOUBLE_SQUARTZ_SLAB = new BlockType(),
         * // END double slabs // slabs STONE_SLAB = new BlockType(0), SANDSTONE_SLAB = new BlockType(), OAK_SLAB = new BlockType(), COBBLESTONE_SLAB = new BlockType(),
         * BRICK_SLAB = new BlockType(), STONE_BRICK_SLAB = new BlockType(), NETHER_BRICK_SLAB = new BlockType(), QUARTZ_SLAB = new BlockType(), // END slabs BRICKS = new
         * BlockType(-1), TNT = new BlockType(), BOOKSHELF = new BlockType(), MOSS_STONE = new BlockType(), OBSIDIAN = new BlockType(), TORCH = new BlockType(), FIRE = new
         * BlockType(), MONSTER_SPAWNER = new BlockType(), OAK_STAIRS = new BlockType(), CHEST = new BlockType(), REDSTONE_WIRE = new BlockType(), DIAMOND_ORE = new
         * BlockType(), DIAMOND_BLOCK = new BlockType(), CRAFTING_TABLE = new BlockType(), WHEAT = new BlockType(), FARMLAND = new BlockType(), FURNACE = new BlockType(),
         * BURNING_FURNACE = new BlockType(), SIGN_POST = new BlockType(), OAK_DOOR = new BlockType(), LADDER = new BlockType(), RAIL = new BlockType(), COBBLESTONE_STAIRS =
         * new BlockType(), WALL_SIGN = new BlockType(), LEVER = new BlockType(), STONE_PRESSURE_PLATE = new BlockType(), IRON_DOOR = new BlockType(), WOODEN_PRESSURE_PLATE =
         * new BlockType(), REDSTONE_ORE = new BlockType(), GLOWING_REDSTONE_ORE = new BlockType(), UNPOWERED_REDSTONE_TORCH = new BlockType(), POWERED_REDSTONE_TORCH = new
         * BlockType(), STONE_BUTTON = new BlockType(), SNOW = new BlockType(), ICE = new BlockType(), SNOW_BLOCK = new BlockType(), CACTUS = new BlockType(), CLAY = new
         * BlockType(), SUGAR_CANES = new BlockType(), JUKEBOX = new BlockType(), OAK_FENCE = new BlockType(), PUMPKIN = new BlockType(), NETHERRACK = new BlockType(),
         * SOUL_SAND = new BlockType(), GLOWSTONE = new BlockType(), NETHER_PORTAL = new BlockType(), JACK_O_LANTERN = new BlockType(), CAKE = new BlockType(),
         * UNPOWERED_REDSTONE_REPEATER = new BlockType(), POWERED_REDSTONE_REPEATER = new BlockType(), // stained glass WHITE_STAINED_GLASS = new BlockType(0),
         * ORANGE_STAINED_GLASS = new BlockType(), MAGENTA_STAINED_GLASS = new BlockType(), LIGHT_BLUE_STAINED_GLASS = new BlockType(), YELLOW_STAINED_GLASS = new BlockType(),
         * LIGHT_GREEN_STAINED_GLASS = new BlockType(), PINK_STAINED_GLASS = new BlockType(), DARK_GRAY_STAINED_GLASS = new BlockType(), LIGHT_GRAY_STAINED_GLASS = new
         * BlockType(), CYAN_STAINED_GLASS = new BlockType(), PURPLE_STAINED_GLASS = new BlockType(), DARK_BLUE_STAINED_GLASS = new BlockType(), BROWN_STAINED_GLASS = new
         * BlockType(), DARK_GREEN_STAINED_GLASS = new BlockType(), RED_STAINED_GLASS = new BlockType(), BLACK_STAINED_GLASS = new BlockType(), // END stained glass
         * WOODEN_TRAPDOOR = new BlockType(-1), // monster eggs STONE_MONSTER_EGG = new BlockType(0), COBBLESTONE_MONSTER_EGG = new BlockType(), STONE_BRICK_MONSTER_EGG = new
         * BlockType(), MOSSY_STONE_BRICK_MONSTER_EGG = new BlockType(), CRACKED_STONE_BRICK_MONSTER_EGG = new BlockType(), CHISELED_STONE_BRICK_MONSTER_EGG = new BlockType(),
         * // END monster eggs // stone bricks STONE_BRICKS = new BlockType(0), MOSSY_STONE_BRICKS = new BlockType(), CRACKED_STONE_BRICKS = new BlockType(),
         * CHISELED_STONE_BRICKS = new BlockType(), // END stone bricks GIANT_RED_MUSHROOM = new BlockType(-1), GIANT_BROWN_MUSHROOM = new BlockType(), IRON_BARS = new
         * BlockType(), GLASS_PANE = new BlockType(), MELON = new BlockType(), PUMPKIN_STEM = new BlockType(), MELON_STEM = new BlockType(), VINES = new BlockType(),
         * OAK_FENCE_GATE = new BlockType(), BRICK_STAIRS = new BlockType(), STONE_BRICK_STAIRS = new BlockType(), MYCELIUM = new BlockType(), LILY_PAD = new BlockType(),
         * NETHER_BRICK = new BlockType(), NETHER_BRICK_FENCE = new BlockType(), NETHER_BRICK_STAIRS = new BlockType(), NETHER_WARTS = new BlockType(), ENCHANTMENT_TABLE = new
         * BlockType(), BREWING_STAND = new BlockType(), CAULDRON = new BlockType(), END_PORTAL = new BlockType(), END_PORTAL_FRAME = new BlockType(), END_STONE = new
         * BlockType(), DRAGON_EGG = new BlockType(), UNPOWERED_REDSTONE_LAMP = new BlockType(), POWERED_REDSTONE_LAMP = new BlockType(), // double wood slabs
         * DOUBLE_OAK_WOOD_SLAB = new BlockType(0), DOUBLE_SPRUCE_WOOD_SLAB = new BlockType(), DOUBLE_BIRCH_WOOD_SLAB = new BlockType(), DOUBLE_JUNGLE_WOOD_SLAB = new
         * BlockType(), DOUBLE_ACACIA_WOOD_SLAB = new BlockType(), DOUBLE_DARK_OAK_WOOD_SLAB = new BlockType(), // END double wood slabs // wood slabs OAK_WOOD_SLAB = new
         * BlockType(0), SPRUCE_WOOD_SLAB = new BlockType(), BIRCH_WOOD_SLAB = new BlockType(), JUNGLE_WOOD_SLAB = new BlockType(), ACACIA_WOOD_SLAB = new BlockType(),
         * DARK_OAK_WOOD_SLAB = new BlockType(), // END wood slabs COCOA = new BlockType(-1), SANDSTONE_STAIRS = new BlockType(), EMERALD_ORE = new BlockType(), ENDER_CHEST =
         * new BlockType(), TRIPWIRE_HOOK = new BlockType(), TRIPWIRE = new BlockType(), EMERALD_BLOCK = new BlockType(), SPRUCE_WOOD_STAIRS = new BlockType(),
         * BIRCH_WOOD_STAIRS = new BlockType(), JUNGLE_WOOD_STAIRS = new BlockType(), COMMAND_BLOCK = new BlockType(), BEACON = new BlockType(), // cobblestone walls
         * COBBLESTONE_WALL = new BlockType(0), MOSSY_COBBLESTONE_WALLS = new BlockType(1), // END cobblestone walls FLOWER_POT = new BlockType(-1), CARROTS = new BlockType(),
         * POTATOES = new BlockType(), WOODEN_BUTTON = new BlockType(), MOB_HEAD = new BlockType(), ANVIL = new BlockType(), TRAPPED_CHEST = new BlockType(),
         * GOLD_PRESSURE_PLATE = new BlockType(), IRON_PRESSURE_PLATE = new BlockType(), UNPOWERED_REDSTONE_COMPARATOR = new BlockType(), POWERED_REDSTONE_COMPARATOR = new
         * BlockType(), DAYLIGHT_SENSOR = new BlockType(), REDSTONE_BLOCK = new BlockType(), QUARTZ_ORE = new BlockType(), HOPPER = new BlockType(), // quartz QUARTZ_BLOCK =
         * new BlockType(0), CHISELED_QUARTZ_BLOCK = new BlockType(), PILLAR_QUARTZ_BLOCK = new BlockType(), // END quartz QUARTZ_STAIRS = new BlockType(-1), ACTIVATOR_RAIL =
         * new BlockType(), DROPPER = new BlockType(), // stained clay WHITE_STAINED_CLAY = new BlockType(0), ORANGE_STAINED_CLAY = new BlockType(), MAGENTA_STAINED_CLAY = new
         * BlockType(), LIGHT_BLUE_STAINED_CLAY = new BlockType(), YELLOW_STAINED_CLAY = new BlockType(), LIGHT_GREEN_STAINED_CLAY = new BlockType(), PINK_STAINED_CLAY = new
         * BlockType(), DARK_GRAY_STAINED_CLAY = new BlockType(), LIGHT_GRAY_STAINED_CLAY = new BlockType(), CYAN_STAINED_CLAY = new BlockType(), PURPLE_STAINED_CLAY = new
         * BlockType(), DARK_BLUE_STAINED_CLAY = new BlockType(), BROWN_STAINED_CLAY = new BlockType(), DARK_GREEN_STAINED_CLAY = new BlockType(), RED_STAINED_CLAY = new
         * BlockType(), BLACK_STAINED_CLAY = new BlockType(), // END stained clay // stained glass panes WHITE_STAINED_GLASS_PANE = new BlockType(0), ORANGE_STAINED_GLASS_PANE
         * = new BlockType(), MAGENTA_STAINED_GLASS_PANE = new BlockType(), LIGHT_BLUE_STAINED_GLASS_PANE = new BlockType(), YELLOW_STAINED_GLASS_PANE = new BlockType(),
         * LIGHT_GREEN_STAINED_GLASS_PANE = new BlockType(), PINK_STAINED_GLASS_PANE = new BlockType(), DARK_GRAY_STAINED_GLASS_PANE = new BlockType(),
         * LIGHT_GRAY_STAINED_GLASS_PANE = new BlockType(), CYAN_STAINED_GLASS_PANE = new BlockType(), PURPLE_STAINED_GLASS_PANE = new BlockType(),
         * DARK_BLUE_STAINED_GLASS_PANE = new BlockType(), BROWN_STAINED_GLASS_PANE = new BlockType(), GREEN_STAINED_GLASS_PANE = new BlockType(), RED_STAINED_GLASS_PANE = new
         * BlockType(), BLACK_STAINED_GLASS_PANE = new BlockType(), // END stained glass panes // more leaves ACACIA_LEAVES = new BlockType(0), DARK_OAK_LEAVES = new
         * BlockType(), // END more leaves // more logs ACACIA_LOG = new BlockType(0), DARK_OAK_LOG = new BlockType(), // END more logs SLIME_BLOCK = new BlockType(-1),
         * BARRIER = new BlockType(), IRON_TRAPDOOR = new BlockType(), // prismarine types PRISMARINE = new BlockType(0), PRISMARINE_BRICKS = new BlockType(), DARK_PRISMARINE
         * = new BlockType(), // END prismarine types SEA_LENTERN = new BlockType(-1), HAY_BALE = new BlockType(), // carpet WHITE_CARPET = new BlockType(0), ORANGE_CARPET =
         * new BlockType(), MAGENTA_CARPET = new BlockType(), LIGHT_BLUE_CARPET = new BlockType(), YELLOW_CARPET = new BlockType(), LIGHT_GREEN_CARPET = new BlockType(),
         * PINK_CARPET = new BlockType(), DARK_GRAY_CARPET = new BlockType(), LIGHT_GRAY_CARPET = new BlockType(), CYAN_CARPET = new BlockType(), PURPLE_CARPET = new
         * BlockType(), DARK_BLUE_CARPET = new BlockType(), BROWN_CARPET = new BlockType(), DARK_GREEN_CARPET = new BlockType(), RED_CARPET = new BlockType(), BLACK_CARPET =
         * new BlockType(), // END carpet HARDENED_CLAY = new BlockType(-1), COAL_BLOCK = new BlockType(), PACKED_ICE = new BlockType(), // large plants SUNFLOWER = new
         * BlockType(0), LILAC = new BlockType(), DOUBLE_TALL_GRASS = new BlockType(), LARGE_FERN = new BlockType(), ROSE_BUSH = new BlockType(), PEONY = new BlockType(), //
         * END large plants BANNER_POST = new BlockType(-1), WALL_BANNER = new BlockType(), INVERTED_DAYLIGHT_SENSOR = new BlockType(), // red sandstone RED_SANDSTONE = new
         * BlockType(0), SMOOTH_RED_SANDSTONE = new BlockType(), CHISELED_RED_SANDSTONE = new BlockType(), // END red sandstone RED_SANDSTONE_STAIRS = new BlockType(),
         * DOUBLE_RED_SANDSTONE_SLAB = new BlockType(), RED_SANDSTONE_SLAB = new BlockType(), SPRUCE_FENCE_GATE = new BlockType(), BIRCH_FENCE_GATE = new BlockType(),
         * JUNGLE_FENCE_GATE = new BlockType(), DARK_OAK_FENCE_GATE = new BlockType(), ACACIA_FENCE_GATE = new BlockType(), SPRUCE_FENCE = new BlockType(), BIRCH_FENCE = new
         * BlockType(), JUNGLE_FENCE = new BlockType(), DARK_OAK_FENCE = new BlockType(), ACACIA_FENCE = new BlockType(), SPRUCE_DOOR = new BlockType(), BIRCH_DOOR = new
         * BlockType(), JUNGLE_DOOR = new BlockType(), ACACIA_DOOR = new BlockType(), DARK_OAK_DOOR = new BlockType(); */

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
        private final MC blockMC;

        // constructors
        // TODO TEMP
        /** This constructor makes a BlockType based on the previous value's I.D. and data. If the previous value has no strictly associated data value (data value = -1), it
         * means that it has no sub-types (e.g. the different colors of wool or types of wood), so use the next I.D.; if it has a data value, give this BlockType the same I.D.
         * and the next data value. Essentially, "I.D. blocks" (blocks of multiple enum constants that all have the same I.D., but different data values) are delimited by the
         * use of the {@link #BlockType(int)} and {@link #BlockType(int, int)} constructors; declaring a new enum value with a data value less than the previous will end a
         * block and declaring one with a data value >= 0 will start a new block. */
        private BlockType() {
            super(nextID(BlockType.class), -1);

            // find the Block with the given I.D.
            blockMC = (MC) net.minecraft.block.Block.getBlockById(getID());
        }

        // TODO TEMP
        /** This constructor makes a BlockType based on the previous value's I.D. and the given data. If the previous value's data value is <= <b><tt>data</b></tt>, then the
         * I.D. block is ending, so it increments the I.D.; otherwise, it will use the same I.D. as the previous value to continue the I.D. block. Essentially, "I.D. blocks"
         * (blocks of multiple enum constants that all have the same I.D., but different data values) are delimited by the use of the {@link #BlockType(int)} and
         * {@link #BlockType(int, int)} constructors; declaring a new enum value with a data value less than the previous will end a block and declaring one with a data value
         * >= 0 will start a new block.
         * 
         * @param data
         *            is the data value for this {@link BlockType}. */
        private BlockType(int data) {
            super(nextID(BlockType.class), data);

            // find the Block with the given I.D.
            blockMC = (MC) net.minecraft.block.Block.getBlockById(getID());
        }

        /** This constructor makes a BlockType with the given I.D. and data. It's necessary for specifying I.D.s when Minecraft skips I.D.s.
         * 
         * @param id
         *            is the block I.D. that this {@link BlockType} is associated with.
         * @param data
         *            is the data value associated with this {@link BlockType}.
         * @see {@link #BlockType(int)} */
        private BlockType(int id, int data) {
            super(id, data);

            // find the Block with the given I.D.
            blockMC = (MC) net.minecraft.block.Block.getBlockById(id);
        }

        // overridden utilities
        @Override
        public byte getMaxStackSize() {
            // ALL blocks stack to 64; it's items that can vary
            return (byte) 64;
        }

        public String getName() {
            return blockMC.getLocalizedName();
        }

        public MC MC() {
            return blockMC;
        }

        // abstract utilities
        public abstract I fromLocation(Location location);

        // utilities
        /** This method determines whether or not a block of this {@link BlockType} can stop grass from spreading onto nearby dirt if placed on top of the dirt. For example, if
         * you place {@link BlockType#STONE stone} on top of dirt, that dirt cannot grow grass even if there is well-lit grass right next to it; on the other hand,
         * {@link BlockType#AIR air} cannot stop grass from spreading onto dirt.
         * 
         * @return <b>true</b> if this {@link BlockType} can stop the spread of grass to dirt; <b>false</b> otherwise. */
        public boolean canBlockGrass() {
            return blockMC.getMaterial().blocksLight();
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
         * {@link BlockType#OAK_LEAVES leaves}, {@link BlockType#OAK_WOOD_PLANKS wooden planks}, and {@link BlockType#OAK_LOG logs} are all burnable, but
         * {@link BlockType#FLOWING_WATER water}, {@link BlockType#DIRT dirt}, and {@link BlockType#STONE stone} are not.
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

        /** This method determines whether or not a block of this {@link BlockType} is liquid. So far (as of 1.8), {@link BlockType#FLOWING_WATER water},
         * {@link BlockType#STILL_WATER stationary water}, {@link BlockType#FLOWING_LAVA lava}, and {@link BlockType#STILL_LAVA stationary lava} are the only liquid blocks in
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
         * this block first. This would return <b>true</b> for things like {@link BlockType#TALL_GRASS tall grass} and {@link BlockType#FLOWING_WATER water}, but not for things
         * like {@link BlockType#STONE stone} and {@link BlockType#GRASS grass blocks}.
         * 
         * @return <b>true</b> if this {@link BlockType} can be replaced without being broken first; <b>false</b> otherwise. */
        public boolean isReplaceable() {
            return blockMC.getMaterial().isReplaceable();
        }

        /** This method determines whether or not a block of this {@link BlockType} is solid. Solid blocks are blocks that you can't walk through; {@link BlockType#GRASS grass
         * blocks}, {@link BlockType#STONE stone}, and {@link BlockType#ANVIL anvils} are solid, but {@link BlockType#TALL_GRASS tall grass}, {@link BlockType#SIGN_POST signs},
         * and {@link BlockType#FLOWING_WATER water} are not.
         * 
         * @return <b>true</b> if this {@link BlockType} is solid; <b>false</b> otherwise. */
        public boolean isSolid() {
            return blockMC.getMaterial().isSolid();
        }

        // TODO: link "pickaxe" in the Javadoc below and re-link stone, dirt, and grass blocks (x2 for grass blocks)
        /** This method determines whether or not a block of this {@link BlockType} requires a tool to be used to be able to obtain any drops from it. For example, stone blocks
         * will not drop anything unless a tool (in this case, a pickaxe) is used to break it; on the other hand, {@link BlockType#DIRT dirt} can drop dirt even if a player
         * uses their bare hands to dig it out. Note that this method only determines whether or not <i>any</i> drops can be obtained without the use of a tool; for example,
         * this will return <b>true</b> for grass blocks even though without the use of a tool, they will drop dirt blocks rather grass blocks.
         * 
         * @return <b>true</b> if this {@link BlockType} will not drop anything if broken without a tool; <b>false</b> otherwise. */
        public boolean requiresTool() {
            return !blockMC.getMaterial().isToolNotRequired();
        }

        // pseudo-enum utilities
        public static BlockType getByID(int id) {
            return getByID(BlockType.class, id);
        }

        public static BlockType getByID(int id, int data) {
            return getByID(BlockType.class, id, data);
        }

        public IBlockState toBlockState() {
            return net.minecraft.block.Block.getBlockById(getID()).getStateFromMeta(getData());
        }

        public static BlockType[] values() {
            return values(BlockType.class);
        }
    }

    // static utilities
    public static Block<?, ?, ?> fromLocation(Location location) {
        return BlockType.getByID(net.minecraft.block.Block.getIdFromBlock(location.getWorld().MC().getBlockState(location.toBlockPos()).getBlock()),
                location.getWorld().MC().getBlockState(location.toBlockPos()).getBlock().getMetaFromState(location.getWorld().MC().getBlockState(location.toBlockPos())))
                .fromLocation(location);
    }

    // instance utilities
    /** This method retrieves the {@link Biome} that this block is located in.
     * 
     * @return the {@link Biome} that this block is in. */
    public Biome getBiome() {
        return getLocation().getBiome();
    }

    public Chunk getChunk() {
        return new Chunk(getLocation().getWorld().MC().getChunkFromChunkCoords(this.location.getBlockX(), this.location.getBlockZ()));
    }

    public byte getData() {
        return (byte) location.getWorld().MC().getBlockState(location.toBlockPos()).getBlock().getMetaFromState(location.getWorld().MC().getBlockState(location.toBlockPos()));
    }

    public Location getLocation() {
        return location;
    }

    /** This method changes the type of this {@link Block} to the given {@link BlockType}.
     * 
     * @param type
     *            is the {@link BlockType} which this {@link Block} will be changed to.
     * 
     * @return the new {@link Block} with the given {@link BlockType} or <b>null</b> if this {@link Block} could not be changed to the given {@link BlockType}. <br>
     *         <i>Note that the old {@link Block} is now obsolete and should not be used any more! Replace it with the {@link Block} returned by this method.</i> */
    @SuppressWarnings({ "unchecked", "hiding" })
    public <S extends Block<S, MC, T>, MC extends net.minecraft.block.Block, T extends BlockType<T, MC, S>> Block<S, MC, T> setType(BlockType<T, MC, S> type) {
        boolean result = location.getWorld().setBlock(this, type);

        if (result)
            return (Block<S, MC, T>) Block.fromLocation(location);
        else
            return null;
    }

    // type utilities
    public boolean isLiquid() {
        return getType().isLiquid();
    }

    public boolean isSolid() {
        return getType().isSolid();
    }
}
