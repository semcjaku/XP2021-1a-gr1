public class BinaryCounter {
    int noOfBits1(String number) throws Exception {
        if (number.isEmpty())
            return 0;
        int numberAfterConversion = Integer.parseInt(number);

        if (numberAfterConversion < 0 || numberAfterConversion > 255)
            throw new Exception("Number out of range 0-255: " + number);
        String binary = Integer.toBinaryString(numberAfterConversion);

        int result = 0;
        for (Character character: binary.toCharArray()) {
            if (character == '1')
                result++;
        }

        return result;
    }
}
