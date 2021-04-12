package xp;

import java.util.Arrays;

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

        String[] individualNumbers = numbers.split(delimiters);

        assureNoNegatives(individualNumbers);

        return Arrays.stream(individualNumbers)
                .filter(str -> !str.isEmpty())
                .map(String::trim)
                .map(Integer::parseInt)
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

    private void assureNoNegatives(String[] individualNumbers) {
        String[] negatives = Arrays.stream(individualNumbers)
                .filter(s -> s.trim().startsWith("-"))
                .toArray(String[]::new);

        if (negatives.length > 0) {
            String negativesRepr = String.join(", ", negatives);
            throw new IllegalArgumentException("Negative numbers given: " + negativesRepr);
        }
    }
}
