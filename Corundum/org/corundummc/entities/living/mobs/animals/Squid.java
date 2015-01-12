package org.corundummc.entities.living.mobs.animals;

import net.minecraft.entity.passive.EntitySquid;
import org.corundummc.entities.living.mobs.animals.Animal.AnimalType;

public class Squid extends Animal<Squid, EntitySquid, Squid.SquidType> {
    public Squid() {
        super(new EntitySquid(null));
    }

    protected Squid(EntitySquid entityMC) {
        super(entityMC);
    }

    protected static class SquidType extends AnimalType<SquidType, EntitySquid, Squid> {
        public static final SquidType TYPE = new SquidType();

        private SquidType() {
            super(94);
        }

        // overridden utilities
        @Override
        public Squid create() {
            return new Squid();
        }

        @Override
        public Squid fromMC(EntitySquid entityMC) {
            return new Squid(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public SquidType getType() {
        return SquidType.TYPE;
    }
}