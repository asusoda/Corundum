package org.corundummc.blocks.living.crops.structured;

import org.corundummc.blocks.living.crops.CropBlock;
import org.corundummc.blocks.living.crops.CropBlock.CropBlockType;
import org.corundummc.world.Location;

import net.minecraft.block.Block;

// TODO LINK
/** This {@link Block} subclass represents any {@link Block} that is a {@link CropBlock crop} that grows in a multi-block structure like melons or sugarcane.
 *
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Block <tt>Object</tt> that this class's {@link BlockType} represents.
 * @param <T>
 *            determines the type of {@link BlockType} that represents the type of this class. */
public abstract class StructuredCropBlock<S extends StructuredCropBlock<S, MC, T>, MC extends Block, T extends StructuredCropBlock.StructuredCropBlockType<T, MC, S>> extends
        CropBlock<S, MC, T> {
    protected StructuredCropBlock(Location location) {
        super(location);
    }

    /** This {@link BlockType} represents the type of any {@link Block} that is a {@link CropBlock crop} that grows in a multi-block structure like melons or sugarcane.
     *
     * @param <S>
     *            is a self-parameterization; this type should be the same type as this class.
     * @param <MC>
     *            determines the type of Minecraft Block <tt>Object</tt> that this class represents.
     * @param <I>
     *            determines the type of {@link Block} that represents the type of this class. */
    public abstract static class StructuredCropBlockType<S extends StructuredCropBlockType<S, MC, I>, MC extends Block, I extends StructuredCropBlock<I, MC, S>> extends
            CropBlockType<S, MC, I> {
        protected StructuredCropBlockType(int id, int data) {
            super(id, data);

            addValueAs(StructuredCropBlockType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static StructuredCropBlockType<?, ?, ?> getByID(int id) {
            return getByID(StructuredCropBlockType.class, id);
        }

        public static StructuredCropBlockType<?, ?, ?>[] values() {
            return values(StructuredCropBlockType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}