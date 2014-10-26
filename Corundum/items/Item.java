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

package Corundum.items;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.Item.ToolMaterial;
import Corundum.items.recipes.FurnaceRecipe;
import Corundum.items.recipes.ShapedCraftingRecipe;
import Corundum.items.recipes.Recipe;
import Corundum.utils.interfaces.HoldableType;
import Corundum.utils.interfaces.IDedType;
import Corundum.utils.interfaces.Matchable;
import Corundum.utils.myList.myList;
import Corundum.world.Block;
import Corundum.world.Biome.BiomeType;
import Corundum.world.Block.BlockType;

public class Item {
    private final ItemType type;
    private myList<Enchantment> enchantments;

    // TODO private InventorySlot location;

    public Item(ItemType type, Enchantment... enchantments) {
        this.type = type;
        this.enchantments = new myList<Enchantment>(enchantments);
    }

    // TODO: add methods for adding, removing, and modifying enchantments

    public myList<Enchantment> getEnchantments() {
        return enchantments;
    }

    public ItemType getType() {
        return type;
    }

    public static Item[] fromMCItems(ItemStack... item_stacks) {
        // TODO
        return null;
    }

    /** This enum is used to represent the different types of {@link Item}s. This list of different types not only includes those types of items differentiated by different
     * I.D.s, but also many of those differentiated by different data values; for example, all different colors of dye are listed individually despite the fact that they all
     * have the same I.D. */
    public enum ItemType implements HoldableType<ItemType>, Matchable<ItemType> {
        IRON_SHOVEL,  // the first value is handled by a base case in the constructor
        IRON_PICKAXE,
        IRON_AXE,
        FLINT_AND_STEEL,
        APPLE,
        BOW,
        ARROW,
        // coal types
        COAL(0),
        CHARCOAL,
        // END coal types
        DIAMOND(-1),
        IRON_INGOT,
        GOLD_INGOT,
        IRON_SWORD,
        WOODEN_SWORD,
        WOODEN_SHOVEL,
        WOODEN_PICKAXE,
        WOODEN_AXE,
        STONE_SWORD,
        STONE_SHOVEL,
        STONE_PICKAXE,
        STONE_AXE,
        DIAMOND_SWORD,
        DIAMOND_SHOVEL,
        DIAMOND_PICKAXE,
        DIAMOND_AXE,
        STICK,
        BOWL,
        MUSHROOM_STEW,
        GOLDEN_SWORD,
        GOLDEN_SHOVEL,
        GOLDEN_PICKAXE,
        GOLDEN_AXE,
        STRING,
        FEATHER,
        GUNPOWDER,
        WOODEN_HOE,
        STONE_HOE,
        IRON_HOE,
        DIAMOND_HOE,
        GOLDEN_HOE,
        SEEDS,
        WHEAT,
        BREAD,
        LEATHER_CAP,
        LEATHER_TUNIC,
        LEATHER_PANTS,
        LEATHER_BOOTS,
        CHAINMAIL_HELMET,
        CHAINMAIL_CHESTPLATE,
        CHAINMAIL_LEGGINGS,
        CHAINMAIL_BOOTS,
        IRON_HELMET,
        IRON_CHESTPLATE,
        IRON_LEGGINGS,
        IRON_BOOTS,
        DIAMOND_HELMET,
        DIAMOND_CHESTPLATE,
        DIAMOND_LEGGINGS,
        DIAMOND_BOOTS,
        GOLDEN_HELMET,
        GOLDEN_CHESTPLATE,
        GOLDEN_LEGGINGS,
        GOLDEN_BOOTS,
        FLINT,
        RAW_PORKCHOP,
        COOKED_PORKCHOP,
        PAINTING,
        // golden apples
        GOLDEN_APPLE(0),
        ENCHANTED_GOLDEN_APPLE,
        // END golden apples
        SIGN(-1),
        OAK_DOOR,
        BUCKET,
        WATER_BUCKET,
        LAVA_BUCKET,
        MINECART,
        SADDLE,
        IRON_DOOR,
        REDSTONE_DUST,
        SNOWBALL,
        BOAT,
        LEATHER,
        MILK_BUCKET,
        BRICK,
        CLAY,
        SUGAR_CANES,
        PAPER,
        BOOK,
        SLIMEBALL,
        STORAGE_MINECART,
        POWERED_MINECART,
        EGG,
        COMPASS,
        FISHING_ROD,
        CLOCK,
        GLOWSTONE_DUST,
        // raw fish
        RAW_COD(0),
        RAW_SALMON,
        CLOWNFISH,
        PUFFERFISH,
        // END raw fish
        // cooked fish
        COOKED_FISH(0),
        COOKED_SALMON,
        // END cooked fish
        // dyes
        INK_SACK(0),
        RED_DYE,
        DARK_GREEN_DYE,
        COCOA_BEANS,
        LAPIS_LAZULI,
        PURPLE_DYE,
        CYAN_DYE,
        LIGHT_GRAY_DYE,
        DARK_GRAY_DYE,
        PINK_DYE,
        LIGHT_GREEN_DYE,
        YELLOW_DYE,
        LIGHT_BLUE_DYE,
        MAGENTA_DYE,
        ORANGE_DYE,
        BONE_MEAL,
        // END dyes
        BONE(-1),
        SUGAR,
        CAKE,
        BED,
        REDSTONE_REPEATER,
        COOKIE,
        MAP,
        SHEARS,
        MELON,
        PUMPKIN_SEEDS,
        MELON_SEEDS,
        RAW_BEEF,
        STEAK,
        RAW_CHICKEN,
        COOKED_CHICKEN,
        ROTTEN_FLESH,
        ENDER_PEARL,
        BLAZE_ROD,
        GHAST_TEAR,
        GOLD_NUGGET,
        NETHER_WART,
        // potions
        WATER_BOTTLE(0),
        AWKWARD_POTION(16),
        THICK_POTION(32),
        MUNDANE_POTION(64),
        REGENERATION_POTION(8193),
        SWIFTNESS_POTION,
        FIRE_RESISTANCE_POTION,
        POISON_POTION,
        HEALING_POTION,
        NIGHT_VISION_POTION,
        WEAKNESS_POTION(8200),
        STRENGTH_POTION,
        SLOWNESS_POTION,
        LEAPING_POTION,
        HARMING_POTION,
        WATER_BREATHING_POTION,
        INVISIBILITY_POTION,
        REGENERATION_POTION_II(8225),
        SWIFTNESS_POTION_II,
        POISON_POTION_II(8228),
        HEALING_POTION_II,
        STRENGTH_POTION_II(8233),
        LEAPING_POTION_II(8235),
        HARMING_POTION_II,
        REGENERATION_POTION_EXTENDED(8257),
        SWIFTNESS_POTION_EXTENDED,
        FIRE_RESISTANCE_POTION_EXTENDED,
        POISON_POTION_EXTENDED,
        NIGHT_VISION_POTION_EXTENDED(8262),
        WEAKNESS_POTION_EXTENDED(8264),
        STRENGTH_POTION_EXTENDED,
        SLOWNESS_POTION_EXTENDED,
        LEAPING_POTION_EXTENDED,
        WATER_BREATHING_POTION_EXTENDED(8269),
        INVISIBILITY_POTION_EXTENDED,
        REGENERATION_POTION_II_EXTENDED(8289),
        SWIFTNESS_POTION_II_EXTENDED,
        POISON_POTION_II_EXTENDED(8292),
        STRENGTH_POTION_II_EXTENDED(8297),
        REGENERATION_SPLASH_POTION(16385),
        SWIFTNESS_SPLASH_POTION,
        FIRE_RESISTANCE_SPLASH_POTION,
        POISON_SPLASH_POTION,
        HEALING_SPLASH_POTION,
        NIGHT_VISION_SPLASH_POTION,
        WEAKNESS_SPLASH_POTION(16392),
        STRENGTH_SPLASH_POTION,
        SLOWNESS_SPLASH_POTION,
        HARMING_SPLASH_POTION(16396),
        WATER_BREATHING_SPLASH_POTION,
        INVISIBILITY_SPLASH_POTION,
        REGENERATION_SPLASH_POTION_II(16417),
        SWIFTNESS_SPLASH_POTION_II,
        POISON_SPLASH_POTION_II(16420),
        HEALING_SPLASH_POTION_II,
        STRENGTH_SPLASH_POTION_II(16425),
        LEAPING_SPLASH_POTION_II(16427),
        HARMING_SPLASH_POTION_II,
        REGENERATION_SPLASH_POTION_EXTENDED(16449),
        SWIFTNESS_SPLASH_POTION_EXTENDED,
        FIRE_RESISTANCE_SPLASH_POTION_EXTENDED,
        POISON_SPLASH_POTION_EXTENDED,
        NIGHT_VISION_SPLASH_POTION_EXTENDED(16454),
        WEAKNESS_SPLASH_POTION_EXTENDED(16456),
        STRENGTH_SPLASH_POTION_EXTENDED,
        SLOWNESS_SPLASH_POTION_EXTENDED,
        LEAPING_SPLASH_POTION_EXTENDED,
        WATER_BREATHING_SPLASH_POTION_EXTENDED(16461),
        INVISIBILITY_SPLASH_POTION_EXTENDED,
        REGENERATION_SPLASH_POTION_II_EXTENDED(16481),
        SWIFTNESS_SPLASH_POTION_II_EXTENDED,
        POISON_SPLASH_POTION_II_EXTENDED(16484),
        STRENGTH_SPLASH_POTION_II_EXTENDED(16489),
        // END potions
        GLASS_BOTTLE(-1),
        SPIDER_EYE,
        FERMENTED_SPIDER_EYE,
        BLAZE_POWDER,
        MAGMA_CREAM,
        BREWING_STAND,
        CAULDRON,
        EYE_OF_ENDER,
        GLISTERING_MELON,
        // spawn eggs
        SPAWN_CREEPER_EGG(50),
        SPAWN_SKELETON_EGG,
        SPAWN_SPIDER_EGG,
        SPAWN_ZOMBIE_EGG(54),
        SPAWN_SLIME_EGG,
        SPAWN_GHAST_EGG,
        SPAWN_ZOMBIE_PIGMAN_EGG,
        SPAWN_ENDERMAN_EGG,
        SPAWN_CAVE_SPIDER_EGG,
        SPAWN_SILVERFISH_EGG,
        SPAWN_BLAZE_EGG,
        SPAWN_MAGMA_CUBE_EGG,
        SPAWN_BAT_EGG(65),
        SPAWN_WITCH_EGG,
        SPAWN_ENDERMITE_EGG,
        SPAWN_GUARDIAN_EGG,
        SPAWN_PIG_EGG(90),
        SPAWN_SHEEP_EGG,
        SPAWN_COW_EGG,
        SPAWN_CHICKEN_EGG,
        SPAWN_SQUID_EGG,
        SPAWN_WOLF_EGG,
        SPAWN_MOOSHROOM_EGG,
        SPAWN_OCELOT_EGG(98),
        SPAWN_HORSE_EGG(100),
        SPAWN_RABBIT_EGG,
        SPAWN_VILLAGER_EGG(120),
        // END spawn eggs
        BOTTLE_O_ENCHANTING(-1),
        FIRE_CHARGE,
        BOOK_AND_QUILL,
        WRITTEN_BOOK,
        EMERALD,
        ITEM_FRAME,
        FLOWER_POT,
        CARROT,
        POTATO,
        BAKED_POTATO,
        POISONOUS_POTATO,
        EMPTY_MAP,
        GOLDEN_CARROT,
        // monster heads
        SKELETON_SKULL(0),
        WITHER_SKELETON_SKULL,
        ZOMBIE_HEAD,
        STEVE_HEAD,
        CREEPER_HEAD,
        // END monster heads
        CARROT_ON_A_STICK(-1),
        NETHER_STAR,
        PUMPKIN_PIE,
        FIREWORK,
        FIREWORK_STAR,
        ENCHANTED_BOOK,
        REDSTONE_COMPARATOR,
        NETHER_BRICK,
        NETHER_QUARTZ,
        TNT_MINECART,
        HOPPER_MINECART,
        PRISMARINE_SHARD,
        PRISMARINE_CRYSTALS,
        RAW_RABBIT,
        COOKED_RABBIT,
        RABBIT_STEW,
        RABBITS_FOOT,
        RABBIT_HIDE,
        ARMOR_STAND,
        IRON_HORSE_ARMOR,
        GOLDEN_HORSE_ARMOR,
        DIAMOND_HORSE_ARMOR,
        LEAD,
        NAME_TAG,
        COMMAND_MINECART,
        RAW_MUTTON,
        COOKED_MUTTON,
        // banners
        BLACK_BANNER(0),
        RED_BANNER,
        DARK_GREEN_BANNER,
        BROWN_BANNER,
        DARK_BLUE_BANNER,
        PURPLE_BANNER,
        CYAN_BANNER,
        LIGHT_GRAY_BANNER,
        DARK_GRAY_BANNER,
        PINK_BANNER,
        LIGHT_GREEN_BANNER,
        YELLOW_BANNER,
        LIGHT_BLUE_BANNER,
        MAGENTA_BANNER,
        ORANGE_BANNER,
        WHITE_BANNER,
        // END banners
        SPRUCE_DOOR(427, -1),
        BIRCH_DOOR,
        JUNGLE_DOOR,
        ACACIA_DOOR,
        DARK_OAK_DOOR,
        MUSIC_DISK_13(2256, -1),
        MUSIC_DISK_CAT,
        MUSIC_DISK_BLOCKS,
        MUSIC_DISK_CHIRP,
        MUSIC_DISK_FAR,
        MUSIC_DISK_MALL,
        MUSIC_DISK_MELLOHI,
        MUSIC_DISK_STAL,
        MUSIC_DISK_STRAD,
        MUSIC_DISK_WARD,
        MUSIC_DISK_11,
        MUSIC_DISK_WAIT;

        /** <b><i>DEV NOTES:</b></i><br>
         * Unlike some other {@link IDedType}s, like {@link BlockType}, this type has its I.D. contained in a <b>short</b> instead of a <b>byte</b>. This is because the range
         * of item I.D.s is greater than 256. Also note that we did not subtract the I.D. by any constant to utilize more of the range of the <b>short</b> simply because it is
         * unnecessary; a <b>short</b> can hold the highest values of the item I.D.s without using any of its negative range. */
        private final short id;
        /** <b><i>DEV NOTES:</b></i><br>
         * The default data value is 0. -1 indicates that this type is a "general" type, which means that it has no static data value and is used to describe all its siblings
         * as a single group. For example, */
        // TODO: finish the note above when there are more enums to link as examples
        private final short data;

        private final net.minecraft.item.Item itemMC;

        /** This constructor makes a ItemType based on the previous value's I.D. and data. If the previous value has no strictly associated data value (data value = -1), it
         * means that it has no sub-types (e.g. the different colors of wool or types of wood), so use the next I.D.; if it has a data value, give this ItemType the same I.D.
         * and the next data value. Essentially, "I.D. items" (blocks of multiple enum constants that all have the same I.D., but different data values) are delimited by the
         * use of the {@link #ItemType(int)} and {@link #ItemType(int, int)} constructors; declaring a new enum value with a data value less than the previous will end a block
         * and declaring one with a data value >= 0 will start a new block. */
        private ItemType() {
            // if this is the first item (IRON_SHOVEL), use a base case
            if (ordinal() == 0) {
                id = 256;
                data = -1;
            } // otherwise, infer the I.D. and data values using the previous type
            else {
                ItemType previous_value = values()[ordinal() - 1];
                /* if the previous data was -1, we are not in an I.D. block, so increment I.D. and default data to -1 */
                if (previous_value.data == -1) {
                    id = (byte) (previous_value.id + 1);
                    data = -1;
                } /* if the previous data value was not -1, we're in an I.D. block, so use the same I.D. as the previous and increment data */
                else {
                    id = previous_value.id;
                    data = (byte) (previous_value.data + 1);
                }
            }

            // find the Item with the given I.D.
            itemMC = net.minecraft.item.Item.getItemById(id);
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
            this.data = (byte) data;

            ItemType previous_value = ItemType.values()[ordinal() - 1];
            // if data <= the previous's data, it indicates the start of a new I.D. block, so increment the I.D. of the previous value
            if (data <= previous_value.data)
                id = (byte) (previous_value.id + 1);
            // otherwise, this is the continuation of an I.D. block, so use the same I.D.
            else
                id = previous_value.id;

            // find the Item with the given I.D.
            itemMC = net.minecraft.item.Item.getItemById(id);
        }

        /** This constructor makes a ItemType with the given I.D. and data. It's necessary for specifying I.D.s when Minecraft skips I.D.s.
         * 
         * @param id
         *            is the item I.D. that this {@link ItemType} is associated with.
         * @param data
         *            is the data value associated with this {@link ItemType}.
         * @see {@link #ItemType(int)} */
        private ItemType(int id, int data) {
            this.id = (short) id;
            this.data = (byte) data;

            // find the Item with the given I.D.
            itemMC = net.minecraft.item.Item.getItemById(id);
        }

        // class-specific methods
        /** This method returns the maximum amount of damage an {@link Item} of this {@link ItemType} can take before it breaks. {@link ItemType}s that represent items that do
         * not take damage such as {@link ItemType#STICK sticks} will return 0.
         * 
         * @return the maximum amount of damage an {@link Item} of this {@link ItemType} can take before breaking or 0 if this {@link ItemType} is not damageable. */
        public short getMaxDurability() {
            return (short) itemMC.getMaxDamage();
        }

        public ItemType getRepairableType() {
            if (itemMC instanceof ItemTool) {
                ToolMaterial material = ((ItemTool) itemMC).func_150913_i();

                /* TODO: replace this "return null" with an if-else if block to take this ToolMaterial and return the corresponding ItemType; there are only five
                 * ToolMaterials. Also, please note that ToolMaterial.EMERALD is diamond; I don't know why the hell it's called "EMERALD" in the code. */
                return null;
            } else
                return null;
        }

        public boolean isDamageable() {
            return getMaxDurability() > 0;
        }

        // overridden properties
        @Override
        public short getData() {
            return data;
        }

        @Override
        public short getID() {
            return (short) (id + 384);
        }

        @Override
        public byte getMaxStackSize() {
            return (byte) itemMC.getItemStackLimit();
        }

        @Override
        public ItemType[] getSiblings() {
            // since ItemTypes are organized by I.D. and data value, all ItemTypes with the same I.D.s will be together
            // first, find the first and last ItemTypes with this same I.D.
            int first_sibling_ordinal = ordinal(), last_sibling_ordinal_plus1 = ordinal() + 1;
            while (first_sibling_ordinal > 0 && ItemType.values()[first_sibling_ordinal - 1].id == id)
                first_sibling_ordinal--;
            while (last_sibling_ordinal_plus1 <= ItemType.values().length && ItemType.values()[last_sibling_ordinal_plus1].id == id)
                last_sibling_ordinal_plus1++;

            // create an array of the appropriate size
            ItemType[] results = new ItemType[last_sibling_ordinal_plus1 - first_sibling_ordinal];

            /* fill in the array with the contents between first_sibling_ordinal and lasT_sibling_ordinal_plus1 (including the ItemType at ordinal first_sibling_ordinal, but
             * NOT including the ItemType at ordinal last_sibling_ordinal_plus1) */
            for (int i = first_sibling_ordinal; i < last_sibling_ordinal_plus1; i++)
                results[i] = ItemType.values()[i];

            return results;
        }

        @Override
        public boolean isASiblingOf(ItemType material) {
            return material.id == id;
        }

        // static utilities
        /** This method retrieves the {@link ItemType} with the given item I.D. value.
         * 
         * @param id
         *            is the item I.D. of the {@link ItemType} you wish to locate.
         * @return the {@link ItemType} with the lowest data value that matches the given item I.D. (This item will almost certainly have a data value of 0 or -1.) */
        public static ItemType getByID(int id) {
            return getByID(id, -1);
        }

        /** This method retrieves the {@link ItemType} with the given item I.D. and data values.
         * 
         * @param id
         *            is the item I.D. of the {@link ItemType} you wish to locate.
         * @param data
         *            is the data value for the item you wish to locate. A negative value is considered a "wild card", meaning that is will consider the given data value
         *            irrelevant and match the item with the given I.D. and the lowest available data value (almost always 0 or -1).
         * @return the {@link ItemType} that matches the given item I.D. and data value or <b>null</b> if no {@link BiomeType} has the given I.D. and data value. */
        public static ItemType getByID(int id, int data) {
            // TODO: replace this linear search with a binary search algorithm
            if (id >= 0) {
                short id_minus_384 = (short) (id - 384);
                for (ItemType item_type : values())
                    if (item_type.id == id_minus_384 && (data < 0 || item_type.data == data))
                        return item_type;
            }
            return null;
        }

        // data management overrides
        @Override
        public Object[] getSortPriorities() {
            return new Object[] { (short) (id + 384), data };
        }
    }

}
