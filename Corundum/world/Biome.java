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

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.plaf.metal.OceanTheme;

import Corundum.entities.Mob.MobType;
import Corundum.utils.interfaces.IDedType;
import Corundum.world.BlockType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.biome.BiomeOE;

public class Biome {
    private final BiomeType type;

    public Biome(BiomeType type) {
        this.type = type;
    }

    public BiomeType getBiomeType() {
        return this.type;
    }
}
