import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class Calculator {

    public int Add(String numbers) throws Exception{
        if (null == numbers || numbers.isEmpty()) {
            return 0;
        }

        String regex = "(,|\n";

        if (numbers.startsWith("//")) {
            String[] splitOnFirstNewLine = numbers.split("\n",2);
            regex += String.format("|\\Q%s\\E", splitOnFirstNewLine[0].substring(2));
            numbers = splitOnFirstNewLine[1];
        }

        regex += ")";

        String[] splitNumbers = numbers.split(regex);
        ArrayList<Integer> parsedNumbers = new ArrayList<>();
        Arrays.stream(splitNumbers).forEach(n -> parsedNumbers.add(parseInt(n)));

        if (parsedNumbers.isEmpty())
            return 0;

        ArrayList<Integer> negativeNumbers = new ArrayList<>();
        for (Integer num:parsedNumbers) {
            if (num < 0) {
                negativeNumbers.add(num);
            }
            else if (num > 1000) {
                parsedNumbers.remove(num);
            }
        }

        if (!negativeNumbers.isEmpty()){
            String exceptionString = "Negatives not allowed: ";
            ArrayList<String> stringNegatives = new ArrayList<>();
            negativeNumbers.forEach(n -> stringNegatives.add(Integer.toString(n)));
            exceptionString += String.join(",",stringNegatives);
            throw new Exception(exceptionString);
        }

        return parsedNumbers.stream().mapToInt(a -> a).sum();
    }
}
