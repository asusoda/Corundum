package org.corundummc.entities.living.mobs.monsters.undead.zombie.natural.human;

import net.minecraft.entity.monster.EntityZombie;

import org.corundummc.entities.living.mobs.monsters.undead.zombie.natural.human.HumanZombie.HumanZombieType;

public class ZombifiedVillager extends HumanZombie<ZombifiedVillager, ZombifiedVillager.ZombifiedVillagerType> {
    public ZombifiedVillager() {
        super(new EntityZombie(null));
    }

    protected ZombifiedVillager(EntityZombie entityMC) {
        super(entityMC);
    }

    protected static class ZombifiedVillagerType extends HumanZombieType<ZombifiedVillagerType, ZombifiedVillager> {
        public static final ZombifiedVillagerType TYPE = new ZombifiedVillagerType();

        private ZombifiedVillagerType() {
            super(1);
        }

        // overridden utilities
        @Override
        public ZombifiedVillager create() {
            return new ZombifiedVillager();
        }

        @Override
        public ZombifiedVillager fromMC(EntityZombie entityMC) {
            return new ZombifiedVillager(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public ZombifiedVillagerType getType() {
        return ZombifiedVillagerType.TYPE;
    }
}