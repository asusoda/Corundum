package org.corundummc.entities.nonliving.projectiles;

import net.minecraft.entity.item.EntityEnderPearl;
import org.corundummc.entities.nonliving.projectiles.Projectile.ProjectileType;

public class EnderPearl extends Projectile<EnderPearl, EntityEnderPearl, EnderPearl.EnderPearlType> {
    public EnderPearl() {
        super(new EntityEnderPearl(null));
    }

    protected EnderPearl(EntityEnderPearl entityMC) {
        super(entityMC);
    }

    protected static class EnderPearlType extends ProjectileType<EnderPearlType, EntityEnderPearl, EnderPearl> {
        public static final EnderPearlType TYPE = new EnderPearlType();

        private EnderPearlType() {
            super(14);
        }

        // overridden utilities
    }

    // instance utilities

    // overridden utilities
    @Override
    public EnderPearlType getType() {
        return EnderPearlType.TYPE;
    }
}
