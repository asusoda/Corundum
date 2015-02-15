package org.corundummc.biomes.interfaces.instances;

import org.corundummc.biomes.Biome.BiomeType;

public interface ColdBiome<W extends BiomeType<W, ?, ?>> {
    public W getWarmRelativeType();
}
