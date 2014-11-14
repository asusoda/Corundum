package net.minecraft.world.biome;

import java.util.ArrayList;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;

/** This class's sole purpose is to be inside the package <tt>net.minecraft.world.biome</tt> so that it can retrieve the values of protected methods and variables inside
 * classes in that package. This class should eventually be replaced by a class inside the normal <tt>Corundum</tt> package that simply extends the <tt>Object</tt>s that we
 * need info from, but to make that work, we need to extend and override everything that uses {@link BiomeGenBase} and {@link BiomeDecorator} to make them instantiate
 * instances of our extended classes rather than the regular Minecraft classes. */
public class BiomeOE {
    public static int getTreesPerChunk(BiomeGenBase biomeMC) {
        return biomeMC.theBiomeDecorator.treesPerChunk;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<SpawnListEntry> getAnimalsSpawnListEntries(BiomeGenBase biomeMC) {
        return (ArrayList<SpawnListEntry>) biomeMC.spawnableCreatureList;
    }
}
