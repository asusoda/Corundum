package org.corundummc.entities.nonliving.projectiles.throwable;

import net.minecraft.entity.projectile.EntityEgg;

import org.corundummc.entities.nonliving.projectiles.Projectile;
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

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Egg fromMC(EntityEgg entityMC) {
            return new Egg(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public EggType getType() {
        return EggType.TYPE;
    }
}
