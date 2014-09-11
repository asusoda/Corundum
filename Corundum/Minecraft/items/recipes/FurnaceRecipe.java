package Corundum.Minecraft.items.recipes;

import Corundum.Minecraft.items.Item;
import Corundum.Minecraft.items.Item.ItemType;

public class FurnaceRecipe extends Recipe {
    private final ItemType cooking_material;

    public FurnaceRecipe(ItemType cooking_material, ItemType resulting_material) {
        super(resulting_material, 1);

        this.cooking_material = cooking_material;
    }
}
