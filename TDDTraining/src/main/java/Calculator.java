import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class Calculator {

    public int Add(String numbers) {
        if (null == numbers || numbers.isEmpty()) {
            return 0;
        }

        String[] splitNumbers = numbers.split(",");
        ArrayList<Integer> parsedNumbers = new ArrayList<>();
        Arrays.stream(splitNumbers).forEach(n -> parsedNumbers.add(parseInt(n)));

        if (parsedNumbers.isEmpty())
            return 0;

        int sum = parsedNumbers.stream().mapToInt(a -> a).sum();
        return sum;
    }
}
