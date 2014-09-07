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

package Minecraft.items;

import main.CorundumException;
import main.Main;
import Minecraft.items.Item.ItemType;

import static utils.StringUtilities.*;

public class Enchantment {
    private int level;

    public enum EnchantmentType {
        PROTECTION(4, ItemType.IRON_SHOVEL /* TODO TEMP: this should not be IRON_SHOVEL; I put it in as a placeholder */);

        private byte max_level;
        private ItemType[] applicable_items;

        private EnchantmentType(int max_level, ItemType... applicable_items) throws CorundumException {
            this.max_level = (byte) max_level;
            if (applicable_items.length == 0) {
                this.applicable_items = null;
                throw new EnchantmentException("Someone forgot to list the items that " + name() + "works with!", "item-less enchantment");
            } else
                this.applicable_items = applicable_items;
        }

        private EnchantmentType(int max_level, int... applicable_item_id_ranges) throws CorundumException {
            this.max_level = (byte) max_level;
            if (applicable_item_id_ranges.length == 0) {
                this.applicable_items = null;
                throw new EnchantmentException("Someone forgot to list the items that " + name() + "works with!", "item-less enchantment");
            } else if (applicable_item_id_ranges.length % 2 != 0) {
                this.applicable_items = null;
                throw new EnchantmentException("Someone forgot to close a range in the applicable item I.D. ranges for " + name() + "!", "invalid applicable item I.D. list");
            } else {
                // first, calculate the size of the array that we'll need
                int number_of_applicable_items = 0;
                for (int i = 0; i < applicable_item_id_ranges.length; i += 2)
                    number_of_applicable_items += applicable_item_id_ranges[i + 1] - applicable_item_id_ranges[i] + 1;

                // create the array
                applicable_items = new ItemType[number_of_applicable_items];

                // add all the ItemTypes within the given ranges to the new array
                int applicable_items_index = 0;
                for (int i = 0; i < applicable_item_id_ranges.length; i += 2)
                    for (int id = applicable_item_id_ranges[i]; id <= applicable_item_id_ranges[i + 1]; id++) {
                        applicable_items[applicable_items_index] = ItemType.get(id);
                        applicable_items_index++;
                    }
            }
        }

        @Override
        public String toString() {
            return capitalizeFully(name().replaceAll("_", " "));
        }
    }

    public static class EnchantmentException extends CorundumException {
        private static final long serialVersionUID = 3077901265588091216L;

        public EnchantmentException(String message, String issue, Object... additional_information) {
            super(message, issue, additional_information);
        }
    }

    public static class EnchantmentLevelException extends EnchantmentException {
        private static final long serialVersionUID = -6386265180756091076L;

        private final EnchantmentType enchantment_type;
        private final byte level_attempted;

        public EnchantmentLevelException(String message, EnchantmentType enchantment_type, int level_attempted, Object... additional_information) {
            super(message, "attempt to set " + aOrAn(enchantment_type.toString()) + " to level " + level_attempted, "Enchantment type=" + enchantment_type.toString(),
                    "level=" + level_attempted, additional_information);

            this.enchantment_type = enchantment_type;
            this.level_attempted = (byte) level_attempted;
        }

    }

    public static class EnchantmentIncompatibleItemException extends EnchantmentException {
        private static final long serialVersionUID = 799394753562709707L;

        private final EnchantmentType enchantment_type;
        private final Item item;

        public EnchantmentIncompatibleItemException(String message, EnchantmentType enchantment_type, Item item, Object[] additional_information) {
            super(message, "attempt to place an incompatible enchantment (" + enchantment_type.toString() + ") onto " + aOrAn(item.getType().toString()), "Enchantment type="
                    + enchantment_type.toString(), "item=" + item.toString(), additional_information);

            this.enchantment_type = enchantment_type;
            this.item = item;
        }

        public EnchantmentType getEnchantmentType() {
            return enchantment_type;
        }

        public Item getItem() {
            return item;
        }

    }
}
