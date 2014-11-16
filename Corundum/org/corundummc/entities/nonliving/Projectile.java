package org.corundummc.entities.nonliving;

import org.corundummc.entities.NonLivingEntity;
import org.corundummc.entities.Velocity;
import org.corundummc.entities.Entity.EntityType;
import org.corundummc.world.Location;

/** Base for projectile non-living entities */
public class Projectile extends NonLivingEntity {
    public Projectile(ProjectileType type, Location location) {
        super(type, location);
    }

    public static class ProjectileType<T extends ProjectileType<T>> extends NonLivingEntityType<T> {
        // TODO: list other ProjectileTypes
        @SuppressWarnings("rawtypes")
        public static final ProjectileType<?> ARROW = new ProjectileType(NonLivingEntityType.ARROW),
        GHAST_FIREBALL = new ProjectileType(NonLivingEntityType.GHAST_FIREBALL),
        BLAZE_FIREBALL = new ProjectileType(NonLivingEntityType.BLAZE_FIREBALL),
        // thrown projectiles
        ENDER_PEARL = new ProjectileType(NonLivingEntityType.ENDER_PEARL),
        SNOWBALL = new ProjectileType(NonLivingEntityType.SNOWBALL),
        EGG = new ProjectileType(NonLivingEntityType.EGG);

        protected ProjectileType(NonLivingEntityType<T> parent) {
            super(parent);
        }
    }
}
