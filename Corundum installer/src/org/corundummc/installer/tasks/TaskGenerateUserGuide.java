package org.corundummc.installer.tasks;

import org.corundummc.installer.Main;

import java.io.*;

/**
 * Generates the user guide.
 */
public class TaskGenerateUserGuide implements ITask {
    public String[] userGuide = new String[] {
            "CORUNDUM USER GUIDE",
            "SERVER OWNERS",
            "",
            "Simply run the CorundumLauncher.jar, after moving CorundumLauncher.jar, Corundum.jar and the minecraft_server.jar file",
            "from the MCP directory to where your server runs.",
            "",
            "PLUGIN DEVELOPERS",
            "",
            "To develop plugins for Corundum, the sources should be located under src in MCP, along with minecraft_server.",
            "When recompiling your mod, DO NOT EDIT EITHER THE MC OR CORUNDUM SOURCES! Doing the former will very likely",
            "make users unable to join your server with a vanilla client! Furthermore, it is tedious to add base edits as",
            "Corundum doesn't base edit at the time of writing, and currently has no plans to."
    };

    @Override
    public void execute() {
        try {
            File userGuide = new File("CORUNDUM-USER-GUIDE.txt");
            userGuide.createNewFile();
            PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(userGuide)));

            for (String line : this.userGuide) {
                out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
