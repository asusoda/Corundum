package org.corundummc.entities.living;

import net.minecraft.entity.EntityLivingBase;

import org.corundummc.entities.Entity;
import org.corundummc.entities.Entity.EntityType;
import org.corundummc.entities.living.mobs.Mob.MobType;
import org.corundummc.exceptions.CorundumException;
import org.corundummc.items.Item;
import org.corundummc.types.Creatable;

public abstract class LivingEntity<T extends EntityLivingBase> extends Entity<T> {

    protected LivingEntity(EntityLivingBase entityMC) {
        super(entityMC);
    }

    public static class LivingEntityType<T extends EntityType<T>> extends EntityType<T> {

        @SuppressWarnings("rawtypes")
        public static final LivingEntityType<?> PLAYER = new LivingEntityType(-1, -1) {
            @Override
            public PlayerEntity create() {
                return new PlayerEntity();
            }
        };
        public static final LivingEntityType<?> CREEPER = MobType.CREEPER;
        public static final LivingEntityType<?> SKELETON = MobType.SKELETON;
        public static final LivingEntityType<?> WITHER_SKELETON = MobType.WITHER_SKELETON;
        public static final LivingEntityType<?> SPIDER = MobType.SPIDER;
        public static final LivingEntityType<?> GIANT = MobType.GIANT;
        public static final LivingEntityType<?> ZOMBIE = MobType.ZOMBIE;
        public static final LivingEntityType<?> ZOMBIFIED_VILLAGER = MobType.ZOMBIFIED_VILLAGER;
        public static final LivingEntityType<?> SLIME = MobType.SLIME;
        public static final LivingEntityType<?> GHAST = MobType.GHAST;
        public static final LivingEntityType<?> ZOMBIE_PIGMAN = MobType.ZOMBIE_PIGMAN;
        public static final LivingEntityType<?> ENDERMAN = MobType.ENDERMAN;
        public static final LivingEntityType<?> CAVE_SPIDER = MobType.CAVE_SPIDER;
        public static final LivingEntityType<?> SILVERFISH = MobType.SILVERFISH;
        public static final LivingEntityType<?> BLAZE = MobType.BLAZE;
        public static final LivingEntityType<?> MAGMA_CUBE = MobType.MAGMA_CUBE;
        public static final LivingEntityType<?> ENDER_DRAGON = MobType.ENDER_DRAGON;
        public static final LivingEntityType<?> WITHER = MobType.WITHER;
        public static final LivingEntityType<?> WITCH = MobType.WITCH;
        public static final LivingEntityType<?> ENDERMITE = MobType.ENDERMITE;
        public static final LivingEntityType<?> GUARDIAN = MobType.GUARDIAN;
        public static final LivingEntityType<?> ELDER_GUARDIAN = MobType.ELDER_GUARDIAN;
        public static final LivingEntityType<?> KILLER_RABBIT = MobType.KILLER_RABBIT;
        public static final LivingEntityType<?> BAT = MobType.BAT;
        public static final LivingEntityType<?> PIG = MobType.PIG;
        public static final LivingEntityType<?> SHEEP = MobType.SHEEP;
        public static final LivingEntityType<?> COW = MobType.COW;
        public static final LivingEntityType<?> CHICKEN = MobType.CHICKEN;
        public static final LivingEntityType<?> SQUID = MobType.SQUID;
        public static final LivingEntityType<?> WOLF = MobType.WOLF;
        public static final LivingEntityType<?> MOOSHROOM = MobType.MOOSHROOM;
        public static final LivingEntityType<?> SNOW_GOLEM = MobType.SNOW_GOLEM;
        public static final LivingEntityType<?> OCELOT = MobType.OCELOT;
        public static final LivingEntityType<?> IRON_GOLEM = MobType.IRON_GOLEM;
        public static final LivingEntityType<?> HORSE = MobType.HORSE;
        public static final LivingEntityType<?> RABBIT = MobType.RABBIT;
        public static final LivingEntityType<?> FARMER = MobType.FARMER;
        public static final LivingEntityType<?> LIBRARIAN = MobType.LIBRARIAN;
        public static final LivingEntityType<?> PRIEST = MobType.PRIEST;
        public static final LivingEntityType<?> BLACKSMITH = MobType.BLACKSMITH;
        public static final LivingEntityType<?> BUTCHER = MobType.BUTCHER;

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

    public static class PlayerCreationException extends CorundumException {
        private static final long serialVersionUID = 5871435907491663907L;

        public PlayerCreationException() {
            super("Someone tried to create a new Player! You can't create new Player Entities (yet)!", "attempt to create a new Player");
        }

    }

}
