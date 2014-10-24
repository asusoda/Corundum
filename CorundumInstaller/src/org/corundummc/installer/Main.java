package org.corundummc.installer;

import org.corundummc.installer.tasks.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The main class for the installer.
 *
 * @author Niadel
 */
public class Main {
    public static final File runningDir = new File(".");
    //Will be false in the actual src-installer download from the Corundum site.
    /**
     * Advantages of downloading from Github:
     *     You get all the latest features and bug fixes, sometimes before builds are put out.
     * Disadvantages of downloading from Github:
     *     The build may be incomplete.
     *     You get all the latest bugs as well.
     */
    public static final boolean downloadSrcFromGithub = true;
    public static final String corundumDownloadUrl = downloadSrcFromGithub ? "https://github.com/asusoda/Corundum/archive/master.zip" : "NODOWNLOADURL";
    private static final List<ITask> tasks = new ArrayList<>();

    public static final void main(String[] args) {
        initSourceTasks();
        initClientTasks();
        execTasks();
    }

    /**
     * Inits the bare minimum for devs and server owners alike.
     */
    private static void initSourceTasks() {
        //Downloads MCP.
        tasks.add(new TaskDownloadAndExtractMCP());
        //Patches mcp.conf for Java 7.
        tasks.add(new TaskPatchConf());
        //Downloads the corundum sources.
        tasks.add(new TaskDownloadAndExtractCorundumSource());
        //Downloads the minecraft server.
        tasks.add(new TaskDownloadMCServer());
        //Decompiles the minecraft server.
        tasks.add(new TaskDecompile());
        //Compiles and reobfuscates the Corundum sources.
        tasks.add(new TaskRecompileAndReobf());
        //Packages Corundum
        tasks.add(new TaskPackageCorundum());
    }

    /**
     * Packages the launcher and stuff.
     */
    private static void initClientTasks() {
        //Packages the Corundum Launcher.
        tasks.add(new TaskPackageCorundumLauncher());
        //Generates the user guide.
        tasks.add(new TaskGenerateUserGuide());
    }

    private static void execTasks() {
        for (ITask task : tasks) {
            task.execute();
        }
    }

    public static boolean getUserOnWindows() {
        return System.getProperty("os.name").equals("Windows");
    }

    public static void exec(String command) {
        try {
            Process process = Runtime.getRuntime().exec((getUserOnWindows() ? "" : "./") + command);
            printProcessOutput(process);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printProcessOutput(Process process) {
        Scanner inScanner = new Scanner(process.getInputStream());

        while (inScanner.hasNext()) {
            System.out.println(inScanner.next());
        }
    }
}
