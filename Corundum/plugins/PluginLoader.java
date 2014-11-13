package Corundum.plugins;

import java.io.File;
import java.net.MalformedURLException;

import Corundum.Corundum;
import Corundum.launcher.CorundumJarLoader;

public class PluginLoader extends CorundumJarLoader {

    public PluginLoader(String jar_name) throws MalformedURLException {
        super(new File(Corundum.PLUGINS_FOLDER, jar_name + ".jar"), PluginLoader.class.getClassLoader());
    }

    @Override
    public Class<?> loadClass(String class_name) throws ClassNotFoundException {
        // redirect requests for new Threads to new PluginThreads
        // TODO TEST
        if (class_name.equals("java.lang.Thread"))
            return PluginThread.class;
        else
            return super.loadClass(class_name);
    }

}
