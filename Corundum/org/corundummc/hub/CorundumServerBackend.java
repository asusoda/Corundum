package org.corundummc.hub;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.corundummc.ArgInfo;
import org.corundummc.CorundumGui;
import org.corundummc.CorundumServer;
import org.corundummc.CorundumServer.Difficulty;
import org.corundummc.entities.living.Player.GameMode;
import org.corundummc.exceptions.CIE;
import org.corundummc.exceptions.CorundumSecurityException;
import org.corundummc.hub.Server;
import org.corundummc.plugins.CorundumPlugin;
import org.corundummc.utils.SettingsManager;
import org.corundummc.utils.versioning.Version;
import org.corundummc.world.Location;
import org.corundummc.world.World;

import net.minecraft.server.dedicated.DedicatedServer;

public class CorundumServerBackend extends DedicatedServer implements Server {
    private final Version VERSION = new Version("0"), MC_VERSION = new Version("1.7.10");
    private final File PLUGINS_FOLDER = new File("plugins");

    private CorundumGui corundumGui;
    private boolean corundum_GUI_enabled = false;
    /** This <b>boolean</b> determines whether or not the server uses the default Minecraft GUI. Usually false but can be changed via --mc-gui. */
    private boolean usingMCGui = false;

    /** This <b>boolean</b> determines whether or not the server is running in "debug" mode, which will cause the server to log debugging messages to the console. Debug mode is
     * off (<b>false</b>) by default. Debug mode can be enabled by passing the argument <tt>--debug</tt> (a.k.a. <tt>-d</tt>) to the console as a command line argument when
     * starting the server. */
    private boolean debugMode;
    /** This <b>boolean</b> determines whether or not the server is running in "verbose" mode, which will cause the server to log a large amount of debugging messages to the
     * console. Verbose mode is off (<b>false</b>) by default. Verbose mode can be enabled by passing the argument <tt>--verbose</tt> (a.k.a. <tt>-v</tt>) to the console as a
     * command line argument when starting the server. Note that if verbose mode is enabled, so is {@link #debugMode debug mode}. */
    private boolean verboseMode;

    /** The {@link ArgInfo} concerning the args passed in {@link #start}. */
    ArgInfo argInfo;

    // config variable names
    private String config_file_name = "Corundum settings.json";

    private SettingsManager settings;

    public CorundumServerBackend(String name) {
        // this DedicatedServer constructor sets up the server and sets MinecraftServer.mcServer to this
        super(new File("."));

        settings = new SettingsManager(new File(config_file_name), "name", name, "prefix", "[" + name + "]");
    }

    @Override
    public Version getMCVersion() {
        return MC_VERSION;
    }

    @Override
    public String getName() {
        return settings.getString("name");
    }

    public File[] getPluginFolders() {
        return new File[] { PLUGINS_FOLDER };
    }

    @Override
    public String getPrefix() {
        return settings.getString("prefix");
    }

    @Override
    public Version getVersion() {
        return VERSION;
    }

    public boolean isDebugging() {
        return debugMode;
    }

    public boolean isVerboselyDebugging() {
        return verboseMode;
    }

    public boolean isUsingMCGui() {
        return usingMCGui;
    }

    public boolean isUsingCorundumGUI() {
        return corundum_GUI_enabled;
    }

    // start up and shut down
    @Override
    public void startServerThread() {
        System.out.println("Starting Corundum server thread!");

        final CorundumServerBackend _this = this;
        new Thread("Server thread") {
            public void run() {
                _this.run();
            }
        }.start();
    }

    // TODO TEMP
    @Override
    public void run() {
        super.run();

        try {
            int low_x = -1000, low_z = 7500, high_x = 4500, high_z = 10500, test_x = 0, test_z = 8000;

            if (test_x < low_x || test_x > high_x || test_z < low_z || test_z > high_z)
                throw new NullPointerException("These numbers are stupid!");

            File file = new File("biome map test.bin");
            if (file.exists())
                file.delete();
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);

            /* file format: test_x test_z x-width z-height map */
            out.write(test_x);
            out.write(test_z);
            out.write(high_x - low_x);
            out.write(high_z - low_z);

            Location location = new Location(0, 0, 0, World.fromMC(worldServers[0]));
            for (int x = low_x; x < high_x; x++)
                for (int z = low_z; z < high_z; z++) {
                    location.setX(x);
                    location.setZ(z);
                    out.write((byte) location.getBiome().getTypeID());
                }

            out.close();
        } catch (IOException exception) {
            exception.printStackTrace();
            quit();
        }
    }

    /** This method starts this {@link CorundumServer}.
     * 
     * @param arguments
     *            are the command-line arguments used to configure the properties of this server on startup. */
    @Override
    public final void start(String[] arguments) throws CorundumSecurityException {
        /* This method is secured by the fact that */

        System.out.println("Starting Corundum server...");

        // To make arg reading easier.
        argInfo = new ArgInfo(arguments);
        // --no-debug takes priority.
        debugMode = argInfo.hasArg("--no-debug", "-D") ? false : argInfo.hasArg("--debug", "-d");
        verboseMode = argInfo.hasArg("--no-verbose", "-V") ? false : argInfo.hasArg("--verbose", "-v");

        if (argInfo.hasArg("--gui-enabled", "-g")) {
            enableGUI();
        }

        try {
            main(arguments);
        } catch (Exception exception) {
            CIE.err("There was a problem starting this Corundum server!", exception);
        }

        // Vanilla property setting is after the server is started-started as, otherwise, the properties gotten from
        // server.properties takes priority.
        /* TODO: I had to comment these parts below because they break the build: Minecraft puts the Minecraft server in a separate thread, which causes startServer() to not
         * be called until later, which causes the lines below to throw a NullPointerException because the MinecraftServer PropertyManager is not initialized until
         * startServer() is called. In addition, I can't just call startServer() before this because when the server thread starts immediately after this method and
         * startServer() is called there, it can't bind to port because it's basically trying ot run two servers in the same place at the same time and it crashes. We also
         * can't put it before the main() call, for obvious reasons. */
        // if (argInfo.hasArg("--online-mode", "-o")) {
        // super.setProperty("online-mode", true);
        // } else if (argInfo.hasArg("--offline-mode", "-O")) {
        // super.setProperty("online-mode", false);
        // }
        //
        // if (argInfo.hasArg("--world")) {
        // super.setProperty("level-name", argInfo.getArgValue("--world"));
        // } else {
        // super.setProperty("level-name", "world");
        // }
    }

    /** This method shuts down Corundum completely.
     * 
     * @throws CorundumSecurityException
     *             if a {@link CorundumPlugin} attempts to call this method. */
    @Override
    public final void quit() throws CorundumSecurityException {
        stopServer();

        // close Corundum
        System.exit(0);
    }

    public void enableGUI() {
        if (argInfo.hasArg("--mc-gui", "-mc-g") || !corundum_GUI_enabled) {
            if (!corundum_GUI_enabled) {
                System.out
                        .println("The Corundum GUI is not enabled! This is a dev thing and hardcoded (ie. Unalterable via args). Corundum is likely in alpha as this is the only time it's likely to be disabled.");
                System.out.println("Using vanilla server GUI as Corundum's GUI is not enabled [HARDCODED VALUE].");
            }

            setGuiEnabled();
            usingMCGui = true;
            corundum_GUI_enabled = false;
        } else {
            corundumGui = new CorundumGui();
            corundum_GUI_enabled = true;
        }
    }

}
