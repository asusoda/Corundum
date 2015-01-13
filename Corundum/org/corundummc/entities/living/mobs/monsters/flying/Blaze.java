package org.corundummc.entities.living.mobs.monsters.flying;

import net.minecraft.entity.monster.EntityBlaze;

import org.corundummc.entities.living.mobs.monsters.flying.FlyingMonster.FlyingMonsterType;

public class Blaze extends FlyingMonster<Blaze, EntityBlaze, Blaze.BlazeType> {
    public Blaze() {
        super(new EntityBlaze(null));
    }

    protected Blaze(EntityBlaze entityMC) {
        super(entityMC);
    }

    protected static class BlazeType extends FlyingMonsterType<BlazeType, EntityBlaze, Blaze> {
        public static final BlazeType TYPE = new BlazeType();

        private BlazeType() {
            super(61);
        }

        // overridden utilities
        @Override
        public Blaze create() {
            return new Blaze();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Blaze fromMC(EntityBlaze entityMC) {
            return new Blaze(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public BlazeType getType() {
        return BlazeType.TYPE;
    }
}