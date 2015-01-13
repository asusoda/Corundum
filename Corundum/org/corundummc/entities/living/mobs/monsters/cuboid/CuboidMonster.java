package org.corundummc.entities.living.mobs.monsters.cuboid;

import net.minecraft.entity.monster.EntitySlime;

import org.corundummc.entities.living.mobs.monsters.Monster;
import org.corundummc.entities.living.mobs.monsters.cuboid.MagmaCube.MagmaCubeType;
import org.corundummc.entities.living.mobs.monsters.cuboid.Slime.SlimeType;

/** This class represents a {@link Monster monster} that is cube-shaped like a {@link Slime} or a {@link MagmaCube}. These kinds of monsters generally hop to get around and can
 * split into smaller versions of themselves.
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class CuboidMonster<S extends CuboidMonster<S, MC, T>, MC extends EntitySlime, T extends CuboidMonster.CuboidMonsterType<T, MC, S>> extends Monster<S, MC, T> {
    protected CuboidMonster(MC entityMC) {
        super(entityMC);
    }

    public static interface CuboidMonsterTypes {
        public static final SlimeType SLIME = SlimeType.TYPE;
        public static final MagmaCubeType MAGMA_CUBE = MagmaCubeType.TYPE;
    }

    public abstract static class CuboidMonsterType<S extends CuboidMonsterType<S, MC, I>, MC extends EntitySlime, I extends CuboidMonster<I, MC, S>> extends
            MonsterType<S, MC, I> {
        protected CuboidMonsterType(int id) {
            super(id, -1);

            addValueAs(CuboidMonsterType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        public static CuboidMonsterType<?, ?, ?> getByID(int id) {
            return getByID(CuboidMonsterType.class, id);
        }

        public static CuboidMonsterType<?, ?, ?> getByID(int id, int data) {
            return getByID(CuboidMonsterType.class, id, data);
        }

        public static CuboidMonsterType<?, ?, ?>[] values() {
            return values(CuboidMonsterType.class);
        }
    }

    // type utilities

    // instance utilities
}