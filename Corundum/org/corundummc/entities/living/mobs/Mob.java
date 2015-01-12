package org.corundummc.entities.living.mobs;

import net.minecraft.entity.EntityLiving;

import org.corundummc.entities.living.LivingEntity;
import org.corundummc.entities.living.Player;
import org.corundummc.entities.living.mobs.animals.Animal;
import org.corundummc.entities.living.mobs.animals.Animal.AnimalTypes;
import org.corundummc.entities.living.mobs.golems.Golem.GolemTypes;
import org.corundummc.entities.living.mobs.monsters.Monster.MonsterTypes;
import org.corundummc.entities.living.mobs.villagers.Villager.VillagerTypes;
import org.corundummc.utils.interfaces.Nameable;

/** This class represents a "mob", an autonomous A.I.-controlled {@link LivingEntity}. This includes things like {@link Monster monsters}, {@link Animal animals}, and even
 * {@link Boss bosses}, but not {@link Player}s.
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class Mob<S extends Mob<S, MC, T>, MC extends EntityLiving, T extends Mob.MobType<T, MC, S>> extends LivingEntity<S, MC, T> implements Nameable<S> {
    protected Mob(MC entityMC) {
        super(entityMC);
    }

    public static interface MobTypes extends AnimalTypes, GolemTypes, MonsterTypes, VillagerTypes {
        // TODO
    }

    public abstract static class MobType<S extends MobType<S, MC, I>, MC extends EntityLiving, I extends Mob<I, MC, S>> extends LivingEntityType<S, MC, I> {
        protected MobType(int id, int data) {
            super(id, data);

            addValueAs(MobType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        @SuppressWarnings("rawtypes")
        public static MobType getByID(int id) {
            return getByID(MobType.class, id);
        }

        @SuppressWarnings("rawtypes")
        public static MobType getByID(int id, int data) {
            return getByID(MobType.class, id, data);
        }

        @SuppressWarnings("rawtypes")
        public static MobType[] values() {
            return values(MobType.class);
        }
    }

    // type utilities

    // instance utilities
    @Override
    public String getCustomName() {
        return entityMC.getCustomNameTag();
    }

    @SuppressWarnings("unchecked")
    @Override
    public S setCustomName(String new_name) {
        entityMC.setCustomNameTag(new_name);
        return (S) this;
    }
}
