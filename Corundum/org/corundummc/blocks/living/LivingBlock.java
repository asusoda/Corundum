package org.corundummc.blocks.living;

import org.corundummc.blocks.Block;
import org.corundummc.blocks.Block.BlockType;
import org.corundummc.world.Location;

// TODO LINK
/** This {@link Block} subclass represents any {@link Block} that represents a living thing, mainly plants such as flowers or fungi such as mushrooms. This also
 * includes blocks that originate as part of a structure that represents a living thing such as a log (part of a tree) or a giant mushroom block.
 *
 * @param <S>
 *            is a self-parameterization; this type should be the same type as this class.
 * @param <MC>
 *            determines the type of Minecraft Block <tt>Object</tt> that this class's {@link BlockType} represents.
 * @param <T>
 *            determines the type of {@link BlockType} that represents the type of this class. */
public abstract class LivingBlock<S extends LivingBlock<S, MC, T>, MC extends net.minecraft.block.Block, T extends LivingBlock.LivingBlockType<T, MC, S>> extends
        Block<S, MC, T> {
    protected LivingBlock(Location location) {
        super(location);
    }

    /** This {@link BlockType} represents the type of any {@link Block} that represents a living thing, mainly plants such as flowers or fungi such as mushrooms. This also
     * includes blocks that originate as part of a structure that represents a living thing such as a log (part of a tree) or a giant mushroom block.
     *
     * @param <S>
     *            is a self-parameterization; this type should be the same type as this class.
     * @param <MC>
     *            determines the type of Minecraft Block <tt>Object</tt> that this class represents.
     * @param <I>
     *            determines the type of {@link Block} that represents the type of this class. */
    public abstract static class LivingBlockType<S extends LivingBlockType<S, MC, I>, MC extends net.minecraft.block.Block, I extends LivingBlock<I, MC, S>> extends
            BlockType<S, MC, I> {
        protected LivingBlockType(int id, int data) {
            super(id, data);

            addValueAs(LivingBlockType.class);
        }

        // abstract utilities

        // overridden utilities

        // static utilities

        // pseudo-enum utilities
        public static LivingBlockType<?, ?, ?> getByID(int id) {
            return getByID(LivingBlockType.class, id);
        }

        public static LivingBlockType<?, ?, ?>[] values() {
            return values(LivingBlockType.class);
        }
    }

    // static utilities

    // type utilities

    // instance utilities
}