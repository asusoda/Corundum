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

package Corundum.world;

import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;
import Corundum.utils.interfaces.Matchable;
import Corundum.utils.myList.myList;

public class World implements Matchable<World> {
    // TODO: public static final World MAIN_WORLD;
    public static myList<World> worlds = new myList<World>();

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

    public static World fromMCWorld(WorldServer worldMC) {
        return worlds.findMatch(null, worldMC);
    }

    public WorldServer getMCWorld() {
        return worldMC;
    }

    public String getName() {
        WorldInfo info = worldMC.getWorldInfo();
        return info.getWorldName();
    }

    public WorldType getType() {
        return type;
    }

    public enum WorldType {
        OVERWORLD, NETHER, END, CUSTOM;
    }

    @Override
    public Object[] getSortPriorities() {
        return new Object[] { type.ordinal(), getName() };
    }

}
