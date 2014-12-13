package org.corundummc.entities.nonliving.blocks;

import net.minecraft.entity.Entity;

import org.corundummc.entities.nonliving.NonLivingEntity;

public abstract class BlockEntityType extends NonLivingEntity {
    protected BlockEntityType(Entity entityMC) {
        super(entityMC);
    }
}
