package Corundum.plugins;


/** Used for plugin threads, and also for securing Corundum. */
public class PluginThread extends Thread {
    private CorundumPlugin plugin;

    public PluginThread(String name) {
        super(name);
    }

    public PluginThread(Runnable target) {
        super(target);

        findPlugin();
    }

    public PluginThread(Runnable target, String name) {
        super(target, name);

        findPlugin();
    }

    public PluginThread(ThreadGroup group, Runnable target) {
        super(group, target);

        findPlugin();
    }

    public PluginThread(ThreadGroup group, String name) {
        super(group, name);

        findPlugin();
    }

    public PluginThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);

        findPlugin();
    }

    public PluginThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);

        findPlugin();
    }

    PluginThread(CorundumPlugin plugin, Runnable target) {
        super(target);

        this.plugin = plugin;
    }

    // private utils
    private void findPlugin() {
        if (Thread.currentThread() instanceof PluginThread)
            plugin = ((PluginThread) Thread.currentThread()).plugin;

        /* if the current thread is not a plugin thread, this must be called from Minecraft or Corundum, so plugin can just remain null */
    }

    public CorundumPlugin getPlugin() {
        return this.plugin;
    }
}
