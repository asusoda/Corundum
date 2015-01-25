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

package org.corundummc.items.recipes;

import org.corundummc.utils.ListUtilities;
import org.corundummc.utils.types.IDedType;

public class ShapedCraftingRecipe extends Recipe {
    public ShapedCraftingRecipe(IDedType[] materials, IDedType resulting_material) {
        this(materials, resulting_material, 1);
    }

    public ShapedCraftingRecipe(IDedType[] materials, IDedType resulting_material, int number_of_resulting_items) {
        super(materials, resulting_material, number_of_resulting_items);

        if (materials.length != 9 && materials.length != 4 && materials.length != 1) {
            materials = null;
            throw new IncompleteCraftingRecipeMaterialsException("The materials for this crafting recipe are not in a 2x2 or 3x3 formation!", materials, "which makes "
                    + resulting_material);
        }
    }

    public ShapedCraftingRecipe(IDedType material11, IDedType material21, IDedType material12, IDedType material22, IDedType resulting_material) {
        this(material11, material21, material12, material22, resulting_material, 1);
    }

    public ShapedCraftingRecipe(IDedType material11, IDedType material21, IDedType material12, IDedType material22, IDedType resulting_material, int number_of_resulting_items) {
        super(new IDedType[] { material11, material21, material12, material22 }, resulting_material, number_of_resulting_items);
    }

    public ShapedCraftingRecipe(IDedType material11, IDedType material21, IDedType material31, IDedType material12, IDedType material22, IDedType material32,
            IDedType material13, IDedType material23, IDedType material33, IDedType resulting_material) {
        this(material11, material21, material31, material12, material22, material32, material13, material23, material33, resulting_material, 1);
    }

    public ShapedCraftingRecipe(IDedType material11, IDedType material21, IDedType material31, IDedType material12, IDedType material22, IDedType material32,
            IDedType material13, IDedType material23, IDedType material33, IDedType resulting_material, int number_of_resulting_items) {
        super(new IDedType[] { material11, material21, material31, material12, material22, material32, material13, material23, material33 }, resulting_material,
                number_of_resulting_items);
    }

    public static class IncompleteCraftingRecipeMaterialsException extends RecipeException {
        private static final long serialVersionUID = -699828186348471323L;

        public IncompleteCraftingRecipeMaterialsException(String message, IDedType[] materials_given, Object... additional_information) {
            super(message, "a malformed crafting recipe material set", "materials given: " + ListUtilities.writeArray(materials_given), additional_information);
        }

    }
}
