package org.corundummc.entities.nonliving;

import net.minecraft.entity.effect.EntityLightningBolt;

import org.corundummc.entities.nonliving.NonLivingEntity.NonLivingEntityType;
import org.corundummc.exceptions.CorundumException;
import org.corundummc.plugins.CorundumPlugin;
import org.corundummc.world.Location;

public class LightningBolt extends NonLivingEntity<LightningBolt, EntityLightningBolt, LightningBolt.LightningBoltType> {
    public LightningBolt(Location location) {
        /* we can't have a nullary constructor for LightningBolts without significant reworks because Minecraft's EntityLightningBolt constructors create the lightning bolt in
         * the constructor, unlike other Entities which can be created without spawning them */
        super(new EntityLightningBolt(location.getWorld().getMCWorld(), location.getX(), location.getY(), location.getZ()));
    }

    protected LightningBolt(EntityLightningBolt entityMC) {
        super(entityMC);
    }

    protected static class LightningBoltType extends NonLivingEntityType<LightningBoltType, EntityLightningBolt, LightningBolt> {
        public static final LightningBoltType TYPE = new LightningBoltType();

        private LightningBoltType() {
            super(-1);
        }

        // overridden utilities
        @Override
        public LightningBolt create() {
            throw new LightningBoltCreationException();
        }

        /** This method is used to create a new instance of {@link Entity Corundum Entity} to wrap around the given {@link Minecraft net.minecraft.entity.Entity}.
         * 
         * @param entityMC
         *            is the Minecraft Entity that will wrapped with a new {@link Entity Corundum Entity} <tt>Object</tt>.
         * @return a new Entity created using the given {@link net.minecraft.entity.Entity Minecraft Entity}. */
        @Override
        public LightningBolt fromMC(EntityLightningBolt entityMC) {
            return new LightningBolt(entityMC);
        }

        public static class LightningBoltCreationException extends CorundumException {
            private static final long serialVersionUID = 4042261105793221025L;

            public LightningBoltCreationException() {
                super(
                        "LightningBolts cannot be created using the CreatableType.create() method! Minecraft's code disallows the creation of their LightningBolt entities without arguments.",
                        "attempt to use create() to create a LightningBolt");
            }
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public LightningBoltType getType() {
        return LightningBoltType.TYPE;
    }
}
