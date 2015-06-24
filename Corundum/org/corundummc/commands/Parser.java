package org.corundummc.commands;

import org.corundummc.exceptions.CorundumException;
import org.corundummc.utils.ListUtilities;
import org.corundummc.utils.StringUtilities;

public abstract class Parser {
    static final Parser[] DEFAULT_PARSERS = new Parser[] { new Parser("String") {

        @Override
        public Object parse(String argument) {
            // that was easy! :)
            return argument;
        }
    }, new Parser("Integer") {

        @Override
        public Object parse(String argument) throws ParseException {
            try {
                return Integer.parseInt(argument);
            } catch (NumberFormatException exception) {
                throw new ParseException("Integer", "2", "-59");
            }
        }
    }, new Parser("Double") {
        @Override
        public Object parse(String argument) throws ParseException {
            try {
                return Double.parseDouble(argument);
            } catch (NumberFormatException exception) {
                throw new ParseException("Double", "25", "0.593");
            }
        }
    } /* TODO: add OnlinePlayer, Player, TimeOfDay, TimeInterval, Plugin */};

    protected final String name;

    Parser(String name) {
        this.name = name;
    }

    public abstract Object parse(String argument) throws ParseException;

    public static class ParseException extends CorundumException {
        private static final long serialVersionUID = 5547185029348537835L;

        private static String formatParserName(String parser_name) {
            // replace all uppercase letters with a space and their lowercase
            for (int i = 0; i < parser_name.length(); i++) {
                if (parser_name.charAt(i) > 'A' && parser_name.charAt(i) < 'Z')
                    parser_name =
                            parser_name.substring(0, i) + ' ' + Character.toLowerCase(parser_name.charAt(i))
                                    + (parser_name.length() > i + 1 ? parser_name.substring(i + 1) : "");
                // since we are adding to the length of parser_name here, there is no need to subtract 1 from i
            }

            return StringUtilities.aOrAn(parser_name.trim());
        }

        public ParseException(String parser_name, String correct_example1, String correct_example2, String... additional_info) {
            super("should be " + formatParserName(parser_name)
                    + (correct_example1 != null ? "like \"" + correct_example1 + "\"" + (correct_example2 != null ? " or \"" + correct_example2 + "\"" : "") : ""),
                    "parsing error", additional_info);
        }
    }

    public static abstract class OptionArgParser extends Parser {
        public OptionArgParser(String name) {
            super(name);
        }

        @Override
        public Object parse(String argument) throws ParseException {
            /* search through the given options for the shortest possible match; it's considered a match if the option's `toString()` output BEGINS WITH the given argument
             * (case-insensitively) */
            Object match = null;
            String match_string = null;
            for (Object option : getOptions()) {
                String option_string = option.toString();
                String argument_match_substring = argument.substring(0, argument.length() > option_string.length() ? option_string.length() : argument.length());
                if (option_string.equalsIgnoreCase(argument_match_substring)
                        && (match_string == null || match_string.length() > option_string.length() || option_string.length() == match_string.length()
                                && option_string.equals(argument_match_substring) /* though the matching is case-insensitive, give preference to case-sensitive matches when
                                                                                   * other criteria rate them as equally good matches */)) {
                    match = option;
                    match_string = option_string;
                }
            }

            if (match == null)
                throw new ParseException(name, getOptions().length > 1 ? getOptions()[0].toString() : null, getOptions().length > 2 ? getOptions()[1].toString() : null,
                        "Options:\n" + ListUtilities.writeArray(getOptions(), ", ", "or"));
            else
                return match;
        }

        public abstract Object[] getOptions();
    }
}
