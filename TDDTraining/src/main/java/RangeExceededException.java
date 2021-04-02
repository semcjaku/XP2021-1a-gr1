public class RangeExceededException extends Exception {
    public RangeExceededException() {
        super("Range 0-255 exceeded");
    }
}
