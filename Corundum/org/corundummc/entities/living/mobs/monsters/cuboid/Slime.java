package org.corundummc.entities.living.mobs.monsters.cuboid;

import net.minecraft.entity.monster.EntitySlime;
import org.corundummc.entities.living.mobs.monsters.cuboid.CuboidMonster.CuboidMonsterType;

public class Slime extends CuboidMonster<Slime, EntitySlime, Slime.SlimeType> {
    public Slime() {
        super(new EntitySlime(null));
    }

    protected Slime(EntitySlime entityMC) {
        super(entityMC);
    }

    protected static class SlimeType extends CuboidMonsterType<SlimeType, EntitySlime, Slime> {
        public static final SlimeType TYPE = new SlimeType();

        private SlimeType() {
            super(37);
        }

        // overridden utilities
        @Override
        public Slime create() {
            return new Slime();
        }

        @Override
        public Slime fromMC(EntitySlime entityMC) {
            return new Slime(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public SlimeType getType() {
        return SlimeType.TYPE;
    }
}