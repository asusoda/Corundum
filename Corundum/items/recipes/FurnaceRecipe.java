package Corundum.items.recipes;

import Corundum.items.MaterialType;

public class FurnaceRecipe extends Recipe {
    private final MaterialType cooking_material;

    public FurnaceRecipe(MaterialType cooking_material, MaterialType resulting_material) {
        super(resulting_material, 1);

        this.cooking_material = cooking_material;
    }
}
