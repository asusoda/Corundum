package org.corundummc.entities.living.mobs.animals;

import net.minecraft.entity.EntityLiving;

import org.corundummc.entities.living.mobs.Mob;

/** The base for all animal entities.
 * 
 * @param <MC>
 *            determines the type of {@link net.minecraft.entity.Entity Minecraft Entity} that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public class Animal<MC extends EntityLiving, T extends AnimalType<T, Animal>> extends Mob {
    @SuppressWarnings("unchecked")
    protected Animal(MC entityMC) {
        super(entityMC);
    }

    // TODO constructors, utilities etc.

    /** This class is used to represent the different types of {@link Animal}s. <br>
     * <br>
     * This list of different types not only includes those types of mobs differentiated by different I.D.s, but also many of those differentiated by different data values;
     * for example, {@link #RABBIT rabbits} and {@link #KILLER_RABBIT killer rabbits} are both represented as separate types despite the fact that they both have the same I.D.
     * value. <br>
     * <br>
     * Because this class is a subclass of {@link EntityType}, calls to {@link EntityType EntityTypes} that are not actually {@link AnimalType AnimalTypes} are possible, e.g.
     * <tt>{@link EntityType#BOAT AnimalType.BOAT}</tt>. However, please note that we advise against using this type of call and that this call will return not an
     * {@link AnimalType}, but one of parent types of {@link AnimalType}.
     * 
     * @param <S>
     *            is a self-parameterization; <b><tt>S</b></tt> is the same type as the type of this instance.
     * @param <I>
     *            represents the instance class of this type (the class that represents something of this type). */
    public static class AnimalType<S extends AnimalType<S, I>, I extends Animal> /* extends MobType<T> */{

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
