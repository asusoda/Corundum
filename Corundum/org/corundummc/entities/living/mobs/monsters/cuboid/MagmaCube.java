package org.corundummc.entities.living.mobs.monsters.cuboid;

import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntitySlime;

import org.corundummc.entities.living.mobs.monsters.cuboid.CuboidMonster.CuboidMonsterType;

public class MagmaCube extends CuboidMonster<MagmaCube, EntityMagmaCube, MagmaCube.MagmaCubeType> {
    public MagmaCube() {
        super(new EntityMagmaCube(null));
    }

    protected MagmaCube(EntityMagmaCube entityMC) {
        super(entityMC);
    }

    protected static class MagmaCubeType extends CuboidMonsterType<MagmaCubeType, EntityMagmaCube, MagmaCube> {
        public static final MagmaCubeType TYPE = new MagmaCubeType();

        private MagmaCubeType() {
            super(62);
        }

        // overridden utilities
        @Override
        public MagmaCube create() {
            return new MagmaCube();
        }

        @Override
        public MagmaCube fromMC(EntityMagmaCube entityMC) {
            return new MagmaCube(entityMC);
        }
    }

    // instance utilities

    // overridden utilities
    @Override
    public MagmaCubeType getType() {
        return MagmaCubeType.TYPE;
    }
}