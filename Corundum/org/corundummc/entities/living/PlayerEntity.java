package org.corundummc.entities.living;

import org.corundummc.utils.exceptions.UnfinishedException;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerEntity extends LivingEntity<EntityPlayer> {

    protected PlayerEntity(EntityPlayer entityMC) {
        super(entityMC);
    }

    public PlayerEntity() {
        this(null);

        // TODO: figure out how to make a fake Player entity
        throw new UnfinishedException("create fake Player Entities");
    }
}
