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

package Corundum.Minecraft.items.recipes;

import Corundum.Corundum;
import Corundum.Minecraft.items.Item.ItemType;

public class CraftingRecipe extends Recipe {
    private final ItemType[][] materials;

    public CraftingRecipe(ItemType[][] materials, ItemType resulting_material) {
        super(resulting_material, 1);

        if (!(materials.length == 3 && materials[0].length == 3) && !(materials.length == 2 && materials[0].length == 2)) {
            materials = null;
            throw new IncompleteCraftingRecipeMaterialsException("The materials for this crafting recipe are not in a 2x2 or 3x3 formation!", materials, "which makes "
                    + resulting_material);
        }
        this.materials = materials;
    }

    public CraftingRecipe(ItemType[][] materials, ItemType resulting_material, int number_of_resulting_items) {
        super(resulting_material, number_of_resulting_items);

        if (!(materials.length == 3 && materials[0].length == 3) && !(materials.length == 2 && materials[0].length == 2)) {
            materials = null;
            throw new IncompleteCraftingRecipeMaterialsException("The materials for this crafting recipe are not in a 2x2 or 3x3 formation!", materials, "which makes "
                    + resulting_material);
        }
        this.materials = materials;
    }

    public static class IncompleteCraftingRecipeMaterialsException extends RecipeException {
        private static final long serialVersionUID = -699828186348471323L;

        public IncompleteCraftingRecipeMaterialsException(String message, ItemType[][] materials_given, Object... additional_information) {
            super(message, "a malformed crafting recipe material set", "materials given: " + materials_given.toString(), additional_information);
        }

    }
}
