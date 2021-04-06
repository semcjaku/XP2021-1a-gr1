import java.util.ArrayList;
import java.util.List;

public class BinaryCounter {
    int noOfBits1(String numbers) throws Exception {
        if (numbers.isEmpty())
            return 0;

        String delimiters = "[;' '\n]";
        String[] splitNumbers = numbers.split(delimiters);
        int result = 0;
        String checkNumbers = numbers
                .replaceAll("[0-9-$a-fA-F]","")
                .replaceAll(delimiters,"");

        if(checkNumbers.length() != 0)
            throw new Exception("Incorrect delimiter in : " + numbers);

        for (String number : splitNumbers) {

            if (number.isEmpty())
                continue;

            int numberAfterConversion = 0;
            if (number.startsWith("$")) {
                number = number.replaceAll("[$]", "0x");
                numberAfterConversion = Integer.decode(number);
            } else {
                numberAfterConversion = Integer.parseInt(number);
            }

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
