package org.corundummc.entities.living.mobs.animals.domestic.bovine;

import net.minecraft.entity.passive.EntityMooshroom;
import org.corundummc.entities.living.mobs.animals.domestic.bovine.Bovine.BovineType;

public class Mooshroom extends Bovine<Mooshroom, EntityMooshroom, Mooshroom.MooshroomType> {
    public Mooshroom() {
        super(new EntityMooshroom(null));
    }

    protected Mooshroom(EntityMooshroom entityMC) {
        super(entityMC);
    }

    protected static class MooshroomType extends BovineType<MooshroomType, EntityMooshroom, Mooshroom> {
        public static final MooshroomType TYPE = new MooshroomType();

        private MooshroomType() {
            super(96);
        }

        // overridden utilities
        @Override
        public Mooshroom create() {
            return new Mooshroom();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public MooshroomType getType() {
        return MooshroomType.TYPE;
    }
}