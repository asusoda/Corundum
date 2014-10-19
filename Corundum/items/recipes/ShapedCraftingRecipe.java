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

package Corundum.items.recipes;

import Corundum.Corundum;
import Corundum.items.MaterialType;

public class ShapedCraftingRecipe extends Recipe {
    private final MaterialType[][] materials;

    // constructors by Materials
    public ShapedCraftingRecipe(MaterialType[][] materials, MaterialType resulting_material) {
        this(materials, resulting_material, 1);
    }

    public ShapedCraftingRecipe(MaterialType[][] materials, MaterialType resulting_material, int number_of_resulting_items) {
        super(resulting_material, number_of_resulting_items);

        if (!(materials.length == 3 && materials[0].length == 3) && !(materials.length == 2 && materials[0].length == 2)) {
            materials = null;
            throw new IncompleteCraftingRecipeMaterialsException("The materials for this crafting recipe are not in a 2x2 or 3x3 formation!", materials, "which makes "
                    + resulting_material);
        }
        this.materials = materials;
    }

    public ShapedCraftingRecipe(MaterialType material11, MaterialType material21, MaterialType material12, MaterialType material22, MaterialType resulting_material) {
        this(material11, material21, material12, material22, resulting_material, 1);
    }

    public ShapedCraftingRecipe(MaterialType material11, MaterialType material21, MaterialType material12, MaterialType material22, MaterialType resulting_material, int number_of_resulting_items) {
        super(resulting_material, number_of_resulting_items);

        materials = new MaterialType[][] { new MaterialType[] { material11, material21 }, new MaterialType[] { material12, material22 } };
    }

    public ShapedCraftingRecipe(MaterialType material11, MaterialType material21, MaterialType material31, MaterialType material12, MaterialType material22, MaterialType material32,
            MaterialType material13, MaterialType material23, MaterialType material33, MaterialType resulting_material) {
        this(material11, material21, material31, material12, material22, material32, material13, material23, material33, resulting_material, 1);
    }

    public ShapedCraftingRecipe(MaterialType material11, MaterialType material21, MaterialType material31, MaterialType material12, MaterialType material22, MaterialType material32,
            MaterialType material13, MaterialType material23, MaterialType material33, MaterialType resulting_material, int number_of_resulting_items) {
        super(resulting_material, number_of_resulting_items);

        materials =
                new MaterialType[][] { new MaterialType[] { material11, material21, material31 }, new MaterialType[] { material12, material22, material32 },
                        new MaterialType[] { material13, material23, material33 } };
    }

    private static MaterialType[][] toMaterials(int[][] IDs) {
        // TODO
        return null;
    }

    public static class IncompleteCraftingRecipeMaterialsException extends RecipeException {
        private static final long serialVersionUID = -699828186348471323L;

        public IncompleteCraftingRecipeMaterialsException(String message, MaterialType[][] materials_given, Object... additional_information) {
            super(message, "a malformed crafting recipe material set", "materials given: " + materials_given.toString(), additional_information);
        }

    }
}
