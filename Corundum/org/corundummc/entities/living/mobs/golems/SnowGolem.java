package org.corundummc.entities.living.mobs.golems;

import net.minecraft.entity.monster.EntitySnowman;
import org.corundummc.entities.living.mobs.golems.Golem.GolemType;

public class SnowGolem extends Golem<SnowGolem, EntitySnowman, SnowGolem.SnowGolemType> {
    public SnowGolem() {
        super(new EntitySnowman(null));
    }

    protected SnowGolem(EntitySnowman entityMC) {
        super(entityMC);
    }

    protected static class SnowGolemType extends GolemType<SnowGolemType, EntitySnowman, SnowGolem> {
        public static final SnowGolemType TYPE = new SnowGolemType();

        private SnowGolemType() {
            super(97, -1);
        }

        // overridden utilities
        @Override
        public SnowGolem create() {
            return new SnowGolem();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public SnowGolemType getType() {
        return SnowGolemType.TYPE;
    }
}