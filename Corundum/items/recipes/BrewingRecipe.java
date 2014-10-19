package Corundum.items.recipes;

import org.apache.commons.lang3.ArrayUtils;

import Corundum.items.MaterialType;
import Corundum.utils.ListUtilities;
import Corundum.utils.StringUtilities;

public class BrewingRecipe extends Recipe {
    private MaterialType[] ingredients;

    public BrewingRecipe(MaterialType[] ingredients, MaterialType resulting_material) {
        super(resulting_material, 1);

        this.ingredients = ingredients;
    }

}
