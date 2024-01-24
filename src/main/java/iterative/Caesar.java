package iterative;

import engine.CaesarEngine;

public class Caesar implements CaesarEngine {
    public String encrypt(String input, int key) {
        StringBuilder result = new StringBuilder();

        for (int index = 0; index < input.length(); index++) {
            // add key
            int ascii = input.charAt(index) + key;

            // check upper bounds
            while (ascii > 90) {
                ascii -= 26;
            }

            // check lower bounds
            while (ascii < 65) {
                ascii += 26;
            }

            // add letter to result
            result.append((char) ascii);
        }

        return result.toString();
    }

    public String decrypt(String input, int key) {
        return encrypt(input, 26 - key);
    }
}
