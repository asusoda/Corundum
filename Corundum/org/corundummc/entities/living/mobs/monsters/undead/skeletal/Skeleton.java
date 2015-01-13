package org.corundummc.entities.living.mobs.monsters.undead.skeletal;

import net.minecraft.entity.monster.EntitySkeleton;
import org.corundummc.entities.living.mobs.monsters.undead.skeletal.SkeletalMonster.SkeletalMonsterType;

public class Skeleton extends SkeletalMonster<Skeleton, Skeleton.SkeletonType> {
    public Skeleton() {
        super(new EntitySkeleton(null));
    }

    protected Skeleton(EntitySkeleton entityMC) {
        super(entityMC);
    }

    protected static class SkeletonType extends SkeletalMonsterType<SkeletonType, Skeleton> {
        public static final SkeletonType TYPE = new SkeletonType();

        private SkeletonType() {
            super(0);
        }

        // overridden utilities
        @Override
        public Skeleton create() {
            return new Skeleton();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public SkeletonType getType() {
        return SkeletonType.TYPE;
    }
}