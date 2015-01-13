package org.corundummc.entities.living.mobs.monsters.bosses;

import net.minecraft.entity.boss.EntityWither;
import org.corundummc.entities.living.mobs.monsters.bosses.Boss.BossType;

public class Wither extends Boss<Wither, EntityWither, Wither.WitherType> {
    public Wither() {
        super(new EntityWither(null));
    }

    protected Wither(EntityWither entityMC) {
        super(entityMC);
    }

    protected static class WitherType extends BossType<WitherType, EntityWither, Wither> {
        public static final WitherType TYPE = new WitherType();

        private WitherType() {
            super(64);
        }

        // overridden utilities
        @Override
        public Wither create() {
            return new Wither();
        }

        @Override
        public Wither fromMC(EntityWither entityMC) {
            return new Wither(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public WitherType getType() {
        return WitherType.TYPE;
    }
}