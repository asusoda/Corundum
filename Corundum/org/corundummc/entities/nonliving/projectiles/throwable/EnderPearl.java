package org.corundummc.entities.nonliving.projectiles.throwable;

import net.minecraft.entity.item.EntityEnderPearl;

import org.corundummc.entities.nonliving.projectiles.Projectile;
import org.corundummc.entities.nonliving.projectiles.Projectile.ProjectileType;

public class EnderPearl extends Projectile<EnderPearl, EntityEnderPearl, EnderPearl.EnderPearlType> {
    public EnderPearl() {
        super(new EntityEnderPearl(null, null));
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
        @Override
        public EnderPearl create() {
            return new EnderPearl();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public EnderPearl fromMC(EntityEnderPearl entityMC) {
            return new EnderPearl(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public EnderPearlType getType() {
        return EnderPearlType.TYPE;
    }
}
