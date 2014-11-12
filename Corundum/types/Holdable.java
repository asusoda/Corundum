package Corundum.types;

import Corundum.items.Enchantment;

public interface Holdable {
    public Enchantment[] getEnchantments();

    public byte getInventorySlot();

    // TODO: getLocation(): if held, get the location of the holder; otherwise, get its current location

    // TODO: isHeld(): true if an Entity has it in an inventory; false otherwise
}
