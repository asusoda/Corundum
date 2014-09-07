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

package utils;

import java.util.ArrayList;
import java.util.HashMap;

import utils.interfaces.Matchable;
import main.CorundumException;
import main.Main;
import static utils.ListUtilities.*;

public class StringUtilities {
    public static final String[] BORDERS = { "[]", "\\/", "\"*", "_^", "-=", ":;", "&%", "#@", ",.", "<>", "~$", ")(", "+-", "|o" }, YESES = { "yes", "yea", "yep", "ja",
            "sure", "why not", "ok", "do it", "fine", "whatever", "w/e", "very well", "accept", "tpa", "cool", "hell yeah", "hells yeah", "hells yes", "come", "k ", "kk",
            "true", "on" }, NOS = { "no ", "na", "nope", "nein", "don't", "shut up", "ignore", "it's not", "its not", "creeper", "unsafe", "wait", "one ", "1 ", "false",
            "off" };

    public static String aOrAn(String message) {
        String check = message;
        check = check.toLowerCase();
        while (check.startsWith("{") || check.startsWith("[") || check.startsWith("/") || check.startsWith("\\") || check.startsWith("\n") || check.startsWith("\t")
                || check.startsWith("(") || check.startsWith("\"") || check.startsWith("'") || check.startsWith(" "))
            check = check.substring(1);
        if (check.startsWith("a") || check.startsWith("e") || check.startsWith("i") || check.startsWith("o") || check.startsWith("u") || check.startsWith("1")
                || check.startsWith("8"))
            return "an " + message;
        return "a " + message;

    }

    public static String border() {
        String border_unit = BORDERS[(int) (Math.random() * BORDERS.length)], border = "";
        for (int i = 0; i < 20; i++)
            border += border_unit;
        return border;
    }

    public static String capitalize(String to_capitalize) {
        if (to_capitalize == null || to_capitalize.equals(""))
            return to_capitalize;
        else if (to_capitalize.length() == 1)
            return to_capitalize.toUpperCase();
        else
            return to_capitalize.substring(0, 1).toUpperCase() + to_capitalize.substring(1);
    }

    public static String capitalizeFully(String to_capitalize) {
        if (to_capitalize == null || to_capitalize.equals(""))
            return to_capitalize;
        else if (to_capitalize.length() == 1)
            return to_capitalize.toUpperCase();
        else
            return to_capitalize.substring(0, 1).toUpperCase() + to_capitalize.substring(1).toLowerCase();
    }

    public static boolean isBorder(String test) {
        if (test.length() == 40) {
            for (String border : BORDERS)
                if (test.contains(border)) {
                    test = replaceAll(test, border, "");
                    break;
                }
            if (test.equals(""))
                return true;
        }
        return false;
    }

    public static boolean isNumber(String test) {
        try {
            Double.parseDouble(test);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int match(Object object, String... match_parameters) {
        if (match_parameters.length == 0)
            return match(object, null);
        // null can be matched to "\null"; otherwise, null is considered the lesser value
        else if (object == null)
            if (match_parameters[0] == null || match_parameters[0].equals("\\null"))
                return 0;  // since null is a wild card, \null can be used to actually search for null specifically
            else
                return -1;  // null is < everything else
        // if it's Matchable, simply call its matchTo() method
        else if (object instanceof Matchable)
            return ((Matchable) object).matchTo(match_parameters);
        // if it's not Matchable and the match parameters are null, just return 0 since null is a "wild card"
        else if (match_parameters[0] == null)
            return 0;
        // match Strings by case-insensitive autocompletion
        else if (object instanceof String)
            if (((String) object).length() < match_parameters[0].length())
                return -1;
            else
                return ((String) object).substring(0, match_parameters[0].length()).compareToIgnoreCase(match_parameters[0]);
        // match Booleans against "T" vs. "F"
        else if (object instanceof Boolean)
            return ((Boolean) object) ^ Character.toUpperCase(match_parameters[0].charAt(0)) == 'T' ? 1 : 0;
        // match integers by their difference
        else if (object instanceof Byte)
            try {
                return ((Byte) object) - Byte.parseByte(match_parameters[0]);
            } catch (NumberFormatException e) {
                return ((Byte) object);
            }
        else if (object instanceof Short)
            try {
                return ((Short) object) - Short.parseShort(match_parameters[0]);
            } catch (NumberFormatException e) {
                return ((Short) object);
            }
        else if (object instanceof Integer)
            try {
                return ((Integer) object) - Integer.parseInt(match_parameters[0]);
            } catch (NumberFormatException e) {
                return ((Integer) object);
            }
        else if (object instanceof Long)
            try {
                return (int) (((Long) object) - Long.parseLong(match_parameters[0]));
            } catch (NumberFormatException e) {
                return (int) ((Long) object).longValue();
            }
        // match floating point numbers by their truncated difference
        else if (object instanceof Float)
            try {
                return (int) (((Float) object) - Float.parseFloat(match_parameters[0]));
            } catch (NumberFormatException e) {
                return (int) ((Float) object).floatValue();
            }
        else if (object instanceof Double)
            try {
                return (int) (((Double) object) - Double.parseDouble(match_parameters[0]));
            } catch (NumberFormatException e) {
                return (int) ((Double) object).doubleValue();
            }
        // match Locations by their block-coordinate writeLocation() outputs
        // TODO TEMP CMT
        /* else if (object instanceof Location) return writeLocation((Location) object, false).compareTo( combine(match_parameters, " ")/* remove decimal places *
         * /.replaceAll(".\\d*,", ",").replaceAll(".\\d*\\)", ")")); */
        // if all else fails, try to match their toString() outputs
        else
            return object.toString().compareTo(combine(match_parameters, " "));
    }

    public static int match(Object[] objects, String[] match_parameters) {
        // NOTE: this method has to be separate from match(Object[], String[]...) because of differences between String[] and String[]...
        if (match_parameters.length == 0)
            return 0;

        // compare the objects given until one does not match
        for (int i = 0; i < objects.length && i < match_parameters.length; i++) {
            int comparison = match(objects[i], match_parameters[i]);
            if (comparison != 0)
                return comparison;
        }

        // if all of the objects matched, return 0
        return 0;
    }

    public static int match(Object[] objects, String[]... match_parameters) {
        // NOTE: this method has to be separate from match(Object[], String[]) because of differences between String[] and String[]...
        if (match_parameters.length == 0)
            return 0;

        // compare the objects given until one does not match
        for (int i = 0; i < objects.length && i < match_parameters.length; i++) {
            int comparison = match(objects[i], match_parameters[i]);
            if (comparison != 0)
                return comparison;
        }

        // if all of the objects matched, return 0
        return 0;
    }

    public static HashMap<String, String> readData(String format, String data) {
        return readData(format, data, true);
    }

    public static HashMap<String, String> readData(String format, String data, boolean display_errors) {
        HashMap<String, String> results = new HashMap<String, String>();
        try {
            while (format.contains("[") && format.contains("]")) {
                int key_begin = format.indexOf('['), key_end = format.indexOf('[') + format.substring(format.indexOf('[')).indexOf(']');
                String key = format.substring(key_begin + 1, key_end), value =
                        data.substring(key_begin, data.indexOf(key_end + 1, key_end + data.substring(key_end).indexOf('[') + 1));
                if (!key.contains("/~"))
                    results.put(key, value);
                else {
                    String true_value = substring(key, "\"", "\""), false_value = substring(key.substring(key.indexOf("/~")), "\"", "\"");
                    if (value.toLowerCase().startsWith(true_value.toLowerCase()))
                        results.put(key, "true");
                    else if (value.toLowerCase().startsWith(false_value.toLowerCase()))
                        results.put(key, "false");
                    else
                        throw new CorundumException("This value didn't match either possibility in this boolean key in this verbose data line!", "key=\"" + key + "\"",
                                "value=\"" + value + "\"", "format=\"" + format + "\"", "data=\"" + data + "\"", "data collected so far: " + results);
                }
            }
        } catch (Exception exception) {
            throw new CorundumException("This data line wasn't formatted correctly according to the given format!", exception, "format=\"" + format + "\"", "data=\"" + data
                    + "\"", "data collected so far: " + results);
        }
        return results;
    }

    public static HashMap<String, String> readParameters(String format, String[] parameters, byte... indices) {
        // TODO: replace with myFlags
        /* [?] = required parameter
         * 
         * (?) = optional parameter
         * 
         * [?...] or (?...) = parsing parameters can include spaces and can continue indefinitely
         * 
         * ["?"] or ("?") = literal parameters are the actual word or character in quotes ("?" here)
         * 
         * ("$"?) or (?"$") or ("$"?"$$") or with "[]"s = specific parameters must start and/or end with a given String or Strings to be accepted
         * 
         * [?],[?] or (?),(?) = list parameters can appear in any order; some may appear multiple times and more than one may appear
         * 
         * [?]/[?] or (?)/(?) = multi-option parameters can be ONE of these options */

        // if this command does not have parameters, ignore this method call and return an empty HashMap
        if (format.length() == 0)
            return new HashMap<String, String>();

        if (format.endsWith("\n"))
            format = format.substring(0, format.length() - 1);

        Main.debug("reading parameters; parameters=\"" + combine(parameters, " ") + "\"; format=\"" + format + "\"");

        // interpret the index values if any were given
        byte start = 0, end = (byte) parameters.length;
        if (indices != null && indices.length > 0) {
            start = indices[0];
            if (indices.length > 1)
                end = indices[1];
        }

        // HashMap<input name from format, value for that input from parameters>
        HashMap<String, String> data = new HashMap<String, String>();

        String[] format_parts = format.split(" ");
        // parsing is used to keep track of the input being parsed over multiple parameters if there is one, e.g. for a warp message
        String parsing = null;
        // i is for the parameters given themselves; j is for parsing through format_parts
        for (byte i = start, j = 0; i < end && (j < format_parts.length || parsing != null);) {
            // keep j on the last parameter if it has been over-incremented because of parameters for a parsing variable that were left untouched
            if (parsing != null && j >= format_parts.length)
                j = (byte) (format_parts.length - 1);

            /* this part generalizes the current format part, allowing us to basically use the same algorithm for a format part whether it is one possiblity or a series of
             * possibilities that may appear in any order */
            String[] options = new String[] { format_parts[j] };
            if (format_parts[j].contains(","))
                options = format_parts[j].split(",");
            else if (format_parts[j].contains("/"))
                options = format_parts[j].split("/");
            Main.debug(options.length + " option(s) found: " + writeArray(options));

            boolean fits = false;
            // first, see if the current parameter fits the current format part; skip to the next format part if the current format part is optional and doesn't fit
            for (String option : options) {
                Main.debug("testing option " + option + "...");

                // determine the starting and terminating Strings needed if it's a specific parameter; default to "" because .startsWith("") >> true
                String required_starter = "", required_terminator = "";
                if (option.contains("\"")) {
                    if (option.toCharArray()[1] == '"')
                        required_starter = option.substring(2, option.substring(2).indexOf('"') + 2);
                    if (option.toCharArray()[option.length() - 2] == '"')
                        required_terminator = option.substring(option.substring(0, option.lastIndexOf('"')).lastIndexOf('"') + 1, option.length() - 2);
                }
                Main.debug("required starter=\"" + required_starter + "\"; required terminator=\"" + required_terminator + "\"");

                /* determine whether or not the option is "specific", meaning it requires a starting and/or terminating String; note that it isn't specific if required_starter
                 * is the same as required_terminator and there are two or less double quotes because that means instead that it's a simple String parameter like ("debug") */
                boolean specific =
                        (required_starter.length() > 0 || required_terminator.length() > 0)
                                && (!required_starter.equals(required_terminator) || option.split("\"").length > 3);

                String option_name =
                        option.substring(1 + (specific && required_starter.length() > 0 ? 2 + required_starter.length() : 0), option.length() - 1
                                - (specific && required_terminator.length() > 0 ? 2 + required_terminator.length() : 0));
                Main.debug("option name=\"" + option_name + "\"");

                // see if the current parameter fits this option's requirements
                if (parameters[i].startsWith(required_starter) && parameters[i].endsWith(required_terminator)) {
                    Main.debug("match identified: \"" + parameters[i] + "\" instance of " + option);
                    data.put(option_name, parameters[i].substring(specific ? required_starter.length() : 0, parameters[i].length()
                            - (specific ? required_terminator.length() : 0)));
                    fits = true;

                    // begin parsing this variable if it is a parsing parameter
                    if (option_name.endsWith("...")) {
                        parsing = option_name;
                        Main.debug("parsing parameter; parsing " + option_name);
                    } // if it is not a parsing parameter, make any current parsing stop
                    else if (parsing != null) {
                        Main.debug("stopping parsing " + parsing + "...");
                        parsing = null;
                    }

                    // increment the format parts (if it's not a list parameter) and break
                    i++;
                    if (!format_parts[j].contains(","))
                        j++;
                    break;
                }
            }

            if (!fits)
                // if the current parameter did not fit the current format part, add the current parameter to the end of the current parsing variable if there is one
                if (parsing != null) {
                    data.put(parsing, data.get(parsing) + " " + parameters[i]);
                    Main.debug("added parameter to current parsing: " + parsing + "=\"" + data.get(parsing) + "\"");
                    i++;
                } else if (format_parts[j].startsWith("(") && !(j == format_parts.length - 1 && format_parts[j].contains(","))) {
                    Main.debug("no match, but parameter is optional; skipping to next format part...");
                    j++;
                } else {
                    Main.debug("WARNING: unrecognized parameter \"" + parameters[i] + "\"; ignoring...");
                    i++;
                }
        }

        for (String key : data.keySet())
            Main.debug("\"" + key + "\" >> \"" + data.get(key) + "\"");
        return data;
    }

    /** This method is used to interpret the answers to questions.
     * 
     * @param response
     *            is the raw String message that will be formatted in this message to be all lower case with no punctuation and analyzed for a "yes" or "no" answer.
     * @param current_status_line
     *            is for use with the <tt>config.txt</tt> questions only; it allows this method to default to the current status of a configuration if no answer is given to a
     *            <tt>config.txt</tt> question.
     * @param current_status_is_true_message
     *            is for use with the <tt>config.txt</tt> questions only; it allows this method to compare <b>current_status_line</b> to this message to determine whether or
     *            not the current status of the configuration handled by this config question is <b>true</b> or <b>false</b>.
     * @return <b>for chat responses:</b> <b>true</b> if the response matches one of the words or phrases in <tt>yeses</tt>, <b>false</b> if the response matches one of the
     *         words or phrases in <tt>nos</tt>, or <b>null</b> if the message did not seem to answer the question. <b>for <tt>config.txt</tt> question responses:</b>
     *         <b>true</b> if the answer to the question matches one of the words or phrases in <tt>yeses</tt>, <b>false</b> if the answer to the question matches one of the
     *         words or phrases in <tt>nos</tt>. If there is no answer to the question or the answer does not match a "yes" or a "no" response, it will return <b>true</b> if
     *         <b><tt>current_status_line</tt></b> matches <b> <tt>current_status_is_true_message</tt></b> or <b>false</b> if it does not. */
    public static Boolean readResponse(String response, String current_status_line, String current_status_is_true_message) {
        boolean said_yes = false, said_no = false;
        // elimiate unnecessary spaces and punctuation
        while (response.startsWith(" "))
            response = response.substring(1);
        while (response.endsWith(" "))
            response = response.substring(0, response.length() - 1);
        response = response.toLowerCase();
        // check their response
        for (String yes : YESES)
            if (response.startsWith(yes))
                said_yes = true;
        if (said_yes)
            return true;
        else {
            for (String no : NOS)
                if (response.startsWith(no))
                    said_no = true;
            if (said_no)
                return false;
            else if (current_status_line != null)
                if (current_status_line.trim().startsWith(current_status_is_true_message))
                    return true;
                else
                    return false;
            else
                return null;
        }
    }

    /** This method is used to interpret the answers to questions.
     * 
     * @param unformatted_response
     *            is the raw String message that will be formatted in this message to be all lower case with no punctuation and analyzed for a "yes" or "no" answer.
     * @return <b>true</b> if the response matches one of the words or phrases in <tt>yeses</tt>, <b>false</b> if the response matches one of the words or phrases in
     *         <tt>nos</tt>, or <b>null</b> if the message did not seem to answer the question. */
    public static Boolean readResponse(String unformatted_response) {
        return readResponse(unformatted_response, null, null);
    }

    public static int readRomanNumeral(String roman_numeral) {
        int value = 0;
        char[] chars = new char[] { 'M', 'D', 'C', 'L', 'X', 'V', 'I' };
        int[] values = new int[] { 1000, 500, 100, 50, 10, 5, 1 };
        while (roman_numeral.length() > 0) {
            char[] digits = roman_numeral.trim().toUpperCase().toCharArray();
            int digit_value = 0;
            for (int i = 0; i < chars.length; i++)
                if (digits[0] == chars[i])
                    digit_value = values[i];
            if (digit_value == 0)
                return 0;
            int zeroless = digit_value;
            while (zeroless >= 10)
                zeroless = zeroless / 10;
            if (digits[0] != chars[0] && zeroless == 1 && digits.length > 1) {
                // if the digit value starts with a 1 and it's not 'M', it could be being used to subtract from the subsequent digit (e.g. "IV"); however, this
                // can only be true if the subsequent digit has a greater value than the current one
                int next_digit_value = 0;
                for (int i = 0; i < chars.length; i++)
                    if (digits[1] == chars[i])
                        next_digit_value = values[i];
                if (next_digit_value == 0)
                    return 0;
                // so, if the current digit's value is less than the subsequent digit's value, the current digit's value must be subtracted, not added
                if (next_digit_value > digit_value)
                    value -= digit_value;
                else
                    value += digit_value;
            } else
                value += digit_value;
            roman_numeral = roman_numeral.substring(1).toLowerCase();
        }
        return value;
    }

    /** This method can translate a String of time terms and values to a single int time in milliseconds (ms). It can interpret a variety of formats from "2d 3s 4m" to
     * "2 days, 4 minutes, and 3 seconds" to "2.375 minutes + 5.369s & 3.29days". Punctuation is irrelevant. Spelling is irrelevant as long as the time terms begin with the
     * correct letter. Order of values is irrelevant. (Days can come before seconds, after seconds, or both.) Repetition of values is irrelevant; all terms are simply
     * converted to ms and summed. Integers and decimal numbers are equally readable. The highest time value it can read is days; it cannot read years or months (to avoid the
     * complications of months' different numbers of days and leap years).
     * 
     * @param written
     *            is the String to be translated into a time in milliseconds (ms).
     * @return the time given by the String <b><tt>written</b></tt> translated into milliseconds (ms) or -1 if the method was unable to read the given <tt>String</tt>. */
    public static long readTime(String written) {
        long time = 0;
        String[] temp = written.split(" ");
        ArrayList<String> words = new ArrayList<String>();
        for (String word : temp)
            if (!word.equalsIgnoreCase("and") && !word.equalsIgnoreCase("&"))
                words.add(word.toLowerCase().replaceAll(",", ""));
        while (words.size() > 0) {
            // for formats like "2 days 3 minutes 5.57 seconds" or "3 d 5 m 12 s"
            try {
                double amount = Double.parseDouble(words.get(0));
                if (words.get(0).contains("d") || words.get(0).contains("h") || words.get(0).contains("m") || words.get(0).contains("s"))
                    throw new NumberFormatException();
                int factor = 0;
                if (words.size() > 1) {
                    if (words.get(1).startsWith("d"))
                        factor = 86400000;
                    else if (words.get(1).startsWith("h"))
                        factor = 3600000;
                    else if (words.get(1).startsWith("m"))
                        factor = 60000;
                    else if (words.get(1).startsWith("s"))
                        factor = 1000;
                    if (factor > 0)
                        // since a double of, say, 1.0 is actually 0.99999..., (int)ing it will reduce exact numbers by one, so I added 0.1 to it to avoid that.
                        time = time + (int) (amount * factor + 0.1);
                    words.remove(0);
                    words.remove(0);
                } else
                    words.remove(0);
            } catch (NumberFormatException e) {
                // if there's no space between the time and units, e.g. "2h, 5m, 25s" or "4hours, 3min, 2.265secs"
                double amount = 0;
                int factor = 0;
                try {
                    if (words.get(0).contains("d") && (!words.get(0).contains("s") || words.get(0).indexOf("s") > words.get(0).indexOf("d"))) {
                        amount = Double.parseDouble(words.get(0).split("d")[0]);
                        factor = 86400000;
                    } else if (words.get(0).contains("h")) {
                        amount = Double.parseDouble(words.get(0).split("h")[0]);
                        factor = 3600000;
                    } else if (words.get(0).contains("m")) {
                        amount = Double.parseDouble(words.get(0).split("m")[0]);
                        factor = 60000;
                    } else if (words.get(0).contains("s")) {
                        amount = Double.parseDouble(words.get(0).split("s")[0]);
                        factor = 1000;
                    }
                    if (factor > 0)
                        // since a double of, say, 1.0 is actually 0.99999..., (int)ing it will reduce exact numbers by one, so I added 0.1 to it to avoid that.
                        time = time + (int) (amount * factor + 0.1);
                } catch (NumberFormatException e2) {
                    throw new CorundumException("I couldn't read this time!", e2, "written time=\"" + written + "\"");
                }
                words.remove(0);
            }
        }
        return time;
    }

    /** This method replaces every instance of each String given in the text given with another String. This method has a few advantages over Java's standard
     * <tt>String.replaceAll(String, String)</tt> method: <b>1)</b> this method can replace multiple Strings with other Strings using a single method while
     * <tt>String.replaceAll(String, String)</tt> only has the ability to replace one String with one other String and <b>2)</b> this method treats brackets ("[]"), hyphens
     * ("-"), braces ("{}"), and other symbols normally whereas many of these symbols have special meanings in <tt>String.replaceAll(String, String)</tt>.
     * 
     * @param text
     *            is the text that must be modified.
     * @param changes
     *            are the changes that must be made to <b><tt>text</b></tt>. Every even-numbered item in this list will be replaced by the next (odd-numbered) String given;
     *            for example, if the four parameters given for <b><tt>changes</b></tt> are <tt>replaceAll(...,"wierd", "weird", "[player]", player.getName())</tt>, this
     *            method will replace all instances of "wierd" with "weird" and all instances of "[player]" with <tt>player.getName()</tt> in <b><tt>text</b></tt>.
     * @return <b><tt>text</b></tt> will all modifications given in <b><tt>changes</b></tt> made. */
    public static String replaceAll(String text, String... changes) {
        if (changes.length == 0)
            return text;
        for (int j = 0; j < changes.length; j += 2) {
            if (!text.toLowerCase().contains(changes[j].toLowerCase()))
                return text;
            for (int i = 0; text.length() >= i + changes[j].length(); i++) {
                if (text.substring(i, i + changes[j].length()).equalsIgnoreCase(changes[j])) {
                    text = text.substring(0, i) + changes[j + 1] + text.substring(i + changes[j].length());
                    i += changes[j + 1].length() - 1;
                }
                if (!text.toLowerCase().contains(changes[j].toLowerCase()))
                    break;
            }
        }
        return text;
    }

    public static String substring(String string, String beginning, String end) {
        if (!string.contains(beginning) || !string.contains(end) || string.indexOf(beginning) >= string.indexOf(end))
            return null;
        else {
            String after_beginning = string.substring(string.indexOf(beginning) + beginning.length());
            return after_beginning.substring(0, after_beginning.indexOf(end));
        }
    }

    public static String writeRomanNumeral(int value) {
        String roman_numeral = "";
        String[] chars = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int[] values = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        for (int i = 0; i < chars.length; i++)
            while (value >= values[i]) {
                roman_numeral += chars[i];
                value -= values[i];
            }
        return roman_numeral;
    }

    /** This method is the inverse counterpart to the {@link #readTime(long, boolean) translateStringToTimeInms()} method. It can construct a String to describe an amount of
     * time in ms in an elegant format that is readable by the aforementioned counterpart method as well as human readers.
     * 
     * @param time
     *            is the time in milliseconds (ms) that is to be translated into a readable phrase.
     * @param round_seconds
     *            determines whether or not the number of seconds should be rounded to make the phrase more elegant and readable to humans. This parameter is normally false if
     *            this method is used to save data for the plugin because we want to be as specific as possible; however, for messages sent to players in game, dropping excess
     *            decimal places makes the phrase more friendly and readable.
     * @return a String describing <b><tt>time</b></tt> */
    public static String writeTime(long time, boolean round_seconds) {
        // get the values (e.g. "2 days" or "55.7 seconds")
        ArrayList<String> values = new ArrayList<String>();
        if (time > 86400000) {
            if ((int) (time / 86400000) > 1)
                values.add((int) (time / 86400000) + " days");
            else
                values.add("1 day");
            time = time % 86400000;
        }
        if (time > 3600000) {
            if ((int) (time / 3600000) > 1)
                values.add((int) (time / 3600000) + " hours");
            else
                values.add("1 hour");
            time = time % 3600000;
        }
        if (time > 60000) {
            if ((int) (time / 60000) > 1)
                values.add((int) (time / 60000) + " minutes");
            else
                values.add("1 minute");
            time = time % 60000;
        }
        // add a seconds value if there is still time remaining or if there are no other values
        if (time > 0 || values.size() == 0)
            // if you have partial seconds and !round_seconds, it's written as a double so it doesn't truncate the decimals
            if ((time / 1000.0) != (time / 1000) && !round_seconds)
                values.add((time / 1000.0) + " seconds");
            // if seconds are a whole number, just write it as a whole number (integer)
            else if (Math.round(time / 1000) > 1)
                values.add(Math.round(time / 1000) + " seconds");
            else
                values.add("1 second");
        // if there are two or more values, add an "and"
        if (values.size() >= 2)
            values.add(values.size() - 1, "and");
        // assemble the final String
        String written = "";
        for (int i = 0; i < values.size(); i++) {
            // add spaces as needed
            if (i > 0)
                written = written + " ";
            written = written + values.get(i);
            // add commas as needed
            if (values.size() >= 4 && i < values.size() - 1 && !values.get(i).equals("and"))
                written = written + ",";
        }
        if (!written.equals(""))
            return written;
        else
            return null;
    }

}