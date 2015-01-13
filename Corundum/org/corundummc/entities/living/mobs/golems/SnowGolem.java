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

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public SnowGolem fromMC(EntitySnowman entityMC) {
            return new SnowGolem(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public SnowGolemType getType() {
        return SnowGolemType.TYPE;
    }
}