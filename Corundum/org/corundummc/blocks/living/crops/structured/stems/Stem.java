package org.corundummc.blocks.living.crops.structured.stems;

import org.corundummc.blocks.living.crops.structured.StructuredCropBlock;
import org.corundummc.world.Location;

// TODO LINK
/** This {@link Block} subclass represents any {@link Block} that represents the stem of a plant, e.g. the stem of a pumpkin or a melon.
 *
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Block <tt>Object</tt> that this class's {@link BlockType} represents.
 * @param <T>
 *            determines the type of {@link BlockType} that represents the type of this class. */
public abstract class Stem<S extends Stem<S, MC, T>, MC extends net.minecraft.block.Block, T extends Stem.StemType<T, MC, S>> extends StructuredCropBlock<S, MC, T> {
    protected Stem(Location location) {
        super(location);
    }

    /** This {@link BlockType} represents the type of any {@link Block} that represents the stem of a plant, e.g. the stem of a pumpkin or a melon.
     *
     * @param <S>
     *            is a self-parameterization; this type should be the same type as this class.
     * @param <MC>
     *            determines the type of Minecraft Block <tt>Object</tt> that this class represents.
     * @param <I>
     *            determines the type of {@link Block} that represents the type of this class. */
    public abstract static class StemType<S extends StemType<S, MC, I>, MC extends net.minecraft.block.Block, I extends Stem<I, MC, S>> extends
            StructuredCropBlockType<S, MC, I> {
        protected StemType(int id, int data) {
            super(id, data);

            addValueAs(StemType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static StemType<?, ?, ?> getByID(int id) {
            return getByID(StemType.class, id);
        }

        public static StemType<?, ?, ?>[] values() {
            return values(StemType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}