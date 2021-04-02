import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BitsCounter {
    public int noOfBits1(String numbers) throws RangeExceededException, FormatException {

        if (numbers == null || numbers.trim().isEmpty()) {
            numbers = "0"; // return 0;
        }

        numbers = numbers.replaceAll("\\s+", ";");

        if (!numbers.matches("(?:-?[1-9]\\d*|\\$[0-9a-fA-F]+|0)(?:;-?[1-9]\\d*|;\\$[0-9a-fA-F]+|;0)*")) { //"[-0-9;]+"
            throw new FormatException();
        }

        numbers = numbers.replaceAll("\\$", "0x");

        List<Integer> decimalNumbers = Arrays.stream(numbers.split(";"))
                .filter(number -> !number.startsWith("0x"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> decimalFromHexNumbers = Arrays.stream(numbers.split(";"))
                .filter(number -> number.startsWith("0x"))
                .map(number -> number.replaceAll("0x",""))
                .map(number -> Integer.parseInt(number,16))
                .collect(Collectors.toList());

        decimalNumbers.addAll(decimalFromHexNumbers);

        if (decimalNumbers.stream().filter(number -> number > 255 || number < 0).findAny().isPresent()) {
            throw new RangeExceededException();
        }

        return  decimalNumbers.stream()
                    .map(Integer::toBinaryString)
                    .map(binaryString -> binaryString
                                            .chars()
                                            .filter(ch -> ch == '1')
                                            .count())
                    .mapToInt(Long::intValue)
                    .reduce(0, Integer::sum);
    }
}
