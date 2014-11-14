package org.corundummc.entities;

import org.corundummc.entities.Entity;

public class Velocity {
    private final Entity target;
    private double x, y, z;

    public Velocity(double x, double y, double z) {
        this(x, y, z, null);
    }

    Velocity(double x, double y, double z, Entity target) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.target = target;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;

        // update the target Entity's velocity to reflect the change
        if (target != null)
            target.setVelocity(this);
    }

    public void setY(double y) {
        this.y = y;

        // update the target Entity's velocity to reflect the change
        if (target != null)
            target.setVelocity(this);
    }

    public void setZ(double z) {
        this.z = z;

        // update the target Entity's velocity to reflect the change
        if (target != null)
            target.setVelocity(this);
    }

    // TODO: add more utils like getPitch() and getYaw() to calculate the pitch and yaw of the movement
}
