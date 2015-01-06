package org.corundummc.entities.living.mobs.golems;

import net.minecraft.entity.monster.EntityIronGolem;
import org.corundummc.entities.living.mobs.golems.Golem.GolemType;

public class IronGolem extends Golem<IronGolem, EntityIronGolem, IronGolem.IronGolemType> {
    public IronGolem() {
        super(new EntityIronGolem(null));
    }

    protected IronGolem(EntityIronGolem entityMC) {
        super(entityMC);
    }

    protected static class IronGolemType extends GolemType<IronGolemType, EntityIronGolem, IronGolem> {
        public static final IronGolemType TYPE = new IronGolemType();

        private IronGolemType() {
            super(99, -1);
        }

        // overridden utilities
        @Override
        public IronGolem create() {
            return new IronGolem();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public IronGolemType getType() {
        return IronGolemType.TYPE;
    }
}