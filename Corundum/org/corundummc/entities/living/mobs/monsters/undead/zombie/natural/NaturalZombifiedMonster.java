package org.corundummc.entities.living.mobs.monsters.undead.zombie.natural;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;

import org.corundummc.entities.living.mobs.monsters.undead.UndeadMonster;
import org.corundummc.entities.living.mobs.monsters.undead.zombie.natural.ZombiePigman.ZombiePigmanType;
import org.corundummc.entities.living.mobs.monsters.undead.zombie.natural.human.HumanZombie.HumanZombieTypes;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class NaturalZombifiedMonster<S extends NaturalZombifiedMonster<S, MC, T>, MC extends EntityMob, T extends NaturalZombifiedMonster.NaturalZombifiedMonsterType<T, MC, S>>
        extends UndeadMonster<S, MC, T> {
    protected NaturalZombifiedMonster(MC entityMC) {
        super(entityMC);
    }

    public static interface NaturalZombifiedMonsterTypes extends HumanZombieTypes {
        public static final ZombiePigmanType ZOMBIE_PIGMAN = ZombiePigmanType.TYPE;
    }

    public abstract static class NaturalZombifiedMonsterType<S extends NaturalZombifiedMonsterType<S, MC, I>, MC extends EntityMob, I extends NaturalZombifiedMonster<I, MC, S>>
            extends UndeadMonsterType<S, MC, I> {
        protected NaturalZombifiedMonsterType(int id, int data) {
            super(id, data);

            addValueAs(NaturalZombifiedMonsterType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        public static NaturalZombifiedMonsterType<?, ?, ?> getByID(int id) {
            return getByID(NaturalZombifiedMonsterType.class, id);
        }

        public static NaturalZombifiedMonsterType<?, ?, ?> getByID(int id, int data) {
            return getByID(NaturalZombifiedMonsterType.class, id, data);
        }

        public static NaturalZombifiedMonsterType<?, ?, ?>[] values() {
            return values(NaturalZombifiedMonsterType.class);
        }
    }

    // type utilities

    // instance utilities
}