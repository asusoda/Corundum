package org.corundummc.entities.living.mobs.animals;

import org.corundummc.entities.living.mobs.Mob;

/** The base for all animal entities. */
public class Animal extends Mob {
    protected Animal(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }

    // TODO constructors, utilities etc.

    /** This class is used to represent the different types of {@link Animal}s. <br>
     * <br>
     * This list of different types not only includes those types of mobs differentiated by different I.D.s, but also many of those differentiated by different data values;
     * for example, {@link #RABBIT rabbits} and {@link #KILLER_RABBIT killer rabbits} are both represented as separate types despite the fact that they both have the same I.D.
     * value.
     * 
     * @param <T>
     *            is a self-parameterization; <b><tt>T</b></tt> is the same type as the type of this instance. */
    public static class AnimalType<T extends AnimalType<T>> /* extends MobType<T> */{

        public static final AnimalType<?> KILLER_RABBIT = RabbitType.KILLER_RABBIT;
        public static final AnimalType<?> RABBIT = RabbitType.RABBIT;

        @SuppressWarnings("rawtypes")
        public static final AnimalType<?> BAT = new AnimalType(65) {
            @Override
            public Bat create() {
                return new Bat();
            }
        };
        @SuppressWarnings("rawtypes")
        public static final AnimalType<?> SQUID = new AnimalType(94) {
            @Override
            public Squid create() {
                return new Squid();
            }
        };
        @SuppressWarnings("rawtypes")
        public static final AnimalType<?> HORSE = new AnimalType(100) {
            @Override
            public Horse create() {
                return new Horse();
            }
        };

        protected AnimalType(int id) {
            this(id, -1);
        }

        protected AnimalType(int id, int data) {
            super(id, data);

            addValueAs(Animal.class);
        }

        @Override
        public Animal create() {
            return (Animal) super.create();
        }

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
