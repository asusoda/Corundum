package org.corundummc.utils.interfaces;

import org.corundummc.entities.Entity;
import org.corundummc.utils.types.Typed;

public interface MCEquivalentType<MC, I extends Typed<?>> {
    /** This method is used to create a new instance of {@link Entity Corundum entity} to wrap around the given {@link net.minecraft.entity.Entity Minecraft entity}.
     *
     * @param entityMC
     *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum entity} <tt>Object</tt>.
     * @return a new {@link Entity Corundum entity} created using the given {@link net.minecraft.entity.Entity Minecraft entity}. */
    public abstract I fromMC(MC entityMC);
}
