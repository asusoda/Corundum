package org.corundummc.types;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

import org.corundummc.exceptions.CIE;
import org.corundummc.exceptions.CorundumException;
import org.corundummc.utils.ListUtilities;

public abstract class CreatableType<S extends CreatableType<S, I>, I extends Typed<S>> extends IDedTypeWithData<S> {
    protected CreatableType(int id, int data) {
        super(id, data);
    }

    public abstract I create();
}
