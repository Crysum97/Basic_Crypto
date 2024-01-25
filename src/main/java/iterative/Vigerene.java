package iterative;

import java.util.Arrays;

/**
 * iterative implementation of the vigerene encryption
 */
public class Vigerene {

    /**
     * encrypts a given text using vigerene encryption
     * @param input text to encrypt
     * @return encrypted text
     */
    public static String encrypt(String input) {
        // construct char table
        char[][] array = generate_table();

        // reversed input is used as key here
        String reversed = new StringBuilder(input).reverse().toString();
        // prepare result
        StringBuilder result = new StringBuilder();
        // iterate through string
        for (int index = 0; index < input.length(); index++) {
            // lookup resulting character
            result.append(array[input.charAt(index) - 65][reversed.charAt(index) - 65]);
        }
        return result.toString();
    }

    /**
     * decrypts a given text using vigerene encryption
     * @param input text to decrypt
     * @param reversedOriginal reversed original text to use as a key
     * @return decrypted text
     */
    public static String decrpyt(String input, String reversedOriginal) {
        // construct char table
        char[][] array = generate_table();

        // prepare result
        StringBuilder result = new StringBuilder();
        // iterate through string
        for (int index = 0; index < input.length(); index++) {
            // lookup resulting character
            for (int i = 0; i < 26; i++) {
                if (array[i][reversedOriginal.charAt(index) - 65] == input.charAt(index)) {
                    result.append((char) (i + 65));
                }
            }
        }
        return result.toString();
    }

    /**
     * Constructs the character table used for encryption and decryption
     * @return vigerene character table
     */
    private static char[][] generate_table() {
        char[][] array = new char[26][26];

        for (int x = 0; x < 26; x++) {
            for (int y = 0; y < 26; y++) {
                // determine which character is written to the table
                int ascii = ((x + y) % 26) + 65;
                array[x][y] = (char)ascii;
            }
        }

        // uncomment to print the char table
        /*
        for(char[] sub: array) {
            System.out.println(Arrays.toString(sub));
        }*/

        return array;
    }
}
