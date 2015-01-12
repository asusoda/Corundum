package org.corundummc.entities.living.mobs.monsters.undead.skeletal;

import net.minecraft.entity.monster.EntitySkeleton;
import org.corundummc.entities.living.mobs.monsters.undead.skeletal.SkeletalMonster.SkeletalMonsterType;

public class WitherSkeleton extends SkeletalMonster<WitherSkeleton, WitherSkeleton.WitherSkeletonType> {
    public WitherSkeleton() {
        super(new EntitySkeleton(null));
    }

    protected WitherSkeleton(EntitySkeleton entityMC) {
        super(entityMC);
    }

    protected static class WitherSkeletonType extends SkeletalMonsterType<WitherSkeletonType, WitherSkeleton> {
        public static final WitherSkeletonType TYPE = new WitherSkeletonType();

        private WitherSkeletonType() {
            super(1);
        }

        // overridden utilities
        @Override
        public WitherSkeleton create() {
            return new WitherSkeleton();
        }

        @Override
        public WitherSkeleton fromMC(EntitySkeleton entityMC) {
            return new WitherSkeleton(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public WitherSkeletonType getType() {
        return WitherSkeletonType.TYPE;
    }
}