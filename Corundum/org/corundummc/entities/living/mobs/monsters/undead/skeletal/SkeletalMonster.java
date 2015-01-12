package org.corundummc.entities.living.mobs.monsters.undead.skeletal;

import net.minecraft.entity.monster.EntitySkeleton;

import org.corundummc.entities.living.mobs.monsters.undead.UndeadMonster;
import org.corundummc.entities.living.mobs.monsters.undead.skeletal.Skeleton.SkeletonType;
import org.corundummc.entities.living.mobs.monsters.undead.skeletal.WitherSkeleton.WitherSkeletonType;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class SkeletalMonster<S extends SkeletalMonster<S, T>, T extends SkeletalMonster.SkeletalMonsterType<T, S>> extends UndeadMonster<S, EntitySkeleton, T> {
    protected SkeletalMonster(EntitySkeleton entityMC) {
        super(entityMC);
    }

    public static interface SkeletalMonsterTypes {
        public static final SkeletonType SKELETON = SkeletonType.TYPE;
        public static final WitherSkeletonType WITHER_SKELETON = WitherSkeletonType.TYPE;
    }

    public abstract static class SkeletalMonsterType<S extends SkeletalMonsterType<S, I>, I extends SkeletalMonster<I, S>> extends UndeadMonsterType<S, EntitySkeleton, I> {
        protected SkeletalMonsterType(int data) {
            super(51, data);

            addValueAs(SkeletalMonsterType.class);
        }

        // abstract utilities

        // overridden utilities
        @Override
        public I fromMC(EntitySkeleton entityMC) {
            /*  */
            if (entityMC.getSkeletonType() == 0)
                return (I) new Skeleton(entityMC);
            else
                return (I) new WitherSkeleton(entityMC);
        }

        // pseudo-enum utilities
        public static SkeletalMonsterType<?, ?> getByID(int id) {
            return getByID(SkeletalMonsterType.class, id);
        }

        public static SkeletalMonsterType<?, ?> getByID(int id, int data) {
            return getByID(SkeletalMonsterType.class, id, data);
        }

        public static SkeletalMonsterType<?, ?>[] values() {
            return values(SkeletalMonsterType.class);
        }
    }

    // type utilities

    // instance utilities
}