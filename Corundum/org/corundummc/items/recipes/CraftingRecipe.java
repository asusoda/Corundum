package org.corundummc.items.recipes;

import org.corundummc.utils.types.IDedType;

public abstract class CraftingRecipe extends Recipe {
    protected CraftingRecipe(IDedType<?>[] materials, IDedType<?> resulting_material, int number_of_resulting_items) {
        super(materials, resulting_material, number_of_resulting_items);
    }
}
