package org.corundummc.entities.nonliving.projectiles;

import net.minecraft.entity.projectile.EntityArrow;
import org.corundummc.entities.nonliving.projectiles.Projectile.ProjectileType;

import org.corundummc.entities.Entity;
import org.corundummc.entities.living.Player;

// TODO: link "Arrow item" to the Arrow in the item packages in the Javadoc below; also link "bow"
/** This class represents an arrow. Unlike the Arrow item, this class represents the {@link Entity} that appears a flies through the air when, for example, a {@link Player}
 * fires a bow. */
public class Arrow extends Projectile<Arrow, EntityArrow, Arrow.ArrowType> {
    public Arrow() {
        super(new EntityArrow(null));
    }

    protected Arrow(EntityArrow entityMC) {
        super(entityMC);
    }

    protected static class ArrowType extends ProjectileType<ArrowType, EntityArrow, Arrow> {
        public static final ArrowType TYPE = new ArrowType();

        private ArrowType() {
            super(10);
        }

        // overridden utilities
    }

    // instance utilities

    // overridden utilities
    @Override
    public ArrowType getType() {
        return ArrowType.TYPE;
    }
}