package Corundum.items;

import Corundum.world.Location;

public interface Material {
    public String getCustomName();
    
    public Enchantment[] getEnchantments();
    
    public byte getInventorySlot();
    
    public Location getLocation();

    public MaterialType getType();
}
