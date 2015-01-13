package org.corundummc.entities.living.mobs.monsters.undead.zombie;

import net.minecraft.entity.monster.EntityMob;

import org.corundummc.entities.living.mobs.monsters.undead.UndeadMonster;
import org.corundummc.entities.living.mobs.monsters.undead.zombie.Giant.GiantType;
import org.corundummc.entities.living.mobs.monsters.undead.zombie.natural.NaturalZombifiedMonster.NaturalZombifiedMonsterTypes;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class ZombifiedMonster<S extends ZombifiedMonster<S, MC, T>, MC extends EntityMob, T extends ZombifiedMonster.ZombifiedMonsterType<T, MC, S>> extends
        UndeadMonster<S, MC, T> {
    protected ZombifiedMonster(MC entityMC) {
        super(entityMC);
    }

    public static interface ZombifiedMonsterTypes extends NaturalZombifiedMonsterTypes {
        public static final GiantType GIANT = GiantType.TYPE;
    }

    public abstract static class ZombifiedMonsterType<S extends ZombifiedMonsterType<S, MC, I>, MC extends EntityMob, I extends ZombifiedMonster<I, MC, S>> extends
            UndeadMonsterType<S, MC, I> {
        protected ZombifiedMonsterType(int id, int data) {
            super(id, data);

            addValueAs(ZombifiedMonsterType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        public static ZombifiedMonsterType<?, ?, ?> getByID(int id) {
            return getByID(ZombifiedMonsterType.class, id);
        }

        public static ZombifiedMonsterType<?, ?, ?> getByID(int id, int data) {
            return getByID(ZombifiedMonsterType.class, id, data);
        }

        public static ZombifiedMonsterType<?, ?, ?>[] values() {
            return values(ZombifiedMonsterType.class);
        }
    }

    // type utilities

    // instance utilities
}