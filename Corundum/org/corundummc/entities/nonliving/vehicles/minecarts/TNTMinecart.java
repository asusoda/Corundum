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

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public TNTMinecart fromMC(EntityMinecartTNT entityMC) {
            return new TNTMinecart(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public TNTMinecartType getType() {
        return TNTMinecartType.TYPE;
    }
}