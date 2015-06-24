package org.corundummc.blocks.living;

import org.corundummc.world.Location;

import net.minecraft.block.Block;

// TODO LINK
/** This {@link Block} subclass represents any {@link Block} that can be somehow farmed by players in order to produce food or a food ingredient.
 *
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Block <tt>Object</tt> that this class's {@link BlockType} represents.
 * @param <T>
 *            determines the type of {@link BlockType} that represents the type of this class. */
public interface CropBlock<S extends CropBlock<S, MC, T>, MC extends Block, T extends CropBlock.CropBlockType<T, MC, S>> {

    /** This {@link BlockType} represents the type of any {@link Block} that can be somehow farmed by players in order to produce food or a food ingredient.
     *
     * @param <S>
     *            is a self-parameterization; this type should be the same type as this class.
     * @param <MC>
     *            determines the type of Minecraft Block <tt>Object</tt> that this class represents.
     * @param <I>
     *            determines the type of {@link Block} that represents the type of this class. */
    public static interface CropBlockType<S extends CropBlockType<S, MC, I>, MC extends Block, I extends CropBlock<I, MC, S>> {
        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static CropBlockType<?, ?, ?> getByID(int id) {
            return getByID(CropBlockType.class, id);
        }

        public static CropBlockType<?, ?, ?>[] values() {
            return values(CropBlockType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}