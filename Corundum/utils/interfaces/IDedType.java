package Corundum.utils.interfaces;

import Corundum.items.Item;
import Corundum.world.Block;
import Corundum.world.BlockType;

/** This class represents anything that can be categorized into types with unique integer I.D. and data values.
 * 
 * @author REALDrummer
 *
 * @param <T>
 *            is a self-parameterization; <b><tt>T</b></tt> should be the same type as the type implementing this interface. This parameterization allows certain methods in
 *            this interface to return the appropriate subtype of {@link IDedType}. */
public abstract class IDedType<T extends IDedType<T>> implements Matchable<IDedType<T>> {
    protected static IDedType<?>[] values = new IDedType<?>[0];

    protected final short id;

    // constructors
    protected IDedType() {
        if (values.length == 0)
            id = 0;
        else
            id = (short) (values[values.length - 1].id + 1);
    }

    protected IDedType(int id) {
        this.id = (short) id;
    }

    // utilities
    /** This method returns the I.D. that represents this {@link IDedType}. In Minecraft, certain things such as {@link Item items} and {@link Block blocks} can be categorized
     * into "types" that are represented by different I.D.s. You can see which I.D.s represent which blocks or items on <a href=http://minecraft-ids.grahamedgecombe.com/>this
     * site</a>.
     * <hr>
     * <i>NOTE:</i> Just like in Minecraft's code, not all Corundum {@link IDedType}s have different I.D.s; some things, like {@link BlockType#STONE stone} and
     * {@link BlockType#GRANITE granite}, are {@link IDedTypeWithData}, which means they can have the same I.D. and are differentiated by their data values. For example,
     * {@link BlockType#STONE stone} and {@link BlockType#GRANITE granite} both have the I.D. 1, but {@link BlockType#STONE stone} has a data value of 0 while
     * {@link BlockType#GRANITE granite} has a data value of 1. Corundum refers to {@link IDedType}s with the same I.D. but different data values as "siblings".
     * 
     * @return the I.D. associated with this {@link IDedType}. */
    public short getID() {
        return id;
    }

    /** This method retrieves the type with the given entity I.D. value.
     * 
     * @param id
     *            is the entity I.D. of the type you wish to locate.
     * @return the type with the lowest data value that matches the given entity I.D. (This item will almost certainly have a data value of 0 or -1.) */
    protected IDedType<T> getByIDHelper(int id) {
        // TODO: replace this linear search with a binary search algorithm
        if (id >= 0)
            for (IDedType<T> type : (IDedType<T>[]) values)
                if (type.id == id)
                    return type;
        return null;
    }

    @Override
    public Object[] getSortPriorities() {
        return new Object[] { getID() };
    }
}
