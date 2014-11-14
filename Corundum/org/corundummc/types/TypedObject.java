package org.corundummc.types;

import org.corundummc.world.Location;

public abstract class TypedObject {
    public abstract String getCustomName();

    public abstract Location getLocation();

    public abstract IDedType getType();
}
