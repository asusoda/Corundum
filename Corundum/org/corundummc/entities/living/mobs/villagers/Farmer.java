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
    }

    // instance utilities

    // overridden utilities
    @Override
    public FarmerType getType() {
        return FarmerType.TYPE;
    }
}