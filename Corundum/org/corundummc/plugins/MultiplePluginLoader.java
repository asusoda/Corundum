package org.corundummc.plugins;

import org.corundummc.CorundumServer;
import org.corundummc.exceptions.CIE;
import org.corundummc.hub.CorundumJarLoader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * A loader that handles the loading of multiple plugins instead of just the one a single {@link org.corundummc.plugins.PluginLoader}
 * supports. Uses the PluginLoader class in loading, however, which is why this doesn't extend either {@link java.net.URLClassLoader}
 * or {@link org.corundummc.hub.CorundumJarLoader}.
 *
 * @author Niadel
 */
public class MultiplePluginLoader {
    public File pluginsDir;
    public CorundumServer server;

    public MultiplePluginLoader(CorundumServer server, File pluginsDir) {
        this.server = server;

        if (pluginsDir.isDirectory()) {
            if (!pluginsDir.exists()) {
                pluginsDir.mkdir();
            }

            this.pluginsDir = pluginsDir;
        }
    }

    public void loadPlugins() {
        File[] potentialPlugins = this.pluginsDir.listFiles();

        if (potentialPlugins != null && potentialPlugins.length > 0) {
            for (File potentialPlugin : potentialPlugins) {
                if (potentialPlugin.getName().endsWith(".jar")) {
                    try {
                        new PluginLoader(this.server, potentialPlugin.getName()).loadJar();
                    } catch (IOException | URISyntaxException | ClassNotFoundException e) {
                        throw new CIE("An error occurred during the loading of a plugin!", "An " + e.getClass().getName()
                                + " occured in a MultiplePluginLoader's .loadPlugins() method!", e.getStackTrace());
                    }
                }
            }
        }
    }
}
