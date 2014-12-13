package org.corundummc.types;

import org.corundummc.exceptions.CIE;

public abstract class CreatableType<T extends CreatableType<T>> extends IDedTypeWithData<T> {
    protected CreatableType(int id, int data) {
        super(id, data);
    }

    public Creatable create() {
        throw new UnimplementedTypeCreationException(this);
    }

    public static class UnimplementedTypeCreationException extends CIE {
        private static final long serialVersionUID = 598324649417872513L;

        public UnimplementedTypeCreationException(CreatableType<?> type) {
            super("Someone forgot to implement the create() method for this " + type.getClass().getSimpleName() + " \"" + type.getName() + "\"!",
                    "type with an unimplemented create() method");
        }
    }
}
