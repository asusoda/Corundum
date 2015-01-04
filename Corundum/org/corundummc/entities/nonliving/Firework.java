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
    }

    // instance utilities

    // overridden utilities
    @Override
    public FireworkType getType() {
        return FireworkType.TYPE;
    }
}
