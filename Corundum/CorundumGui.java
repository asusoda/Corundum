package Corundum;

import javax.swing.*;
import java.awt.*;

/** The GUI of Corundum. */
public class CorundumGui {
    private JFrame frame;
    private CorundumServer corundumServer;

    public CorundumGui(CorundumServer server) {
        if (server.isRunningGUI()) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // I know it's bad practice to just catch Exception e, but there's a LOT of possibly thrown exceptions.
                e.printStackTrace();
            }

            this.corundumServer = server;
            this.frame = new JFrame("Corundum Server");
            this.addComponents();
            this.frame.pack();
            this.frame.setLocationRelativeTo((Component) null);
            this.frame.setVisible(true);
            this.addWindowListener();
        }
    }

    public void addComponents() {
        this.initLogComponent();
        // this.frame.add(...)
        // Some components will have listeners.
    }

    /** Initialises the log. */
    public void initLogComponent() {
        // TODO
    }

    /** Adds a {@link java.awt.event.WindowAdapter} to this.frame via {@link JFrame#addWindowListener(java.awt.event.WindowListener)} */
    public void addWindowListener() {
        // TODO
    }

    /** Logs something to the GUI's log. */
    public void log() {
        // TODO
    }
}
