package Corundum;

import java.util.Collection;

import Corundum.utils.myList.myList;

public abstract class CorundumPlugin implements CorundumListener {
    private myList<CorundumListener> listeners = new myList<CorundumListener>(this);

    // internal Corundum plugin handling
    public final void load() {
        // TODO
        onLoad();
        // TODO
        Main.plugins.add(this);
    }

    public final void enable() {
        // TODO
        onEnable();
        // TODO
    }

    public final void disable() {
        // TODO
        onDisable();
        // TODO
    }

    public final void unload() {
        // TODO
        onUnload();
        // TODO
        Main.plugins.remove(this);
    }

    // plugin handling event handling for plugin makers
    public void onLoad() {
        // do nothing
    }

    public void onEnable() {
        // do nothing
    }

    public void onDisable() {
        // do nothing
    }

    public void onUnload() {
        // do nothing
    }

    // listener management
    public final void addListener(CorundumListener listener) {
        listeners.add(listener);
    }

    public final void clearListeners() {
        listeners.clear();
    }

    public final myList<CorundumListener> getListeners() {
        return listeners;
    }

    public final void setListeners(myList<CorundumListener> listeners) {
        this.listeners = listeners;
    }

    public final void setListeners(Collection<CorundumListener> listeners) {
        this.listeners = new myList<CorundumListener>(listeners);
    }

    public final void setListeners(CorundumListener[] listeners) {
        this.listeners = new myList<CorundumListener>(listeners);
    }

    public final void removeListener(CorundumListener listener) {
        listeners.remove(listener);
    }

    // abstract getters
    public abstract String getName();

    public abstract String getVersion();
}
