package org.corundummc.entities.living.mobs.monsters.undead.zombie;

import net.minecraft.entity.monster.EntityZombie;
import org.corundummc.entities.living.mobs.monsters.undead.zombie.ZombifiedMonster.ZombifiedMonsterType;

public class ZombiePigman extends ZombifiedMonster<ZombiePigman, EntityZombie, ZombiePigman.ZombiePigmanType> {
    public ZombiePigman() {
        super(new EntityZombie(null));
    }

    protected ZombiePigman(EntityZombie entityMC) {
        super(entityMC);
    }

    protected static class ZombiePigmanType extends ZombifiedMonsterType<ZombiePigmanType, EntityZombie, ZombiePigman> {
        public static final ZombiePigmanType TYPE = new ZombiePigmanType();

        private ZombiePigmanType() {
            super(57, -1);
        }

        // overridden utilities
        @Override
        public ZombiePigman create() {
            return new ZombiePigman();
        }

        @Override
        public ZombiePigman fromMC(EntityZombie entityMC) {
            return new ZombiePigman(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public ZombiePigmanType getType() {
        return ZombiePigmanType.TYPE;
    }
}