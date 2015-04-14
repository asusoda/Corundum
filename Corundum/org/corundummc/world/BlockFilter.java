package org.corundummc.world;

import org.corundummc.blocks.Block;
import org.corundummc.plugins.CorundumPlugin;

public abstract class BlockFilter {
    public static final BlockFilter SOLID = new BlockFilter() {
        @Override
        public boolean matches(@SuppressWarnings("rawtypes") Block block) {
            return block.isSolid();
        }
    };
    public static final BlockFilter LIQUID = new BlockFilter() {
        public boolean matches(Block block) {
            return block.isLiquid();
        }
    };

    // abstract utilities
    public abstract boolean matches(@SuppressWarnings("rawtypes") Block block);

    // static utilities
    /** This method iterates through a given list of {@link BlockFilter}s and determines whether or not the given {@link Block} matches all the given {@link BlockFilter}s. <br>
     * <br>
     * If the given list of {@link BlockFilter}s is empty, this method will always return <b>true</b>.
     * 
     * @param block
     *            is the {@link Block} to be matched against the given list of {@link BlockFilter}s.
     * @param filters
     *            is the list of {@link BlockFilter}s to match against the given {@link Block}.
     * @return <b>true</b> if the given {@link Block} matches all of the given {@link BlockFilter} or if no {@link BlockFilter}s were given; <b>false</b> if any
     *         {@link BlockFilter}s reject the given {@link Block}. */
    public static boolean matches(@SuppressWarnings("rawtypes") Block block, BlockFilter... filters) {
        for (BlockFilter filter : filters)
            if (!filter.matches(block))
                return false;

        return true;
    }

    /** This method can construct a {@link BlockFilter} that checks if any of the given {@link BlockFilter}s match a certain {@link Block}. Because
     * {@link #matches(Block, BlockFilter...)} checks for all {@link BlockFilter}s to match the given {@link Block}, it could be said that the results take on the form of an
     * "AND" operation; this method allows one to easily use "OR" operations as well in simple constructs like the one shown below that matches both {@link Block#isSolid()
     * solid} and {@link Block#isLiquid() liquid} {@link Block}s (where the {@link BlockFilter} methods and
     * variables shown have been statically imported):
     * 
     * <pre>
     *  if (block.{@link #matches(Block, BlockFilter...) matches}({@link #or(BlockFilter...) or}({@link #SOLID}, {@link #LIQUID})))
     *      {@link CorundumPlugin#debug(String) debug}("It's either a solid or a liquid block!");
     * </pre>
     * 
     * @param filters
     *            is the list of {@link BlockFilter}s to be combined with a pseudo-"OR" operation.
     * @return a {@link BlockFilter} that matches any {@link Block} that matches one or more of the {@link BlockFilter}s given. */
    public static BlockFilter or(final BlockFilter... filters) {
        return new BlockFilter() {
            @Override
            public boolean matches(@SuppressWarnings("rawtypes") Block block) {
                for (BlockFilter filter : filters)
                    if (filter.matches(block))
                        return true;
                return false;
            }
        };
    }
}