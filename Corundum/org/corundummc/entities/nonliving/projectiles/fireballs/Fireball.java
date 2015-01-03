package org.corundummc.entities.nonliving.projectiles.fireballs;

import net.minecraft.entity.projectile.EntityFireball;

import org.corundummc.entities.Entity.EntityType;
import org.corundummc.entities.nonliving.projectiles.Projectile;
import org.corundummc.entities.nonliving.projectiles.Projectile.ProjectileType;
import org.corundummc.entities.nonliving.projectiles.fireballs.LargeFireball.LargeFireballType;
import org.corundummc.entities.nonliving.projectiles.fireballs.SmallFireball.SmallFireballType;

// TODO: when available, link Blazes, Ghasts, and other Minecraft types mentioned in the Javadoc below
/** This class represents a fireball {@link Projectile}. Fireballs can come from various sources, including Ghasts, Blazes, or Dispensers containing Fire Charges.
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class Fireball<S extends Fireball<S, MC, T>, MC extends EntityFireball, T extends Fireball.FireballType<T, MC, S>> extends Projectile<S, MC, T> {
    protected Fireball(MC entityMC) {
        super(entityMC);
    }

    public static interface FireballTypes {
        public static final LargeFireballType LARGE_FIREBALL = LargeFireballType.TYPE;
        public static final SmallFireballType SMALL_FIREBALL = SmallFireballType.TYPE;
    }

    public abstract static class FireballType<S extends FireballType<S, MC, I>, MC extends EntityFireball, I extends Fireball<I, MC, S>> extends ProjectileType<S, MC, I> {
        protected FireballType(int id) {
            super(id);

            addValueAs(FireballType.class);
        }

        // abstract utilities
        public abstract boolean canExplode();

        // overridden utilities

        // pseudo-enum utilities
        @SuppressWarnings("rawtypes")
        public static FireballType getByID(int id) {
            return getByID(FireballType.class, id);
        }

        @SuppressWarnings("rawtypes")
        public static FireballType getByID(int id, int data) {
            return getByID(FireballType.class, id, data);
        }

        @SuppressWarnings("rawtypes")
        public static FireballType[] values() {
            return values(FireballType.class);
        }
    }

    // type utilities
    public boolean canExplode() {
        return getType().canExplode();
    }

    // instance utilities
}
