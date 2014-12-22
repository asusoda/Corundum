package org.corundummc.types;

public abstract class HoldableType extends IDedTypeWithData {
    protected HoldableType(int id, int data) {
        super(id, data);
    }

    /** This method returns the maximum number of this type of item that can be put into one single inventory slot. Many items and all blocks have a max stack size of 64.
     * 
     * @return the maximum stack size of this {@link IDedType}. */
    public abstract byte getMaxStackSize();

    public boolean isStackable() {
        return getMaxStackSize() != 1;
    }
}