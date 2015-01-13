package org.corundummc.utils.types;

public abstract class Typed<T extends IDedType> {
    public abstract T getType();

    public int getTypeID() {
        return getType().getID();
    }

    public short getTypeData() {
        if (getType() instanceof IDedTypeWithData<?>)
            return ((IDedTypeWithData<?>) getType()).getData();
        else
            return -1;
    }
}
