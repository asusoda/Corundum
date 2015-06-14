package org.corundummc.blocks.living.crops.structured.stems;

import org.corundummc.world.Location;

import net.minecraft.block.BlockStem;

import org.corundummc.blocks.Block;
import org.corundummc.blocks.living.crops.structured.stems.Stem.StemType;

// TODO LINK
/** This {@link Block} subclass represents a pumpkin stem block, which can produce pumpkins next to it under the right conditions. */
public class PumpkinStem extends Stem<PumpkinStem, BlockStem, PumpkinStem.PumpkinStemType> {
    protected PumpkinStem(Location location) {
        super(location);
    }

    /** This class represents one single {@link BlockType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class PumpkinStemType extends StemType<PumpkinStemType, BlockStem, PumpkinStem> {
        public static final PumpkinStemType TYPE = new PumpkinStemType();

        private PumpkinStemType() {
            super(104, -1);
        }

        @Override
        public PumpkinStem fromLocation(Location location) {
            return new PumpkinStem(location);
        }
    }

    @Override
    public PumpkinStemType getType() {
        return PumpkinStemType.TYPE;
    }
}