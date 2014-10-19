package Corundum.items.recipes;

import Corundum.items.Item.ItemType;

public abstract class CraftingRecipe extends Recipe {
    protected CraftingRecipe(ItemType resulting_material, int number_of_resulting_items) {
        super(resulting_material, number_of_resulting_items);
    }
}
