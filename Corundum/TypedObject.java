package Corundum;

import Corundum.world.Location;

public interface TypedObject {
    public String getCustomName();

    public Location getLocation();

    public IDedType getType();
}
