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

import Corundum.items.recipes.Recipe;
import Corundum.utils.myList.myList;

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

    public enum ItemType {
        IRON_SHOVEL(256);

        private final short id;
        private final short data;  // -1 indicates no static data value; the default is 0
        private final short max_durability;
        private final ItemType repairable_with;
        private final Recipe recipe;

        private ItemType(int id) {
            this.id = (short) id;
            data = 0;
            max_durability = 0;
            recipe = null;
            repairable_with = null;
        }

        private ItemType(int id, int data) {
            this.id = (short) id;
            this.data = (byte) data;
            max_durability = 0;
            recipe = null;
            repairable_with = null;
        }

        private ItemType(int id, int max_durability, ItemType repairable_with) {
            this.id = (short) id;
            data = -1;
            this.max_durability = (short) max_durability;
            recipe = null;
            this.repairable_with = repairable_with;
        }

        private ItemType(int id, Recipe recipe) {
            this.id = (short) id;
            data = 0;
            max_durability = 0;
            this.recipe = recipe;
            repairable_with = null;
        }

        private ItemType(int id, int data, Recipe recipe) {
            this.id = (short) id;
            this.data = (byte) data;
            max_durability = 0;
            this.recipe = recipe;
            repairable_with = null;
        }

        private ItemType(int id, int max_durability, ItemType repairable_with, Recipe recipe) {
            this.id = (short) id;
            data = -1;
            this.max_durability = (short) max_durability;
            this.recipe = recipe;
            this.repairable_with = repairable_with;
        }

        /** This method retrieves the {@link ItemType} with the given item I.D. value.
         * 
         * @param id
         *            is the item I.D. of the {@link ItemType} you wish to locate.
         * @return the {@link ItemType} with the lowest data value that matches the given item I.D. (This item will almost certainly have a data value of 0 or -1.) */
        public static ItemType get(int id) {
            return get(id, -1);
        }

        /** This method retrieves the {@link ItemType} with the given item I.D. and data values.
         * 
         * @param id
         *            is the item I.D. of the {@link ItemType} you wish to locate.
         * @param data
         *            is the data value for the item you wish to locate. A negative value is considered a "wild card", meaning that is will consider the given data value
         *            irrelevant and match the item with the given I.D. and the lowest available data value (almost always 0 or -1).
         * @return the {@link ItemType} that matches the given item I.D. and data value. */
        public static ItemType get(int id, int data) {
            // TODO: replace this linear search with a binary search algorithm
            for (ItemType item_type : values())
                if (item_type.id == id && (data < 0 || item_type.data == data))
                    return item_type;
            return null;
        }
    }
}
