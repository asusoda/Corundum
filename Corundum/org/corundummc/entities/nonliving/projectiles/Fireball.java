package org.corundummc.entities.nonliving.projectiles;

import org.corundummc.world.Location;

// TODO: when available, link Blazes and Ghasts in the Javadoc below
/** This class represents the fireball projectile. Fireballs can come from Ghasts or Blazes or from dispensers firing fire charges. */
public class Fireball extends Projectile {
    protected Fireball(net.minecraft.entity.Entity entityMC) {
        super(entityMC);
    }

    public static class FireballType extends ProjectileType<FireballType> {
        public static final FireballType LARGE_FIREBALL = new FireballType(12), SMALL_FIREBALL = new FireballType();

        protected FireballType() {
            super();
        }

        protected FireballType(int id) {
            super(id);
        }
    }

    public boolean getExplodes() {
        return this.getType() == EntityType.LARGE_FIREBALL;
    }
}
