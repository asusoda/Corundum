package org.corundummc.entities.living;

import net.minecraft.entity.EntityLivingBase;

import org.corundummc.entities.Entity;
import org.corundummc.entities.living.mobs.Mob.MobType;
import org.corundummc.entities.living.mobs.Mob.MobTypes;

public abstract class LivingEntity<S extends LivingEntity<S, MC, T>, MC extends EntityLivingBase, T extends LivingEntity.LivingEntityType<T, MC, S>> extends Entity<S, MC, T> {

    protected LivingEntity(MC entityMC) {
        super(entityMC);
    }

    public static class LivingEntityTypes {
        public static final LivingEntityType PLAYER = new LivingEntityType(-1, -1);

        public static final LivingEntityType CREEPER = MobTypes.CREEPER;
        public static final LivingEntityType SKELETON = MobTypes.SKELETON;
        public static final LivingEntityType WITHER_SKELETON = MobTypes.WITHER_SKELETON;
        public static final LivingEntityType SPIDER = MobTypes.SPIDER;
        public static final LivingEntityType GIANT = MobTypes.GIANT;
        public static final LivingEntityType ZOMBIE = MobTypes.ZOMBIE;
        public static final LivingEntityType ZOMBIFIED_VILLAGER = MobTypes.ZOMBIFIED_VILLAGER;
        public static final LivingEntityType SLIME = MobTypes.SLIME;
        public static final LivingEntityType GHAST = MobTypes.GHAST;
        public static final LivingEntityType ZOMBIE_PIGMAN = MobTypes.ZOMBIE_PIGMAN;
        public static final LivingEntityType ENDERMAN = MobTypes.ENDERMAN;
        public static final LivingEntityType CAVE_SPIDER = MobTypes.CAVE_SPIDER;
        public static final LivingEntityType SILVERFISH = MobTypes.SILVERFISH;
        public static final LivingEntityType BLAZE = MobTypes.BLAZE;
        public static final LivingEntityType MAGMA_CUBE = MobTypes.MAGMA_CUBE;
        public static final LivingEntityType ENDER_DRAGON = MobTypes.ENDER_DRAGON;
        public static final LivingEntityType WITHER = MobTypes.WITHER;
        public static final LivingEntityType WITCH = MobTypes.WITCH;
        public static final LivingEntityType ENDERMITE = MobTypes.ENDERMITE;
        public static final LivingEntityType GUARDIAN = MobTypes.GUARDIAN;
        public static final LivingEntityType ELDER_GUARDIAN = MobTypes.ELDER_GUARDIAN;
        public static final LivingEntityType KILLER_RABBIT = MobTypes.KILLER_RABBIT;
        public static final LivingEntityType BAT = MobTypes.BAT;
        public static final LivingEntityType PIG = MobTypes.PIG;
        public static final LivingEntityType SHEEP = MobTypes.SHEEP;
        public static final LivingEntityType COW = MobTypes.COW;
        public static final LivingEntityType CHICKEN = MobTypes.CHICKEN;
        public static final LivingEntityType SQUID = MobTypes.SQUID;
        public static final LivingEntityType WOLF = MobTypes.WOLF;
        public static final LivingEntityType MOOSHROOM = MobTypes.MOOSHROOM;
        public static final LivingEntityType SNOW_GOLEM = MobTypes.SNOW_GOLEM;
        public static final LivingEntityType OCELOT = MobTypes.OCELOT;
        public static final LivingEntityType IRON_GOLEM = MobTypes.IRON_GOLEM;
        public static final LivingEntityType HORSE = MobTypes.HORSE;
        public static final LivingEntityType RABBIT = MobTypes.RABBIT;
        public static final LivingEntityType FARMER = MobTypes.FARMER;
        public static final LivingEntityType LIBRARIAN = MobTypes.LIBRARIAN;
        public static final LivingEntityType PRIEST = MobTypes.PRIEST;
        public static final LivingEntityType BLACKSMITH = MobTypes.BLACKSMITH;
        public static final LivingEntityType BUTCHER = MobTypes.BUTCHER;
    }

    public static class LivingEntityType<S extends LivingEntityType<S, MC, I>, MC extends EntityLivingBase, I extends LivingEntity<I, MC, S>> extends EntityType<S, MC, I>
            implements LivingEntityTypes {
        protected LivingEntityType(int id, int data) {
            super(id, data);
            
            addValueAs(LivingEntityType.class);
        }

        // pseudo-enum utilities
        public static LivingEntityType getByID(int id) {
            return getByID(LivingEntityType.class, id);
        }

        public static LivingEntityType getByID(int id, int data) {
            return getByID(LivingEntityType.class, id, data);
        }

        public static LivingEntityType[] values() {
            return values(LivingEntityType.class);
        }
    }

}
