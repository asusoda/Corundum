package org.corundummc.entities.nonliving.projectiles.throwable;

import net.minecraft.entity.item.EntityEnderEye;

import org.corundummc.entities.nonliving.projectiles.Projectile;
import org.corundummc.entities.nonliving.projectiles.Projectile.ProjectileType;

public class EyeOfEnder extends Projectile<EyeOfEnder, EntityEnderEye, EyeOfEnder.EyeOfEnderType> {
    public EyeOfEnder() {
        super(new EntityEnderEye(null));
    }

    protected EyeOfEnder(EntityEnderEye entityMC) {
        super(entityMC);
    }

    protected static class EyeOfEnderType extends ProjectileType<EyeOfEnderType, EntityEnderEye, EyeOfEnder> {
        public static final EyeOfEnderType TYPE = new EyeOfEnderType();

        private EyeOfEnderType() {
            super(15);
        }

        // overridden utilities
        @Override
        public EyeOfEnder create() {
            return new EyeOfEnder();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public EyeOfEnder fromMC(EntityEnderEye entityMC) {
            return new EyeOfEnder(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public EyeOfEnderType getType() {
        return EyeOfEnderType.TYPE;
    }
}
