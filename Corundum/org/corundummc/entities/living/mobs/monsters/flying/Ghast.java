package org.corundummc.entities.living.mobs.monsters.flying;

import net.minecraft.entity.monster.EntityGhast;

import org.corundummc.entities.living.mobs.monsters.flying.FlyingMonster.FlyingMonsterType;

public class Ghast extends FlyingMonster<Ghast, EntityGhast, Ghast.GhastType> {
    public Ghast() {
        super(new EntityGhast(null));
    }

    protected Ghast(EntityGhast entityMC) {
        super(entityMC);
    }

    protected static class GhastType extends FlyingMonsterType<GhastType, EntityGhast, Ghast> {
        public static final GhastType TYPE = new GhastType();

        private GhastType() {
            super(56);
        }

        // overridden utilities
        @Override
        public Ghast create() {
            return new Ghast();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Ghast fromMC(EntityGhast entityMC) {
            return new Ghast(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public GhastType getType() {
        return GhastType.TYPE;
    }
}