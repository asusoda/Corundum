/** This code is property of the Corundum project managed under the Software Developers' Association of Arizona State University.
 *
 * Copying and use of this open-source code is permitted provided that the following requirements are met:
 *
 * - This code may not be used or distributed for private enterprise, including but not limited to personal or corporate profit. - Any products resulting from the copying,
 * use, or modification of this code may not claim endorsement by the Corundum project or any of its members or associates without written formal permission from the endorsing
 * party or parties. - This code may not be copied or used under any circumstances without the inclusion of this notice and mention of the contribution of the code by the
 * Corundum project. In source code form, this notice must be included as a comment as it is here; in binary form, proper documentation must be included with the final product
 * that includes this statement verbatim.
 * 
 * @author REALDrummer */

package Corundum;

import java.io.File;
import java.io.IOException;
import java.net.URLClassLoader;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.ResolverUtil.IsA;

import Corundum.exceptions.CorundumSecurityException;
import Corundum.utils.messaging.MessageColor;
import Corundum.utils.messaging.Messenger;
import Corundum.utils.messaging.MessengerUtilities;
import Corundum.utils.myList.myList;
import static Corundum.utils.StringUtilities.capitalize;
import static Corundum.utils.messaging.Messenger.*;

/** This is the main (executable) class of Corundum. ...Does anything more need to be said?
 * 
 * @author REALDrummer */
public class Corundum implements Messenger {
    public static final Corundum CORUNDUM = new Corundum();
    /** This {@link OperatingSystem} represents the operating system is currently running on. */
    public static final OperatingSystem OS = OperatingSystem.getFromName(System.getProperty("os.name"));
    public static final CorundumServer SERVER = new CorundumServer(new File("."));
    /** This {@link OperatingSystem} represents the single console from which the server is centrally run. */
    public static final Console CONSOLE = new Console();

    /** This list contians all the currently loaded {@link CorundumPlugin}s on the server. Note that loading and unloading plugins will add or remove them from this list,
     * respectively, but enabling or disabling them will <i>not</i> affect this list. */
    public static myList<CorundumPlugin> plugins = new myList<CorundumPlugin>();

    /** This is the main method of Corundum. When Corundum is started, this is the method that is called to start the program.
     * 
     * @param arguments
     *            is the list of <tt>String</tt> arguments given in the command to start this program. These arguments are given through a command line like the Windows
     *            command prompt or Unix terminal.<br>
     * <br>
     *            Accepted arguments:<br>
     *            <i>debugging</i>:<br>
     *            <li><tt>--debug, -d</tt> to activate debugging mode, which outputs debugging information to the console and the logs</li> <li><tt>--no-debug, -D</tt> to
     *            deactivate debugging mode</li> <li><tt>--verbose, -v</tt> to activate verbose debugging mode, which outputs lots of extra detailed debugging information to
     *            the console and the logs</li> <li><tt>--no-verbose, -V</tt> to deactivate verbose debugging mode</li> <i>server properties <b>(not yet supported)</b></i>: <br>
     *            <li>
     *            <tt>--online-mode, -o</tt> to activate online mode, which makes the server connect to Mojang's authentication servers and verify users when they try to
     *            connect to the server</li> <li><tt>--offline-mode, -O</tt> to deactivate online mode</li> <li><tt>--world=[WORLD]</tt> to specify the name of your main world
     *            (usually the Overworld); leave it blank to specify the default world name, "world"</li> */
    public static void main(String[] arguments) {
        for (int i = 0; i < arguments.length; i++) {
            // TODO TEST: make sure that using getDebuggers() and then modifying the result will actually change the MessengerUtilities.getDebuggers() list
            if (arguments[i].equals("--debug") || arguments[i].equals("-d")) {
                MessengerUtilities.getDebuggers().add((UUID) null);
                CORUNDUM.debug("All normal debugging modes are on.");
            } else if (arguments[i].equals("--no-debug") || arguments[i].equals("-D")) {
                MessengerUtilities.getDebuggers().remove((UUID) null);
            } else if (arguments[i].equals("--verbose") || arguments[i].equals("-v")) {
                MessengerUtilities.getVerboseDebuggers().add((UUID) null);
                CORUNDUM.debug("All verbose debugging modes are on.");
            } else if (arguments[i].equals("--no-verbose") || arguments[i].equals("-V")) {
                MessengerUtilities.getVerboseDebuggers().remove((UUID) null);
                CORUNDUM.debug("All verbose debugging modes are off.");
            } else
                CORUNDUM.debug("I don't know what \"" + arguments[i] + "\" means!");
        }

        CORUNDUM.debug("STARTING CORUNDUM...");

    }

    /** This method can check at any given spot whether or not a call is "secure". The point is to ensure that certain methods are only called form inside Corundum, not by
     * Corundum plugins. If the spot is not secure, it will throw a {@link CorundumSecurityException}.
     * 
     * @throws CorundumSecurityException
     *             if the method was called by something other than Corundum internal code.
     * @see {@link #secure(String)} */
    public static void secure() throws CorundumSecurityException {
        secure(null, true);
    }

    /** This method can check at any given spot whether or not a call is "secure". The point is to ensure that certain methods are only called form inside Corundum, not by
     * Corundum plugins. If the spot is not secure, it will throw a {@link CorundumSecurityException}.
     * 
     * @param message
     *            is the message that will go with the {@link CorundumSecurityException} if one is thrown. It should be used in cases where this method is not just used to
     *            secure a whole method, but to secure it in a certain case, so as not to mislead users trying to debug their plugin.
     * 
     * @throws CorundumSecurityException
     *             if the method was called by something other than Corundum internal code.
     * @see {@link #secure()} */
    public static void secure(String message) throws CorundumSecurityException {
        secure(message, true);
    }

    private static void secure(String message, @SuppressWarnings("unused") boolean _tag_) throws CorundumSecurityException {
        // TODO TEST
        /* create a NullPointerException and catch it to generate a stack tract and get the fourth to last entry in the stack trace; the fourth to last entry represents the
         * location from which the method whose security is in question was called (the third to last represents the method that's checking its security, the second to last
         * represents the public secure() method that was called to get here, and the last one is this method) */
        StackTraceElement[] stack_trace;
        try {
            throw new NullPointerException();
        } catch (NullPointerException exception) {
            stack_trace = exception.getStackTrace();
        }

        if (!stack_trace[stack_trace.length - 4].getClassName().startsWith("Corundum."))
            if (message == null)
                throw new CorundumSecurityException(stack_trace[stack_trace.length - 4], stack_trace[stack_trace.length - 3]);
            else
                throw new CorundumSecurityException(message, stack_trace[stack_trace.length - 4], stack_trace[stack_trace.length - 3]);
    }

    /** This method shuts down Corundum completely. */
    public static final void quit() {
        // TODO: close Minecraft

        // close Corundum
        System.exit(0);
    }

    /** This enum represents a type of operating system. It can be {@link #WINDOWS Windows}, {@link #MAC Mac OS}, {@link #LINUX Linux}, {@link #UNIX Unix}, or {@link #OTHER
     * "other"}. The public static final <tt>OperatingSystem</tt> {@link Corundum#OS OS} represents the operating system that this server is currently running on.
     * 
     * @author REALDrummer */
    public static enum OperatingSystem {
        WINDOWS, MAC, LINUX, UNIX, OTHER;

        public static OperatingSystem getFromName(String name) {
            if (name.startsWith("Windows"))
                return WINDOWS;
            else if (name.startsWith("Mac OS"))
                return MAC;
            else if (name.startsWith("Linux"))
                return LINUX;
            else if (name.contains("Unix"))
                return UNIX;
            else
                return OTHER;
        }

        @Override
        public String toString() {
            if (this == MAC)
                return "Mac OS";
            else if (this == OTHER)
                return "other";
            else
                return capitalize(super.toString());
        }
    }

    @Override
    public CorundumPlugin getPlugin() {
        return null;
    }
}