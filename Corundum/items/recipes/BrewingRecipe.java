package Corundum.items.recipes;

import org.apache.commons.lang3.ArrayUtils;

import Corundum.items.Item.ItemType;
import Corundum.utils.ListUtilities;
import Corundum.utils.StringUtilities;

public class BrewingRecipe extends Recipe {
    private ItemType[] ingredients;

    public BrewingRecipe(ItemType[] ingredients, ItemType resulting_material) {
        super(resulting_material, 1);

        this.ingredients = ingredients;
    }

}
