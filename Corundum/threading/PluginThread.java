package Corundum.threading;

import Corundum.CorundumPlugin;

/**
 * Used for plugin threads, and also for securing Corundum.
 */
public class PluginThread extends Thread {
    private CorundumPlugin threadOwner;
    private Runnable threadRunnable;

    public PluginThread(CorundumPlugin plugin, Runnable runnable) {
        this.threadOwner = plugin;
        this.threadRunnable = runnable;
    }

    @Override
    public void run() {
        this.threadRunnable.run();
    }

    public CorundumPlugin getThreadOwnerPlugin() {
        return this.threadOwner;
    }
}
