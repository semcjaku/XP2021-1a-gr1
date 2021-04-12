package xp;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringCalculator {
    private final String defaultDelimiters = "[,\n]";

    public int Add(String input) {
        String delimiters;
        String numbers;

        if (isCustomDelimiterSpecified(input)) {
            delimiters = getCustomDelimiter(input);
            numbers = omitDelimiterLine(input);
        }
        else {
            delimiters = defaultDelimiters;
            numbers = input;
        }

        int[] individualNumbers = parseListOfNumbers(numbers, delimiters);

        assureNumbersSatisfyPredicate(individualNumbers, n -> n > 0, "Negative numbers are not allowed");

        return Arrays.stream(individualNumbers)
                .reduce(0, Integer::sum);
    }

    public int noOfBits1(String numbers) {
        final String allowedDelimiters = ";[\\ \t\n]+";
        final String allowedNumbers = "(\\$[[0-9][a-f][A-F]]+)|[0-9]+";

        final String allowedExpressionFormat =  regexStar(regexAlternative(allowedDelimiters + allowedNumbers));

        if (!numbers.matches(allowedExpressionFormat)) {
            throw new IllegalArgumentException("Incorrect expression");
        }

        int[] numbersArr = parseListOfNumbers(numbers, regexAlternative(allowedDelimiters));

        assureNumbersSatisfyPredicate(numbersArr, n -> n >= 0 && n <= 255, "Numbers outside of range 0-255 not allowed");

        return Arrays.stream(numbersArr)
                .map(this::count1Bits)
                .reduce(0, Integer::sum);
    }

    private boolean isCustomDelimiterSpecified(String input) {
        return input.startsWith("//");
    }

    private String getCustomDelimiter(String input) {
        String[] parts = input.split("\n", 2);
        return parts[0].replace("//", "");
    }

    private String omitDelimiterLine(String input) {
        return input.split("\n", 2)[1];
    }

    private void assureNumbersSatisfyPredicate(int[] numbers, Function<Integer, Boolean> predicate, String errorMessage) {
        String negatives = Arrays.stream(numbers)
                .filter(n -> !predicate.apply(n))
                .mapToObj(i -> ((Integer) i).toString())
                .collect(Collectors.joining(", "));

        if (!negatives.isEmpty()) {
            String negativesRepr = String.join(", ", negatives);
            throw new IllegalArgumentException(errorMessage + ": " + negativesRepr);
        }
    }

    private int[] parseListOfNumbers(String input, String delimiters) {
        String[] individualNumbers =  input.split(delimiters);

        return Arrays.stream(individualNumbers)
                .filter(str -> !str.isEmpty())
                .map(String::trim)
                .map(this::parseInt)
                .mapToInt(x -> x)
                .toArray();
    }

    private int count1Bits(int number) {
        int ones = 0;

        while (number > 0) {
            ones += number % 2;
            number /= 2;
        }

        return ones;
    }

    private String regexAlternative(String expression) {
        return "[" + expression + "]";
    }

    private String regexStar(String expression) {
        return expression + "*";
    }

    private int parseInt(String number) {
        if (number.startsWith("$")) {
            return Integer.parseInt(number.substring(1), 16);
        }
        else {
            return  Integer.parseInt(number);
        }
    }
}
