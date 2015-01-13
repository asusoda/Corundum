package org.corundummc.entities.living.mobs.monsters;

import net.minecraft.entity.EntityLiving;

import org.corundummc.entities.Entity;
import org.corundummc.entities.living.LivingEntity;
import org.corundummc.entities.living.Player;
import org.corundummc.entities.living.mobs.HostileMob;
import org.corundummc.entities.living.mobs.Mob;
import org.corundummc.entities.living.mobs.animals.Animal;
import org.corundummc.entities.living.mobs.animals.domestic.tameable.pet.Wolf;
import org.corundummc.entities.living.mobs.monsters.Creeper.CreeperType;
import org.corundummc.entities.living.mobs.monsters.Enderman.EndermanType;
import org.corundummc.entities.living.mobs.monsters.Witch.WitchType;
import org.corundummc.entities.living.mobs.monsters.bosses.Boss.BossTypes;
import org.corundummc.entities.living.mobs.monsters.bugs.Bug.BugTypes;
import org.corundummc.entities.living.mobs.monsters.cuboid.CuboidMonster.CuboidMonsterTypes;
import org.corundummc.entities.living.mobs.monsters.flying.FlyingMonster.FlyingMonsterTypes;
import org.corundummc.entities.living.mobs.monsters.undead.UndeadMonster.UndeadMonsterTypes;

import com.google.common.eventbus.AllowConcurrentEvents;

/** This class represents a {@link Mob mob} that is meant to be a natural enemy to a {@link Player player}.<br>
 * NOTE: {@link Wolf Wolves} are actually considered {@link Animal animals}, not {@link Monster monsters}, despite the fact that they may attack {@link Player players}. Any
 * {@link Mob mob} that may potentially attack something else is, however, considered a {@link HostileMob hostile mob}, including {@link Wolf wolves}.
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class Monster<S extends Monster<S, MC, T>, MC extends EntityLiving, T extends Monster.MonsterType<T, MC, S>> extends Mob<S, MC, T> implements HostileMob {
    protected Monster(MC entityMC) {
        super(entityMC);
    }

    public static interface MonsterTypes extends BossTypes, BugTypes, CuboidMonsterTypes, FlyingMonsterTypes, UndeadMonsterTypes/* 1.8:, GuardianTypes */{
        public static final CreeperType CREEPER = CreeperType.TYPE;
        public static final EndermanType ENDERMAN = EndermanType.TYPE;
        public static final WitchType WITCH = WitchType.TYPE;
    }

    public abstract static class MonsterType<S extends MonsterType<S, MC, I>, MC extends EntityLiving, I extends Monster<I, MC, S>> extends MobType<S, MC, I> {
        protected MonsterType(int id, int data) {
            super(id, data);

            addValueAs(MonsterType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        @SuppressWarnings("rawtypes")
        public static MonsterType getByID(int id) {
            return getByID(MonsterType.class, id);
        }

        @SuppressWarnings("rawtypes")
        public static MonsterType getByID(int id, int data) {
            return getByID(MonsterType.class, id, data);
        }

        @SuppressWarnings("rawtypes")
        public static MonsterType[] values() {
            return values(MonsterType.class);
        }
    }

    // type utilities

    // instance utilities
    @Override
    public void attack(LivingEntity<?, ?, ?> target) {
        // TODO TEST
        entityMC.setAttackTarget(target.MC());
    }

    @Override
    public LivingEntity<?, ?, ?> getTarget() {
        return (LivingEntity<?, ?, ?>) Entity.fromMC(entityMC.getAttackTarget());
    }
}