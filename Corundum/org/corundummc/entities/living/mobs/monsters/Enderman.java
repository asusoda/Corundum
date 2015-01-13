package org.corundummc.entities.living.mobs.monsters;

import net.minecraft.entity.monster.EntityEnderman;
import org.corundummc.entities.living.mobs.monsters.Monster.MonsterType;

public class Enderman extends Monster<Enderman, EntityEnderman, Enderman.EndermanType> {
    public Enderman() {
        super(new EntityEnderman(null));
    }

    protected Enderman(EntityEnderman entityMC) {
        super(entityMC);
    }

    protected static class EndermanType extends MonsterType<EndermanType, EntityEnderman, Enderman> {
        public static final EndermanType TYPE = new EndermanType();

        private EndermanType() {
            super(58, -1);
        }

        // overridden utilities
        @Override
        public Enderman create() {
            return new Enderman();
        }

        @Override
        public Enderman fromMC(EntityEnderman entityMC) {
            return new Enderman(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public EndermanType getType() {
        return EndermanType.TYPE;
    }
}