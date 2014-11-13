package Corundum.types;

public abstract class HoldableType<T extends HoldableType<T>> extends IDedTypeWithData<T> {
    protected HoldableType() {
        super();
    }

    protected HoldableType(int data) {
        super(data);
    }

    protected HoldableType(int id, int data) {
        super(id, data);
    }

    protected HoldableType(HoldableType<?> parent) {
        super(parent);
    }

    /** This method returns the maximum number of this type of item that can be put into one single inventory slot. Many items and all blocks have a max stack size of 64.
     * 
     * @return the maximum stack size of this {@link IDedType}. */
    public abstract byte getMaxStackSize();

    public boolean isStackable() {
        return getMaxStackSize() != 1;
    }
}