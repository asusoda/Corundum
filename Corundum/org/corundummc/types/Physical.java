package org.corundummc.types;

import org.corundummc.world.Location;

public interface Physical extends Typed {
    public abstract String getCustomName();

    // TODO: getLocation(): if held, get the location of the holder; otherwise, get its current location
    public abstract Location getLocation();
}
