package org.corundummc.types;

import org.corundummc.items.Enchantment;
import org.corundummc.world.Location;

public interface Holdable {
    public Enchantment[] getEnchantments();

    public byte getInventorySlot();

    // TODO: isHeld(): true if an Entity has it in an inventory; false otherwise
}
