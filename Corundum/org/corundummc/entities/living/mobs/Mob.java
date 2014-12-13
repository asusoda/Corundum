package org.corundummc.entities.living.mobs;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityMob;

import org.corundummc.entities.Entity;
import org.corundummc.entities.living.LivingEntity;
import org.corundummc.entities.living.LivingEntity.LivingEntityType;
import org.corundummc.items.Item;
import org.corundummc.types.Creatable;

/** This class represents a "mob", an autonomous {@link LivingEntity} */
public class Mob extends LivingEntity {

    protected Mob(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }

    /** This class is used to represent the different types of {@link Mob}s. <br>
     * <br>
     * This list of different types not only includes those types of mobs differentiated by different I.D.s, but also many of those differentiated by different data values;
     * for example, {@link #FARMER_VILLAGER farmer villagers} and {@link #LIBRARIAN_VILLAGER librarian villager} are both represented as separate types despite the fact that
     * they both have the same I.D. value.
     * 
     * @param <T>
     *            is a self-parameterization; <b><tt>T</b></tt> is the same type as the type of this instance. */
    public static class MobType<T extends LivingEntityType<T>> extends LivingEntityType<T> {

        @SuppressWarnings({ "rawtypes", "unchecked" })
        public static final MobType<?> FARMER_VILLAGER = PassiveMobs.FARMER_VILLAGER;
        public static final MobType<?> LIBRARIAN_VILLAGER = PassiveMobs.LIBRARIAN_VILLAGER;

        protected MobType() {
            super();

            addValueAs(MobType.class);
        }

        protected MobType(int data) {
            super(data);

            addValueAs(MobType.class);
        }

        protected MobType(int id, int data) {
            super(id, data);

            addValueAs(MobType.class);
        }

        // pseudo-enum utilities
        @SuppressWarnings("unchecked")
        public static MobType<?> getByID(int id) {
            return getByID(MobType.class, id);
        }

        @SuppressWarnings("unchecked")
        public static MobType<?> getByID(int id, int data) {
            return getByID(MobType.class, id, data);
        }

        @SuppressWarnings("unchecked")
        public static MobType<?>[] values() {
            return values(MobType.class);
        }
    }
}
