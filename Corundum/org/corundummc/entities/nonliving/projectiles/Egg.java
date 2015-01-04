package org.corundummc.entities.nonliving.projectiles;

import net.minecraft.entity.projectile.EntityEgg;
import org.corundummc.entities.nonliving.projectiles.Projectile.ProjectileType;

public class Egg extends Projectile<Egg, EntityEgg, Egg.EggType> {
    public Egg() {
        super(new EntityEgg(null));
    }

    protected Egg(EntityEgg entityMC) {
        super(entityMC);
    }

    protected static class EggType extends ProjectileType<EggType, EntityEgg, Egg> {
        public static final EggType TYPE = new EggType();

        private EggType() {
            super(-1);
        }

        // overridden utilities
        @Override
        public Egg create() {
            return new Egg();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public EggType getType() {
        return EggType.TYPE;
    }
}
