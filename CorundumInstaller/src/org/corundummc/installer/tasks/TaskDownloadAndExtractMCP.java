package org.corundummc.installer.tasks;

import org.corundummc.installer.util.FileUtils;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.corundummc.installer.Main;

/**
 * Downloads and extracts MCP.
 *
 * @author Niadel
 */
public class TaskDownloadAndExtractMCP implements ITask {
    private static String mcpDownloadUrl = "http://download1783.mediafire.com/k8tsqz0f3m0g/2czafa60rh4ajhj/mcp908.zip";

    @Override
    public void execute() {
        this.extractMCPZip(this.downloadMCPZip(Main.runningDir));
        new File(Main.runningDir.getAbsolutePath() + "src/minecraft_server").mkdir();
    }

    public File downloadMCPZip(File outDir) {
        try {
            return FileUtils.downloadFile(new URL(mcpDownloadUrl), outDir, "mcp.zip");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("MCP download URL does not work!");
    }

    public void extractMCPZip(File mcpZip) {
        FileUtils.extractFilesFromZip(mcpZip, Main.runningDir);
    }
}
