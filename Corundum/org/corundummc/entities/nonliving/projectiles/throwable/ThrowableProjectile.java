package org.corundummc.entities.nonliving.projectiles.throwable;

import net.minecraft.entity.projectile.EntityThrowable;

import org.corundummc.entities.nonliving.projectiles.Projectile;
import org.corundummc.entities.nonliving.projectiles.throwable.BottleOEnchanting.BottleOEnchantingType;
import org.corundummc.entities.nonliving.projectiles.throwable.Egg.EggType;
import org.corundummc.entities.nonliving.projectiles.throwable.EnderPearl.EnderPearlType;
import org.corundummc.entities.nonliving.projectiles.throwable.EyeOfEnder.EyeOfEnderType;
import org.corundummc.entities.nonliving.projectiles.throwable.Snowball.SnowballType;
import org.corundummc.entities.nonliving.projectiles.throwable.SplashPotion.SplashPotionType;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class ThrowableProjectile<S extends ThrowableProjectile<S, MC, T>, MC extends net.minecraft.entity.Entity, T extends ThrowableProjectile.ThrowableProjectileType<T, MC, S>>
        extends Projectile<S, MC, T> {
    protected ThrowableProjectile(MC entityMC) {
        super(entityMC);
    }

    public static interface ThrowableProjectileTypes {
        public static final EggType EGG = EggType.TYPE;
        public static final SnowballType SNOWBALL = SnowballType.TYPE;
        public static final EnderPearlType ENDER_PEARL = EnderPearlType.TYPE;
        public static final EyeOfEnderType EYE_OF_ENDER = EyeOfEnderType.TYPE;
        public static final SplashPotionType SPLASH_POTION = SplashPotionType.TYPE;
        public static final BottleOEnchantingType BOTTLE_O_ENCHANTING = BottleOEnchantingType.TYPE;
    }

    public abstract static class ThrowableProjectileType<S extends ThrowableProjectileType<S, MC, I>, MC extends net.minecraft.entity.Entity, I extends ThrowableProjectile<I, MC, S>>
            extends ProjectileType<S, MC, I> {
        protected ThrowableProjectileType(int id, int data) {
            super(id);

            addValueAs(ThrowableProjectileType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        public static ThrowableProjectileType<?, ?, ?> getByID(int id) {
            return getByID(ThrowableProjectileType.class, id);
        }

        public static ThrowableProjectileType<?, ?, ?> getByID(int id, int data) {
            return getByID(ThrowableProjectileType.class, id, data);
        }

        public static ThrowableProjectileType<?, ?, ?>[] values() {
            return values(ThrowableProjectileType.class);
        }
    }

    // type utilities

    // instance utilities
}