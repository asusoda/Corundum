package org.corundummc.installer.tasks;

import org.corundummc.installer.Main;

/**
 * Decompiles via MCP.
 */
public class TaskDecompile implements ITask {
    @Override
    public void execute() {
        Main.exec("decompile" + (Main.getUserOnWindows() ? ".bat" : ".sh"));
    }
}
