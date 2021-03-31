public class Calculator {
    public int Add(String numbers) {
        String[] splitNumbers = numbers.split(",");

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
