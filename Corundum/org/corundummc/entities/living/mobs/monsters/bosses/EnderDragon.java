package org.corundummc.entities.living.mobs.monsters.bosses;

import net.minecraft.entity.boss.EntityDragon;
import org.corundummc.entities.living.mobs.monsters.bosses.Boss.BossType;

public class EnderDragon extends Boss<EnderDragon, EntityDragon, EnderDragon.EnderDragonType> {
    public EnderDragon() {
        super(new EntityDragon(null));
    }

    protected EnderDragon(EntityDragon entityMC) {
        super(entityMC);
    }

    protected static class EnderDragonType extends BossType<EnderDragonType, EntityDragon, EnderDragon> {
        public static final EnderDragonType TYPE = new EnderDragonType();

        private EnderDragonType() {
            super(63);
        }

        // overridden utilities
        @Override
        public EnderDragon create() {
            return new EnderDragon();
        }

        @Override
        public EnderDragon fromMC(EntityDragon entityMC) {
            return new EnderDragon(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public EnderDragonType getType() {
        return EnderDragonType.TYPE;
    }
}