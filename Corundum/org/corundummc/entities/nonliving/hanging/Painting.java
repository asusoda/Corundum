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
    }

    // instance utilities

    // overridden utilities
    @Override
    public PaintingType getType() {
        return PaintingType.TYPE;
    }
}
