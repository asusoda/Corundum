package org.corundummc.entities.nonliving;

import net.minecraft.entity.effect.EntityLightningBolt;

public class LightningBolt extends NonLivingEntity<EntityLightningBolt> {

    protected LightningBolt(EntityLightningBolt entityMC) {
        super(entityMC);
    }

    public LightningBolt() {
        // Minecraft's EntityLightningBolts are created in the constructor, so we cannot create a new one now; we will create a new one in spawn() instead
        super(null);
    }
}
