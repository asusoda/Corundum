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

package org.corundummc.items;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.Item.ToolMaterial;
import org.corundummc.CorundumServer;
import org.corundummc.exceptions.CIE;
import org.corundummc.types.HoldableType;
import org.corundummc.utils.myList.myList;
import org.corundummc.world.Block.BlockType;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private final ItemType type;
    private myList<Enchantment> enchantments;

    // TODO private InventorySlot location;

    // constructors
    public Item(ItemType type, Enchantment... enchantments) {
        this.type = type;
        this.enchantments = new myList<Enchantment>(enchantments);
    }

    // type class
    /** This enum is used to represent the different types of {@link Item}s. This list of different types not only includes those types of items differentiated by different
     * I.D.s, but also many of those differentiated by different data values; for example, all different colors of dye are listed individually despite the fact that they all
     * have the same I.D. */
    public static class ItemType extends HoldableType<ItemType> {
        public static final ItemType IRON_SHOVEL = new ItemType(256, -1),
                IRON_PICKAXE = new ItemType(),
                IRON_AXE = new ItemType(),
                FLINT_AND_STEEL = new ItemType(),
                APPLE = new ItemType(),
                BOW = new ItemType(),
                ARROW = new ItemType(),
                // coal types
                COAL = new ItemType(0),
                CHARCOAL = new ItemType(),
                // END coal types
                DIAMOND = new ItemType(-1),
                IRON_INGOT = new ItemType(),
                GOLD_INGOT = new ItemType(),
                IRON_SWORD = new ItemType(),
                WOODEN_SWORD = new ItemType(),
                WOODEN_SHOVEL = new ItemType(),
                WOODEN_PICKAXE = new ItemType(),
                WOODEN_AXE = new ItemType(),
                STONE_SWORD = new ItemType(),
                STONE_SHOVEL = new ItemType(),
                STONE_PICKAXE = new ItemType(),
                STONE_AXE = new ItemType(),
                DIAMOND_SWORD = new ItemType(),
                DIAMOND_SHOVEL = new ItemType(),
                DIAMOND_PICKAXE = new ItemType(),
                DIAMOND_AXE = new ItemType(),
                STICK = new ItemType(),
                BOWL = new ItemType(),
                MUSHROOM_STEW = new ItemType(),
                GOLDEN_SWORD = new ItemType(),
                GOLDEN_SHOVEL = new ItemType(),
                GOLDEN_PICKAXE = new ItemType(),
                GOLDEN_AXE = new ItemType(),
                STRING = new ItemType(),
                FEATHER = new ItemType(),
                GUNPOWDER = new ItemType(),
                WOODEN_HOE = new ItemType(),
                STONE_HOE = new ItemType(),
                IRON_HOE = new ItemType(),
                DIAMOND_HOE = new ItemType(),
                GOLDEN_HOE = new ItemType(),
                SEEDS = new ItemType(),
                WHEAT = new ItemType(),
                BREAD = new ItemType(),
                LEATHER_CAP = new ItemType(),
                LEATHER_TUNIC = new ItemType(),
                LEATHER_PANTS = new ItemType(),
                LEATHER_BOOTS = new ItemType(),
                CHAINMAIL_HELMET = new ItemType(),
                CHAINMAIL_CHESTPLATE = new ItemType(),
                CHAINMAIL_LEGGINGS = new ItemType(),
                CHAINMAIL_BOOTS = new ItemType(),
                IRON_HELMET = new ItemType(),
                IRON_CHESTPLATE = new ItemType(),
                IRON_LEGGINGS = new ItemType(),
                IRON_BOOTS = new ItemType(),
                DIAMOND_HELMET = new ItemType(),
                DIAMOND_CHESTPLATE = new ItemType(),
                DIAMOND_LEGGINGS = new ItemType(),
                DIAMOND_BOOTS = new ItemType(),
                GOLDEN_HELMET = new ItemType(),
                GOLDEN_CHESTPLATE = new ItemType(),
                GOLDEN_LEGGINGS = new ItemType(),
                GOLDEN_BOOTS = new ItemType(),
                FLINT = new ItemType(),
                RAW_PORKCHOP = new ItemType(),
                COOKED_PORKCHOP = new ItemType(),
                PAINTING = new ItemType(),
                // golden apples
                GOLDEN_APPLE = new ItemType(0),
                ENCHANTED_GOLDEN_APPLE = new ItemType(),
                // END golden apples
                SIGN = new ItemType(-1),
                OAK_DOOR = new ItemType(),
                BUCKET = new ItemType(),
                WATER_BUCKET = new ItemType(),
                LAVA_BUCKET = new ItemType(),
                MINECART = new ItemType(),
                SADDLE = new ItemType(),
                IRON_DOOR = new ItemType(),
                REDSTONE_DUST = new ItemType(),
                SNOWBALL = new ItemType(),
                BOAT = new ItemType(),
                LEATHER = new ItemType(),
                MILK_BUCKET = new ItemType(),
                BRICK = new ItemType(),
                CLAY = new ItemType(),
                SUGAR_CANES = new ItemType(),
                PAPER = new ItemType(),
                BOOK = new ItemType(),
                SLIMEBALL = new ItemType(),
                STORAGE_MINECART = new ItemType(),
                POWERED_MINECART = new ItemType(),
                EGG = new ItemType(),
                COMPASS = new ItemType(),
                FISHING_ROD = new ItemType(),
                CLOCK = new ItemType(),
                GLOWSTONE_DUST = new ItemType(),
                // raw fish
                RAW_COD = new ItemType(0),
                RAW_SALMON = new ItemType(),
                CLOWNFISH = new ItemType(),
                PUFFERFISH = new ItemType(),
                // END raw fish
                // cooked fish
                COOKED_FISH = new ItemType(0),
                COOKED_SALMON = new ItemType(),
                // END cooked fish
                // dyes
                INK_SACK = new ItemType(0),
                RED_DYE = new ItemType(),
                DARK_GREEN_DYE = new ItemType(),
                COCOA_BEANS = new ItemType(),
                LAPIS_LAZULI = new ItemType(),
                PURPLE_DYE = new ItemType(),
                CYAN_DYE = new ItemType(),
                LIGHT_GRAY_DYE = new ItemType(),
                DARK_GRAY_DYE = new ItemType(),
                PINK_DYE = new ItemType(),
                LIGHT_GREEN_DYE = new ItemType(),
                YELLOW_DYE = new ItemType(),
                LIGHT_BLUE_DYE = new ItemType(),
                MAGENTA_DYE = new ItemType(),
                ORANGE_DYE = new ItemType(),
                BONE_MEAL = new ItemType(),
                // END dyes
                BONE = new ItemType(-1),
                SUGAR = new ItemType(),
                CAKE = new ItemType(),
                BED = new ItemType(),
                REDSTONE_REPEATER = new ItemType(),
                COOKIE = new ItemType(),
                MAP = new ItemType(),
                SHEARS = new ItemType(),
                MELON = new ItemType(),
                PUMPKIN_SEEDS = new ItemType(),
                MELON_SEEDS = new ItemType(),
                RAW_BEEF = new ItemType(),
                STEAK = new ItemType(),
                RAW_CHICKEN = new ItemType(),
                COOKED_CHICKEN = new ItemType(),
                ROTTEN_FLESH = new ItemType(),
                ENDER_PEARL = new ItemType(),
                BLAZE_ROD = new ItemType(),
                GHAST_TEAR = new ItemType(),
                GOLD_NUGGET = new ItemType(),
                NETHER_WART = new ItemType(),
                // potions
                WATER_BOTTLE = new ItemType(0), AWKWARD_POTION = new ItemType(16), THICK_POTION = new ItemType(32), MUNDANE_POTION = new ItemType(64),
                REGENERATION_POTION = new ItemType(8193), SWIFTNESS_POTION = new ItemType(), FIRE_RESISTANCE_POTION = new ItemType(), POISON_POTION = new ItemType(),
                HEALING_POTION = new ItemType(), NIGHT_VISION_POTION = new ItemType(), WEAKNESS_POTION = new ItemType(8200), STRENGTH_POTION = new ItemType(),
                SLOWNESS_POTION = new ItemType(), LEAPING_POTION = new ItemType(), HARMING_POTION = new ItemType(), WATER_BREATHING_POTION = new ItemType(),
                INVISIBILITY_POTION = new ItemType(), REGENERATION_POTION_II = new ItemType(8225), SWIFTNESS_POTION_II = new ItemType(),
                POISON_POTION_II = new ItemType(8228), HEALING_POTION_II = new ItemType(), STRENGTH_POTION_II = new ItemType(8233),
                LEAPING_POTION_II = new ItemType(8235),
                HARMING_POTION_II = new ItemType(),
                REGENERATION_POTION_EXTENDED = new ItemType(8257),
                SWIFTNESS_POTION_EXTENDED = new ItemType(),
                FIRE_RESISTANCE_POTION_EXTENDED = new ItemType(),
                POISON_POTION_EXTENDED = new ItemType(),
                NIGHT_VISION_POTION_EXTENDED = new ItemType(8262),
                WEAKNESS_POTION_EXTENDED = new ItemType(8264),
                STRENGTH_POTION_EXTENDED = new ItemType(),
                SLOWNESS_POTION_EXTENDED = new ItemType(),
                LEAPING_POTION_EXTENDED = new ItemType(),
                WATER_BREATHING_POTION_EXTENDED = new ItemType(8269),
                INVISIBILITY_POTION_EXTENDED = new ItemType(),
                REGENERATION_POTION_II_EXTENDED = new ItemType(8289),
                SWIFTNESS_POTION_II_EXTENDED = new ItemType(),
                POISON_POTION_II_EXTENDED = new ItemType(8292),
                STRENGTH_POTION_II_EXTENDED = new ItemType(8297),
                REGENERATION_SPLASH_POTION = new ItemType(16385),
                SWIFTNESS_SPLASH_POTION = new ItemType(),
                FIRE_RESISTANCE_SPLASH_POTION = new ItemType(),
                POISON_SPLASH_POTION = new ItemType(),
                HEALING_SPLASH_POTION = new ItemType(),
                NIGHT_VISION_SPLASH_POTION = new ItemType(),
                WEAKNESS_SPLASH_POTION = new ItemType(16392),
                STRENGTH_SPLASH_POTION = new ItemType(),
                SLOWNESS_SPLASH_POTION = new ItemType(),
                HARMING_SPLASH_POTION = new ItemType(16396),
                WATER_BREATHING_SPLASH_POTION = new ItemType(),
                INVISIBILITY_SPLASH_POTION = new ItemType(),
                REGENERATION_SPLASH_POTION_II = new ItemType(16417),
                SWIFTNESS_SPLASH_POTION_II = new ItemType(),
                POISON_SPLASH_POTION_II = new ItemType(16420),
                HEALING_SPLASH_POTION_II = new ItemType(),
                STRENGTH_SPLASH_POTION_II = new ItemType(16425),
                LEAPING_SPLASH_POTION_II = new ItemType(16427),
                HARMING_SPLASH_POTION_II = new ItemType(),
                REGENERATION_SPLASH_POTION_EXTENDED = new ItemType(16449),
                SWIFTNESS_SPLASH_POTION_EXTENDED = new ItemType(),
                FIRE_RESISTANCE_SPLASH_POTION_EXTENDED = new ItemType(),
                POISON_SPLASH_POTION_EXTENDED = new ItemType(),
                NIGHT_VISION_SPLASH_POTION_EXTENDED = new ItemType(16454),
                WEAKNESS_SPLASH_POTION_EXTENDED = new ItemType(16456),
                STRENGTH_SPLASH_POTION_EXTENDED = new ItemType(),
                SLOWNESS_SPLASH_POTION_EXTENDED = new ItemType(),
                LEAPING_SPLASH_POTION_EXTENDED = new ItemType(),
                WATER_BREATHING_SPLASH_POTION_EXTENDED = new ItemType(16461),
                INVISIBILITY_SPLASH_POTION_EXTENDED = new ItemType(),
                REGENERATION_SPLASH_POTION_II_EXTENDED = new ItemType(16481),
                SWIFTNESS_SPLASH_POTION_II_EXTENDED = new ItemType(),
                POISON_SPLASH_POTION_II_EXTENDED = new ItemType(16484),
                STRENGTH_SPLASH_POTION_II_EXTENDED = new ItemType(16489),
                // END potions
                GLASS_BOTTLE = new ItemType(-1),
                SPIDER_EYE = new ItemType(),
                FERMENTED_SPIDER_EYE = new ItemType(),
                BLAZE_POWDER = new ItemType(),
                MAGMA_CREAM = new ItemType(),
                BREWING_STAND = new ItemType(),
                CAULDRON = new ItemType(),
                EYE_OF_ENDER = new ItemType(),
                GLISTERING_MELON = new ItemType(),
                // spawn eggs
                SPAWN_CREEPER_EGG = new ItemType(50),
                SPAWN_SKELETON_EGG = new ItemType(),
                SPAWN_SPIDER_EGG = new ItemType(),
                SPAWN_ZOMBIE_EGG = new ItemType(54),
                SPAWN_SLIME_EGG = new ItemType(),
                SPAWN_GHAST_EGG = new ItemType(),
                SPAWN_ZOMBIE_PIGMAN_EGG = new ItemType(),
                SPAWN_ENDERMAN_EGG = new ItemType(),
                SPAWN_CAVE_SPIDER_EGG = new ItemType(),
                SPAWN_SILVERFISH_EGG = new ItemType(),
                SPAWN_BLAZE_EGG = new ItemType(),
                SPAWN_MAGMA_CUBE_EGG = new ItemType(),
                SPAWN_BAT_EGG = new ItemType(65),
                SPAWN_WITCH_EGG = new ItemType(),
                SPAWN_ENDERMITE_EGG = new ItemType(),
                SPAWN_GUARDIAN_EGG = new ItemType(),
                SPAWN_PIG_EGG = new ItemType(90),
                SPAWN_SHEEP_EGG = new ItemType(),
                SPAWN_COW_EGG = new ItemType(),
                SPAWN_CHICKEN_EGG = new ItemType(),
                SPAWN_SQUID_EGG = new ItemType(),
                SPAWN_WOLF_EGG = new ItemType(),
                SPAWN_MOOSHROOM_EGG = new ItemType(),
                SPAWN_OCELOT_EGG = new ItemType(98),
                SPAWN_HORSE_EGG = new ItemType(100),
                SPAWN_RABBIT_EGG = new ItemType(),
                SPAWN_VILLAGER_EGG = new ItemType(120),
                // END spawn eggs
                BOTTLE_O_ENCHANTING = new ItemType(-1),
                FIRE_CHARGE = new ItemType(),
                BOOK_AND_QUILL = new ItemType(),
                WRITTEN_BOOK = new ItemType(),
                EMERALD = new ItemType(),
                ITEM_FRAME = new ItemType(),
                FLOWER_POT = new ItemType(),
                CARROT = new ItemType(),
                POTATO = new ItemType(),
                BAKED_POTATO = new ItemType(),
                POISONOUS_POTATO = new ItemType(),
                EMPTY_MAP = new ItemType(),
                GOLDEN_CARROT = new ItemType(),
                // monster heads
                SKELETON_SKULL = new ItemType(0),
                WITHER_SKELETON_SKULL = new ItemType(),
                ZOMBIE_HEAD = new ItemType(),
                STEVE_HEAD = new ItemType(),
                CREEPER_HEAD = new ItemType(),
                // END monster heads
                CARROT_ON_A_STICK = new ItemType(-1), NETHER_STAR = new ItemType(), PUMPKIN_PIE = new ItemType(), FIREWORK = new ItemType(), FIREWORK_STAR = new ItemType(),
                ENCHANTED_BOOK = new ItemType(), REDSTONE_COMPARATOR = new ItemType(), NETHER_BRICK = new ItemType(), NETHER_QUARTZ = new ItemType(),
                TNT_MINECART = new ItemType(), HOPPER_MINECART = new ItemType(), PRISMARINE_SHARD = new ItemType(), PRISMARINE_CRYSTALS = new ItemType(),
                RAW_RABBIT = new ItemType(), COOKED_RABBIT = new ItemType(), RABBIT_STEW = new ItemType(), RABBITS_FOOT = new ItemType(), RABBIT_HIDE = new ItemType(),
                ARMOR_STAND = new ItemType(), IRON_HORSE_ARMOR = new ItemType(), GOLDEN_HORSE_ARMOR = new ItemType(),
                DIAMOND_HORSE_ARMOR = new ItemType(),
                LEAD = new ItemType(),
                NAME_TAG = new ItemType(),
                COMMAND_MINECART = new ItemType(),
                RAW_MUTTON = new ItemType(),
                COOKED_MUTTON = new ItemType(),
                // banners
                BLACK_BANNER = new ItemType(0), RED_BANNER = new ItemType(), DARK_GREEN_BANNER = new ItemType(), BROWN_BANNER = new ItemType(),
                DARK_BLUE_BANNER = new ItemType(), PURPLE_BANNER = new ItemType(), CYAN_BANNER = new ItemType(), LIGHT_GRAY_BANNER = new ItemType(),
                DARK_GRAY_BANNER = new ItemType(), PINK_BANNER = new ItemType(), LIGHT_GREEN_BANNER = new ItemType(), YELLOW_BANNER = new ItemType(),
                LIGHT_BLUE_BANNER = new ItemType(), MAGENTA_BANNER = new ItemType(),
                ORANGE_BANNER = new ItemType(),
                WHITE_BANNER = new ItemType(),
                // END banners
                SPRUCE_DOOR = new ItemType(427, -1), BIRCH_DOOR = new ItemType(), JUNGLE_DOOR = new ItemType(), ACACIA_DOOR = new ItemType(), DARK_OAK_DOOR = new ItemType(),
                MUSIC_DISK_13 = new ItemType(2256, -1), MUSIC_DISK_CAT = new ItemType(), MUSIC_DISK_BLOCKS = new ItemType(), MUSIC_DISK_CHIRP = new ItemType(),
                MUSIC_DISK_FAR = new ItemType(), MUSIC_DISK_MALL = new ItemType(), MUSIC_DISK_MELLOHI = new ItemType(), MUSIC_DISK_STAL = new ItemType(),
                MUSIC_DISK_STRAD = new ItemType(), MUSIC_DISK_WARD = new ItemType(), MUSIC_DISK_11 = new ItemType(), MUSIC_DISK_WAIT = new ItemType();

        private final net.minecraft.item.Item itemMC;

        // constructors
        /** This constructor makes a ItemType based on the previous value's I.D. and data. If the previous value has no strictly associated data value (data value = -1), it
         * means that it has no sub-types (e.g. the different colors of wool or types of wood), so use the next I.D.; if it has a data value, give this ItemType the same I.D.
         * and the next data value. Essentially, "I.D. items" (blocks of multiple enum constants that all have the same I.D., but different data values) are delimited by the
         * use of the {@link #ItemType(int)} and {@link #ItemType(int, int)} constructors; declaring a new enum value with a data value less than the previous will end a block
         * and declaring one with a data value >= 0 will start a new block. */
        private ItemType() {
            super();

            // find the Item with the given I.D.
            itemMC = net.minecraft.item.Item.getItemById(getID());
        }

        /** This constructor makes a ItemType based on the previous value's I.D. and the given data. If the previous value's data value is <= <b><tt>data</b></tt>, then the
         * I.D. block is ending, so it increments the I.D.; otherwise, it will use the same I.D. as the previous value to continue the I.D. block. Essentially, "I.D. blocks"
         * (blocks of multiple enum constants that all have the same I.D., but different data values) are delimited by the use of the {@link #ItemType(int)} and
         * {@link #ItemType(int, int)} constructors; declaring a new enum value with a data value less than the previous will end a block and declaring one with a data value
         * >= 0 will start a new block.
         * 
         * @param data
         *            is the data value for this {@link ItemType}. */
        private ItemType(int data) {
            super(data);

            // find the Item with the given I.D.
            itemMC = net.minecraft.item.Item.getItemById(getID());
        }

        /** This constructor makes a ItemType with the given I.D. and data. It's necessary for specifying I.D.s when Minecraft skips I.D.s.
         * 
         * @param id
         *            is the item I.D. that this {@link ItemType} is associated with.
         * @param data
         *            is the data value associated with this {@link ItemType}.
         * @see {@link #ItemType(int)} */
        private ItemType(int id, int data) {
            super(id, data);

            // find the Item with the given I.D.
            itemMC = net.minecraft.item.Item.getItemById(id);
        }

        // private utilities
        private static ItemType fromMCArmorMaterial(ArmorMaterial material) {
            switch (material) {
                case DIAMOND:
                    return ItemType.DIAMOND;
                case IRON:
                case CHAIN:
                    return ItemType.IRON_INGOT;
                case GOLD:
                    return ItemType.GOLD_INGOT;
                case CLOTH:
                    return ItemType.LEATHER;
                default:
                    throw new CIE("This piece of armor is made of a material that I don't recognize! Is Corundum running with a Minecraft server version higher than "
                            + CorundumServer.MCVERSION + "?", "unidentified tool material", "material=" + material.toString());
            }
        }

        private static HoldableType<?> fromMCToolMaterial(ToolMaterial material) {
            switch (material) {
                case EMERALD:
                    return ItemType.DIAMOND;
                case IRON:
                    return ItemType.IRON_INGOT;
                case STONE:
                    return BlockType.COBBLESTONE;
                case GOLD:
                    return ItemType.GOLD_INGOT;
                case WOOD:
                    return BlockType.OAK_WOOD_PLANKS;
                default:
                    throw new CIE("This tool is made of a material that I don't recognize! Is Corundum running with a Minecraft server version higher than "
                            + CorundumServer.MCVERSION + "?", "unidentified tool material", "material=" + material.toString());
            }
        }

        // class-specific methods
        /** This method returns the maximum amount of damage an {@link Item} of this {@link ItemType} can take before it breaks. {@link ItemType}s that represent items that do
         * not take damage such as {@link ItemType#STICK sticks} will return 0.
         * 
         * @return the maximum amount of damage an {@link Item} of this {@link ItemType} can take before breaking or 0 if this {@link ItemType} is not damageable. */
        public short getMaxDurability() {
            return (short) itemMC.getMaxDamage();
        }

        /** This method returns the type of material -- either a {@link BlockType} or an {@link ItemType} -- that can be used to repair this type of item in an
         * {@link BlockType#ANVIL anvil}. Note that this is only applicable for tools and armor; calling this method for any other kind of item will return <b>null</b>. If an
         * {@link ItemType} can only be repaired with another item of the same type -- like {@link ItemType#CARROT_ON_A_STICK a carrot on a stick} -- this method still returns
         * <b>null</b>.
         * 
         * @return the {@link BlockType} or {@link ItemType} that can be used to repair this item in an anvil or <b>null</b> if an item of this type cannot be repaired in an
         *         {@link BlockType#ANVIL anvil} with any other type of item besides itself. */
        public HoldableType<?> getRepairMaterial() {
            if (itemMC instanceof ItemTool)
                return fromMCToolMaterial(((ItemTool) itemMC).func_150913_i());
            else if (itemMC instanceof ItemArmor)
                return fromMCArmorMaterial(((ItemArmor) itemMC).getArmorMaterial());
            else
                return null;
        }

        /** This method determines whether or not an item of this {@link ItemType} can be damaged. Tools such as {@link ItemType#IRON_AXE iron axes} and {@link ItemType#SHEARS
         * shears} and armor such as {@link ItemType#IRON_CHESTPLATE iron chestplates} are damageable while most other items are not.
         * 
         * @return <b>true</b> if this an item of this {@link ItemType} can be damaged like armor or tools; <b>false</b> otherwise. */
        public boolean isDamageable() {
            return getMaxDurability() > 0;
        }

        /** This method determines whether or not this {@link ItemType} represents a music disk such as {@link ItemType#MUSIC_DISK_MELLOHI "Mellohi"}.
         * 
         * @return <b>true</b> if this {@link ItemType} represents a type of music disk; <b>false</b> otherwise. */
        public boolean isMusicDisk() {
            // TODO: when we can, replace it with something like "instanceof MusicDiskType"
            return getID() >= 2256;
        }

        /** This method determines whether or not this {@link ItemType} represents a potion like {@link ItemType#SWIFTNESS_POTION a Swiftness Potion}. Note that despite the
         * fact that they have the same I.D. as potions, this method does <i>not</i> return <b>true</b> for {@link ItemType#WATER_BOTTLE water bottles}.
         * 
         * @return <b>true</b> if this {@link ItemType} represents a potion (not including {@link ItemType#WATER_BOTTLE water bottles}; <b>false</b> otherwise. */
        public boolean isPotion() {
            // TODO: when we can, replace it with something like "instanceof PotionType"
            return isASiblingOf(ItemType.SWIFTNESS_POTION) && getData() > 0 /* the data check eliminates water bottles */;
        }

        // overridden properties
        @Override
        public byte getMaxStackSize() {
            return (byte) itemMC.getItemStackLimit();
        }

        @Override
        public boolean isASiblingOf(ItemType material) {
            return material.getID() == getID();
        }

        // static utilities
        /** This method retrieves the {@link ItemType} with the given item I.D. value.
         * 
         * @param id
         *            is the item I.D. of the {@link ItemType} you wish to locate.
         * @return the {@link ItemType} with the lowest data value that matches the given item I.D. (This item will almost certainly have a data value of 0 or -1.) */
        public static ItemType getByID(int id) {
            return getByID(ItemType.class, id);
        }

        /** This method retrieves the {@link ItemType} with the given item I.D. and data values.
         * 
         * @param id
         *            is the item I.D. of the {@link ItemType} you wish to locate.
         * @param data
         *            is the data value for the item you wish to locate. A negative value is considered a "wild card", meaning that is will consider the given data value
         *            irrelevant and match the item with the given I.D. and the lowest available data value (almost always 0 or -1).
         * @return the {@link ItemType} that matches the given item I.D. and data value or <b>null</b> if no {@link ItemType} has the given I.D. and data value. */
        public static ItemType getByID(int id, int data) {
            return getByID(ItemType.class, id, data);
        }

        public static ItemType[] values() {
            return values(ItemType.class);
        }

        // data management overrides
        @Override
        public Object[] getSortPriorities() {
            return new Object[] { getID(), getData() };
        }
    }

    // TODO: add methods for adding, removing, and modifying enchantments

    public myList<Enchantment> getEnchantments() {
        return enchantments;
    }

    public ItemType getType() {
        return type;
    }

    public static Item[] fromMCItems(ItemStack... item_stacks) {
        List<net.minecraft.item.Item> items = new ArrayList<>();

        for (ItemStack itemStack : item_stacks) {
            items.add(itemStack.getItem());
        }

        List<Item> corundumItems = new ArrayList<>();

        for (net.minecraft.item.Item mcItem : items) {
            corundumItems.add(new Item(ItemType.getByID(net.minecraft.item.Item.getIdFromItem(mcItem))));
        }

        return corundumItems.toArray(new Item[corundumItems.size()]);
    }
}
