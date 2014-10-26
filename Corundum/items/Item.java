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

    public enum ItemType implements HoldableType<ItemType>, Matchable<ItemType> {
        // TODO: finish ItemType constructors
        IRON_SHOVEL,
        IRON_PICKAXE;

        /** <b><i>DEV NOTES:</b></i><br>
         * Unlike some other {@link IDedType}s, like {@link BlockType}, this type has its I.D. contained in a <b>short</b> instead of a <b>byte</b>. This is because the range
         * of item I.D.s is greater than 256. Also note that we did not subtract the I.D. by any constant to utilize more of the range of the <b>short</b> simply because it is
         * unnecessary; a <b>short</b> can hold the highest values of the item I.D.s without using any of its negative range. */
        private final short id;
        /** <b><i>DEV NOTES:</b></i><br>
         * The default data value is 0. -1 indicates that this type is a "general" type, which means that it has no static data value and is used to describe all its siblings
         * as a single group. For example, */
        // TODO: finish the note above when there are more enums to link as examples
        private final byte data;

        private final net.minecraft.item.Item itemMC;

        /** This constructor makes a ItemType based on the previous value's I.D. and data. If the previous value has no strictly associated data value (data value = -1), it
         * means that it has no sub-types (e.g. the different colors of wool or types of wood), so use the next I.D.; if it has a data value, give this ItemType the same I.D.
         * and the next data value. Essentially, "I.D. items" (blocks of multiple enum constants that all have the same I.D., but different data values) are delimited by the
         * use of the {@link #ItemType(int)} and {@link #ItemType(int, int)} constructors; declaring a new enum value with a data value less than the previous will end a block
         * and declaring one with a data value >= 0 will start a new block. */
        private ItemType() {
            ItemType previous_value = ItemType.values()[ordinal() - 1];
            if (previous_value.data == -1) {
                id = (byte) (previous_value.id + 1);
                data = -1;
            } else {
                id = previous_value.id;
                data = (byte) (previous_value.data + 1);
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
        public byte getData() {
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
