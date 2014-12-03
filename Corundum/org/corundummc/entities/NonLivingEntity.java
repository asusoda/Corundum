package org.corundummc.entities;

import org.corundummc.entities.nonliving.Projectile.ProjectileType;
import org.corundummc.world.Location;

/** Class that represents an entity that isn't alive like Cows. Examples include arrows, item entities, boats, etc. Also a base for the specialised non living Entity classes. */
public class NonLivingEntity extends Entity {

    protected NonLivingEntity(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }

    protected NonLivingEntity(NonLivingEntityType<?> type) {
        super(type);
    }

    public static abstract class NonLivingEntityType<T extends NonLivingEntityType<T>> extends EntityType<T> {
        // TODO: figure out what's up with "non-saved entities", which apparently includes thrown chicken egg entities

        @SuppressWarnings({ "rawtypes", "unchecked" })
        public static final NonLivingEntityType<?> BOAT = new NonLivingEntityType(EntityType.BOAT), DROPPED_ITEM = new NonLivingEntityType(EntityType.DROPPED_ITEM),
                MINECART = new NonLivingEntityType(EntityType.MINECART), XP_ORB = new NonLivingEntityType(EntityType.XP_ORB), ARROW = ProjectileType.ARROW,
                SNOWBALL = ProjectileType.SNOWBALL, ENDER_PEARL = ProjectileType.ENDER_PEARL, /* EYE_OF_ENDER_HOVERING = new NonLivingEntityType ( EntityType .
                                                                                               * EYE_OF_ENDER_HOVERING ) , FIREWORK_ROCKET_LAUNCHED = new NonLivingEntityType (
                                                                                               * EntityType . FIREWORK_ROCKET_LAUNCHED ) , PRIMED_TNT = new NonLivingEntityType
                                                                                               * ( EntityType . PRIMED_TNT ) , FALLING_SAND = new NonLivingEntityType (
                                                                                               * EntityType . FALLING_SAND ) , FISHING_ROD_BOBBER = new NonLivingEntityType (
                                                                                               * EntityType . FISHING_ROD_BOBBER ) , LIGHTNING_BOLT = new NonLivingEntityType (
                                                                                               * EntityType . LIGHTNING_BOLT ) , */
                PAINTING = new NonLivingEntityType(EntityType.PAINTING), ITEM_FRAME = new NonLivingEntityType(EntityType.ITEM_FRAME),
                GHAST_FIREBALL = ProjectileType.GHAST_FIREBALL, BLAZE_FIREBALL = ProjectileType.BLAZE_FIREBALL, ENDER_CRYSTAL = new NonLivingEntityType(
                        EntityType.ENDER_CRYSTAL)/* , EGG = new NonLivingEntityType(EntityType.EGG) */;

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
