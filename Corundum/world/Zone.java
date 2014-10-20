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

        } else {
            //If the worlds are different, throw an exception.
            throw new ZoneCornersInDifferentWorldsException(location1, location2);
        }
    }

    public class ZoneCornersInDifferentWorldsException extends CorundumException {
        private static final long serialVersionUID = -1202714861919120683L;

        public ZoneCornersInDifferentWorldsException(Location location1, Location location2, Object... additional_information) {
            super("The corners of a new zone were given in two different worlds!", "zone in two worlds", "Location #1: " + location1.toString(), "Location #2: "
                    + location2.toString());
        }

    }

    // TODO: utilities
}
