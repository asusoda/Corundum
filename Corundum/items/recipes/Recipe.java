package Corundum.items.recipes;

import java.util.ArrayList;

import Corundum.exceptions.CorundumException;
import Corundum.types.IDedType;
import Corundum.utils.ListUtilities;
import Corundum.utils.interfaces.Matchable;
import Corundum.utils.myList.myList;

public abstract class Recipe implements Matchable<Recipe> {
    public static final myList<Recipe> RECIPES = new myList<Recipe>( /* TODO */);

    protected final IDedType<?>[] materials;
    protected final IDedType<?> resulting_material;
    protected final byte number_of_resulting_items;

    protected Recipe(IDedType<?>[] materials, IDedType resulting_material, int number_of_resulting_items) {
        this.materials = materials;
        this.resulting_material = resulting_material;
        this.number_of_resulting_items = (byte) number_of_resulting_items;
    }

    public static class RecipeException extends CorundumException {
        private static final long serialVersionUID = 7672151015638648693L;

        public RecipeException(String message, String issue, Object... additional_information) {
            super(message, issue, additional_information);
        }
    }

    @Override
    public Object[] getSortPriorities() {
        ArrayList<IDedType<?>> sort_priorities = new ArrayList<IDedType<?>>();

        // sort priorities first by their resulting material type
        sort_priorities.add(resulting_material);

        // sort recipes secondarily by their input materials
        for (IDedType<?> material : materials)
            sort_priorities.add(material);

        return sort_priorities.toArray();
    }

    @Override
    public String toString() {
        return ListUtilities.writeArray(materials) + " -> " + resulting_material;
    }
}
