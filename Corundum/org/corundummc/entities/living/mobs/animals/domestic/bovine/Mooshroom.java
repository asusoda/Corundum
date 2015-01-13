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

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Mooshroom fromMC(EntityMooshroom entityMC) {
            return new Mooshroom(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public MooshroomType getType() {
        return MooshroomType.TYPE;
    }
}