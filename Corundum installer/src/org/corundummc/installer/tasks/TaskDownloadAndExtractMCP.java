package org.corundummc.installer.tasks;

import org.corundummc.installer.util.FileUtils;

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
    private static String mcpDownloadUrl = "http://www.mediafire.com/download/2czafa60rh4ajhj/mcp908.zip";

    @Override
    public void execute() {
        Main.logSpecial("Downloading and extracting MCP!");
        this.extractMCPZip(this.downloadMCPZip(Main.runningDir));
        new File("src/minecraft_server").mkdir();
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
