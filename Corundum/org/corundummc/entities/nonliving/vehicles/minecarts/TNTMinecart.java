package org.corundummc.entities.nonliving.vehicles.minecarts;

import net.minecraft.entity.item.EntityMinecartTNT;
import org.corundummc.entities.nonliving.vehicles.minecarts.Minecart.MinecartType;

public class TNTMinecart extends Minecart<TNTMinecart, EntityMinecartTNT, TNTMinecart.TNTMinecartType> {
    public TNTMinecart() {
        super(new EntityMinecartTNT(null));
    }

    protected TNTMinecart(EntityMinecartTNT entityMC) {
        super(entityMC);
    }

    protected static class TNTMinecartType extends MinecartType<TNTMinecartType, EntityMinecartTNT, TNTMinecart> {
        public static final TNTMinecartType TYPE = new TNTMinecartType();

        private TNTMinecartType() {
            super(45);
        }

        // overridden utilities
        @Override
        public TNTMinecart create() {
            return new TNTMinecart();
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public TNTMinecartType getType() {
        return TNTMinecartType.TYPE;
    }
}