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
    }

    // instance utilities

    // overridden utilities
    @Override
    public LeadType getType() {
        return LeadType.TYPE;
    }
}
