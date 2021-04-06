public class BinaryCounter {
    int noOfBits1(String numbers) throws Exception {
        if (numbers.isEmpty())
            return 0;

        int result = 0;
        String[] splitNumbers = numbers.split("[;,' ']");
        for (String number : splitNumbers) {
            int numberAfterConversion = Integer.parseInt(number);

            if (numberAfterConversion < 0 || numberAfterConversion > 255)
                throw new Exception("Number out of range 0-255: " + numbers);
            String binary = Integer.toBinaryString(numberAfterConversion);

            for (Character character: binary.toCharArray()) {
                if (character == '1')
                    result++;
            }
        }

        return result;
    }
}
