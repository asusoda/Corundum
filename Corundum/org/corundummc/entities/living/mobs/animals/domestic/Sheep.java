package org.corundummc.entities.living.mobs.animals.domestic;

import net.minecraft.entity.passive.EntitySheep;
import org.corundummc.entities.living.mobs.animals.domestic.DomesticAnimal.DomesticAnimalType;

public class Sheep extends DomesticAnimal<Sheep, EntitySheep, Sheep.SheepType> {
    public Sheep() {
        super(new EntitySheep(null));
    }

    protected Sheep(EntitySheep entityMC) {
        super(entityMC);
    }

    protected static class SheepType extends DomesticAnimalType<SheepType, EntitySheep, Sheep> {
        public static final SheepType TYPE = new SheepType();

        private SheepType() {
            super(91);
        }

        // overridden utilities
        @Override
        public Sheep create() {
            return new Sheep();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public SheepType getType() {
        return SheepType.TYPE;
    }
}