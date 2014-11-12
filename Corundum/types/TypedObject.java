package Corundum.types;

import Corundum.world.Location;

public abstract class TypedObject {
    public abstract String getCustomName();

    public abstract Location getLocation();

    public abstract IDedType getType();
}
