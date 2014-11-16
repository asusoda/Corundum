package org.corundummc.installer.tasks;

import org.corundummc.installer.Main;
import org.corundummc.installer.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Handles the downloading of the Corundum source code.
 */
public class TaskDownloadAndExtractCorundumSource implements ITask {
    @Override
    public void execute() {
        this.extractCorundumSource(this.downloadCorundumSource());
        new File("/corundum_src.zip").delete();
    }

    public File downloadCorundumSource() {
        try {
            return FileUtils.downloadFile(new URL(Main.corundumDownloadUrl), Main.runningDir, "corundum_src.zip");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Unable to download the Corundum sources!");
    }

    public void extractCorundumSource(File zip) {
        FileUtils.extractFilesFromZip(zip, new File("src/"));
        this.removeCorundumInstallerSources();
    }

    public void moveOutOfCorundumMasterFolder() {
        if (Main.downloadSrcFromGithub) {
            File corundumSrcLoc = new File("src/Corundum-master/Corundum");
            File corundumShouldBeSrcLoc = new File("src/Corundum");
            corundumShouldBeSrcLoc.mkdir();
            FileUtils.copyFile(corundumSrcLoc, corundumShouldBeSrcLoc);
        }
    }

    /**
     * Should only operate if the installer downloads the sources from Github.
     */
    public void removeCorundumInstallerSources() {
        if (Main.downloadSrcFromGithub) {
            File corundumInstallerBase = new File("src/Corundum-master/Corundum installer");
            corundumInstallerBase.delete();
        }
    }
}
