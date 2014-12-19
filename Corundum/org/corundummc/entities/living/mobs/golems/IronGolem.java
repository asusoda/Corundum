package org.corundummc.entities.living.mobs.golems;

import net.minecraft.entity.monster.EntityIronGolem;

public class IronGolem extends Golem<EntityIronGolem> {

    protected IronGolem(EntityIronGolem entityMC) {
        super(entityMC);
    }

    public IronGolem() {
        super(new EntityIronGolem(null));
    }
}