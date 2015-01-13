package org.corundummc.entities.nonliving;

import net.minecraft.entity.EntityLeashKnot;
import org.corundummc.entities.nonliving.NonLivingEntity.NonLivingEntityType;

public class Lead extends NonLivingEntity<Lead, EntityLeashKnot, Lead.LeadType> {
    public Lead() {
        super(new EntityLeashKnot(null));
    }

    protected Lead(EntityLeashKnot entityMC) {
        super(entityMC);
    }

    protected static class LeadType extends NonLivingEntityType<LeadType, EntityLeashKnot, Lead> {
        public static final LeadType TYPE = new LeadType();

        private LeadType() {
            super(8);
        }

        // overridden utilities
        @Override
        public Lead create() {
            return new Lead();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Lead fromMC(EntityLeashKnot entityMC) {
            return new Lead(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public LeadType getType() {
        return LeadType.TYPE;
    }
}
