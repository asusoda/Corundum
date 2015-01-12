package org.corundummc.entities.living.mobs.monsters.bugs;

import net.minecraft.entity.monster.EntitySilverfish;
import org.corundummc.entities.living.mobs.monsters.bugs.Bug.BugType;

public class Silverfish extends Bug<Silverfish, EntitySilverfish, Silverfish.SilverfishType> {
    public Silverfish() {
        super(new EntitySilverfish(null));
    }

    protected Silverfish(EntitySilverfish entityMC) {
        super(entityMC);
    }

    protected static class SilverfishType extends BugType<SilverfishType, EntitySilverfish, Silverfish> {
        public static final SilverfishType TYPE = new SilverfishType();

        private SilverfishType() {
            super(60);
        }

        // overridden utilities
        @Override
        public Silverfish create() {
            return new Silverfish();
        }

        @Override
        public Silverfish fromMC(EntitySilverfish entityMC) {
            return new Silverfish(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public SilverfishType getType() {
        return SilverfishType.TYPE;
    }
}