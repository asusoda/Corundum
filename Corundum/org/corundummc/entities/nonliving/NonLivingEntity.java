package org.corundummc.entities.nonliving;

import net.minecraft.entity.EntityList;

import org.corundummc.entities.Entity;
import org.corundummc.entities.Entity.EntityType;
import org.corundummc.entities.living.LivingEntity.LivingEntityType;
import org.corundummc.entities.nonliving.NonLivingEntity.NonLivingEntityType;
import org.corundummc.entities.nonliving.blocks.BlockEntity;
import org.corundummc.entities.nonliving.drops.Drop.DropType;
import org.corundummc.entities.nonliving.projectiles.Projectile.ProjectileType;
import org.corundummc.entities.nonliving.vehicles.Vehicle.VehicleType;
import org.corundummc.types.Creatable;
import org.corundummc.world.Location;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;

/** This class represents all the {@link Entity Entities} that are not living such as {@link NonLivingEntityType#BOAT boats} and {@link NonLivingEntityType#ITEM_FRAME item
 * frames}. Note that {@link LivingEntityType#ZOMBIE zombies} are actually {@link LivingEntityType}s even though they are technically not "living", but rather "undead".
 * 
 * @param <T>
 *            determines the type of {@link net.minecraft.entity.Entity Minecraft Entity} that this class represents. */
public abstract class NonLivingEntity<T extends net.minecraft.entity.Entity> extends Entity<T> {

    @SuppressWarnings("javadoc")
    protected NonLivingEntity(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }

    public static class NonLivingEntityType<T extends NonLivingEntityType<T>> extends EntityType<T> {
        // projectiles
        public static final NonLivingEntityType<?> ARROW = ProjectileType.ARROW;
        public static final NonLivingEntityType<?> BOTTLE_O_ENCHANTING = ProjectileType.BOTTLE_O_ENCHANTING;
        public static final NonLivingEntityType<?> EGG = ProjectileType.EGG;
        public static final NonLivingEntityType<?> ENDER_PEARL = ProjectileType.ENDER_PEARL;
        public static final NonLivingEntityType<?> EYE_OF_ENDER = ProjectileType.EYE_OF_ENDER;
        public static final NonLivingEntityType<?> FIREWORK = ProjectileType.FIREWORK;
        public static final NonLivingEntityType<?> LARGE_FIREBALL = ProjectileType.LARGE_FIREBALL;
        public static final NonLivingEntityType<?> SMALL_FIREBALL = ProjectileType.SMALL_FIREBALL;
        public static final NonLivingEntityType<?> SNOWBALL = ProjectileType.SNOWBALL;
        public static final NonLivingEntityType<?> SPLASH_POTION = ProjectileType.SPLASH_POTION;
        public static final NonLivingEntityType<?> WITHER_SKULL = ProjectileType.WITHER_SKULL;

        // vehicles
        public static final NonLivingEntityType<?> BOAT = VehicleType.BOAT;
        public static final NonLivingEntityType<?> COMMAND_MINECART = VehicleType.COMMAND_MINECART;
        public static final NonLivingEntityType<?> HOPPER_MINECART = VehicleType.HOPPER_MINECART;
        public static final NonLivingEntityType<?> PASSENGER_MINECART = VehicleType.PASSENGER_MINECART;
        public static final NonLivingEntityType<?> POWERED_MINECART = VehicleType.POWERED_MINECART;
        public static final NonLivingEntityType<?> SPAWNER_MINECART = VehicleType.SPAWNER_MINECART;
        public static final NonLivingEntityType<?> STORAGE_MINECART = VehicleType.STORAGE_MINECART;
        public static final NonLivingEntityType<?> TNT_MINECART = VehicleType.TNT_MINECART;

        // drops
        public static final NonLivingEntityType<?> DROPPED_ITEM = DropType.DROPPED_ITEM;
        public static final NonLivingEntityType<?> XP_ORB = DropType.XP_ORB;

        // block entities
        public static final NonLivingEntityType<?> FALLING_BLOCK = BlockEntity.FALLING_BLOCK;
        public static final NonLivingEntityType<?> TNT = BlockEntity.TNT;

        // hanging entities
        public static final NonLivingEntityType<?> ITEM_FRAME = HangingType.ITEM_FRAME;
        public static final NonLivingEntityType<?> PAINTING = HangingType.PAINTING;

        // others
        public static final NonLivingEntityType<?> ENDER_CRYSTAL = new NonLivingEntityType<>(200);
        public static final NonLivingEntity.NonLivingEntityType<?> FISH_HOOK = new NonLivingEntityType<>(-1);
        public static final NonLivingEntityType<?> LEAD = new NonLivingEntityType<>(8);
        public static final NonLivingEntity.NonLivingEntityType<?> LIGHTNING_BOLT = new NonLivingEntityType<>(-1);

        @SuppressWarnings("javadoc")
        protected NonLivingEntityType(int id) {
            super(id, -1);

            addValueAs(NonLivingEntityType.class);
        }

        // pseudo-enum utilities
        @SuppressWarnings("unchecked")
        public static NonLivingEntityType<?> getByID(int id) {
            return getByID(NonLivingEntityType.class, id);
        }

        @SuppressWarnings("unchecked")
        public static NonLivingEntityType<?> getByID(int id, int data) {
            return getByID(NonLivingEntityType.class, id, data);
        }

        @SuppressWarnings("unchecked")
        public static NonLivingEntityType<?>[] values() {
            return values(NonLivingEntityType.class);
        }
    }
}
