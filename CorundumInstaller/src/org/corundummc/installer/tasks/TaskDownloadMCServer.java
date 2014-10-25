package org.corundummc.installer.tasks;

import org.corundummc.installer.Main;
import org.corundummc.installer.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Downloads the MC server and puts it under jars.
 */
public class TaskDownloadMCServer implements ITask {
    @Override
    public void execute() {
        try {
            FileUtils.downloadFile(new URL("https://s3.amazonaws.com/Minecraft.Download/versions/1.7.10/minecraft_server.1.7.10.jar"), new File("jars/"), "minecraft_server.1.7.10.jar");
            //Make a copy for use of the Corundum Launcher.
            FileUtils.copyFile(new File("jars/minecraft_server.1.7.10.jar"), new File("minecraft_server.jar"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
