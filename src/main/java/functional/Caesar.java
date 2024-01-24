package functional;

import engine.CaesarEngine;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Caesar implements CaesarEngine {
    public String encrypt(String input, int key) {
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

    public String decrypt(String input, int key) {
        return encrypt(input, 26 - key);
    }
}
