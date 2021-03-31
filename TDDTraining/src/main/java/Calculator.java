import java.util.ArrayList;
import java.util.List;

public class Calculator {
    public int Add(String numbers) {
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

        int result = 0;

        for (String number:splitNumbers) {
            result +=Integer.parseInt(number);
        }

        return result;
    }
}
