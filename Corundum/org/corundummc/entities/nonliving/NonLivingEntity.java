package org.corundummc.entities.nonliving;

import io.netty.handler.codec.http.HttpHeaders.Values;
import net.minecraft.entity.item.EntityFireworkRocket;

import org.corundummc.entities.Entity;
import org.corundummc.entities.Entity.EntityType;
import org.corundummc.entities.living.LivingEntity.LivingEntityType;
import org.corundummc.entities.nonliving.EnderCrystal.EnderCrystalType;
import org.corundummc.entities.nonliving.Firework.FireworkType;
import org.corundummc.entities.nonliving.FishHook.FishHookType;
import org.corundummc.entities.nonliving.Lead.LeadType;
import org.corundummc.entities.nonliving.LightningBolt.LightningBoltType;
import org.corundummc.entities.nonliving.blocks.BlockEntity.BlockEntityType;
import org.corundummc.entities.nonliving.blocks.BlockEntity.BlockEntityTypes;
import org.corundummc.entities.nonliving.drops.Drop.DropType;
import org.corundummc.entities.nonliving.drops.Drop.DropTypes;
import org.corundummc.entities.nonliving.hanging.HangingEntity.HangingEntityType;
import org.corundummc.entities.nonliving.hanging.HangingEntity.HangingEntityTypes;
import org.corundummc.entities.nonliving.projectiles.Projectile.ProjectileType;
import org.corundummc.entities.nonliving.projectiles.Projectile.ProjectileTypes;
import org.corundummc.entities.nonliving.vehicles.Vehicle.VehicleType;
import org.corundummc.entities.nonliving.vehicles.Vehicle.VehicleTypes;

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

    public static interface NonLivingEntityTypes extends ProjectileTypes, VehicleTypes, DropTypes, BlockEntityTypes, HangingEntityTypes {
        public static final EnderCrystalType ENDER_CRYSTAL = EnderCrystalType.TYPE;
        public static final FireworkType FIREWORK = FireworkType.TYPE;
        public static final FishHookType FISH_HOOK = FishHookType.TYPE;
        public static final LeadType LEAD = LeadType.TYPE;
        public static final LightningBoltType LIGHTNING_BOLT = LightningBoltType.TYPE;
    }

    public abstract static class NonLivingEntityType<S extends NonLivingEntityType<S, MC, I>, MC extends net.minecraft.entity.Entity, I extends NonLivingEntity<I, MC, S>>
            extends EntityType<S, MC, I> {
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
