package functional;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Caesar {
    public static String encrypt(String input, int key) {
        int validKey = key % 26;

        return IntStream.range(0, input.length())
                // add key
                .mapToObj(index -> (char) (input.charAt(index) + validKey))
                // translate back into bounds if necessary
                .map(sign -> (char) (((sign - 65) % 26) + 65))
                // map to String
                .map(sign -> Character.toString(sign))
                // build result
                .collect(Collectors.joining());
    }

    public static String decrypt(String input, int key) {
        return Caesar.encrypt(input, 26 - key);
    }
}
