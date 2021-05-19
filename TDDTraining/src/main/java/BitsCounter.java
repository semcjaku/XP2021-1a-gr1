import exceptions.InvalidFormatException;
import exceptions.NumberOutOfRangeException;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BitsCounter {

    public int noOfBits1(String numbers) throws InvalidFormatException, NumberOutOfRangeException {
        if (null == numbers || numbers.isEmpty()) {
            return 0;
        }

        if (!numbers.matches("[a-f\\d\\s$;-]+")) {
            throw new InvalidFormatException("Invalid character.");
        }

        String[] splitNumbers = numbers.split("(;|\\s+)");
        ArrayList<Integer> parsedNumbers = new ArrayList<>();
        for(String num:splitNumbers) {
            if (num.startsWith("$")) {
                parsedNumbers.add(parseInt(num.substring(1),16));
            }
            else {
                parsedNumbers.add(parseInt(num));
            }
        }

        int count = 0;
        for (Integer n:parsedNumbers) {
            if(n > 255 || n < 0) {
                throw new NumberOutOfRangeException("Allowed range is 0-255.");
            }

            while (n > 0) {
                count += n & 1;
                n >>= 1;
            }
        }
        return count;
    }
}
