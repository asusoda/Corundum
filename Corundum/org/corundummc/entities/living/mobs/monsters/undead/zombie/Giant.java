package org.corundummc.entities.living.mobs.monsters.undead.zombie;

import org.corundummc.entities.living.mobs.monsters.undead.zombie.ZombifiedMonster.ZombifiedMonsterType;

import net.minecraft.entity.monster.EntityGiantZombie;

public class Giant extends ZombifiedMonster<Giant, EntityGiantZombie, Giant.GiantType> {
    public Giant() {
        super(new EntityGiantZombie(null));
    }

    protected Giant(EntityGiantZombie entityMC) {
        super(entityMC);
    }

    protected static class GiantType extends ZombifiedMonsterType<GiantType, EntityGiantZombie, Giant> {
        public static final GiantType TYPE = new GiantType();

        private GiantType() {
            super(53, -1);
        }

        // overridden utilities
        @Override
        public Giant create() {
            return new Giant();
        }

        @Override
        public Giant fromMC(EntityGiantZombie entityMC) {
            return new Giant(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public GiantType getType() {
        return GiantType.TYPE;
    }
}