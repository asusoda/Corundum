package org.corundummc.entities.nonliving.projectiles;

import net.minecraft.entity.projectile.EntityArrow;

import org.corundummc.entities.Entity;
import org.corundummc.entities.Velocity;
import org.corundummc.entities.living.Player;
import org.corundummc.world.Location;

// TODO: link "Arrow item" to the Arrow in the item packages in the Javadoc below; also link "bow"
/** This class represents an arrow. Unlike the Arrow item, this class represents the {@link Entity} that appears a flies through the air when, for example, a {@link Player}
 * fires a bow. */
public class Arrow extends Projectile {
    public Arrow() {
        /* TODO TEST: you can pass null into here without getting immediate NPEs in the constructor as far as I can tell, but I noticed that if you pass a null world, the
         * dimension I.D. will not be set and the dimension I.D. is also not set in Minecraft's Entity.setWorld(), so that may cause issues */
        super(new EntityArrow(null));
    }
}
