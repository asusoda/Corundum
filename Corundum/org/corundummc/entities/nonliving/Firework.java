package org.corundummc.entities.nonliving;

import net.minecraft.entity.item.EntityFireworkRocket;
import org.corundummc.entities.nonliving.NonLivingEntity.NonLivingEntityType;

public class Firework extends NonLivingEntity<Firework, EntityFireworkRocket, Firework.FireworkType> {
    public Firework() {
        super(new EntityFireworkRocket(null));
    }

    protected Firework(EntityFireworkRocket entityMC) {
        super(entityMC);
    }

    protected static class FireworkType extends NonLivingEntityType<FireworkType, EntityFireworkRocket, Firework> {
        public static final FireworkType TYPE = new FireworkType();

        private FireworkType() {
            super(22);
        }

        // overridden utilities
        @Override
        public Firework create() {
            return new Firework();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public Firework fromMC(EntityFireworkRocket entityMC) {
            return new Firework(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public FireworkType getType() {
        return FireworkType.TYPE;
    }
}
