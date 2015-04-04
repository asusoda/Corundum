package org.corundummc.biomes.overworld.mushroom;

import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;

import net.minecraft.world.biome.BiomeGenMushroomIsland;

import org.corundummc.biomes.overworld.mushroom.MushroomLadenBiome.MushroomLadenBiomeType;

public class MushroomIslandBiome extends MushroomLadenBiome<MushroomIslandBiome, BiomeGenMushroomIsland, MushroomIslandBiome.MushroomIslandBiomeType> {
    protected MushroomIslandBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class MushroomIslandBiomeType extends MushroomLadenBiomeType<MushroomIslandBiomeType, BiomeGenMushroomIsland, MushroomIslandBiome> {
        public static final MushroomIslandBiomeType TYPE = new MushroomIslandBiomeType();

        private MushroomIslandBiomeType() {
            super((BiomeGenMushroomIsland) BiomeGenBase.mushroomIsland);
        }

        @Override
        public MushroomIslandBiome fromLocation(Location location) {
            return new MushroomIslandBiome(location);
        }
    }

    @Override
    public MushroomIslandBiomeType getType() {
        return MushroomIslandBiomeType.TYPE;
    }
}