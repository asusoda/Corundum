package org.corundummc.entities.nonliving.projectiles;

import net.minecraft.entity.item.EntityEnderEye;
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
    }

    // instance utilities

    // overridden utilities
    @Override
    public EyeOfEnderType getType() {
        return EyeOfEnderType.TYPE;
    }
}
