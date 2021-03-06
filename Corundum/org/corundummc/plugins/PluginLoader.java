package org.corundummc.plugins;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.corundummc.CorundumServer;
import org.corundummc.exceptions.CorundumSecurityException;
import org.corundummc.hub.CorundumJarLoader;
import org.corundummc.listeners.plugins.PluginDisableListener;

public class PluginLoader extends CorundumJarLoader {
    private CorundumPlugin plugin;

    public PluginLoader(CorundumServer server, String jar_name) throws MalformedURLException {
        super(new File(server.getPluginsFolders()[0], jar_name + ".jar"), PluginLoader.class.getClassLoader());
    }

    @Override
    public Class<?> loadClass(String class_name) throws ClassNotFoundException {
        // redirect requests for new Threads to new PluginThreads
        // TODO TEST
        if (class_name.equals("java.lang.Thread") || class_name.equals("org.corundummc.hub.CorundumServerThread"))
            CorundumServer.secure("access " + class_name);

        return super.loadClass(class_name);
    }

    @Override
    public void loadJar() throws IOException, NoClassDefFoundError, ClassNotFoundException, URISyntaxException {
        final PluginLoader _this = this;
        super.loadJar(new ClassLoadAction() {
            @Override
            public void onClassLoad(Class<?> clazz) {
                // TODO: if clazz is a CorundumPlugin, set plugin to this
                try {
                    Class<?> parent = clazz.getSuperclass();

                    // check if the class extends CorundumPlugin.
                    while (parent != null) {
                        if (parent == CorundumPlugin.class) {
                            _this.plugin = (CorundumPlugin) clazz.newInstance();
                            break;
                        }

                        parent = clazz.getSuperclass();
                    }
                } catch (InstantiationException | IllegalAccessException e) {
                    System.out.println("Reflective operation exception!");
                    e.printStackTrace();
                }
            }
        });
    }
}