package org.corundummc.world;

import org.corundummc.exceptions.CorundumException;
import org.corundummc.world.Block.BlockType;

public class Zone {
    /** <b><i>DEV NOTES:</b></i><br>
     * This location is given the lowest x, lowest y, and lowest z coordinates from each of the two locations given to the constructor. */
    private Location low;
    /** <b><i>DEV NOTES:</b></i><br>
     * This location is given the highest x, highest y, and highest z coordinates from each of the two locations given to the constructor. */
    private Location high;

    public Zone(Location location1, Location location2) throws ZoneCornersInDifferentWorldsException {
        // if the two corners of the zone are in different worlds, throw an exception
        if (!location1.getWorld().equals(location2.getWorld()))
            throw new ZoneCornersInDifferentWorldsException(location1, location2);

        // put all the lowest-valued coordinates into the "low" location
        low =
                new Location(location1.getX() <= location2.getX() ? location1.getX() : location2.getX(), location1.getY() <= location2.getY() ? location1.getY() : location2
                        .getY(), location1.getZ() <= location2.getZ() ? location1.getZ() : location2.getZ(), location1.getWorld());
        // put all the highest-valued coordinates into the "high" location
        high =
                new Location(location1.getX() >= location2.getX() ? location1.getX() : location2.getX(), location1.getY() >= location2.getY() ? location1.getY() : location2
                        .getY(), location1.getZ() >= location2.getZ() ? location1.getZ() : location2.getZ(), location1.getWorld());
    }

    public class ZoneCornersInDifferentWorldsException extends CorundumException {
        private static final long serialVersionUID = -1202714861919120683L;

        public ZoneCornersInDifferentWorldsException(Location location1, Location location2) {
            super("The corners of a new zone were given in two different worlds!", "zone in two worlds", "Location #1: " + location1.toString(), "Location #2: "
                    + location2.toString());
        }

    }

    public double getVolume() {
        double xLength = high.getX() - low.getX();
        double yLength = high.getY() - low.getY();
        double zLength = high.getZ() - low.getZ();

        return xLength * yLength * zLength;
    }

    public void fillWithBlock(@SuppressWarnings("rawtypes") BlockType block) {
        // This Location is reused so as not to spam the VM with Location objects.
        Location currLocation = new Location(0, 0, 0, low.getWorld());

        for (int x = low.getBlockX(); x == high.getBlockX(); x++) {
            for (int y = low.getBlockY(); y == high.getBlockY(); y++) {
                for (int z = low.getBlockZ(); z == high.getBlockZ(); z++) {
                    currLocation.setX(x);
                    currLocation.setY(y);
                    currLocation.setZ(z);
                    low.getWorld().setBlock(block, currLocation);
                }
            }
        }
    }

    /* TODO TEMP CMT public void fillWithAir() { fillWithBlock(Block.BlockType.AIR); } */
}
