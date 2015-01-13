package org.corundummc.utils.interfaces;

import org.corundummc.utils.types.Typed;

public interface CreatableType<I extends Typed<?>> {
    public I create();
}
