package utils;

import exceptions.InvalidFormatException;
import exceptions.NumberOutOfRangeException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BitsCounter {

    private static final int LOWER_BOUNDARY_DEC = 0;
    private static final int UPPER_BOUNDARY_DEC = 255;

    private static final String INVALID_SIGNS = "[^[0-9][a-f]-;\\s$]"; // anything except: numbers, minus, semicolon, whitespace or dollar
    private static final String SEPARATOR = ";|\\s"; // semicolon or whitespace

    public int noOfBits1(String numbers) throws Exception {

        if (numbers.isEmpty()) {
            return 0;
        }

        if (Pattern.compile(INVALID_SIGNS).matcher(numbers).find()) {
            throw new InvalidFormatException("Invalid characters provided");
        }

        List<Integer> integerDecList = Arrays.stream(numbers.split(SEPARATOR))
                .filter(s -> !s.isEmpty())
                .map(s -> {
                    if (s.startsWith("$")){
                        return Integer.parseInt(s.substring(1), 16);
                    } else {
                        return Integer.parseInt(s);
                    }
                })
                .collect(Collectors.toList());

        String outOfRangeNumbers = integerDecList.stream()
                .filter(number -> number < LOWER_BOUNDARY_DEC || number > UPPER_BOUNDARY_DEC)
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        if (!outOfRangeNumbers.isEmpty()) {
            throw new NumberOutOfRangeException("Numbers out of range: " + outOfRangeNumbers);
        }

        return integerDecList.stream()
                .map(Integer::toBinaryString)
                .map(s -> (int) s.chars()
                        .mapToObj(i -> (char) i)
                        .filter(ch -> ch.equals('1'))
                        .count())
                .reduce(0, Integer::sum);
    }


}
