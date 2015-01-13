package org.corundummc.utils.interfaces;

import org.corundummc.items.Item;
import org.corundummc.world.Location;

public interface Physical {
    public Item[] getDrops();

    public Location getLocation();
}
