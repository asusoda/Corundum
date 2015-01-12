package org.corundummc.entities.nonliving.hanging;

import net.minecraft.entity.item.EntityPainting;
import org.corundummc.entities.nonliving.hanging.HangingEntity.HangingEntityType;

public class Painting extends HangingEntity<Painting, EntityPainting, Painting.PaintingType> {
    public Painting() {
        super(new EntityPainting(null));
    }

    protected Painting(EntityPainting entityMC) {
        super(entityMC);
    }

    protected static class PaintingType extends HangingEntityType<PaintingType, EntityPainting, Painting> {
        public static final PaintingType TYPE = new PaintingType();

        private PaintingType() {
            super(9);
        }

        // overridden utilities
        @Override
        public Painting create() {
            return new Painting();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Painting fromMC(EntityPainting entityMC) {
            return new Painting(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public PaintingType getType() {
        return PaintingType.TYPE;
    }
}
