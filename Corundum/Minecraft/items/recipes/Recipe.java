package Corundum.Minecraft.items.recipes;

import Corundum.CorundumException;
import Corundum.Minecraft.items.Item;
import Corundum.Minecraft.items.Item.ItemType;

public abstract class Recipe {
    protected final ItemType resulting_material;
    protected final byte number_of_resulting_items;

    protected Recipe(ItemType resulting_material, int number_of_resulting_items) {
        this.resulting_material = resulting_material;
        this.number_of_resulting_items = (byte) number_of_resulting_items;
    }

    public static class RecipeException extends CorundumException {
        private static final long serialVersionUID = 7672151015638648693L;

        public RecipeException(String message, String issue, Object... additional_information) {
            super(message, issue, additional_information);
        }
    }
}
