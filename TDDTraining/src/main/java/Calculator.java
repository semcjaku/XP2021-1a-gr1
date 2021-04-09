import java.util.ArrayList;
import exceptions.NegativesNotAllowedException;


public class Calculator {
    public int add(String numbers) throws NegativesNotAllowedException {

        String delimiter = ",";

        if (numbers.startsWith("//")) {
            String[] splittedOnFirstNewLine = numbers.split("\n", 2);
            delimiter += "|" + splittedOnFirstNewLine[0].replace("//", "");
            numbers = splittedOnFirstNewLine[1];
        }

        if (numbers.isEmpty()){
            return 0;
        }

        ArrayList<Integer> negativeNumbers = new ArrayList<Integer>();

        String[] numbersList = numbers.
                replaceAll("\\n", ",").
                replaceAll("\\s+", "").
                split(delimiter);

        for(String s : numbersList) {
            if (Integer.parseInt(s) < 0){
                negativeNumbers.add(Integer.parseInt(s));
            }
        }

        if (!negativeNumbers.isEmpty()){
            throw new NegativesNotAllowedException("Negatives not allowed: " + negativeNumbers.toString().
                    replaceAll("[\\[\\]]", ""));
        }

        int output = 0;
        for(String s : numbersList) {
            output = output + Integer.parseInt(s);
        }

        return output;
    }
}