package org.corundummc.entities.living.mobs.monsters;

import net.minecraft.entity.monster.EntityCreeper;
import org.corundummc.entities.living.mobs.monsters.Monster.MonsterType;

public class Creeper extends Monster<Creeper, EntityCreeper, Creeper.CreeperType> {
    public Creeper() {
        super(new EntityCreeper(null));
    }

    protected Creeper(EntityCreeper entityMC) {
        super(entityMC);
    }

    protected static class CreeperType extends MonsterType<CreeperType, EntityCreeper, Creeper> {
        public static final CreeperType TYPE = new CreeperType();

        private CreeperType() {
            super(/* TODO */0, -1);
        }

        // overridden utilities
        @Override
        public Creeper create() {
            return new Creeper();
        }

        @Override
        public Creeper fromMC(EntityCreeper entityMC) {
            return new Creeper(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public CreeperType getType() {
        return CreeperType.TYPE;
    }
}