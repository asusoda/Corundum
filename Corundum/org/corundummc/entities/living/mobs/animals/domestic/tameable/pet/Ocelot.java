package org.corundummc.entities.living.mobs.animals.domestic.tameable.pet;

import net.minecraft.entity.passive.EntityOcelot;

import org.corundummc.entities.living.mobs.animals.domestic.tameable.TameableAnimal;
import org.corundummc.entities.living.mobs.animals.domestic.tameable.TameableAnimal.TameableAnimalType;
import org.corundummc.entities.living.mobs.animals.domestic.tameable.pet.Pet.PetType;

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

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Ocelot fromMC(EntityOcelot entityMC) {
            return new Ocelot(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public OcelotType getType() {
        return OcelotType.TYPE;
    }
}