package org.corundummc.entities;

import net.minecraft.entity.EntityList;

import org.corundummc.entities.LivingEntity.LivingEntityType;
import org.corundummc.entities.NonLivingEntity.NonLivingEntityType;
import org.corundummc.entities.nonliving.Projectile.ProjectileType;
import org.corundummc.entities.nonliving.Vehicle.VehicleType;
import org.corundummc.types.Creatable;
import org.corundummc.world.Location;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;

/** This class represents all the {@link Entity Entities} that are not living such as {@link NonLivingEntityType#BOAT boats} and {@link NonLivingEntityType#ITEM_FRAME item
 * frames}. Note that {@link LivingEntityType#ZOMBIE zombies} are actually {@link LivingEntityType}s even though they are technically not "living", but rather "undead". */
public class NonLivingEntity extends Entity {

    protected NonLivingEntity(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }

    public static class NonLivingEntityType<T extends NonLivingEntityType<T>> extends EntityType<T> {
        public static final NonLivingEntityType<?> DROPPED_ITEM = DropType.DROPPED_ITEM;
        public static final NonLivingEntityType<?> XP_ORB = DropType.XP_ORB;
        public static final NonLivingEntityType<?> LEAD = new NonLivingEntityType<>(8);
        public static final NonLivingEntityType<?> PAINTING = HangingType.PAINTING;
        public static final NonLivingEntityType<?> ARROW = ProjectileType.ARROW;
        public static final NonLivingEntityType<?> SNOWBALL = ProjectileType.SNOWBALL;
        public static final NonLivingEntityType<?> LARGE_FIREBALL = ProjectileType.LARGE_FIREBALL;
        public static final NonLivingEntityType<?> SMALL_FIREBALL = ProjectileType.SMALL_FIREBALL;
        public static final NonLivingEntityType<?> ENDER_PEARL = ProjectileType.ENDER_PEARL;
        public static final NonLivingEntityType<?> EYE_OF_ENDER = ProjectileType.EYE_OF_ENDER;
        public static final NonLivingEntityType<?> SPLASH_POTION = ProjectileType.SPLASH_POTION;
        public static final NonLivingEntityType<?> BOTTLE_O_ENCHANTING = ProjectileType.BOTTLE_O_ENCHANTING;
        public static final NonLivingEntityType<?> ITEM_FRAME = HangingType.ITEM_FRAME;
        public static final NonLivingEntityType<?> WITHER_SKULL = ProjectileType.WITHER_SKULL;
        public static final NonLivingEntityType<?> PRIMED_TNT = BlockEntityType.PRIMED_TNT;
        public static final NonLivingEntity.NonLivingEntityType<?> FALLING_BLOCK = BlockEntityType.FALLING_BLOCK;
        public static final NonLivingEntity.NonLivingEntityType<?> FIREWORK = ProjectileType.FIREWORK;
        public static final NonLivingEntityType<?> BOAT = VehicleType.BOAT;
        public static final NonLivingEntityType<?> MINECART = VehicleType.MINECART;
        /* EYE_OF_ENDER_HOVERING = new NonLivingEntityType ( EntityType . EYE_OF_ENDER_HOVERING ) , FIREWORK_ROCKET_LAUNCHED = new NonLivingEntityType ( EntityType .
         * FIREWORK_ROCKET_LAUNCHED ) , PRIMED_TNT = new NonLivingEntityType ( EntityType . PRIMED_TNT ) , FALLING_SAND = new NonLivingEntityType ( EntityType . FALLING_SAND )
         * , FISHING_ROD_BOBBER = new NonLivingEntityType ( EntityType . FISHING_ROD_BOBBER ) , LIGHTNING_BOLT = new NonLivingEntityType ( EntityType . LIGHTNING_BOLT ) , */

        public static final NonLivingEntityType<?> ENDER_CRYSTAL = new NonLivingEntityType(200)/* , EGG = new NonLivingEntityType(EntityType.EGG) */;

        protected NonLivingEntityType() {
            super(-1);

            addValueAs(NonLivingEntityType.class);
        }

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
