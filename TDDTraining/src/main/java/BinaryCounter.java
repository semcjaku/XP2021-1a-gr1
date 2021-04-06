public class BinaryCounter {
    int noOfBits1(String number) {
        if (number.isEmpty())
            return 0;
        String binary = Integer.toBinaryString(Integer.parseInt(number));

        int result = 0;
        for (Character character: binary.toCharArray()) {
            if (character == '1')
                result++;
        }

        return result;
    }
}
