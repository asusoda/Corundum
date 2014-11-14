package org.corundummc.utils.messaging;

/** This enum class represents the different chat colors available in the Minecraft chat. There are 16 in total. These {@link MessageColor}s can be added to messages being sent
 * to the ANSI-compatible console or a player and they will change all the following text in that message to the corresponding color.
 * 
 * @author REALDrummer */
public enum MessageColor {
    /** This {@link MessageColor} changes the color of the following text to black (0x000000). */
    BLACK('0', "30"),
    /** This {@link MessageColor} changes the color of the following text to dark red (0xAA0000). */
    DARK_RED('4', "31"),
    /** This {@link MessageColor} changes the color of the following text to dark green (0x00AA00). */
    DARK_GREEN('2', "32"),
    /** This {@link MessageColor} changes the color of the following text to gold (a.k.a. dark yellow or orange) (0xFFAA00). */
    GOLD('6', "33"),
    /** This {@link MessageColor} changes the color of the following text to dark blue (0x0000AA). */
    DARK_BLUE('1', "34"),
    /** This {@link MessageColor} changes the color of the following text to dark purple (0xAA00AA). */
    DARK_PURPLE('5', "35"),
    /** This {@link MessageColor} changes the color of the following text to dark aqua (a.k.a. dark cyan) (0x00AAAA). */
    DARK_AQUA('3', "36"),
    /** This {@link MessageColor} changes the color of the following text to (light) gray (0xAAAAAA). */
    GRAY('7', "37"),
    /** This {@link MessageColor} changes the color of the following text to dark gray (0x00AAAA). */
    DARK_GRAY('8', "1;30"),
    /** This {@link MessageColor} changes the color of the following text to (light) red (0xFF5555). */
    RED('c', "1;31"),
    /** This {@link MessageColor} changes the color of the following text to (light) green (0x55FF55). */
    GREEN('a', "1;32"),
    /** This {@link MessageColor} changes the color of the following text to yellow (0xFFFF55). */
    YELLOW('e', "1;33"),
    /** This {@link MessageColor} changes the color of the following text to (light) blue (0xFFFF55). */
    BLUE('9', "1;34"),
    /** This {@link MessageColor} changes the color of the following text to light purple (a.k.a. pink) (0xFFFF55). */
    LIGHT_PURPLE('d', "1;35"),
    /** This {@link MessageColor} changes the color of the following text to light aqua (a.k.a. light cyan) (0xFFFF55). */
    AQUA('b', "1;36"),
    /** This {@link MessageColor} changes the color of the following text to white (0xFFFF55). */
    WHITE('f', "1;37"),
    /** This {@link MessageColor} applies "magic" formatting to the following text. In Minecraft in-game chat, this causes the characters to flip to other characters very very
     * quickly, completely obfuscating whatever the text originally read; in the console, the text is made invisible. */
    MAGIC('k', "8"),
    /** This {@link MessageColor} applies bold formatting to the following text. In Minecraft in-game chat, this causes the characters to appear bold; in the console, the text
     * is changed to the lighter version of that color (e.g. purple becomes light purple). This is a result of the fact that light colors in the console are made by applying
     * bold formatting to dark colors and applying bold formatting a second time has no effect. I know it's annoying that you can't have regular light-colored letters or bold
     * dark-colored letters in the console; please direct all complaints to the makers of ANSI. */
    BOLD('l', "1"),
    /** This {@link MessageColor} applies strikethrough formatting to the following text (meaning it crosses it off). This works normally both in Minecraft in-game chat and in
     * an ANSI-compatible console setting. */
    STRIKETHROUGH('m', "9"),
    /** This {@link MessageColor} applies underline formatting to the following text. This works normally both in Minecraft in-game chat and in an ANSI-compatible console
     * setting. */
    UNDERLINE('n', "4"),
    /** This {@link MessageColor} applies italics formatting to the following text. This works normally both in Minecraft in-game chat and in an ANSI-compatible console
     * setting. */
    ITALICS('o', "3"),
    /** This {@link MessageColor} resets the color of the following text to whatever the default color of the text is. In a console, this color is a shade of gray; in Minecraft
     * chat, it is plain white. */
    RESET('r', "0");

    private final char MC_chat_char;
    private final String ANSI_CSI_code;

    private MessageColor(char MC_chat_char, String ANSI_CSI_code) {
        this.MC_chat_char = MC_chat_char;
        this.ANSI_CSI_code = ANSI_CSI_code;
    }

    /** This method runs through a given <tt>String</tt> (<b><tt>message</b></tt>) and replaces all Minecraft-formatted color codes (\u00A7 plus a character)
     * 
     * @param message
     *            is the message in which all Minecraft in-game chat color codes should be replaced with ANSI-compatible coloring and formatting escape sequences.
     * @return the original <b><tt>message</b></tt> with all Minecraft in-game chat formatted color codes replaced with ANSI-compatible color escape sequences. */
    public static String translateMCChatCodesToANSICodes(String message) {
        for (int i = 0; i < message.length() - 1 /* the -1 stops the message parsing 1 character before the end because we need two characters to make a Minecraft chat color
                                                  * code */; i++) {
            // search for a section symbol (\u00A7), which is what Minecraft uses to mark color codes in chat
            if (message.charAt(i) == '\u00A7') {
                // determine if this color code matches any know MessageColors
                MessageColor color = getByMCChatChar(message.charAt(i + 1));

                // if we found a match, replace the Minecraft chat code with an ANSI code
                if (color != null)
                    message = message.substring(0, i) + color.toANSICode() + (message.length() == i + 2 ? "" : message.substring(i + 2));
            }
        }

        return message;
    }

    /** This method takes in a char and locates the {@link MessageColor} with a corresponding Minecraft chat color code char that matches it, e.g. if this method is called with
     * <tt>'a'</tt>, it will return {@link MessageColor#GREEN GREEN} because in the Minecraft chat color codes, §a corresponds to the light green color.
     * 
     * @param MC_chat_char
     *            is the character which will be used to find the corresponding {@link MessageColor}.
     * @return the {@link MessageColor} that corresponds to the given <b><tt>MC_chat_char</b></tt>. */
    public static MessageColor getByMCChatChar(char MC_chat_char) {
        for (MessageColor color : values())
            if (Character.toLowerCase(color.MC_chat_char) == Character.toLowerCase(MC_chat_char))
                return color;

        return null;
    }

    /** This method determines whether or not this {@link MessageColor} represents a color like {@link MessageColor#RED RED} as opposed to a code that represents a formatting
     * system like {@link MessageColor#ITALICS ITALICS}.
     * 
     * @return <b>true</b> if this {@link MessageColor} represents a color; <b>false</b> otherwise. */
    public boolean isColor() {
        return MC_chat_char <= 'f' || MC_chat_char == 'r';
    }

    /** This method determines whether or not this {@link MessageColor} represents a formatting system like {@link MessageColor#ITALICS} as opposed to a code that represents a
     * color like {@link MessageColor#RED RED}.
     * 
     * @return <b>true</b> if this {@link MessageColor} represents a formatting system; <b>false</b> otherwise. */
    public boolean isFormatting() {
        return MC_chat_char > 'f';
    }

    /** This method gives the Minecraft in-game chat color code that corresponds to this {@link MessageColor}, e.g. "§a" for {@link MessageColor#GREEN GREEN}. This color code
     * can be put in the Minecraft in-game chat messages to change all text following it in that message (or up to the next color code) to the corresponding color.
     * 
     * @return the Minecraft in-game chat color code that corresponds to this {@link MessageColor}. */
    public String toMCChatCode() {
        return "\u00A7" + MC_chat_char;
    }

    /** This method gives the terminal/command prompt ANSI-compatible escape sequence that corresponds to this {@link MessageColor}, e.g. "\\u001B[1;32m" for
     * {@link MessageColor#GREEN GREEN}. This color code can be put into an ANSI-compatible console system such as a Windows command prompt or a Unix terminal to change all the
     * text following it in that message (or up to the next color code) to the corresponding color.
     * 
     * @return the ANSI-compatible escape sequence that corresponds to this {@link MessageColor}. */
    public String toANSICode() {
        return "\u001B[" + ANSI_CSI_code + "m";
    }

    /** This method returns the Minecraft color code text represented by this {@link MessageColor}, allowing users to put it into messages with a simple "+" operator to color
     * chat messages. */
    @Override
    public String toString() {
        // TODO: figure out how to make this system work not only with ANSI-compatible consoles, but also in Minecraft chat
        return toMCChatCode();
    }
}
