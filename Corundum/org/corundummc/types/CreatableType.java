package org.corundummc.types;

import org.corundummc.exceptions.CIE;

public abstract class CreatableType<T extends CreatableType<T>> extends IDedTypeWithData<T> {
    protected CreatableType(int id, int data) {
        super(id, data);
    }

    static {
        /* TODO: if debugging mode is on, do a check to see if all the EntityTypes are organized correctly; we need to somehow make sure that 1) all the values of any given
         * EntityType subclass has corresponding values with the same name in their parent class, 2) every type has their own overridden create() method (so their create()
         * method should not throw a custom CorundumException that we'll make parent type create() methods throw unless overridden), 3) every type class has their own
         * overridden create() method that returns that specific type (rather than their less specific parent type) */
    }

    public Creatable create() {
        /* I used this Exception instead of simply making the method abstract because the create() method will have to be implemented for each individual type; however, to
         * create the types, many of the type classes in the hierarchy are not abstract, so they have to implement this method even though those classes themselves cannot
         * implement create() properly since it must be different for each type. Therefore, this Exception is useful in that it tells us when an individual type has not
         * implemented create() like it's supposed to even when their type class is forced to implement it. */
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
