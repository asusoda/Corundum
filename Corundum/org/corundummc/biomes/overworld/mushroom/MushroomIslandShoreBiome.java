package org.corundummc.biomes.overworld.mushroom;

import org.corundummc.biomes.interfaces.instances.VariantBiome;
import org.corundummc.biomes.interfaces.types.VariantBiomeType;
import org.corundummc.world.Location;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMushroomIsland;

import org.corundummc.biomes.overworld.mushroom.MushroomIslandBiome.MushroomIslandBiomeType;

import org.corundummc.biomes.overworld.mushroom.MushroomLadenBiome.MushroomLadenBiomeType;

public class MushroomIslandShoreBiome extends MushroomLadenBiome<MushroomIslandShoreBiome, BiomeGenMushroomIsland, MushroomIslandShoreBiome.MushroomIslandShoreBiomeType>
        implements VariantBiome<MushroomIslandBiomeType> {
    protected MushroomIslandShoreBiome(Location location) {
        super(location);
    }

    /** This class represents one single {@link BiomeType}; classes like this are used instead of
     * simple instances of their parent types because of the self-parameterization <tt>S</tt>. */
    static class MushroomIslandShoreBiomeType extends MushroomLadenBiomeType<MushroomIslandShoreBiomeType, BiomeGenMushroomIsland, MushroomIslandShoreBiome> implements
            VariantBiomeType<MushroomIslandBiomeType> {
        public static final MushroomIslandShoreBiomeType TYPE = new MushroomIslandShoreBiomeType();

        private MushroomIslandShoreBiomeType() {
            super((BiomeGenMushroomIsland) BiomeGenBase.mushroomIslandShore);
        }

        @Override
        public MushroomIslandShoreBiome fromLocation(Location location) {
            return new MushroomIslandShoreBiome(location);
        }

        @Override
        public MushroomIslandBiomeType getBase() {
            return MushroomIslandBiomeType.TYPE;
        }
    }

    @Override
    public MushroomIslandBiomeType getBaseType() {
        return getType().getBase();
    }

    @Override
    public MushroomIslandShoreBiomeType getType() {
        return MushroomIslandShoreBiomeType.TYPE;
    }
}