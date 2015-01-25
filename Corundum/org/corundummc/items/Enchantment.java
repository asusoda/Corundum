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

import net.minecraft.util.StatCollector;

import org.corundummc.exceptions.CorundumException;
import org.corundummc.hub.CorundumThread;
import org.corundummc.items.Item.ItemType;
import org.corundummc.utils.types.IDedType;
import org.corundummc.utils.types.Typed;
import org.corundummc.world.Location;

import static org.corundummc.utils.StringUtilities.*;

public class Enchantment extends Typed<Enchantment.EnchantmentType> {
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

    public Enchantment(int level, EnchantmentType type) {
        this.level = level;
        this.type = type;
    }

    public int getLevel() {
        return this.level;
    }

    public EnchantmentType getType() {
        return this.type;
    }

    public static final class EnchantmentType extends IDedType<EnchantmentType> {
        public static final EnchantmentType PROTECTION = new EnchantmentType(net.minecraft.enchantment.Enchantment.protection), FIRE_PROTECTION = new EnchantmentType(
                net.minecraft.enchantment.Enchantment.fireProtection), FEATHER_FALLING = new EnchantmentType(net.minecraft.enchantment.Enchantment.featherFalling),
                BLAST_PROTECTION = new EnchantmentType(net.minecraft.enchantment.Enchantment.blastProtection), PROJECTILE_PROTECTION = new EnchantmentType(
                        net.minecraft.enchantment.Enchantment.projectileProtection), RESPIRATION = new EnchantmentType(net.minecraft.enchantment.Enchantment.respiration),
                AQUA_AFFINITY = new EnchantmentType(net.minecraft.enchantment.Enchantment.aquaAffinity), THORNS = new EnchantmentType(
                        net.minecraft.enchantment.Enchantment.thorns), SHARPNESS = new EnchantmentType(net.minecraft.enchantment.Enchantment.sharpness),
                SMITE = new EnchantmentType(net.minecraft.enchantment.Enchantment.smite), BANE_OF_ARTHROPODS = new EnchantmentType(
                        net.minecraft.enchantment.Enchantment.baneOfArthropods), KNOCKBACK = new EnchantmentType(net.minecraft.enchantment.Enchantment.knockback),
                FIRE_ASPECT = new EnchantmentType(net.minecraft.enchantment.Enchantment.fireAspect), LOOTING = new EnchantmentType(
                        net.minecraft.enchantment.Enchantment.fireAspect), EFFICIENCY = new EnchantmentType(net.minecraft.enchantment.Enchantment.efficiency),
                SILK_TOUCH = new EnchantmentType(net.minecraft.enchantment.Enchantment.silkTouch), UNBREAKING = new EnchantmentType(
                        net.minecraft.enchantment.Enchantment.unbreaking), FORTUNE = new EnchantmentType(net.minecraft.enchantment.Enchantment.fortune),
                POWER = new EnchantmentType(net.minecraft.enchantment.Enchantment.power), PUNCH = new EnchantmentType(net.minecraft.enchantment.Enchantment.punch),
                FLAME = new EnchantmentType(net.minecraft.enchantment.Enchantment.flame), INFINITY = new EnchantmentType(net.minecraft.enchantment.Enchantment.infinity);

        private net.minecraft.enchantment.Enchantment enchantmentMC;

        private EnchantmentType(net.minecraft.enchantment.Enchantment enchantmentMC) {
            super(enchantmentMC.effectId);

            this.enchantmentMC = enchantmentMC;
        }

        public static ItemType[] getApplicableItems() {
            // TODO
            return null;
        }

        public byte getMaxLevel() {
            return (byte) enchantmentMC.getMaxLevel();
        }

        @Override
        public String getName() {
            return StatCollector.translateToLocal(enchantmentMC.getName());
        }

        public static EnchantmentType getByID(int id) {
            return IDedType.getByID(EnchantmentType.class, id);
        }
    }
}
