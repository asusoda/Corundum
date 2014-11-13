package Corundum.entities.nonliving;

import Corundum.entities.NonLivingEntity;
import Corundum.entities.Velocity;
import Corundum.entities.Entity.EntityType;
import Corundum.world.Location;

/** Base for projectile non-living entities */
public class Projectile extends NonLivingEntity {
    public Projectile(ProjectileType type, Location location) {
        super(type, location);
    }

    public static class ProjectileType<T extends ProjectileType<T>> extends NonLivingEntityType<T> {
        // TODO: list other ProjectileTypes
        @SuppressWarnings("rawtypes")
        public static final ProjectileType<?> ARROW = new ProjectileType(NonLivingEntityType.ARROW);

        protected ProjectileType(NonLivingEntityType<T> parent) {
            super(parent);
        }
    }
}
