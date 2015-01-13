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

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public IronGolem fromMC(EntityIronGolem entityMC) {
            return new IronGolem(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public IronGolemType getType() {
        return IronGolemType.TYPE;
    }
}