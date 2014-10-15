package Corundum.exceptions;

public class CorundumSecurityException extends CorundumException {
    private static final long serialVersionUID = 1618988412190198962L;

    public CorundumSecurityException(StackTraceElement security_risk, StackTraceElement protected_method) {
        super(security_risk.getClassName() + "" + security_risk.getMethodName() + "() tried to call " + protected_method.getClassName() + ""
                + protected_method.getMethodName() + " without permission!", "illegal attempt at calling a Corundum internal method");
    }

    public CorundumSecurityException(String message, StackTraceElement security_risk, StackTraceElement protected_method) {
        super(message, "illegal attempt at calling a Corundum internal method",
                "protected method=" + protected_method.getClassName() + "" + protected_method.getMethodName(), "method attempting access=" + security_risk.getClassName()
                        + "" + security_risk.getMethodName());
    }

}
