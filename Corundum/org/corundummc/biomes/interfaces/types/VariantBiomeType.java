package org.corundummc.biomes.interfaces.types;

import org.corundummc.biomes.Biome.BiomeType;

public interface VariantBiomeType<B extends BiomeType<B, ?, ?>> {
    public B getBaseBiomeType();
}
