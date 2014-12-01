package org.corundummc.hub;

import static org.corundummc.utils.StringUtilities.aOrAn;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.corundummc.exceptions.CorundumException;
import org.corundummc.utils.messaging.MessageColor;

/** This {@link Throwable} class represents a "<u>C</u>orundum <u>I</u>nternal <u>E</u>xception", which is an exception that occurs due to an error inside Corundum itself, as
 * opposed to a {@link CorundumException}, which is for use with the A.P.I. to handle errors that occur inside {@link CorundumPlugin}s.
 * 
 * @author REALDrummer */
public class CIE extends RuntimeException {

    private static final long serialVersionUID = 7593335510270990574L;

    private final String message;
    private final String issue;
    private final Throwable cause;
    private final Object[] additional_information;
    private final boolean skip_first_line;

    public CIE(String message, String issue, Object... additional_information) {
        this.message = message;
        this.issue = issue;
        cause = new NullPointerException();
        this.additional_information = additional_information;

        /* here, we must skip the first line of the stack trace since that line will simply be the line on which the NullPointerException was created above */
        skip_first_line = true;
    }

    public CIE(String message, Throwable cause, Object... additional_information) {
        this.message = message;
        issue = cause.getClass().getSimpleName();
        this.cause = cause;
        this.additional_information = additional_information;

        skip_first_line = false;
    }

    /** This method causes Corundum to consider the exception uncaught. Upon calling, it will log the error in the Corundum <tt>error log.txt</tt>, then inform all available
     * ops and the console of what has occurred. This method is meant to be called only after sending the {@link CIE} through the plugins without being caught; after that, it
     * should be caught by Corundum itself and this method should be called. */
    void err() {
        try {
            // localize the cause and message so that they can be changed in the loop
            Throwable cause = this.cause;
            String message = this.message;

            // generate a full description of the issue
            String description = "There was ";

            // skip stack trace lines from the beginning until we get to some lines not from the native Java code; those are the lines that will be helpful
            int first_useful_line = 0;
            if (skip_first_line)
                first_useful_line++;
            while (first_useful_line < cause.getStackTrace().length
                    && (cause.getStackTrace()[first_useful_line].getLineNumber() < 0 || cause.getStackTrace()[first_useful_line].getClassName().startsWith("java") || cause
                            .getStackTrace()[first_useful_line].getClassName().startsWith("sun")))
                first_useful_line++;

            // skip lines at the end of the stack trace to filter out lines from the naitve Java code that will not help
            int last_useful_line = cause.getStackTrace().length - 1;
            while (last_useful_line >= first_useful_line
                    && (cause.getStackTrace()[last_useful_line].getLineNumber() < 0 || cause.getStackTrace()[last_useful_line].getClassName().startsWith("java") || cause
                            .getStackTrace()[last_useful_line].getClassName().startsWith("sun")))
                last_useful_line--;

            // create and format a message that gives only pertinent information from the stack trace
            while (cause != null) {
                description += aOrAn(issue) + "...\n";
                if (cause.getMessage() != null)
                    description += "...to which Java says \"" + cause.getMessage() + "\"...\n";

                if (first_useful_line < cause.getStackTrace().length)
                    description +=
                            "...at line " + cause.getStackTrace()[first_useful_line].getLineNumber() + " of " + cause.getStackTrace()[first_useful_line].getClassName() + "."
                                    + cause.getStackTrace()[first_useful_line].getMethodName();
                if (first_useful_line + 1 < cause.getStackTrace().length)
                    for (int i = first_useful_line + 1; i <= last_useful_line; i++)
                        description +=
                                "\n...and at line " + cause.getStackTrace()[i].getLineNumber() + " of " + cause.getStackTrace()[i].getClassName() + "."
                                        + cause.getStackTrace()[i].getMethodName();

                cause = cause.getCause();
                if (cause != null)
                    message += "\n...which was caused by:\n";
            }
            for (int i = 0; i < additional_information.length; i++)
                description += "\n..." + (i == 0 ? "that involved" : "and") + ":\n" + additional_information[i].toString();

            // log the error
            try {
                File error_log = new File("error log.txt");
                if (!error_log.exists())
                    error_log.createNewFile();

                BufferedWriter out = new BufferedWriter(new FileWriter(error_log, true));
                GregorianCalendar calendar = new GregorianCalendar();
                out.write(/* date */(calendar.get(2) + 1) + "/" + calendar.get(5) + "/" + calendar.get(1) + " "
                        + /* time */new SimpleDateFormat("HH:mm:ss").format(new Date()) + /* error info */"\n" + message + "\n" + description + "\n\n");
                out.close();
            } catch (IOException exception) {
                /* TODO TEMP RPLC tellOps */System.out.println(/* TODO TEMP RPLC ChatColor.DARK_RED + */MessageColor.RED + "I got "
                        + aOrAn(exception.getClass().getSimpleName()) + " trying to log this error!\n" + message + "\n" + description);
            }

            // display the error to ops (note: \u2639 is a Unicode frowny face)
            /* TODO TEMP RPLC tellOps */System.out.println(MessageColor.RED + "Corundum had an accident! \u2639\n" + message
                    + "\nPlease give the Corundum team your error log.txt!"/* TODO TEMP CMT , true */);
        } catch (Throwable error) {
            System.out.println("I got an error while trying to print another error!");
            error.printStackTrace();
        }
    }
}
