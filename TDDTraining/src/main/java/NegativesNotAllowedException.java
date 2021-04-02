public class NegativesNotAllowedException extends Exception{
    public NegativesNotAllowedException(String negatives) {
        super("Negatives not allowed: " + negatives);
    }
}
