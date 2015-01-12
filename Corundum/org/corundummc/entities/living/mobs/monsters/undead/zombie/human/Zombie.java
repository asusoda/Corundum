package org.corundummc.entities.living.mobs.monsters.undead.zombie.human;

import net.minecraft.entity.monster.EntityZombie;
import org.corundummc.entities.living.mobs.monsters.undead.zombie.human.HumanZombie.HumanZombieType;

public class Zombie extends HumanZombie<Zombie, Zombie.ZombieType> {
    public Zombie() {
        super(new EntityZombie(null));
    }

    protected Zombie(EntityZombie entityMC) {
        super(entityMC);
    }

    protected static class ZombieType extends HumanZombieType<ZombieType, Zombie> {
        public static final ZombieType TYPE = new ZombieType();

        private ZombieType() {
            super(0);
        }

        // overridden utilities
        @Override
        public Zombie create() {
            return new Zombie();
        }

        @Override
        public Zombie fromMC(EntityZombie entityMC) {
            return new Zombie(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public ZombieType getType() {
        return ZombieType.TYPE;
    }
}