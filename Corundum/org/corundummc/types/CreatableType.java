package org.corundummc.types;

public abstract class CreatableType<T extends CreatableType<T>> extends IDedTypeWithData<T> {
    protected CreatableType() {
        super();
    }

    protected CreatableType(int data) {
        super(data);
    }

    protected CreatableType(int id, int data) {
        super(id, data);
    }

    public abstract Creatable create();
}
