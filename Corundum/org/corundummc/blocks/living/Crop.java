package org.corundummc.blocks.living;

import org.corundummc.world.Location;

import net.minecraft.block.Block;

// TODO LINK
/** This {@link Block} subclass represents any {@link Block} that can be cultivated to produce food, including not only single-block crops like wheat and potatoes, but also
 * multi-block plant structures like sugarcane and melons (and melon stems).
 *
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Block <tt>Object</tt> that this class's {@link BlockType} represents.
 * @param <T>
 *            determines the type of {@link BlockType} that represents the type of this class. */
public abstract class Crop<S extends Crop<S, MC, T>, MC extends Block, T extends Crop.CropType<T, MC, S>> extends LivingBlock<S, MC, T> {
    protected Crop(Location location) {
        super(location);
    }

    /** This {@link BlockType} represents the type of any {@link Block} that can be cultivated to produce food, including not only single-block crops like wheat and potatoes,
     * but also multi-block plant structures like sugarcane and melons (and melon stems).
     *
     * @param <S>
     *            is a self-parameterization; this type should be the same type as this class.
     * @param <MC>
     *            determines the type of Minecraft Block <tt>Object</tt> that this class represents.
     * @param <I>
     *            determines the type of {@link Block} that represents the type of this class. */
    public abstract static class CropType<S extends CropType<S, MC, I>, MC extends Block, I extends Crop<I, MC, S>> extends LivingBlockType<S, MC, I> {
        protected CropType(int id, int data) {
            super(id, data);

            addValueAs(CropType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static CropType<?, ?, ?> getByID(int id) {
            return getByID(CropType.class, id);
        }

        public static CropType<?, ?, ?>[] values() {
            return values(CropType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}