package org.corundummc.biomes.interfaces.instances;

import org.corundummc.biomes.Biome.BiomeType;

public interface VariantBiome<B extends BiomeType<B, ?, ?>> {
    public B getBaseBiomeType();
}
