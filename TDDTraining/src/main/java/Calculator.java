import java.util.ArrayList;
import java.util.List;

public class Calculator {
    public int Add(String numbers) throws Exception {
        List<String> del = new ArrayList<>();
        del.add(",");
        del.add("\n");

        if (numbers.startsWith("//")) {
            String[] splitFirstLines = numbers.split("\n", 2);
            String customDelimiter = splitFirstLines[0].replace("//", "");
            del.add(customDelimiter);
            numbers = splitFirstLines[1];
        }

        String delimiters = "[" + String.join(",", del) + "]";

        String[] splitNumbers = numbers.split(delimiters);

        if (splitNumbers.length == 0) {
            return 0;
        }

        if (splitNumbers.length == 1) {
            if (splitNumbers[0].length() == 0) {
                return 0;
            }
        }

        List<String> negativeNumbers = new ArrayList<>();
        int result = 0;

        for (String stringNumber:splitNumbers) {
            int number = Integer.parseInt(stringNumber);
            result += number;
            if (number < 0) {
                negativeNumbers.add(stringNumber);
            }
        }

        if (negativeNumbers.size() > 0) {
            throw new Exception("Negatives not allowed: " + String.join(",", negativeNumbers));
        }

        return result;
    }
}
