package org.corundummc.biomes.overworld.sandy.beach;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBeach;

import org.corundummc.biomes.overworld.sandy.beach.BeachesqueBiome.BeachesqueBiomeType;

public class Beach extends BeachesqueBiome<Beach, BiomeGenBeach, Beach.BeachType> {
    protected Beach(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class BeachType extends BeachesqueBiomeType<BeachType, BiomeGenBeach, Beach> {
        public static final BeachType TYPE = new BeachType();

        private BeachType() {
            super((BiomeGenBeach) BiomeGenBase.beach);
        }

        @Override
        public Beach fromLocation(Location location) {
            return new Beach(location);
        }
    }

    @Override
    public BeachType getType() {
        return BeachType.TYPE;
    }
}