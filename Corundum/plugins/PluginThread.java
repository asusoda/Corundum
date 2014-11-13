package Corundum.plugins;

/** Used for plugin threads, and also for securing Corundum. */
public class PluginThread extends Thread {
    private final CorundumPlugin plugin;

    // constructors
    public PluginThread(String name) {
        super(name);

        if (Thread.currentThread() instanceof PluginThread)
            plugin = ((PluginThread) Thread.currentThread()).plugin;
        /* if the current thread is not a plugin thread, this must be called from Minecraft or Corundum, so plugin can just remain null */
        else
            plugin = null;
    }

    public PluginThread(Runnable target) {
        super(target);

        if (Thread.currentThread() instanceof PluginThread)
            plugin = ((PluginThread) Thread.currentThread()).plugin;
        /* if the current thread is not a plugin thread, this must be called from Minecraft or Corundum, so plugin can just remain null */
        else
            plugin = null;
    }

    public PluginThread(Runnable target, String name) {
        super(target, name);

        if (Thread.currentThread() instanceof PluginThread)
            plugin = ((PluginThread) Thread.currentThread()).plugin;
        /* if the current thread is not a plugin thread, this must be called from Minecraft or Corundum, so plugin can just remain null */
        else
            plugin = null;
    }

    public PluginThread(ThreadGroup group, Runnable target) {
        super(group, target);

        if (Thread.currentThread() instanceof PluginThread)
            plugin = ((PluginThread) Thread.currentThread()).plugin;
        /* if the current thread is not a plugin thread, this must be called from Minecraft or Corundum, so plugin can just remain null */
        else
            plugin = null;
    }

    public PluginThread(ThreadGroup group, String name) {
        super(group, name);

        if (Thread.currentThread() instanceof PluginThread)
            plugin = ((PluginThread) Thread.currentThread()).plugin;
        /* if the current thread is not a plugin thread, this must be called from Minecraft or Corundum, so plugin can just remain null */
        else
            plugin = null;
    }

    public PluginThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);

        if (Thread.currentThread() instanceof PluginThread)
            plugin = ((PluginThread) Thread.currentThread()).plugin;
        /* if the current thread is not a plugin thread, this must be called from Minecraft or Corundum, so plugin can just remain null */
        else
            plugin = null;
    }

    public PluginThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);

        if (Thread.currentThread() instanceof PluginThread)
            plugin = ((PluginThread) Thread.currentThread()).plugin;
        /* if the current thread is not a plugin thread, this must be called from Minecraft or Corundum, so plugin can just remain null */
        else
            plugin = null;
    }

    PluginThread(CorundumPlugin plugin, Runnable target) {
        super(target);

        this.plugin = plugin;
    }

    // instance utils
    public CorundumPlugin getPlugin() {
        return this.plugin;
    }
}
