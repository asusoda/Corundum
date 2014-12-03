package org.corundummc.hub;

public interface AbstractCorundumServer {
    /* This interface exists so that I can store a CorundumServer inside a CorundumServerThread without knowing what a CorundumServer is by making CorundumServer implement
     * this (since CorundumServer is part of Corundum and the Corundum Hub has to be independent of Corundum) */

    public void start(String... arguments);

    public void quit();
}
