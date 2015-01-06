package org.corundummc.entities.living.mobs;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;

import org.corundummc.entities.Entity;
import org.corundummc.entities.living.LivingEntity;
import org.corundummc.entities.living.LivingEntity.LivingEntityType;
import org.corundummc.entities.living.mobs.animals.Animal;
import org.corundummc.entities.living.mobs.animals.Animal.AnimalType;
import org.corundummc.entities.living.mobs.golems.Golem.GolemType;
import org.corundummc.entities.living.mobs.villagers.Villager.VillagerType;
import org.corundummc.items.Item;

/** This class represents a "mob", an autonomous A.I.-controlled {@link LivingEntity}. This includes things like {@link Monster monsters}, {@link Animal animals}, and even
 * {@link Boss bosses}.
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class Mob<S extends Mob<S, MC, T>, MC extends EntityLiving, T extends Mob.MobType<T, MC, S>> extends LivingEntity<S, MC, T> {
    protected Mob(MC entityMC) {
        super(entityMC);
    }

    public static interface MobTypes {
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
}
