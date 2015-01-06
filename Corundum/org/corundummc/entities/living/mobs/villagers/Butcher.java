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
    }

    // instance utilities

    // overridden utilities
    @Override
    public ButcherType getType() {
        return ButcherType.TYPE;
    }
}