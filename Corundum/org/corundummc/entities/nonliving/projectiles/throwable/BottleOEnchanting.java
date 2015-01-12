package org.corundummc.entities.nonliving.projectiles.throwable;

import net.minecraft.entity.item.EntityExpBottle;

import org.corundummc.entities.nonliving.projectiles.Projectile;
import org.corundummc.entities.nonliving.projectiles.Projectile.ProjectileType;

public class BottleOEnchanting extends Projectile<BottleOEnchanting, EntityExpBottle, BottleOEnchanting.BottleOEnchantingType> {
    public BottleOEnchanting() {
        super(new EntityExpBottle(null));
    }

    protected BottleOEnchanting(EntityExpBottle entityMC) {
        super(entityMC);
    }

    protected static class BottleOEnchantingType extends ProjectileType<BottleOEnchantingType, EntityExpBottle, BottleOEnchanting> {
        public static final BottleOEnchantingType TYPE = new BottleOEnchantingType();

        private BottleOEnchantingType() {
            super(17);
        }

        // overridden utilities
        @Override
        public BottleOEnchanting create() {
            return new BottleOEnchanting();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public BottleOEnchanting fromMC(EntityExpBottle entityMC) {
            return new BottleOEnchanting(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public BottleOEnchantingType getType() {
        return BottleOEnchantingType.TYPE;
    }
}
