package Corundum.utils.interfaces;

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

    /** This method returns the maximum number of this type of item that can be put into one single inventory slot. Many items and all blocks have a max stack size of 64.
     * 
     * @return the maximum stack size of this {@link IDedType}. */
    public abstract byte getMaxStackSize();

    // TODO: getLocation(): if held, get the location of the holder; otherwise, get its current location

    // TODO: isHeld(): true if an Entity has it in an inventory; false otherwise
}
