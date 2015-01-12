package org.corundummc.entities.living;

import org.corundummc.entities.living.LivingEntity.LivingEntityType;
import org.corundummc.utils.exceptions.UnfinishedException;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerEntity extends LivingEntity<PlayerEntity, EntityPlayer, PlayerEntity.PlayerEntityType> {
    public PlayerEntity() {
        // TODO
        super(null);
    }

    protected PlayerEntity(EntityPlayer entityMC) {
        super(entityMC);
    }

    protected static class PlayerEntityType extends LivingEntityType<PlayerEntityType, EntityPlayer, PlayerEntity> {
        public static final PlayerEntityType TYPE = new PlayerEntityType();

        private PlayerEntityType() {
            super(-1, -1);
        }

        // overridden utilities
        @Override
        public PlayerEntity create() {
            return new PlayerEntity();
        }

        @Override
        public PlayerEntity fromMC(EntityPlayer entityMC) {
            return new PlayerEntity(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public PlayerEntityType getType() {
        return PlayerEntityType.TYPE;
    }
}
