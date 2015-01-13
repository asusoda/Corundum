package org.corundummc.entities.living.mobs.villagers;

import net.minecraft.entity.passive.EntityVillager;
import org.corundummc.entities.living.mobs.villagers.Villager.VillagerType;

public class Butcher extends Villager<Butcher, Butcher.ButcherType> {
    public Butcher() {
        super();
    }

    protected Butcher(EntityVillager entityMC) {
        super(entityMC);
    }

    protected static class ButcherType extends VillagerType<ButcherType, Butcher> {
        public static final ButcherType TYPE = new ButcherType();

        private ButcherType() {
            super(4);
        }

        // overridden utilities
        @Override
        public Butcher create() {
            return new Butcher();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Butcher fromMC(EntityVillager entityMC) {
            return new Butcher(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public ButcherType getType() {
        return ButcherType.TYPE;
    }
}