package org.corundummc.types;

import java.util.ArrayList;
import java.util.Arrays;

import org.corundummc.exceptions.CIE;
import org.corundummc.utils.ListUtilities;
import org.corundummc.world.Block.BlockType;
import org.corundummc.world.Block;

public abstract class IDedTypeWithData<T extends IDedTypeWithData<T>> extends IDedType<T> {
    private final short data;

    // constructors
    protected IDedTypeWithData(int id, int data) {
        super(id);

        this.data = (short) data;
    }

    // static utilities
    /** This method retrieves the {@link IDedTypeWithData} with the given item I.D. and data values.
     * 
     * @param clazz
     *            is the subclass for which the values should be retrieved.
     * @param id
     *            is the item I.D. of the {@link IDedTypeWithData} you wish to locate.
     * @param data
     *            is the data value for the item you wish to locate. A negative value is considered a "wild card", meaning that is will consider the given data value
     *            irrelevant and match the item with the given I.D. and the lowest available data value (almost always 0 or -1).
     * @return the {@link IDedTypeWithData} that matches the given item I.D. and data value or <b>null</b> if no {@link IDedTypeWithData} has the given I.D. and data value. */
    protected static <R extends IDedTypeWithData<?>> R getByID(Class<R> clazz, int id, int data) {
        // TODO: replace this linear search with a binary search algorithm
        if (id >= 0)
            for (R type : values(clazz))
                if (type.getID() == id && (data < 0 || data == type.getData()))
                    return type;
        return null;
    }

    // instance utilities
    /** This method returns the data value associated with this {@link IDedTypeWithData}. The data value is an extra identifier in Minecraft used to differentiate between
     * blocks on a more specific level than I.D.s. If two blocks have the same I.D., but different data values, they could be the same kind of block, but be different
     * sub-types; for example, {@link BlockType#OAK_LOG oak logs} and {@link BlockType#SPRUCE_LOG spruce logs} are both types of wood logs and they both have the same I.D. and
     * a lot of the same properties, but their data values are different: oak logs have a data value of 0 while spruce logs have a data value of 1.
     * <hr>
     * <i>NOTE:</i> Minecraft often uses data values to indicate different things about the block it pertains to, including not just sub-types like what kind of slab it is,
     * but also things like the orientation of a log block (up/down, north/south, or east/west). Therefore, not all blocks will have the same data values as their
     * {@link IDedTypeWithData}s would indicate. However, there is a pattern in the way Minecraft handles orientation of blocks using their data values: all orientations of a
     * given block will have the same data value as its {@link IDedTypeWithData} plus the number of subtypes for that block I.D. times some integer (i.e.
     * <tt>[{@link Block} data] = [{@link IDedTypeWithData} data] + [number of different subtypes] * [some non-negative integer]</tt>).
     * <hr>
     * You can learn more about which kinds of blocks have which I.D. and data values at <a href=http://minecraft-ids.grahamedgecombe.com/>this site</a>.
     * 
     * @return the data value associated with this {@link IDedTypeWithData} or -1 if this {@link IDedTypeWithData} is not associated with a particular data value like
     *         {@link BlockType#GRASS grass blocks}. */
    public short getData() {
        return data;
    }

    /** This method retrieves all the {@link IDedType}s that are "siblings" of this {@link IDedType}, i.e. all the {@link IDedType}s with the same I.D. as this one.
     * 
     * @return all the siblings of this {@link IDedType}. */
    @SuppressWarnings("unchecked")
    public T[] getSiblings() {
        // retrieve the values for this type
        T[] values = (T[]) values(getClass());

        // find the first index with a value with the same I.D. as this type
        // TODO: replace this linear search with a binary search algorithm
        int first_index = -1;
        for (int i = 0; i < values.length; i++)
            if (values[i].getID() == getID())
                first_index = getID();
        if (first_index == -1) {
            CIE.err("I couldn't find anything with this I.D. when searching through the list for values for this type when searching for this type's siblings... not even this type itself!",
                    "missing type value", "type=" + getClass() + " " + toString(), "I.D.=" + getID(), "data=" + data);
            return null;
        }

        // find the last index with this I.D.
        int last_index_plus_1 = first_index + 1;
        while (values[last_index_plus_1].getID() == getID())
            last_index_plus_1++;

        // build an list with the elements from first_index to last_index_plus_1 (not including [last_index_plus_1])
        // NOTE: an ArrayList has to be made and converted to an array because you can't make an array of a generic T in Java
        return (T[]) new ArrayList<>(Arrays.asList(ListUtilities.subArray(values, first_index, last_index_plus_1))).toArray();
    }

    /** This method determines whether or not this {@link IDedType} is a "sibling" of the given {@link IDedType}. Corundum considered two {@link IDedType}s "siblings" if the
     * have the same item I.D. like {@link BlockType#STONE stone}, {@link BlockType#GRANITE granite}, and {@link BlockType#DIORITE diorite}, which all have the I.D. 0 but have
     * different data values to differentiate between them.
     * <hr>
     * You can see which I.D.s represent which blocks and which blocks have the same I.D. on <a href=http://minecraft-ids.grahamedgecombe.com/>this site</a>.
     * 
     * @param material
     *            is the {@link IDedType} to compare to this {@link IDedType} to see if they're "siblings".
     * @return <b>true</b> if this {@link IDedType} has the same I.D. as <b><tt>type</b></tt>; <b>false</b> otherwise. */
    public boolean isASiblingOf(T material) {
        return material.getID() == getID();
    }

    // data management overrides
    @Override
    public Object[] getSortPriorities() {
        return new Object[] { getID(), getData() };
    }

    @Override
    public String toString() {
        return this.getName() + " (" + this.getID() + ":" + this.getData() + ")";
    }
}
