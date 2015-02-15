package org.corundummc.biomes.interfaces.types;

import org.corundummc.biomes.Biome.BiomeType;

public interface ColdBiomeType<W extends BiomeType<W, ?, ?>> {
    public W getWarmRelative();
}
