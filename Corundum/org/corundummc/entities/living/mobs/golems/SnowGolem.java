package org.corundummc.entities.living.mobs.golems;

import net.minecraft.entity.monster.EntitySnowman;

public class SnowGolem extends Golem<EntitySnowman> {

    protected SnowGolem(EntitySnowman entityMC) {
        super(entityMC);
    }

    public SnowGolem() {
        super(new EntitySnowman(null));
    }
}