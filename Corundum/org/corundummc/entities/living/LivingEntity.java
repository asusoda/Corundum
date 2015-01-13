package org.corundummc.entities.living;

import net.minecraft.entity.EntityLivingBase;

import org.corundummc.entities.Entity;
import org.corundummc.entities.living.PlayerEntity.PlayerEntityType;
import org.corundummc.entities.living.mobs.Mob.MobTypes;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class LivingEntity<S extends LivingEntity<S, MC, T>, MC extends EntityLivingBase, T extends LivingEntity.LivingEntityType<T, MC, S>> extends Entity<S, MC, T> {
    protected LivingEntity(MC entityMC) {
        super(entityMC);
    }

    public static interface LivingEntityTypes extends MobTypes {
        public static final PlayerEntityType PLAYER = PlayerEntityType.TYPE;
    }

    public abstract static class LivingEntityType<S extends LivingEntityType<S, MC, I>, MC extends EntityLivingBase, I extends LivingEntity<I, MC, S>> extends
            EntityType<S, MC, I> {
        protected LivingEntityType(int id, int data) {
            super(id, data);

            addValueAs(LivingEntityType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        public static LivingEntityType<?, ?, ?> getByID(int id) {
            return getByID(LivingEntityType.class, id);
        }

        public static LivingEntityType<?, ?, ?> getByID(int id, int data) {
            return getByID(LivingEntityType.class, id, data);
        }

        public static LivingEntityType<?, ?, ?>[] values() {
            return values(LivingEntityType.class);
        }
    }

    // type utilities

    // instance utilities
}
