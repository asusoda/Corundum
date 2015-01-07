package org.corundummc.entities.living.mobs.animals.domestic.tameable;

import net.minecraft.entity.passive.EntityWolf;
import org.corundummc.entities.living.mobs.animals.domestic.tameable.Pet.PetType;

public class Wolf extends Pet<Wolf, EntityWolf, Wolf.WolfType> {
    public Wolf() {
        super(new EntityWolf(null));
    }

    protected Wolf(EntityWolf entityMC) {
        super(entityMC);
    }

    protected static class WolfType extends PetType<WolfType, EntityWolf, Wolf> {
        public static final WolfType TYPE = new WolfType();

        private WolfType() {
            super(95);
        }

        // overridden utilities
        @Override
        public Wolf create() {
            return new Wolf();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public WolfType getType() {
        return WolfType.TYPE;
    }
}