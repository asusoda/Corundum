package org.corundummc.utils.interfaces;

import org.corundummc.items.Item;
import org.corundummc.items.Item.ItemType;

public interface Container<S extends Container<S>> {
    public boolean add(ItemType type);

    public boolean add(ItemType type, int amount);

    public boolean add(Item item);

    public S clear();

    public Item get(int index);

    public Item[] getContents();

    public boolean remove(ItemType type);

    public boolean remove(ItemType type, int amount);

    public boolean remove(Item item);

    public S set(int index, ItemType type);

    public S set(int index, Item item);

    public S setContents();

    public static class ContainerUtils {
        /* TODO: this class can be used to contain static methods that can be used to help implement some of the methods required by this Container interface; some of these
         * methods could have been implemented without the need of this static utilities class if we could have used Java 8, but... oh well */
    }
}
