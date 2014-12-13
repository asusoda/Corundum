package org.corundummc.entities.nonliving.projectiles;

import net.minecraft.entity.projectile.EntitySnowball;

public class Snowball extends Projectile {
    public Snowball() {
        super(new EntitySnowball(null));
    }
}