package org.corundummc.types;

public abstract class Typed<T extends IDedType> {
    public abstract String getCustomName();

    public abstract T getType();
}
