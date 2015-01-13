package org.corundummc.types;

public interface CreatableType<I extends Typed<?>> {
    public I create();
}
