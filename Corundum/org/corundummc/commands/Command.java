package org.corundummc.commands;

import java.util.regex.Pattern;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.corundummc.entities.living.Player;
import org.corundummc.plugins.CorundumPlugin;

// TODO LINK "console"
/** This class represents a command, a specifically formatted message used by Minecraft, Corundum, or a {@link CorundumPlugin} to allow {@link Player}s or console users to
 * execute various functions on the server. {@link Command}s can be used in the console or by {@link Player}s in game by sending a message beginning with a <tt>"/"</tt>. <br>
 * <br>
 * A {@link CorundumPlugin} can list its commands in its main class through a {@link CorundumPlugin#getCommands() getCommands()} method. This way, Corundum can handle much
 * of the commands' requirements, including lots of basic error checking (correct number and type of arguments); setup of help pages using <tt>"/help"</tt>; and automatic
 * conversion of the plain <tt>String</tt> arguments into more appropriate types of <tt>Object</tt>s.<br>
 * <br>
 * All Corundum commands have a very specific and highly regulated BASH-style syntax that consists of the following characteristics:<br>
 * <ul>
 * <li>All commands begin with one word that identifies the command.
 * <li>Commands may also have 0 or more arguments, some of which may be required and some of which may not be. Arguments have a specific type, e.g. an integer or an online
 * player, and can often be autocompleted with Tab (depending on the type; we obviously can't autocomplete a number).
 * <li>In addition to arguments, commands can have extra options marked by identifiers preceded by two dashes (<tt>"--"</tt>) and followed by a space and the value (if
 * applicable). For example, if a command to create a warp (<tt>/create <String warp-name></tt>) had an option called "<tt>type</tt>" that let you give what type of warp you
 * wanted it to be, you could use "<tt>/create Warp --type open</tt>" where "<tt>open</tt>" is the type you want your new warp, <tt>Warp</tt>, to be.
 * <li>All arguments or option values have to be one "word". Normally this means that they must contain no spaces, but if something needs to contain spaces, there are two ways
 * to use spaces in an argument:
 * <ul>
 * <li>You can enclose the argument in single quotes (<tt>"''"</tt>) or double quotes (<tt>""""</tt>) <i>OR</i>
 * <li>You can escape spaces by putting a backslash (<tt>"\"</tt>) before them; the backslash will be ommitted when Corundum reads it.
 * </ul>
 * </ul> */
public class Command {
    private final Pattern first_word;
    private final Argument[] arguments;
    private final Option[] options;

    private final String help_description;

    private static class Argument {
        private final String identifier;
        private final String help_description;

        private final Parser parser;

        Argument(String identifier, String help_description, Parser parser) {
            this.identifier = identifier;
            this.help_description = help_description;
            this.parser = parser;
        }
    }

    private static class Option extends Argument {
        private char short_flag_char = '\0';

        Option(String identifier, String help_description, Parser parser) {
            super(identifier, help_description, parser);
        }

        public char getShortFlagChar() {
            return short_flag_char;
        }

        public void setShortFlagChar(char short_flag_char) {
            this.short_flag_char = short_flag_char;
        }
    }

    /* TODO: while parsing, handle parsing errors by catching ParseExceptions and sending messages in the following format:
     * for arguments:
     *  The [first/second/...] argument, the [argument name], [e.getIssue()].\n[additional info]
     *  for options:
     *  The "[option name]" option [e.getIssue()].\n[additional info] */

    /* TODO LINK "the console", "diamond helmet", CommandArgParser (in several places), "registration" (under @param usage, link to `CommandArgParser.register(name)`),
     * "Many basic types [...]" whole sentence (in @param usage, link to documented list of pre-defined ComamndArgParsers wherever it may be found) */
    /** <i>WARNING:</i> This is kind of complicated, but very important and super awesome, so read carefully and hang onto your diamond helmets!<br>
     * <br>
     * This constructor creates a new {@link Command}, an essential part of just about any {@link CorundumPlugin plugin}.<br>
     * <br>
     * <i>Read the parameters' descriptions carefully! If you disobey any of the rules described below, Corundum will throw an error when your plugin is enabled and your
     * plugin will be immediately shut down!</i>
     *
     * @param usage
     *            is the basic usage of the {@link Command}. This can be given as a plain old <tt>String</tt>, but has a very specific format:
     *            <ul>
     *            <li>The first "word" (collection of non-whitespace characters) represents the first word of the command. This first word allows regular expressions (regex),
     *            but only the basic operators! Regex special groups (parenthetical groups beginning with a "?" inside) will be treated literally as parentheses with a
     *            question mark inside, but operators like <tt>"*"</tt>, <tt>"|"</tt>, <tt>"+"</tt>, and anything else not a special group is allowed.
     *            <li>After the first word, there are arguments. Arguments work like this:
     *            <ul>
     *            <li>Arguments begin and end with angle brackets (<tt>"<>"</tt>) if they should be required arguments or square brackets (<tt>"[]"</tt>) if they should be
     *            optional arguments.
     *            <li>The first word in the argument will be treated as the argument's type. Generally, this word should be in capital camelcase like a Java class's name. An
     *            argument type must be associated with a {@link Parser} created in that {@link CorundumPlugin}. Many basic types like <tt>String</tt> and
     *            <tt>OnlinePlayer</tt> have already been created for you. Note that registration of these works for a plugin, not for every plugin, to avoid name conflicts
     *            between plugins.
     *            <li>The second word in the argument should be the name of the argument. It must start with a letter. Generally, this word should be all lowercase with words
     *            separated by hyphens ( <tt>"-"</tt>). This word is what you will use to identify this argument when it is given to a {@link CorundumPlugin} to execute, so
     *            make sure that every argument and command option (see <tt><b>options</b></tt> below) has a different name!
     *            <li>After the second word, there should be a colon (<tt>":"</tt>) followed by a brief description of what that argument is for. This description will be used
     *            in the help pages. This description can (and probably should) be more than one word and can include any kinds of characters you want except the closing
     *            partner of what you surrounded the argument with (<tt>">"</tt> if you used angle brackets or <tt>"]"</tt> if you used square brackets).
     *            </ul>
     *            </ul>
     * @param help_description
     *            is the description of this command as a whole. This will be used as part of the /help command. It can be as long or as short as you want, but try to make it
     *            brief and descriptive.
     * @param options
     *            is a list of extra options for the command. Like the basic usage's arguments (see <b><tt>usage</b></tt> above), they should consist of a type, an identifier,
     *            a colon, and a brief help description. Generally, these options can be used in commands by writing two dashes (<tt>"--"</tt>) followed by the identifier, a
     *            space, and the value.
     * @EXAMPLES
     *           <ul>
     *           <li>This call creates a {@link Command} for teleporting to the last place you died. It's the simplest kind of command: it has no arguments or extra options.
     *
     *           <pre>
     * {@code new Command("death", "teleports you back to the last place you died")}
     * </pre>
     *
     *           Examples of the command in use:
     *
     *           <pre>
     * /death
     * </pre>
     *
     *           <li>This call creates a {@link Command} for teleporting one player to another. It requires two arguments: the OnlinePlayer to be teleported and the
     *           OnlinePlayer that the first one will be teleported to.
     *
     *           <pre>
     * {@code new Command("tp <OnlinePlayer teleportee: the player who will be teleported> <OnlinePlayer destination: the player teleportee will be teleported to>",
     *           "teleports the first online player to the other online player")}
     * </pre>
     *
     *           Examples of the command in use (where "REALDrummer" and "DracoRanger" are players currently online):
     *
     *           <pre>
     * /tp REALDrummer DracoRanger
     * </pre>
     *
     *           <li>This call creates a {@link Command} for creating a warp. Its first word uses regex to allow <tt>"create"</tt>, <tt>"make"</tt>, <tt>"set"</tt>,
     *           <tt>"createwarp"</tt>, <tt>"makewarp"</tt>, or <tt>"setwarp"</tt>. It requires a name for the warp (a <tt>String</tt>) and has one extra option: the type of
     *           the warp (a <tt>WarpType</tt>). This assumes that a {@link Parser} was already created and registered in this plugin with the name
     *           <tt>"WarpType"</tt>.<br>
     *
     *           <pre>
     * {@code new Command("(create|make|set)(warp)? <String warp-name: the name of the warp to be created>", "creates a new warp with the given name",
     *           "WarpType type: the type of warp that the new warp should be");}
     * </pre>
     *
     *           Examples of the command in use (assuming "open" and "private" are valid <tt>WarpType</tt>s):
     *
     *           <pre>
     * /create Warp
     * /make OpenWarp --type open
     * /createwarp --type private PrivateWarp
     * /setwarp OtherOpenWarp -t open
     * </pre>
     *
     *           </ul> */
    public Command(String usage, String help_description, String... options) {

    }
}
