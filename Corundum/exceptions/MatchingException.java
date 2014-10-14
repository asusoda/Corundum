package Corundum.exceptions;

public class MatchingException extends CorundumException {
    private static final long serialVersionUID = 2817382083691652835L;

    public MatchingException(Object to_match, Object to_match_to) {
        super("I couldn't find a way to match this " + to_match.getClass().getSimpleName() + " with this " + to_match_to.getClass().getSimpleName() + "!",
                "unmatchable components", to_match.getClass().getSimpleName() + "=" + to_match, to_match_to.getClass().getSimpleName() + "=" + to_match_to);
    }
}
