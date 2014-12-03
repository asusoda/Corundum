package org.corundummc.types;

import org.corundummc.world.Location;

public interface Physical extends Typed {
    public abstract String getCustomName();

    public abstract Location getLocation();
}
