package Corundum.world;

import Corundum.exceptions.CorundumException;

public class Zone {
    /** <b><i>DEV NOTES:</b></i><br>
     * This location is given the lowest x, lowest y, and lowest z coordinates from each of the two locations given to the constructor. */
    private Location low;
    /** <b><i>DEV NOTES:</b></i><br>
     * This location is given the highest x, highest y, and highest z coordinates from each of the two locations given to the constructor. */
    private Location high;

    public Zone(Location location1, Location location2) {
        // TODO when Location is complete:
        // - if location1 and location2 are in different worlds, throw a ZoneCornersInDifferentWorldsException
        // - initialize "low" and "high" as described in the DEV NOTES Javadoc comments above
        if (location1.getWorld() == location2.getWorld()) {
            if (location1.getX() > location2.getX() && location1.getY() > location2.getY() && location1.getZ() > location2.getZ()) {
                this.high = location1;
                this.low = location2;
            } else {
                this.high = location2;
                this.low = location1;
            }
        } else {
            //If the worlds are different, throw an exception.
            throw new ZoneCornersInDifferentWorldsException(location1, location2);
        }
    }

    public class ZoneCornersInDifferentWorldsException extends CorundumException {
        private static final long serialVersionUID = -1202714861919120683L;

        public ZoneCornersInDifferentWorldsException(Location location1, Location location2) {
            super("The corners of a new zone were given in two different worlds!", "zone in two worlds", "Location #1: " + location1.toString(), "Location #2: "
                    + location2.toString());
        }

    }

    public int getBlockCountInZone() {
        int xLength = this.high.getBlockX() - this.low.getBlockX();
        int yLength = this.high.getBlockY() - this.low.getBlockY();
        int zLength = this.high.getBlockZ() - this.low.getBlockZ();

        return xLength * yLength * zLength;
    }

    public float getVolume() {
        float xLength = this.high.getX() - this.low.getX();
        float yLength = this.high.getY() - this.low.getY();
        float zLength = this.high.getZ() - this.low.getZ();

        return xLength * yLength * zLength;
    }

    // TODO: utilities
}
