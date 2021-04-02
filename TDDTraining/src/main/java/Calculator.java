import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {
    public int Add(String numbers) throws NegativesNotAllowedException {

        String delimiters = ",|\n";

        if (numbers.startsWith("//")) {
            String[] customDelimiterAndNumbers = numbers.split("\n",2);
            String customDelimiter = customDelimiterAndNumbers[0].replace("//", "");
            delimiters += "|" + customDelimiter;
            numbers = customDelimiterAndNumbers[1];
        }

        if (numbers == null || numbers.trim().isEmpty()) {
            return 0;
        }

        List<Integer> ints = Arrays.stream(numbers.split(delimiters))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String negatives = ints
                .stream()
                .filter(i -> i < 0)
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        if (!negatives.isEmpty()) {
            throw new NegativesNotAllowedException(negatives);
        }

        return  ints.stream().reduce(0, Integer::sum);
    }
}
