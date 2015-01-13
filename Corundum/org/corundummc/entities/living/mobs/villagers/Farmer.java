package org.corundummc.entities.living.mobs.villagers;

import net.minecraft.entity.passive.EntityVillager;
import org.corundummc.entities.living.mobs.villagers.Villager.VillagerType;

public class Farmer extends Villager<Farmer, Farmer.FarmerType> {
    public Farmer() {
        super(new EntityVillager(null));
    }

    protected Farmer(EntityVillager entityMC) {
        super(entityMC);
    }

    protected static class FarmerType extends VillagerType<FarmerType, Farmer> {
        public static final FarmerType TYPE = new FarmerType();

        private FarmerType() {
            super(0);
        }

        // overridden utilities
        @Override
        public Farmer create() {
            return new Farmer();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Farmer fromMC(EntityVillager entityMC) {
            return new Farmer(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public FarmerType getType() {
        return FarmerType.TYPE;
    }
}