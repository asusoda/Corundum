package org.corundummc.utils.exceptions;

import org.corundummc.exceptions.CorundumException;

public class UnfinishedException extends CorundumException {
    private static final long serialVersionUID = 77618127119032906L;

    public UnfinishedException(String unfinished_feature) {
        super("My apologies, but the ability to " + unfinished_feature + " has not yet been implemented. Please look forward to it in a future version of Corundum, though!",
                "attempt to do " + unfinished_feature);
    }

}
