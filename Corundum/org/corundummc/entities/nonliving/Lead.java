package org.corundummc.entities.nonliving;

import net.minecraft.entity.EntityLeashKnot;

public class Lead extends NonLivingEntity<EntityLeashKnot> {

    protected Lead(EntityLeashKnot entityMC) {
        super(entityMC);
    }

    public Lead() {
        super(new EntityLeashKnot(null));
    }
}
