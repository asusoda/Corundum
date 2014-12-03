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

import org.corundummc.exceptions.CorundumException;
import org.corundummc.items.Item.ItemType;
import org.corundummc.utils.ListUtilities;
import org.corundummc.utils.myList.myList;

import java.util.HashMap;
import java.util.Map;

import static org.corundummc.utils.StringUtilities.*;

public class Enchantment {
    private int level;
    private EnchantmentType type;

    // TODO: when we can, we can replace all of these with things like HoeType.values(
    private static final ItemType[] allArmourTypes = new ItemType[] { ItemType.IRON_BOOTS, ItemType.IRON_LEGGINGS, ItemType.IRON_CHESTPLATE, ItemType.IRON_HELMET,
            ItemType.DIAMOND_BOOTS, ItemType.DIAMOND_LEGGINGS, ItemType.DIAMOND_CHESTPLATE, ItemType.DIAMOND_HELMET, ItemType.LEATHER_BOOTS, ItemType.LEATHER_PANTS,
            ItemType.LEATHER_TUNIC, ItemType.LEATHER_CAP, ItemType.GOLDEN_BOOTS, ItemType.GOLDEN_LEGGINGS, ItemType.GOLDEN_CHESTPLATE, ItemType.GOLDEN_HELMET,
            ItemType.CHAINMAIL_BOOTS, ItemType.CHAINMAIL_LEGGINGS, ItemType.CHAINMAIL_CHESTPLATE, ItemType.CHAINMAIL_HELMET };
    private static final ItemType[] allBootArmourTypes = new ItemType[] { ItemType.IRON_BOOTS, ItemType.GOLDEN_BOOTS, ItemType.DIAMOND_BOOTS, ItemType.LEATHER_BOOTS,
            ItemType.CHAINMAIL_BOOTS };
    private static final ItemType[] allHelmetArmourTypes = new ItemType[] { ItemType.IRON_HELMET, ItemType.GOLDEN_HELMET, ItemType.DIAMOND_HELMET, ItemType.LEATHER_CAP,
            ItemType.CHAINMAIL_HELMET };
    private static final ItemType[] allSwordTypes = new ItemType[] { ItemType.WOODEN_SWORD, ItemType.STONE_SWORD, ItemType.IRON_SWORD, ItemType.GOLDEN_SWORD,
            ItemType.DIAMOND_SWORD };
    private static final ItemType[] allAxeTypes = new ItemType[] { ItemType.WOODEN_AXE, ItemType.STONE_AXE, ItemType.IRON_AXE, ItemType.GOLDEN_AXE, ItemType.DIAMOND_AXE };
    private static final ItemType[] allPickAxeTypes = new ItemType[] { ItemType.WOODEN_PICKAXE, ItemType.STONE_PICKAXE, ItemType.IRON_PICKAXE, ItemType.GOLDEN_PICKAXE,
            ItemType.DIAMOND_PICKAXE };
    private static final ItemType[] allShovelTypes = new ItemType[] { ItemType.WOODEN_SHOVEL, ItemType.STONE_SHOVEL, ItemType.IRON_SHOVEL, ItemType.GOLDEN_SHOVEL,
            ItemType.DIAMOND_SHOVEL };
    private static final ItemType[] allHoeTypes = new ItemType[] { ItemType.WOODEN_HOE, ItemType.STONE_HOE, ItemType.IRON_HOE, ItemType.GOLDEN_HOE, ItemType.DIAMOND_HOE };

    private final net.minecraft.enchantment.Enchantment mcEnchantment;

    public Enchantment(int level, EnchantmentType type) {
        if (!(level > type.getMaxLevel())) {
            this.level = level;
            this.type = type;
            this.mcEnchantment = type.getMcEnchant();
        } else {
            throw new EnchantmentLevelException("Attempted to create an enchantment with a level higher than the maximum possible for it's enchantment type!", type, level);
        }
    }

    public int getLevel() {
        return this.level;
    }

    public EnchantmentType getType() {
        return this.type;
    }

    public net.minecraft.enchantment.Enchantment getMcEnchantment() {
        return this.mcEnchantment;
    }

    public enum EnchantmentType {
        PROTECTION(net.minecraft.enchantment.Enchantment.protection),
        FIRE_PROTECTION(net.minecraft.enchantment.Enchantment.fireProtection),
        FEATHER_FALLING(net.minecraft.enchantment.Enchantment.featherFalling),
        BLAST_PROTECTION(net.minecraft.enchantment.Enchantment.blastProtection),
        PROJECTILE_PROTECTION(net.minecraft.enchantment.Enchantment.projectileProtection),
        RESPIRATION(net.minecraft.enchantment.Enchantment.respiration),
        AQUA_AFFINITY(net.minecraft.enchantment.Enchantment.aquaAffinity),
        THORNS(net.minecraft.enchantment.Enchantment.thorns),
        SHARPNESS(net.minecraft.enchantment.Enchantment.sharpness),
        SMITE(net.minecraft.enchantment.Enchantment.smite),
        BANE_OF_ARTHROPODS(net.minecraft.enchantment.Enchantment.baneOfArthropods),
        KNOCKBACK(net.minecraft.enchantment.Enchantment.knockback),
        FIRE_ASPECT(net.minecraft.enchantment.Enchantment.fireAspect),
        LOOTING(net.minecraft.enchantment.Enchantment.fireAspect),
        EFFICIENCY(net.minecraft.enchantment.Enchantment.efficiency),
        SILK_TOUCH(net.minecraft.enchantment.Enchantment.silkTouch),
        UNBREAKING(net.minecraft.enchantment.Enchantment.unbreaking),
        FORTUNE(net.minecraft.enchantment.Enchantment.fortune),
        POWER(net.minecraft.enchantment.Enchantment.power),
        PUNCH(net.minecraft.enchantment.Enchantment.punch),
        FLAME(net.minecraft.enchantment.Enchantment.flame),
        INFINITY(net.minecraft.enchantment.Enchantment.infinity);

        private static Map<net.minecraft.enchantment.Enchantment, ItemType[]> enchantsMap = new HashMap<>();
        private byte max_level;
        private ItemType[] applicable_items;
        private net.minecraft.enchantment.Enchantment mcEnchant;

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
                        applicable_items[applicable_items_index] = ItemType.getByID(id);
                        applicable_items_index++;
                    }
            }
        }

        private EnchantmentType(net.minecraft.enchantment.Enchantment mcEnchant) {
            this(mcEnchant.getMaxLevel(), getTypesForMCEnchant(mcEnchant));
            this.mcEnchant = mcEnchant;
        }

        public static ItemType[] getTypesForMCEnchant(net.minecraft.enchantment.Enchantment mcEnchant) {
            return enchantsMap.get(mcEnchant);
        }

        public byte getMaxLevel() {
            return this.max_level;
        }

        public ItemType[] getApplicableItems() {
            return this.applicable_items;
        }

        public net.minecraft.enchantment.Enchantment getMcEnchant() {
            return this.mcEnchant;
        }

        @Override
        public String toString() {
            return capitalizeFully(name().replaceAll("_", " "));
        }

        static {
            enchantsMap.put(net.minecraft.enchantment.Enchantment.protection, allArmourTypes);
            enchantsMap.put(net.minecraft.enchantment.Enchantment.fireProtection, allArmourTypes);
            enchantsMap.put(net.minecraft.enchantment.Enchantment.featherFalling, allBootArmourTypes);
            enchantsMap.put(net.minecraft.enchantment.Enchantment.blastProtection, allArmourTypes);
            enchantsMap.put(net.minecraft.enchantment.Enchantment.projectileProtection, allArmourTypes);
            enchantsMap.put(net.minecraft.enchantment.Enchantment.respiration, allHelmetArmourTypes);
            enchantsMap.put(net.minecraft.enchantment.Enchantment.aquaAffinity, allHelmetArmourTypes);
            enchantsMap.put(net.minecraft.enchantment.Enchantment.thorns, allArmourTypes);
            enchantsMap.put(net.minecraft.enchantment.Enchantment.sharpness, ListUtilities.concatenate(allSwordTypes, allAxeTypes));
            enchantsMap.put(net.minecraft.enchantment.Enchantment.smite, ListUtilities.concatenate(allSwordTypes, allAxeTypes));
            enchantsMap.put(net.minecraft.enchantment.Enchantment.baneOfArthropods, ListUtilities.concatenate(allSwordTypes, allAxeTypes));
            enchantsMap.put(net.minecraft.enchantment.Enchantment.knockback, allSwordTypes);
            enchantsMap.put(net.minecraft.enchantment.Enchantment.fireAspect, allSwordTypes);
            enchantsMap.put(net.minecraft.enchantment.Enchantment.looting, allSwordTypes);
            enchantsMap.put(net.minecraft.enchantment.Enchantment.efficiency, ListUtilities.concatenate(allAxeTypes, allPickAxeTypes, allShovelTypes, new ItemType[] { ItemType.SHEARS }));
            enchantsMap.put(net.minecraft.enchantment.Enchantment.silkTouch, ListUtilities.concatenate(allAxeTypes, allPickAxeTypes, allShovelTypes, new ItemType[] { ItemType.SHEARS }));
            enchantsMap.put(net.minecraft.enchantment.Enchantment.unbreaking, ListUtilities.concatenate(allAxeTypes, allPickAxeTypes, allShovelTypes, allSwordTypes, allArmourTypes, allHoeTypes, new ItemType[] { ItemType.SHEARS,
                    ItemType.FISHING_ROD, ItemType.CARROT_ON_A_STICK, ItemType.BOW }));
            enchantsMap.put(net.minecraft.enchantment.Enchantment.fortune, ListUtilities.concatenate(allAxeTypes, allPickAxeTypes, allShovelTypes));
            enchantsMap.put(net.minecraft.enchantment.Enchantment.power, new ItemType[] {ItemType.BOW});
            enchantsMap.put(net.minecraft.enchantment.Enchantment.punch, new ItemType[] {ItemType.BOW});
            enchantsMap.put(net.minecraft.enchantment.Enchantment.infinity, new ItemType[] {ItemType.BOW});
            enchantsMap.put(net.minecraft.enchantment.Enchantment.infinity, new ItemType[] {ItemType.BOW});
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
