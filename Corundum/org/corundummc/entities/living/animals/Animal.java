package org.corundummc.entities.living.animals;

import net.minecraft.entity.passive.*;
import org.corundummc.entities.Entity;
import org.corundummc.world.Location;

/**
 * The base for all animal entities.
 */
public class Animal extends Entity {
    public Animal(AnimalType animalType, Location location) {
        super(animalType, location);
        // TODO
    }

    // TODO constructors, utilities etc.

    public static class AnimalType<T extends AnimalType<T>> extends Entity.EntityType<T> {
        public static final AnimalType COW = new AnimalType(EntityCow.class),
                PIG = new AnimalType(EntityPig.class),
                CHICKEN = new AnimalType(EntityChicken.class),
                SHEEP = new AnimalType(EntitySheep.class),
                HORSE = new AnimalType(EntityHorse.class),
                MOOSHROOM = new AnimalType(EntityMooshroom.class);

        private Class<? extends EntityAnimal> mcAnimalClass;

        public AnimalType(Class<? extends EntityAnimal> clazz) {
            this.mcAnimalClass = clazz;
        }


    }
}
