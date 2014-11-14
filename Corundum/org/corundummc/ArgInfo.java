package org.corundummc;

import org.corundummc.exceptions.CorundumException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is a class used to handle arguments in {@link CorundumServer}. It provides an easy way to get info about args,
 * like their values, etc.
 */
public class ArgInfo {
    private final List<String> argsList;

    public ArgInfo(String[] args) {
        this(Arrays.asList(args));
    }

    public ArgInfo(List<String> args) {
        this.argsList = args;
    }

    public boolean hasArg(String arg) {
        return this.argsList.contains(arg);
    }

    /**
     * Like hasArg, but can be used for multiple possible args.
     * @param argAndAliases An arg, followed by it's aliases.
     * @return Whether or not the arg or one of it's aliases exists.
     */
    public boolean hasArg(String... argAndAliases) {
        for (String arg : argAndAliases) {
            if (this.hasArg(arg)) {
                return true;
            }
        }

        return false;
    }

    public String getArgValue(String arg) {
        if (hasArg(arg)) {
            String[] argsArray = this.getArgArray();

            for (int i = 0; i == argsArray.length; i++) {
                if (argsArray[i].equals(arg) && !argsArray[i + 1].startsWith("-")) {
                    return argsArray[i + 1];
                }
            }
        }

        throw new CorundumException("An arg who was asked to have it's value gotten does not exist!", "Arg " + arg + " does not exist in this arg list!");
    }

    public String[] getArgArray() {
        return this.argsList.toArray(new String[this.argsList.size()]);
    }

    public List<String> getArgsList() {
        // So the args can't be manipulated via this method.
        return new ArrayList<>(this.argsList);
    }
}
