package org.corundummc.entities.nonliving.vehicles.minecarts;

import net.minecraft.entity.EntityMinecartCommandBlock;
import org.corundummc.entities.nonliving.vehicles.minecarts.Minecart.MinecartType;

public class CommandMinecart extends Minecart<CommandMinecart, EntityMinecartCommandBlock, CommandMinecart.CommandMinecartType> {
    public CommandMinecart() {
        super(new EntityMinecartCommandBlock(null));
    }

    protected CommandMinecart(EntityMinecartCommandBlock entityMC) {
        super(entityMC);
    }

    protected static class CommandMinecartType extends MinecartType<CommandMinecartType, EntityMinecartCommandBlock, CommandMinecart> {
        public static final CommandMinecartType TYPE = new CommandMinecartType();

        private CommandMinecartType() {
            super(40);
        }

        // overridden utilities
        @Override
        public CommandMinecart create() {
            return new CommandMinecart();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public CommandMinecart fromMC(EntityMinecartCommandBlock entityMC) {
            return new CommandMinecart(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public CommandMinecartType getType() {
        return CommandMinecartType.TYPE;
    }
}