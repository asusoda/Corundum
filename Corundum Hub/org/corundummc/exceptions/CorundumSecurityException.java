package org.corundummc.exceptions;

import org.corundummc.exceptions.CorundumException;

public class CorundumSecurityException extends CorundumException {
    private static final long serialVersionUID = 1618988412190198962L;

    public CorundumSecurityException(String security_offender, String offense) {
        this(null, security_offender, offense);
    }

    public CorundumSecurityException(String message, String security_offender, String offense) {
        super(message != null ? message : (security_offender + " tried to " + offense + " illegally!"), "attempt to " + offense);
    }

}
