package Corundum.utils.interfaces;

import Corundum.world.Block;
import Corundum.world.BlockType;

public abstract class IDedTypeWithData<T extends IDedTypeWithData<T>> extends IDedType<T> {
    protected final short data;

    protected IDedTypeWithData() {
        super(initID1());

        // infer the data value using the previous type
        IDedTypeWithData<T> previous_value = (IDedTypeWithData<T>) valuesHelper()[valuesHelper().length - 1];

        /* if the previous data was -1, we are not in an I.D. block, so increment I.D. and default data to -1 */
        if (previous_value.data == -1)
            data = -1;
        /* if the previous data value was not -1, we're in an I.D. block, so use the same I.D. as the previous and increment data */
        else
            data = (short) (previous_value.data + 1);
    }

    protected IDedTypeWithData(int data) {
        this(initID2(data), data);
    }

    protected IDedTypeWithData(int id, int data) {
        super(id);

        this.data = (short) data;
    }

    // constructor methods (only used for super() calls in constructors)
    private static int initID1() {
        // infer the I.D. using the previous type
        IDedTypeWithData<?> previous_value = (IDedTypeWithData<?>) values(T.class)[valuesHelper().length - 1];

        /* if the previous data was -1, we are not in an I.D. block, so increment I.D. and default data to -1 */
        if (previous_value.data == -1)
            return previous_value.id + 1;
        /* if the previous data value was not -1, we're in an I.D. block, so use the same I.D. as the previous and increment data */
        else
            return previous_value.id;
    }

    private static int initID2(int given_data) {
        // infer the I.D. using the previous type
        IDedTypeWithData<?> previous_value = (IDedTypeWithData<?>) valuesHelper()[valuesHelper().length - 1];

        // if data <= the previous's data, it indicates the start of a new I.D. block, so increment the I.D. of the previous value
        if (given_data <= previous_value.data)
            return previous_value.id + 1;
        // otherwise, this is the continuation of an I.D. block, so use the same I.D.
        else
            return previous_value.id;
    }

    // utilities
    /** This method returns the data value associated with this {@link IDedType}. The data value is an extra identifier in Minecraft used to differentiate between blocks on a
     * more specific level than I.D.s. If two blocks have the same I.D., but different data values, they could be the same kind of block, but be different sub-types; for
     * example, {@link IDedType#OAK_LOG oak logs} and {@link IDedType#SPRUCE_LOG spruce logs} are both types of wood logs and they both have the same I.D. and a lot of the same
     * properties, but their data values are different: oak logs have a data value of 0 while spruce logs have a data value of 1.
     * <hr>
     * <i>NOTE:</i> Minecraft often uses data values to indicate different things about the block it pertains to, including not just sub-types like what kind of slab it is,
     * but also things like the orientation of a log block (up/down, north/soth, or east/west). Therefore, not all blocks will have the same data values as their
     * {@link IDedType}s would indicate. However, there is a pattern in the way Minecraft handles orientation of blocks using their data values: all orientations of a given
     * block will have the same data value as its {@link IDedType} plus the number of subtypes for that block I.D. times some integer (i.e.
     * <tt>[{@link Block} data] = [{@link IDedType} data] + [number of different subtypes] * [some non-negative integer]</tt>).
     * <hr>
     * You can learn more about which kinds of blocks have which I.D. and data values at <a href=http://minecraft-ids.grahamedgecombe.com/>this site</a>.
     * 
     * @return the data value associated with this {@link IDedType} or -1 if this {@link IDedType} is not associated with a particular data value like {@link IDedType#GRASS
     *         grass blocks}. */
    public short getData() {
        return data;
    }

    /** This method retrieves all the {@link IDedType}s that are "siblings" of this {@link IDedType}, i.e. all the {@link IDedType}s with the same I.D. as this one.
     * 
     * @return all the siblings of this {@link IDedType}. */
    public T[] getSiblings() {
        // TODO
        return null;
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

    /** This method retrieves the {@link IDedTypeWithData} with the given item I.D. and data values.
     * 
     * @param id
     *            is the item I.D. of the {@link IDedTypeWithData} you wish to locate.
     * @param data
     *            is the data value for the item you wish to locate. A negative value is considered a "wild card", meaning that is will consider the given data value
     *            irrelevant and match the item with the given I.D. and the lowest available data value (almost always 0 or -1).
     * @return the {@link IDedTypeWithData} that matches the given item I.D. and data value or <b>null</b> if no {@link IDedTypeWithData} has the given I.D. and data value. */
    public IDedTypeWithData<T> getByIDHelper(int id, int data) {
        // TODO: replace this linear search with a binary search algorithm
        if (id >= 0)
            for (IDedTypeWithData<T> type : (IDedTypeWithData<T>[]) valuesHelper())
                if (type.id == id && (data < 0 || data == type.data))
                    return type;
        return null;
    }

    @Override
    public Object[] getSortPriorities() {
        return new Object[] { getID(), getData() };
    }
}
