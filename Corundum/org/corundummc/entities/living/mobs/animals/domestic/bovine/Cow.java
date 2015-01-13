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

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Cow fromMC(EntityCow entityMC) {
            return new Cow(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public CowType getType() {
        return CowType.TYPE;
    }
}