package exceptions;

public class NegativesNotAllowedException extends Exception {
    public NegativesNotAllowedException (String errorMessage) {
        super(errorMessage);
    }
}
