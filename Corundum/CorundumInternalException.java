package Corundum;

import static Corundum.utils.StringUtilities.aOrAn;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class CorundumInternalException extends RuntimeException {
    private static final long serialVersionUID = 970447372251179781L;

    private final String message;
    private final String issue;
    private final Throwable cause;
    private final Object[] additional_information;

    public CorundumInternalException(String message, String issue, Object... additional_information) {
        this.message = message;
        this.issue = issue;
        cause = null;
        this.additional_information = additional_information;
    }

    public CorundumInternalException(String message, Throwable cause, Object... additional_information) {
        this.message = message;
        issue = cause.getClass().getSimpleName();
        this.cause = cause;
        this.additional_information = additional_information;
    }

    /** This method causes Corundum to consider the exception uncaught. Upon calling, it will log the error in the Corundum <tt>error log.txt</tt>, then inform all available
     * ops and the console of what has occurred. This method is meant to be called only after sending the {@link CorundumException} through the plugins without being caught;
     * after that, it should be caught by Corundum itself and this method should be called. */
    void err() {
        // localize the cause and message so that they can be changed in the loop
        Throwable cause = this.cause;
        String message = this.message;

        // generate a full description of the issue
        String description = "There was ";

        int lines_to_skip = 0;
        // skip stack trace lines until we get to some lines with line numbers associated with them not from the native Java code; those are the lines that will be helpful
        while (lines_to_skip < cause.getStackTrace().length
                && (cause.getStackTrace()[lines_to_skip].getLineNumber() < 0 || !cause.getStackTrace()[lines_to_skip].getClassName().contains("REALDrummer")))
            lines_to_skip++;

        // create and format a message that gives only pertinent information from the stack trace
        while (cause != null) {
            description += aOrAn(issue) + "...\n";
            if (cause.getMessage() != null)
                description += "...to which Java says \"" + cause.getMessage() + "\"...\n";
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
            out.write(/* date */(calendar.get(2) + 1) + "/" + calendar.get(5) + "/" + calendar.get(1) + " " + /* time */new SimpleDateFormat("HH:mm:ss").format(new Date())
                    + /* error info */"\n" + message + "\n" + description + "\n\n");
            out.close();
        } catch (IOException exception) {
            /* TODO TEMP RPLC tellOps */System.out.println(/* TODO TEMP RPLC ChatColor.DARK_RED + */MessageColor.RED + "I got " + aOrAn(exception.getClass().getSimpleName())
                    + " trying to log this error!\n" + message + "\n" + description);
        }

        // display the error to ops (note: \u2639 is a Unicode frowny face)
        /* TODO TEMP RPLC tellOps */System.out
                .println(MessageColor.RED + "myCraft had an accident! \u2639\n" + message + "\nPlease give REALDrummer your error log.txt!"/* TODO TEMP CMT , true */);
    }

}
