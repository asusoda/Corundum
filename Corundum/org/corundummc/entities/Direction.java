package org.corundummc.entities;

/**
 * Class that represents the rotation info of something, usually an entity. Deals with pitch and yaw.
 */
public class Direction {
    private float pitch, yaw;

    public Direction(float pitch, float yaw) {
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public float getYaw() {
        return this.yaw;
    }

    public void setPitch(float newPitch) {
        this.pitch = newPitch;
    }

    public void setYaw(float newYaw) {
        this.yaw = newYaw;
    }

    public String toString() {
        //format: facing [pitch], [yaw]
        return "facing " + this.pitch + ", " + this.yaw;
    }
}
