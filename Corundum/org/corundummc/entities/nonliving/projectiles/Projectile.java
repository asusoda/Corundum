package org.corundummc.entities.nonliving.projectiles;

import org.corundummc.entities.nonliving.NonLivingEntity;
import org.corundummc.entities.nonliving.projectiles.Fireball.FireballType;

/** Base for projectile non-living entities */
public class Projectile extends NonLivingEntity {
    protected Projectile(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }

    /** This class is used to represent the different types of {@link Projectile}s. <br>
     * <br>
     * This list of different types not only includes those types of mobs differentiated by different I.D.s, but also many of those differentiated by different data values;
     * for example, {@link #object object2} and {@link #object3 object4} are both represented as separate types despite the fact that they both have the same I.D. value.
     * 
     * @param <T>
     *            is a self-parameterization; <b><tt>T</b></tt> is the same type as the type of this instance. */
    public static class ProjectileType<T extends NonLivingEntityType<T>> extends NonLivingEntityType<T> {

        // throwable projectiles
        @SuppressWarnings("rawtypes")
        public static final ProjectileType<?> SNOWBALL = new ProjectileType(11) {
            @Override
            public Snowball create() {
                return new Snowball();
            }
        };
        @SuppressWarnings("rawtypes")
        public static final ProjectileType<?> ENDER_PEARL = new ProjectileType(14) {
            @Override
            public EnderPearl create() {
                return new EnderPearl();
            }
        };
        @SuppressWarnings("rawtypes")
        public static final ProjectileType<?> EYE_OF_ENDER = new ProjectileType(15) {
            @Override
            public EyeOfEnder create() {
                return new EyeOfEnder();
            }
        };
        @SuppressWarnings("rawtypes")
        public static final ProjectileType<?> SPLASH_POTION = new ProjectileType(16) {
            @Override
            public SplashPotion create() {
                return new SplashPotion();
            }
        };
        @SuppressWarnings("rawtypes")
        public static final ProjectileType<?> BOTTLE_O_ENCHANTING = new ProjectileType(17) {
            @Override
            public BottleOEnchanting create() {
                return new BottleOEnchanting();
            }
        };

        // fired projectiles
        @SuppressWarnings("rawtypes")
        public static final ProjectileType<?> LARGE_FIREBALL = new ProjectileType(12) {
            @Override
            public LargeFireball create() {
                return new LargeFireball();
            }
        };
        @SuppressWarnings("rawtypes")
        public static final ProjectileType<?> SMALL_FIREBALL = new ProjectileType(13) {
            @Override
            public SmallFireball create() {
                return new SmallFireball();
            }
        };
        public static final ProjectileType<?> ARROW = new ProjectileType(10) {
            @Override
            public Arrow create() {
                return new Arrow();
            }
        };
        @SuppressWarnings("rawtypes")
        public static final ProjectileType<?> WITHER_SKULL = new ProjectileType(19) {
            @Override
            public WitherSkull create() {
                return new WitherSkull();
            }
        };

        protected ProjectileType(int id) {
            super(id);

            addValueAs(Projectile.class);
        }

        @Override
        public Projectile create() {
            return (Projectile) super.create();
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
