package org.corundummc.items.recipes;

import org.corundummc.utils.types.IDedType;

public class BrewingRecipe extends Recipe {
    public BrewingRecipe(IDedType[] ingredients, IDedType resulting_material) {
        super(ingredients, resulting_material, 1);
    }

}
