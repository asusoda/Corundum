package org.corundummc.world;

public class BoundingBox {
    private float min_x, min_y, min_z, max_x, max_y, max_z;

    public BoundingBox(float min_x, float min_y, float min_z, float max_x, float max_y, float max_z) {
        this.min_x = min_x;
        this.min_y = min_y;
        this.min_z = min_z;
        this.max_x = max_x;
        this.max_y = max_y;
        this.max_z = max_z;
    }

    // TODO: make utilities
}
