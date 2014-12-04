package org.corundummc.entities.nonliving;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;

import org.corundummc.entities.LivingEntity;
import org.corundummc.entities.NonLivingEntity;
import org.corundummc.entities.Velocity;
import org.corundummc.entities.Entity.EntityType;
import org.corundummc.types.Creatable;
import org.corundummc.world.Location;

/** Base for projectile non-living entities */
public class Projectile extends NonLivingEntity {
    protected Projectile(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }

    public Projectile(ProjectileType type) {
        super(type);
    }

    public static class ProjectileType<T extends NonLivingEntityType<T>> extends NonLivingEntityType<T> {

        public static final ProjectileType ARROW = new ProjectileType(10) {
            public Creatable create() {
                /* TODO TEST: you can pass null into here without getting immediate NPEs in the constructor as far as I can tell, but I noticed that if you pass a null world,
                 * the dimension I.D. will not be set and the dimension I.D. is also not set in Minecraft's Entity.setWorld(), so that may cause issues */
                return new Projectile(new EntityArrow(null));
            }

        }, // TODO: override create() methods for each type here
                SNOWBALL = new ProjectileType(), LARGE_FIREBALL = new ProjectileType(), SMALL_FIREBALL = new ProjectileType(),
                ENDER_PEARL = new ProjectileType(),
                EYE_OF_ENDER = new ProjectileType(), SPLASH_POTION = new ProjectileType(), BOTTLE_O_ENCHANTING = new ProjectileType(), WITHER_SKULL = new ProjectileType();

        protected ProjectileType() {
            super();
        }

        protected ProjectileType(int id) {
            super(id);
        }

        // pseudo-enum utilities
        @SuppressWarnings("unchecked")
        public static ProjectileType<?> getByID(int id) {
            return getByID(ProjectileType.class, id);
        }

        @SuppressWarnings("unchecked")
        public static ProjectileType<?> getByID(int id, int data) {
            return getByID(ProjectileType.class, id, data);
        }

        @SuppressWarnings("unchecked")
        public static ProjectileType<?>[] values() {
            return values(ProjectileType.class);
        }
    }
}
