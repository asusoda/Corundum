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

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Blacksmith fromMC(EntityVillager entityMC) {
            return new Blacksmith(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public BlacksmithType getType() {
        return BlacksmithType.TYPE;
    }
}