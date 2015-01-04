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
    }

    // instance utilities

    // overridden utilities
    @Override
    public CommandMinecartType getType() {
        return CommandMinecartType.TYPE;
    }
}