package Corundum.utils.interfaces;

import static Corundum.utils.StringUtilities.readResponse;

import java.util.Arrays;

import Corundum.exceptions.MatchingException;

public class MatchUtilities {
    public static int match(Object object, Object... match_parameters) {
        if (match_parameters.length == 0)
            return match(object, null);
        // null can be matched to "\null"; otherwise, null is considered the lesser value
        else if (object == null)
            if (match_parameters[0] == null || match_parameters[0].equals("\\null"))
                return 0;  // since null is a wild card, \null can be used to actually search for null specifically
            else
                return -1;  // null is < everything else
        // if it's Matchable, simply call its matchTo() method
        else if (object instanceof Matchable) {
            Object[] sort_priorities = ((Matchable) object).getSortPriorities();
            for (int i = 0; i < sort_priorities.length; i++) {
                int comparison = match(sort_priorities[i], match_parameters[i]);

                if (comparison != 0)
                    return comparison;
            }

            return 0;
        }
        // if it's not Matchable and the match parameters are null, just return 0 since null is a "wild card"
        else if (match_parameters[0] == null)
            return 0;
        else if (object instanceof String)
            return matchToString((String) object, match_parameters[0]);
        else if (object instanceof Boolean)
            return matchToBoolean((Boolean) object, match_parameters[0]);
        else if (object instanceof Number)
            return matchToNumber((Number) object, match_parameters[0]);
        // if all else fails, try to match their toString() outputs
        else
            return object.toString().compareTo(Arrays.toString(match_parameters));
    }

    public static int match(Object[] objects, Object[] match_parameters) {
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

    public static int match(Object[] objects, Object[]... match_parameters) {
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

    public static int matchToBoolean(Boolean to_match, Object to_match_to) {
        Boolean matchable;
        if (to_match_to instanceof String) {
            matchable = readResponse((String) to_match_to);
            if (matchable == null)
                throw new MatchingException(to_match, to_match_to);
        } else if (to_match_to instanceof Number)
            matchable = ((Number) to_match_to).doubleValue() > 0;
        else if (to_match_to instanceof Boolean)
            matchable = (Boolean) to_match_to;
        else
            throw new MatchingException(to_match, to_match_to);

        return to_match && !matchable ? 1 : !to_match && matchable ? -1 : 0;
    }

    public static int matchToNumber(Number to_match, Object to_match_to) {
        // match numbers by their difference
        Double matchable;

        if (to_match_to instanceof Number)
            matchable = to_match.doubleValue();
        else if (to_match_to instanceof String)
            matchable = Double.parseDouble((String) to_match_to);
        else
            throw new MatchingException(to_match, to_match_to);

        return (int) (to_match.doubleValue() - matchable);
    }

    public static int matchToString(String to_match, Object to_match_to) {
        // match Strings by case-insensitive autocompletion matching (i.e. they match if to_match begins with to_match_to)
        String matchable = to_match_to instanceof String ? (String) to_match_to : to_match_to.toString();

        if (to_match.length() < matchable.length())
            return -1;
        else
            return to_match.substring(0, matchable.length()).compareToIgnoreCase(matchable);
    }

}
