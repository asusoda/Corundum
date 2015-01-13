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

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Priest fromMC(EntityVillager entityMC) {
            return new Priest(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public PriestType getType() {
        return PriestType.TYPE;
    }
}