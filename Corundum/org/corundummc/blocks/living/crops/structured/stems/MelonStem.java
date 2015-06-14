package org.corundummc.blocks.living.crops.structured.stems;

import org.corundummc.world.Location;

import net.minecraft.block.BlockStem;

import org.corundummc.blocks.living.crops.structured.stems.Stem.StemType;

// TODO LINK
/** This {@link Block} subclass represents a melon stem, which can produce melons next to it under the right conditions. */
public class MelonStem extends Stem<MelonStem, BlockStem, MelonStem.MelonStemType> {
    protected MelonStem(Location location) {
        super(location);
    }

    /** This class represents one single {@link BlockType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class MelonStemType extends StemType<MelonStemType, BlockStem, MelonStem> {
        public static final MelonStemType TYPE = new MelonStemType();

        private MelonStemType() {
            super(104, -1);
        }

        @Override
        public MelonStem fromLocation(Location location) {
            return new MelonStem(location);
        }
    }

    @Override
    public MelonStemType getType() {
        return MelonStemType.TYPE;
    }
}