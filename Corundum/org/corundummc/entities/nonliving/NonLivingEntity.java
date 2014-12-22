package org.corundummc.entities.nonliving;

import org.corundummc.entities.Entity;
import org.corundummc.entities.Entity.EntityType;
import org.corundummc.entities.living.LivingEntity.LivingEntityType;
import org.corundummc.entities.nonliving.blocks.BlockEntity.BlockEntityType;
import org.corundummc.entities.nonliving.drops.Drop.DropType;
import org.corundummc.entities.nonliving.hanging.HangingEntity.HangingEntityType;
import org.corundummc.entities.nonliving.projectiles.Projectile.ProjectileType;
import org.corundummc.entities.nonliving.vehicles.Vehicle.VehicleType;

/** This class represents all the {@link Entity Entities} that are not living such as {@link NonLivingEntityType#BOAT boats} and {@link NonLivingEntityType#ITEM_FRAME item
 * frames}. Note that {@link LivingEntityType#ZOMBIE zombies} are actually {@link LivingEntityType}s even though they are technically not "living", but rather "undead".
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of {@link net.minecraft.entity.Entity Minecraft Entity} that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class NonLivingEntity<S extends NonLivingEntity<S, MC, T>, MC extends net.minecraft.entity.Entity, T extends NonLivingEntity.NonLivingEntityType<T, MC, S>>
        extends Entity<S, MC, T> {

    @SuppressWarnings("javadoc")
    protected NonLivingEntity(MC entityMC) {
        super(entityMC);
    }

    public static class NonLivingEntityType<S extends NonLivingEntityType<S, MC, I>, MC extends net.minecraft.entity.Entity, I extends NonLivingEntity<I, MC, S>> extends
            EntityType<S, MC, I> {
        // projectiles
        public static final NonLivingEntityType ARROW = ProjectileType.ARROW;
        public static final NonLivingEntityType BOTTLE_O_ENCHANTING = ProjectileType.BOTTLE_O_ENCHANTING;
        public static final NonLivingEntityType EGG = ProjectileType.EGG;
        public static final NonLivingEntityType ENDER_PEARL = ProjectileType.ENDER_PEARL;
        public static final NonLivingEntityType EYE_OF_ENDER = ProjectileType.EYE_OF_ENDER;
        public static final NonLivingEntityType FIREWORK = ProjectileType.FIREWORK;
        public static final NonLivingEntityType LARGE_FIREBALL = ProjectileType.LARGE_FIREBALL;
        public static final NonLivingEntityType SMALL_FIREBALL = ProjectileType.SMALL_FIREBALL;
        public static final NonLivingEntityType SNOWBALL = ProjectileType.SNOWBALL;
        public static final NonLivingEntityType SPLASH_POTION = ProjectileType.SPLASH_POTION;
        public static final NonLivingEntityType WITHER_SKULL = ProjectileType.WITHER_SKULL;

        // vehicles
        public static final NonLivingEntityType BOAT = VehicleType.BOAT;
        public static final NonLivingEntityType COMMAND_MINECART = VehicleType.COMMAND_MINECART;
        public static final NonLivingEntityType HOPPER_MINECART = VehicleType.HOPPER_MINECART;
        public static final NonLivingEntityType PASSENGER_MINECART = VehicleType.PASSENGER_MINECART;
        public static final NonLivingEntityType POWERED_MINECART = VehicleType.POWERED_MINECART;
        public static final NonLivingEntityType SPAWNER_MINECART = VehicleType.SPAWNER_MINECART;
        public static final NonLivingEntityType STORAGE_MINECART = VehicleType.STORAGE_MINECART;
        public static final NonLivingEntityType TNT_MINECART = VehicleType.TNT_MINECART;

        // drops
        public static final NonLivingEntityType DROPPED_ITEM = DropType.DROPPED_ITEM;
        public static final NonLivingEntityType XP_ORB = DropType.XP_ORB;

        // block entities
        public static final NonLivingEntityType FALLING_BLOCK = BlockEntityType.FALLING_BLOCK;
        public static final NonLivingEntityType PRIMED_TNT = BlockEntityType.PRIMED_TNT;

        // hanging entities
        public static final NonLivingEntityType ITEM_FRAME = HangingEntityType.ITEM_FRAME;
        public static final NonLivingEntityType PAINTING = HangingEntityType.PAINTING;

        // others
        @SuppressWarnings("rawtypes")
        public static final NonLivingEntityType ENDER_CRYSTAL = new NonLivingEntityType(200);
        @SuppressWarnings("rawtypes")
        public static final NonLivingEntity.NonLivingEntityType FISH_HOOK = new NonLivingEntityType(-1);
        @SuppressWarnings("rawtypes")
        public static final NonLivingEntityType LEAD = new NonLivingEntityType(8) {
            @Override
            public Lead create() {
                return new Lead();
            }
        };
        @SuppressWarnings("rawtypes")
        public static final NonLivingEntity.NonLivingEntityType LIGHTNING_BOLT = new NonLivingEntityType(-1) {
            @Override
            public LightningBolt create() {
                return new LightningBolt();
            }
        };

        @SuppressWarnings("javadoc")
        protected NonLivingEntityType(int id) {
            super(id, -1);

            addValueAs(NonLivingEntityType.class);
        }

        // pseudo-enum utilities
        public static NonLivingEntityType getByID(int id) {
            return getByID(NonLivingEntityType.class, id);
        }

        public static NonLivingEntityType getByID(int id, int data) {
            return getByID(NonLivingEntityType.class, id, data);
        }

        public static NonLivingEntityType[] values() {
            return values(NonLivingEntityType.class);
        }
    }
}
