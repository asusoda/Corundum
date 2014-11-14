package org.corundummc.entities;

import org.corundummc.world.Location;
import org.corundummc.world.Rotation;

/** Class that represents an entity that isn't alive like Cows. Examples include arrows, item entities, boats, etc. Also a base for the specialised non living Entity classes. */
public class NonLivingEntity extends Entity {

    protected NonLivingEntity(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }

    public NonLivingEntity(NonLivingEntityType<?> type, Location location) {
        super(type, location);
    }

    public static class NonLivingEntityType<T extends NonLivingEntityType<T>> extends EntityType<T> {
        // TODO: figure out what's up with "non-saved entities", which apparently includes thrown chicken egg entities

        @SuppressWarnings({ "rawtypes", "unchecked" })
        public static final NonLivingEntityType<?> BOAT = new NonLivingEntityType(EntityType.BOAT), DROPPED_ITEM = new NonLivingEntityType(EntityType.DROPPED_ITEM),
                MINECART = new NonLivingEntityType(EntityType.MINECART), XP_ORB = new NonLivingEntityType(EntityType.XP_ORB),
                ARROW = new NonLivingEntityType(EntityType.ARROW), /* CHICKEN_EGG = new NonLivingEntityType(EntityType.CHICKEN_EGG), */SNOWBALL = new NonLivingEntityType(
                        EntityType.SNOWBALL), ENDER_PEARL = new NonLivingEntityType(EntityType.ENDER_PEARL), /* EYE_OF_ENDER_HOVERING = new
                                                                                                              * NonLivingEntityType(EntityType.EYE_OF_ENDER_HOVERING),
                                                                                                              * FIREWORK_ROCKET_LAUNCHED = new
                                                                                                              * NonLivingEntityType(EntityType.FIREWORK_ROCKET_LAUNCHED),
                                                                                                              * PRIMED_TNT = new NonLivingEntityType(EntityType.PRIMED_TNT),
                                                                                                              * FALLING_SAND = new
                                                                                                              * NonLivingEntityType(EntityType.FALLING_SAND),
                                                                                                              * FISHING_ROD_BOBBER = new
                                                                                                              * NonLivingEntityType(EntityType.FISHING_ROD_BOBBER),
                                                                                                              * LIGHTNING_BOLT = new
                                                                                                              * NonLivingEntityType(EntityType.LIGHTNING_BOLT), */
                PAINTING = new NonLivingEntityType(EntityType.PAINTING), ITEM_FRAME = new NonLivingEntityType(EntityType.ITEM_FRAME),
                GHAST_FIREBALL = new NonLivingEntityType(EntityType.GHAST_FIREBALL), BLAZE_FIREBALL = new NonLivingEntityType(EntityType.BLAZE_FIREBALL),
                ENDER_CRYSTAL = new NonLivingEntityType(EntityType.ENDER_CRYSTAL);

        protected NonLivingEntityType(EntityType<T> parent) {
            super(parent);
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

    public void setRotation(Rotation rotation) {
        // TODO
    }

    // TODO: spawn() and spawn(Location)
}
