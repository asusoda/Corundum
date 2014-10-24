package org.corundummc.installer.tasks;

import org.corundummc.installer.Main;
import org.corundummc.installer.util.FileUtils;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

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
        this.extractCorundumSource(downloadCorundumSource());
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
        try {
            ZipFile zipFile = new ZipFile(zip);
            zipFile.extractAll(new File(Main.runningDir.getAbsolutePath() + "src/").getAbsolutePath());
            this.removeCorundumInstallerSources();
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }

    public void moveOutOfCorundumMasterFolder() {
        if (Main.downloadSrcFromGithub) {

        }
    }

    /**
     * Should only operate if the installer downloads the sources from Github.
     */
    public void removeCorundumInstallerSources() {
        if (Main.downloadSrcFromGithub) {
            File corundumInstallerBase = new File(Main.runningDir.getAbsolutePath() + "src/Corundum-master/CorundumInstaller");
            corundumInstallerBase.delete();
        }
    }
}
