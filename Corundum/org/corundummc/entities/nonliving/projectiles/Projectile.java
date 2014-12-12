package org.corundummc.entities.nonliving.projectiles;

import net.minecraft.entity.projectile.EntityArrow;

import org.corundummc.entities.nonliving.NonLivingEntity;
import org.corundummc.entities.nonliving.NonLivingEntity.NonLivingEntityType;
import org.corundummc.entities.nonliving.projectiles.Fireball.FireballType;
import org.corundummc.types.Creatable;

/** Base for projectile non-living entities */
public class Projectile extends NonLivingEntity {
    protected Projectile(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }

    public static abstract class ProjectileType<T extends NonLivingEntityType<T>> /* extends NonLivingEntityType<T> */{
        // thrown projectiles
        public static final ProjectileType<?> SNOWBALL = new ProjectileType();
        public static final ProjectileType<?> ENDER_PEARL = new ProjectileType();
        public static final ProjectileType<?> EYE_OF_ENDER = new ProjectileType();
        public static final ProjectileType<?> SPLASH_POTION = new ProjectileType();
        public static final ProjectileType<?> BOTTLE_O_ENCHANTING = new ProjectileType();

        // fired projectiles
        public static final ProjectileType<?> LARGE_FIREBALL = FireballType.LARGE_FIREBALL;
        public static final ProjectileType<?> SMALL_FIREBALL = FireballType.SMALL_FIREBALL;
        public static final ProjectileType<?> ARROW = new ProjectileType(10) {
            public Creatable create() {
                /* TODO TEST: you can pass null into here without getting immediate NPEs in the constructor as far as I can tell, but I noticed that if you pass a null world,
                 * the dimension I.D. will not be set and the dimension I.D. is also not set in Minecraft's Entity.setWorld(), so that may cause issues */
                return new Projectile(new EntityArrow(null));
            }

        };// TODO: override create() methods for each type here
        public static final ProjectileType<?> WITHER_SKULL = new ProjectileType();

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
