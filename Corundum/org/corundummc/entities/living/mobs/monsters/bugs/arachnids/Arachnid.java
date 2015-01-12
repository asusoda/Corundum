package org.corundummc.entities.living.mobs.monsters.bugs.arachnids;

import net.minecraft.entity.monster.EntitySpider;

import org.corundummc.entities.living.mobs.monsters.bugs.Bug;
import org.corundummc.entities.living.mobs.monsters.bugs.arachnids.CaveSpider.CaveSpiderType;
import org.corundummc.entities.living.mobs.monsters.bugs.arachnids.Spider.SpiderType;

/** TODO
 * 
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Entity <tt>Object</tt> that this class represents.
 * @param <T>
 *            determines the type of {@link EntityType} that represents the type of this class. */
public abstract class Arachnid<S extends Arachnid<S, MC, T>, MC extends EntitySpider, T extends Arachnid.ArachnidType<T, MC, S>> extends Bug<S, MC, T> {
    protected Arachnid(MC entityMC) {
        super(entityMC);
    }

    public static interface ArachnidTypes {
        public static final SpiderType SPIDER = SpiderType.TYPE;
        public static final CaveSpiderType CAVE_SPIDER = CaveSpiderType.TYPE;
    }

    public abstract static class ArachnidType<S extends ArachnidType<S, MC, I>, MC extends EntitySpider, I extends Arachnid<I, MC, S>> extends BugType<S, MC, I> {
        protected ArachnidType(int id) {
            super(id);

            addValueAs(ArachnidType.class);
        }

        // abstract utilities

        // overridden utilities

        // pseudo-enum utilities
        public static ArachnidType<?, ?, ?> getByID(int id) {
            return getByID(ArachnidType.class, id);
        }

        public static ArachnidType<?, ?, ?> getByID(int id, int data) {
            return getByID(ArachnidType.class, id, data);
        }

        public static ArachnidType<?, ?, ?>[] values() {
            return values(ArachnidType.class);
        }
    }

    // type utilities

    // instance utilities
}