package org.corundummc.entities.living;

import net.minecraft.entity.EntityLivingBase;

import org.corundummc.entities.Entity;
import org.corundummc.entities.Entity.EntityType;
import org.corundummc.items.Item;
import org.corundummc.types.Creatable;

public abstract class LivingEntity<T extends EntityLivingBase> extends Entity<T> {

    protected LivingEntity(EntityLivingBase entityMC) {
        super(entityMC);
    }

    public static class LivingEntityType<T extends EntityType<T>> extends EntityType<T> {

        @SuppressWarnings({ "rawtypes", "unchecked" })
        public static final LivingEntityType<?> PLAYER = new LivingEntityType(-1, -1) {
            @Override
            public Player create() {
                return new Player();
            }
        };

        protected LivingEntityType(int id, int data) {
            super(id, data);
        }

        // pseudo-enum utilities
        @SuppressWarnings("unchecked")
        public static LivingEntityType<?> getByID(int id) {
            return getByID(LivingEntityType.class, id);
        }

        @SuppressWarnings("unchecked")
        public static LivingEntityType<?> getByID(int id, int data) {
            return getByID(LivingEntityType.class, id, data);
        }

        @SuppressWarnings("unchecked")
        public static LivingEntityType<?>[] values() {
            return values(LivingEntityType.class);
        }
    }

}
