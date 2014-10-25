package org.corundummc.installer.tasks;

import org.corundummc.installer.Main;

import java.io.*;

/**
 * Packages the compiled and reobfuscated Corundum binaries into a jar, handling META-INF stuffs as it goes.
 */
public class TaskPackageCorundum implements ITask {
    public File corundumMetaInf;

    @Override
    public void execute() {
        this.generateMetaInf();
        this.packageSources();
    }

    public void generateMetaInf() {
        try {
            this.corundumMetaInf = new File("jars/MANIFEST.mf");

            if (!this.corundumMetaInf.exists()) {
                this.corundumMetaInf.createNewFile();
            }

            PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(this.corundumMetaInf)));

            out.println("Main-Class: Corundum.Corundum");
            //To fulfil the META-INF ending in newline requirement.
            out.println();
            out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packageSources() {
        Main.exec("jar cmf jars/MANIFEST.mf Corundum.jar reobf/minecraft/Corundum");
        this.corundumMetaInf.delete();
    }
}
