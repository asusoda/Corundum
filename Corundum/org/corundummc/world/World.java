/** This code is property of the Corundum project managed under the Software Developers' Association of Arizona State University.
 *
 * Copying and use of this open-source code is permitted provided that the following requirements are met:
 *
 * - This code may not be used or distributed for private enterprise, including but not limited to personal or corporate profit. - Any products resulting from the copying,
 * use, or modification of this code may not claim endorsement by the Corundum project or any of its members or associates without written formal permission from the endorsing
 * party or parties. - This code may not be copied or used under any circumstances without the inclusion of this notice and mention of the contribution of the code by the
 * Corundum project. In source code form, this notice must be included as a comment as it is here; in binary form, proper documentation must be included with the final product
 * that includes this statement verbatim.
 *
 * @author REALDrummer */

package org.corundummc.world;

import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.WorldServer;
import org.corundummc.utils.interfaces.MCEquivalent;
import org.corundummc.utils.interfaces.Matchable;
import org.corundummc.utils.myList.myList;

public class World implements Matchable<World>, MCEquivalent<WorldServer> {
    // TODO: public static final World MAIN_WORLD;
    private static myList<World> worlds = new myList<>();

    private final WorldServer worldMC;
    private final WorldType type;

    // TODO: add more constructors to allow the creation of new worlds

    public World(WorldServer worldMC) {
        this.worldMC = worldMC;

        if (worldMC.provider instanceof WorldProviderSurface)
            type = WorldType.OVERWORLD;
        else if (worldMC.provider instanceof WorldProviderHell)
            type = WorldType.NETHER;
        else if (worldMC.provider instanceof WorldProviderEnd)
            type = WorldType.END;
        else
            type = WorldType.CUSTOM;

        if (!worlds.contains(this))
            worlds.add(this);
    }

    public static World fromMC(WorldServer worldMC) {
        for (World world : worlds)
            if (world.MC() == worldMC)
                return world;
        return new World(worldMC);
    }

    // static utilities
    public static myList<World> getWorlds() {
        return worlds;
    }

    // instance utilities
    public String getName() {
        return worldMC.getWorldInfo().getWorldName();
    }

    public WorldType getType() {
        return type;
    }

    public void setBlock(Block.BlockType blockType, Location location) {
        this.worldMC.setBlock(location.getBlockX(), location.getBlockY(), location.getBlockZ(), blockType.MC());
    }

    public void setBlockWithMetadata(Block.BlockType blockType, Location location, byte data) {
        this.worldMC.setBlock(location.getBlockX(), location.getBlockY(), location.getBlockZ(), blockType.MC(), data, 3);
    }

    public void setBlockData(Location blockLocation, byte newData) {
        this.worldMC.setBlockMetadata(blockLocation.getBlockX(), blockLocation.getBlockY(), blockLocation.getBlockZ(), newData, 3);
    }

    public enum WorldType {
        OVERWORLD, NETHER, END, CUSTOM;
    }

    // overridden utilities
    @Override
    public WorldServer MC() {
        return worldMC;
    }

    @Override
    public Object[] getSortPriorities() {
        return new Object[] { type.ordinal(), getName() };
    }
}
