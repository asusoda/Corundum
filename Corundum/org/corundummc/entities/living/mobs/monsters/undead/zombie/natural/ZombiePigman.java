package org.corundummc.entities.living.mobs.monsters.undead.zombie.natural;

import net.minecraft.entity.monster.EntityZombie;

import org.corundummc.entities.living.mobs.monsters.undead.zombie.natural.NaturalZombifiedMonster.NaturalZombifiedMonsterType;

public class ZombiePigman extends NaturalZombifiedMonster<ZombiePigman, EntityZombie, ZombiePigman.ZombiePigmanType> {
    public ZombiePigman() {
        super(new EntityZombie(null));
    }

    protected ZombiePigman(EntityZombie entityMC) {
        super(entityMC);
    }

    protected static class ZombiePigmanType extends NaturalZombifiedMonsterType<ZombiePigmanType, EntityZombie, ZombiePigman> {
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