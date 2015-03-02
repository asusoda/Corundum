package org.corundummc.world;

import java.math.BigDecimal;

import org.corundummc.entities.Entity;
import org.corundummc.entities.Direction;
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

    /** This class represents a {@link CorundumException} thrown when the two {@link Location}s given to construct a new {@link Zone} are in different {@link World}s;
     * {@link Zone}s must only occupy a single {@link World}.
     * 
     * @author REALDrummer */
    public class ZoneCornersInDifferentWorldsException extends CorundumException {
        private static final long serialVersionUID = -1202714861919120683L;

        ZoneCornersInDifferentWorldsException(Location location1, Location location2) {
            super("The corners of a new zone were given in two different worlds!", "zone in two worlds", "Location #1: " + location1.toString(), "Location #2: "
                    + location2.toString());
        }

    }

    public boolean contains(Entity entity) {
        return contains(entity.getLocation());
    }

    public boolean contains(Location location) {
        return location.getX() >= low.getX() && location.getX() <= high.getX() && location.getY() >= low.getY() && location.getY() <= high.getY()
                && location.getZ() >= low.getZ() && location.getZ() <= high.getZ();
    }

    /** This method expands the borders of this {@link Zone} in the {@link Direction} and amount given.
     * 
     * @param direction
     *            is the {@link Direction} in which this {@link Zone} will be expanded.
     * @param amount
     *            is the amount to expand this {@link Zone} is the given {@link Direction} in meters (blocks). */
    public void expand(Direction direction, double amount) {
        expandX(amount * Math.cos(Math.toRadians(direction.getYaw() + 90)) * Math.cos(Math.toRadians(-direction.getPitch())));
        expandY(amount * Math.sin(Math.toRadians(-direction.getPitch())));
        expandZ(amount * Math.sin(Math.toRadians(direction.getYaw() + 90)) * Math.cos(Math.toRadians(-direction.getPitch())));
    }

    /** This method expands this {@link Zone} the given <b><tt>amount</b></tt> in the x direction.
     * 
     * @param amount
     *            is the amount to expand the zone in the x direction. Positive values expand the zone in the positive x direction (east) while negative values expand the zone
     *            in the negative x direction (west); 0 values are ineffective. */
    public void expandX(double amount) {
        if (amount > 0)
            high.setX(high.getX() + amount);
        else if (amount < 0)
            low.setX(low.getX() + amount);  // note that amount is negative, so adding them will give a lower x
    }

    /** This method expands this {@link Zone} the given <b><tt>amount</b></tt> in the y direction.
     * 
     * @param amount
     *            is the amount to expand the zone in the y direction. Positive values expand the zone in the positive y direction (up) while negative values expand the zone
     *            in the negative y direction (down); 0 values are ineffective. */
    public void expandY(double amount) {
        if (amount > 0)
            high.setY(high.getY() + amount);
        else if (amount < 0)
            low.setY(low.getY() + amount);  // note that amount is negative, so adding them will give a lower y
    }

    /** This method expands this {@link Zone} the given <b><tt>amount</b></tt> in the z direction.
     * 
     * @param amount
     *            is the amount to expand the zone in the z direction. Positive values expand the zone in the positive z direction (south) while negative values expand the
     *            zone in the negative z direction (north); 0 values are ineffective. */
    public void expandZ(double amount) {
        if (amount > 0)
            high.setZ(high.getZ() + amount);
        else if (amount < 0)
            low.setZ(low.getZ() + amount);  // note that amount is negative, so adding them will give a lower z
    }

    /* TODO TEMP CMT public void fillWithAir() { fillWithBlock(Block.BlockType.AIR); } */

    public void fillWith(@SuppressWarnings("rawtypes") BlockType new_type) {
        replaceAll(new_type);
    }

    /** This method retrieves the {@link Location} of one specific corner of this rectangular prismatic {@link Zone}.
     * 
     * @param high_x
     *            determines whether or not the x-coordinate of the corner {@link Location} returned should represent the highest x-coordinate (eastern) edge or the lowest
     *            x-coordinate (western) edge.
     * @param high_y
     *            determines whether or not the y-coordinate of the corner {@link Location} returned should represent the highest y-coordinate (top) edge or the lowest
     *            y-coordinate (bottom) edge.
     * @param high_z
     *            determines whether or not the z-coordinate of the corner {@link Location} returned should represent the highest z-coordinate (southern) edge or the lowest
     *            z-coordinate (northern) edge.
     * @return the corner of this {@link Zone} determined by the three given <b>boolean</b>s. */
    public Location getCorner(boolean high_x, boolean high_y, boolean high_z) {
        return new Location(high_x ? high.getX() : low.getX(), high_y ? high.getY() : low.getZ(), high_z ? high.getZ() : low.getZ(), low.getWorld());
    }

    public Location getLowCorner() {
        return low.clone();
    }

    public Location getHighCorner() {
        return high.clone();
    }

    public BigDecimal getVolume() {
        return new BigDecimal(high.getX() - low.getX()).multiply(new BigDecimal(high.getY() - low.getY())).multiply(new BigDecimal(high.getZ() - low.getZ()));
    }

    /** This method determines whether or not this {@link Zone} overlaps with another given {@link Zone}.
     * 
     * @param zone
     *            is the {@link Zone} that will be checked for overlap with this {@link Zone}.
     * @return <b>true</b> if this {@link Zone} overlaps <b><tt>zone</b></tt>; <b>false</b> otherwise. */
    public boolean overlaps(Zone zone) {
        return zone.low.getX() <= high.getX() && zone.high.getX() >= high.getX() && zone.low.getY() <= high.getY() && zone.high.getY() >= high.getY()
                && zone.low.getZ() <= high.getZ() && zone.high.getZ() >= high.getZ();
    }

    /** This method replaces each block that matches all of the given {@link BlockFilter}s with a new {@link Block} of the given {@link BlockType}.
     * 
     * @param new_type
     *            is the new {@link BlockType} to replace the matching blocks with.
     * @param filters
     *            is a list of {@link BlockFilter}s used to determine which {@link Block}s in the {@link Zone} should be replaced with blocks of the new given
     *            {@link BlockType} and which should not. Only {@link Block}s that match <i>all</i> of the given {@link BlockFilter}s will be replaced. If no
     *            {@link BlockFilter}s are given, every {@link Block} in the zone will be replaced with new {@link Block}s of the given {@link BlockType}.
     * @return the number of {@link Block}s replaced with new {@link Block}s of the given {@link BlockType}. */
    @SuppressWarnings("unchecked")
    public int replaceAll(@SuppressWarnings("rawtypes") BlockType new_type, BlockFilter... filters) {
        Location location = new Location(0, 0, 0, low.getWorld());
        int blocks_replaced = 0;

        for (int x = low.getBlockX(); x <= high.getBlockX(); x++)
            for (int y = low.getBlockY(); y <= high.getBlockY(); y++)
                for (int z = low.getBlockZ(); z <= high.getBlockZ(); z++) {
                    location.setX(x);
                    location.setY(y);
                    location.setZ(z);
                    /* TODO: if possible, do this in such a way that it doesn't have to send the update information to the client for every single block individually */
                    if (BlockFilter.matches(location.getBlock(), filters)) {
                        blocks_replaced++;
                        location.getBlock().setType(new_type);
                    }
                }

        return blocks_replaced;
    }
}
