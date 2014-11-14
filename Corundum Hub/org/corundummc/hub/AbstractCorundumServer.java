package org.corundummc.hub;

public interface AbstractCorundumServer {
    /* This interface exists so that I can store a CorundumSevrer inside a CorundumServerThread without knowing what a CorundumServer by making CorundumServer implement this
     * (since CorundumServer is part of Corundum and the Corundum launcher has to be independent of Corundum) */

    public void start(String[] arguments);

    public void quit();
}
