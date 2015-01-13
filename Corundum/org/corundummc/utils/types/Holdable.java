package org.corundummc.utils.types;

import org.corundummc.items.Enchantment;

public interface Holdable {
    public Enchantment[] getEnchantments();

    // TODO: public Inventory getInventory();

    public byte getInventorySlot();

    // TODO: isHeld(): true if an Entity has it in an inventory; false otherwise
}
