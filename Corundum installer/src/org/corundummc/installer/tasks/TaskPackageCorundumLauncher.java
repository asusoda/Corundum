package org.corundummc.installer.tasks;

import org.corundummc.installer.Main;
import org.corundummc.installer.util.FileUtils;

import java.io.*;

/**
 * Packages the Corundum launcher.
 */
public class TaskPackageCorundumLauncher implements ITask {
    public File corundumPackageFolder;
    public File corundumLauncherMetaInf;

    @Override
    public void execute() {
        Main.logSpecial("Packaging the Corundum Hub!");
        this.prepareExtractToFolder();
        this.extractCorundumLauncherFromReobf();
        this.prepareMetaInf();
        this.packageLauncher();
    }

    public void prepareExtractToFolder() {
        this.corundumPackageFolder = new File("org/corundummc/");
        this.corundumPackageFolder.mkdir();
        File launcherBaseFolder = new File(this.corundumPackageFolder, "hub");
        launcherBaseFolder.mkdir();
    }

    public void extractCorundumLauncherFromReobf() {
        File corundumRoot = new File("reobf/minecraft_server/org/corundummc");
        File launcherRoot = new File(corundumRoot, "hub");
        File[] filesInLauncherFolder = launcherRoot.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".class");
            }
        });

        for (File file : filesInLauncherFolder) {
            FileUtils.copyFile(file, new File(this.corundumPackageFolder.getAbsolutePath() + "hub/" + file.getName()));
        }
    }

    public void prepareMetaInf() {
        try {
            this.corundumLauncherMetaInf = new File("jars/MANIFEST.mf");
            this.corundumLauncherMetaInf.createNewFile();

            PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(this.corundumLauncherMetaInf)));

            out.println("Main-Class: org.corundummc.hub.CorundumLauncher");
            //To fulfil the META-INF ending in newline requirement.
            out.println();
            out.println();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packageLauncher() {
        Main.exec("jar cmf jars/MANIFEST.mf CorundumLauncher.jar Corundum");
        this.corundumLauncherMetaInf.delete();
        this.corundumPackageFolder.delete();
    }
}
