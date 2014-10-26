package Corundum.utils.interfaces;

import Corundum.world.Block;

public interface HoldableType<T> extends IDedType<T> {
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
    public short getData();

    /** This method returns the maximum number of this type of item that can be put into one single inventory slot. Many items and all blocks have a max stack size of 64.
     * 
     * @return the maximum stack size of this {@link IDedType}. */
    public byte getMaxStackSize();

    /** This method retrieves all the {@link IDedType}s that are "siblings" of this {@link IDedType}, i.e. all the {@link IDedType}s with the same I.D. as this one.
     * 
     * @return all the siblings of this {@link IDedType}. */
    public T[] getSiblings();

    /** This method determines whether or not this {@link IDedType} is a "sibling" of the given {@link IDedType}. Corundum considered two {@link IDedType}s "siblings" if the
     * have the same item I.D. like {@link BlockType#STONE stone}, {@link BlockType#GRANITE granite}, and {@link BlockType#DIORITE diorite}, which all have the I.D. 0 but have
     * different data values to differentiate between them.
     * <hr>
     * You can see which I.D.s represent which blocks and which blocks have the same I.D. on <a href=http://minecraft-ids.grahamedgecombe.com/>this site</a>.
     * 
     * @param material
     *            is the {@link IDedType} to compare to this {@link IDedType} to see if they're "siblings".
     * @return <b>true</b> if this {@link IDedType} has the same I.D. as <b><tt>type</b></tt>; <b>false</b> otherwise. */
    public boolean isASiblingOf(T material);

}
