package org.corundummc.entities.living.animals;

import net.minecraft.entity.passive.*;

import org.corundummc.entities.Entity;
import org.corundummc.entities.living.mobs.Mob.MobType;
import org.corundummc.world.Location;

/** The base for all animal entities. */
public class Animal extends Entity {
    public Animal(AnimalType type) {
        super(type);

        // TODO
    }

    // TODO constructors, utilities etc.

    public static class AnimalType<T extends MobType<T>> extends MobType<T> {

        @SuppressWarnings({ "rawtypes", "unchecked" })
        public static final AnimalType<?> COW = null /* TODO */;
        
        

        // pseudo-enum utilities
        @SuppressWarnings("unchecked")
        public static AnimalType<?> getByID(int id) {
            return getByID(AnimalType.class, id);
        }

        @SuppressWarnings("unchecked")
        public static AnimalType<?> getByID(int id, int data) {
            return getByID(AnimalType.class, id, data);
        }

        @SuppressWarnings("unchecked")
        public static AnimalType<?>[] values() {
            return values(AnimalType.class);
        }
    }
}
