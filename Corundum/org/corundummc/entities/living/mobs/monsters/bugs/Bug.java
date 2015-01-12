package org.corundummc.entities.living.mobs.monsters.bugs;

import net.minecraft.entity.monster.EntityMob;

import org.corundummc.entities.living.mobs.monsters.Monster;
import org.corundummc.entities.living.mobs.monsters.bugs.Silverfish.SilverfishType;
import org.corundummc.entities.living.mobs.monsters.bugs.arachnids.Arachnid.ArachnidTypes;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class Bug<S extends Bug<S, MC, T>, MC extends EntityMob, T extends Bug.BugType<T, MC, S>> extends Monster<S, MC, T> {
    protected Bug(MC entityMC) {
        super(entityMC);
    }

    public static interface BugTypes extends ArachnidTypes {
        public static final SilverfishType SILVERFISH = SilverfishType.TYPE;
        // 1.8: public static final EndermiteType ENDERMITE = Endermite.TYPE;
    }

    public abstract static class BugType<S extends BugType<S, MC, I>, MC extends EntityMob, I extends Bug<I, MC, S>> extends MonsterType<S, MC, I> {
        protected BugType(int id) {
            super(id, -1);

            addValueAs(BugType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        public static BugType<?, ?, ?> getByID(int id) {
            return getByID(BugType.class, id);
        }

        public static BugType<?, ?, ?> getByID(int id, int data) {
            return getByID(BugType.class, id, data);
        }

        public static BugType<?, ?, ?>[] values() {
            return values(BugType.class);
        }
    }

    // type utilities

    // instance utilities
}