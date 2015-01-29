package org.corundummc.world;

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
    public static boolean matches(Block block, BlockFilter... filters) {
        for (BlockFilter filter : filters)
            if (!filter.matches(block))
                return false;

        return true;
    }

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