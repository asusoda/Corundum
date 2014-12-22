package org.corundummc.entities.living;

import org.corundummc.entities.living.LivingEntity.LivingEntityType;
import org.corundummc.utils.exceptions.UnfinishedException;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerEntity extends LivingEntity<PlayerEntity, EntityPlayer, PlayerEntity.PlayerType> {

    protected PlayerEntity(EntityPlayer entityMC) {
        super(entityMC);
    }

    public PlayerEntity() {
        this(null);

        // TODO: figure out how to make a fake Player entity
        throw new UnfinishedException("create fake Player Entities");
    }

    @Override
    public PlayerType getType() {
        return PlayerType.INSTANCE;
    }

    static class PlayerType extends LivingEntityType<PlayerType, EntityPlayer, PlayerEntity> {
        static final PlayerType INSTANCE = new PlayerType();

        protected PlayerType() {
            super(-1, -1);
        }
    }
}
