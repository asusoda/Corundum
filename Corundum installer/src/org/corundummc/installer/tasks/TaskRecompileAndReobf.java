package org.corundummc.installer.tasks;

import org.corundummc.installer.Main;

/**
 * Decompiles and reobfuscates via MCP.
 */
public class TaskRecompileAndReobf implements ITask {
    @Override
    public void execute() {
        Main.logSpecial("Recompiling and reobfuscating!");
        Main.exec("recompile" + (Main.getUserOnWindows() ? ".bat" : ".sh"));
        Main.exec("reobfuscate" + (Main.getUserOnWindows() ? ".bat" : ".sh"));
    }
}
