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

import Corundum.exceptions.CorundumSecurityException;
import static Corundum.utils.StringUtilities.capitalize;

/** This is the main class of Corundum. ...Does anything more need to be said?
 * 
 * @author REALDrummer */
public class Corundum {
    public final String VERSION = "pre-α";

    public static final Corundum CORUNDUM = new Corundum();
    /** This {@link OperatingSystem} represents the operating system is currently running on. */
    public static final OperatingSystem OS = OperatingSystem.getFromName(System.getProperty("os.name"));
    public static final CorundumServer SERVER = new CorundumServer("Corundum.jar");

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

    // start up and shut down
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
    public static void start(String[] arguments) {
        System.out.println("Starting Corundum server...");

        CorundumServer.main(arguments);
    }

    /** This method shuts down Corundum completely. */
    public static final void quit() {
        // TODO: close Minecraft

        // close Corundum
        System.exit(0);
    }

    // method security
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
        /* note: this method has to exist to make sure that both this method and secure() (without arguments) will have the same number of stack trace elements between the end
         * of the stack trace and the method that called the secured method */
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

    // overrides
    @Override
    public boolean equals(Object object) {
        return object instanceof Corundum;
    }

    @Override
    public String toString() {
        return "\\Corundum";
    }
}