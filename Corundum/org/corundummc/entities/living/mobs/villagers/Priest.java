package org.corundummc.entities.living.mobs.villagers;

import net.minecraft.entity.passive.EntityVillager;
import org.corundummc.entities.living.mobs.villagers.Villager.VillagerType;

public class Priest extends Villager<Priest, Priest.PriestType> {
    public Priest() {
        super();
    }

    protected Priest(EntityVillager entityMC) {
        super(entityMC);
    }

    protected static class PriestType extends VillagerType<PriestType, Priest> {
        public static final PriestType TYPE = new PriestType();

        private PriestType() {
            super(2);
        }

        // overridden utilities
        @Override
        public Priest create() {
            return new Priest();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public PriestType getType() {
        return PriestType.TYPE;
    }
}