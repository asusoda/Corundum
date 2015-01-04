package org.corundummc.entities.nonliving.projectiles;

import net.minecraft.entity.item.EntityExpBottle;
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
    }

    // instance utilities

    // overridden utilities
    @Override
    public BottleOEnchantingType getType() {
        return BottleOEnchantingType.TYPE;
    }
}
