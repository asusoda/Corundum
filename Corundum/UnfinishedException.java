package Corundum;

public class UnfinishedException extends CorundumInternalException {
    private static final long serialVersionUID = -2600820745236875269L;

    public UnfinishedException(String unfinished_method_name, Object... additional_information) {
        super("The " + unfinished_method_name + " method has not been made yet!", "call to the unfinished " + unfinished_method_name + " method", additional_information);
    }

}
