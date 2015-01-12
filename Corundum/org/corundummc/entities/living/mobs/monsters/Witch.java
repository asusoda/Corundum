package org.corundummc.entities.living.mobs.monsters;

import net.minecraft.entity.monster.EntityWitch;
import org.corundummc.entities.living.mobs.monsters.Monster.MonsterType;

public class Witch extends Monster<Witch, EntityWitch, Witch.WitchType> {
    public Witch() {
        super(new EntityWitch(null));
    }

    protected Witch(EntityWitch entityMC) {
        super(entityMC);
    }

    protected static class WitchType extends MonsterType<WitchType, EntityWitch, Witch> {
        public static final WitchType TYPE = new WitchType();

        private WitchType() {
            super(/* TODO */0, -1);
        }

        // overridden utilities
        @Override
        public Witch create() {
            return new Witch();
        }

        @Override
        public Witch fromMC(EntityWitch entityMC) {
            return new Witch(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public WitchType getType() {
        return WitchType.TYPE;
    }
}