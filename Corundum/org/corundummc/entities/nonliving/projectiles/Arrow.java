package org.corundummc.entities.nonliving.projectiles;

import net.minecraft.entity.projectile.EntityArrow;

import org.corundummc.entities.Entity;
import org.corundummc.entities.Player;
import org.corundummc.entities.Velocity;
import org.corundummc.world.Location;

// TODO: link "Arrow item" to the Arrow in the item packages in the Javadoc below; also link "bow"
/** This class represents an arrow. Unlike the Arrow item, this class represents the {@link Entity} that appears a flies through the air when, for example, a {@link Player}
 * fires a bow. */
public class Arrow extends Projectile {
    public Arrow() {
        super(new EntityArrow(null));
    }
}
