import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {

    public int add(String numbers) throws NegativeNumbersNotAllowedException {

        List<Integer> integerList = Arrays.stream(numbers.split("[^0-9-]"))
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::valueOf)
                .boxed()
                .collect(Collectors.toList());

        String negativeNumbers = integerList.stream()
                .filter(value -> value < 0)
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        if (!negativeNumbers.isEmpty()) {
            throw new NegativeNumbersNotAllowedException("Negatives not allowed: " + negativeNumbers);
        }

        return integerList.stream().reduce(0, Integer::sum);
    }
}
