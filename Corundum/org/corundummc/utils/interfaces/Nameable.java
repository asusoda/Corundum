package org.corundummc.utils.interfaces;

public interface Nameable<S extends Nameable<S>> {
    public String getCustomName();

    public S setCustomName(String new_name);
}
