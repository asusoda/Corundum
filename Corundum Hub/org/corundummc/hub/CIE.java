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

            // skip stack trace lines until we get to some lines with line numbers associated with them not from the native Java code; those are the lines that will be helpful
            int lines_to_skip = 0;
            if (skip_first_line)
                lines_to_skip++;

            while (lines_to_skip < cause.getStackTrace().length
                    && (cause.getStackTrace()[lines_to_skip].getLineNumber() < 0 || cause.getStackTrace()[lines_to_skip].getClassName().startsWith("java") || cause
                            .getStackTrace()[lines_to_skip].getClassName().startsWith("sun")))
                lines_to_skip++;

            // create and format a message that gives only pertinent information from the stack trace
            while (cause != null) {
                description += aOrAn(issue) + "...\n";
                if (cause.getMessage() != null)
                    description += "...to which Java says \"" + cause.getMessage() + "\"...\n";
                if (lines_to_skip < cause.getStackTrace().length)
                    description += "...at line " + cause.getStackTrace()[lines_to_skip].getLineNumber() + " of " + cause.getStackTrace()[lines_to_skip].getClassName();
                if (lines_to_skip + 1 < cause.getStackTrace().length)
                    for (int i = lines_to_skip + 1; i < cause.getStackTrace().length; i++)
                        if (cause.getStackTrace()[i].getLineNumber() < 0 || !cause.getStackTrace()[i].getClassName().contains("REALDrummer"))
                            break;
                        else
                            description += "\n...and at line " + cause.getStackTrace()[i].getLineNumber() + " of " + cause.getStackTrace()[i].getClassName();
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
