package org.corundummc.entities.living.mobs.villagers;

import net.minecraft.entity.passive.EntityVillager;
import org.corundummc.entities.living.mobs.villagers.Villager.VillagerType;

public class Blacksmith extends Villager<Blacksmith, Blacksmith.BlacksmithType> {
    public Blacksmith() {
        super();
    }

    protected Blacksmith(EntityVillager entityMC) {
        super(entityMC);
    }

    protected static class BlacksmithType extends VillagerType<BlacksmithType, Blacksmith> {
        public static final BlacksmithType TYPE = new BlacksmithType();

        private BlacksmithType() {
            super(3);
        }

        // overridden utilities
        @Override
        public Blacksmith create() {
            return new Blacksmith();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public BlacksmithType getType() {
        return BlacksmithType.TYPE;
    }
}