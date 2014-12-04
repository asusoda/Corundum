package org.corundummc.types;

public abstract class Creatable implements Typed {
    public static Creatable newInstance(CreatableType type) {
        return type.create();
    }
}
