package exceptions;

public class NumberExceedsBoundariesException extends Exception {
    public NumberExceedsBoundariesException (String errorMessage) {
        super(errorMessage);
    }
}
