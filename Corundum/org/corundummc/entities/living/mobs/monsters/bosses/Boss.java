package org.corundummc.entities.living.mobs.monsters.bosses;

import net.minecraft.entity.EntityLiving;

import org.corundummc.entities.living.mobs.monsters.Monster;
import org.corundummc.entities.living.mobs.monsters.bosses.EnderDragon.EnderDragonType;
import org.corundummc.entities.living.mobs.monsters.bosses.Wither.WitherType;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class Boss<S extends Boss<S, MC, T>, MC extends EntityLiving, T extends Boss.BossType<T, MC, S>> extends Monster<S, MC, T> {
    protected Boss(MC entityMC) {
        super(entityMC);
    }

    public static interface BossTypes {
        public static final EnderDragonType ENDER_DRAGON = EnderDragonType.TYPE;
        public static final WitherType WITHER = WitherType.TYPE;
    }

    public abstract static class BossType<S extends BossType<S, MC, I>, MC extends EntityLiving, I extends Boss<I, MC, S>> extends MonsterType<S, MC, I> {
        protected BossType(int id) {
            super(id, -1);

            addValueAs(BossType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        public static BossType<?, ?, ?> getByID(int id) {
            return getByID(BossType.class, id);
        }

        public static BossType<?, ?, ?> getByID(int id, int data) {
            return getByID(BossType.class, id, data);
        }

        public static BossType<?, ?, ?>[] values() {
            return values(BossType.class);
        }
    }

    // type utilities

    // instance utilities
}