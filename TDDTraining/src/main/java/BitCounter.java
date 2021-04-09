import exceptions.NumberExceedsBoundariesException;
import exceptions.InvalidFormatException;


public class BitCounter  {

    private static final int UPPER_BOUNDARY = 255;
    private static final int LOWER_BOUNDARY = 0;
    private static final String DELIMITER = ";|\\s+";
    private static final String VALID_SIGNS = "[0-9a-zA-Z$;\\s]";

    int noOfBits1(String numbers) throws NumberExceedsBoundariesException, InvalidFormatException {
        if (numbers.isEmpty()) {
            return 0;
        }

        int output = 0;
        int number;

        for (char sign: numbers.toCharArray()){
            if (!(Character.toString(sign).matches(VALID_SIGNS))) {
                throw new InvalidFormatException("The format of given string is invalid!");
            }
        }

        for (String numberString: numbers.split(DELIMITER)) {
            if (numberString.startsWith("$")) {
                number = Integer.parseInt(numberString.substring(1), 16);
            }
            else {
                number = Integer.parseInt(numberString);
            }

            if (!(number > LOWER_BOUNDARY && number < UPPER_BOUNDARY)) {
                throw new NumberExceedsBoundariesException("Number " + number + " exceeds one of 8 bit number boundary!");
            }

            String binaryNumber = Integer.toBinaryString(number);

            for (char bit : binaryNumber.toCharArray()) {
                if (bit == '1') {
                    output++;
                }
            }
        }

        return output;
    }
}
