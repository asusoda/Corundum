package org.corundummc.types;

import org.corundummc.world.Location;

public interface PhysicalObject extends TypedObject {
    public abstract String getCustomName();

    // TODO: getLocation(): if held, get the location of the holder; otherwise, get its current location
    public abstract Location getLocation();
}
