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

package Corundum.Minecraft.entities;

import Corundum.Minecraft.items.Item;

public class Mob extends Entity {
    private Item[] drops;

    public enum MobType {
        PIG, SKELETON;  // TODO: 3. finish this list

        // TODO: 1. make final variables for all the information that needs to be kept for any mob type

        // TODO: 2. make a private constructor for MobTypes
    }
}
