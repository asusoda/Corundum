package org.corundummc.installer.tasks;

import org.corundummc.installer.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Patches mcp.conf so it recompiles in Java 7, which is important for Corundum to work right, as it IS Java 7.
 */
public class TaskPatchConf implements ITask {
    public String newRecompScalaLine = "CmdRecompScala = %s -encoding UTF-8 -deprecation -target:jvm-1.7 -classpath \"{classpath}\" -sourcepath {sourcepath} -d {outpath} {pkgs}";
    public String newRecompJavaLine = "CmdRecomp     = %s -Xlint:-options -deprecation -g -source 1.7 -target 1.7 -classpath \"{classpath}\" -sourcepath {sourcepath} -d {outpath} {pkgs}";
    public File mcpConf = new File(Main.runningDir.getAbsolutePath() + "/conf/mcp.conf");

    @Override
    public void execute() {
        try {
            List<String> mcpConfData = new ArrayList<>();
            Scanner mcpConfScanner = new Scanner(new BufferedInputStream(new FileInputStream(this.mcpConf)));
            PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(this.mcpConf)));

            while (mcpConfScanner.hasNextLine()) {
                String nextLine = mcpConfScanner.nextLine();

                if (nextLine.startsWith("CmdRecomp")) {
                    mcpConfData.add(this.newRecompJavaLine);
                    continue;
                } else if (nextLine.startsWith("CmdRecompScala")) {
                    mcpConfData.add(this.newRecompScalaLine);
                    continue;
                }

                mcpConfData.add(mcpConfScanner.nextLine());
            }

            this.mcpConf.delete();
            this.mcpConf.createNewFile();

            for (String line : mcpConfData) {
                out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
