package org.corundummc.entities.living.mobs.animals.domestic.bovine;

import net.minecraft.entity.passive.EntityCow;
import org.corundummc.entities.living.mobs.animals.domestic.bovine.Bovine.BovineType;

public class Cow extends Bovine<Cow, EntityCow, Cow.CowType> {
    public Cow() {
        super(new EntityCow(null));
    }

    protected Cow(EntityCow entityMC) {
        super(entityMC);
    }

    protected static class CowType extends BovineType<CowType, EntityCow, Cow> {
        public static final CowType TYPE = new CowType();

        private CowType() {
            super(92);
        }

        // overridden utilities
        @Override
        public Cow create() {
            return new Cow();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public CowType getType() {
        return CowType.TYPE;
    }
}