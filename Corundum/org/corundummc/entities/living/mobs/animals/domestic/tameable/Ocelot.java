package org.corundummc.entities.living.mobs.animals.domestic.tameable;

import net.minecraft.entity.passive.EntityOcelot;
import org.corundummc.entities.living.mobs.animals.domestic.tameable.Pet.PetType;

public class Ocelot extends Pet<Ocelot, EntityOcelot, Ocelot.OcelotType> {
    public Ocelot() {
        super(new EntityOcelot(null));
    }

    protected Ocelot(EntityOcelot entityMC) {
        super(entityMC);
    }

    protected static class OcelotType extends PetType<OcelotType, EntityOcelot, Ocelot> {
        public static final OcelotType TYPE = new OcelotType();

        private OcelotType() {
            super(98);
        }

        // overridden utilities
        @Override
        public Ocelot create() {
            return new Ocelot();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public OcelotType getType() {
        return OcelotType.TYPE;
    }
}