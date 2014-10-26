package Corundum.items.recipes;

import Corundum.utils.interfaces.IDedType;

public class BrewingRecipe extends Recipe {
    public BrewingRecipe(IDedType[] ingredients, IDedType resulting_material) {
        super(ingredients, resulting_material, 1);
    }

}
